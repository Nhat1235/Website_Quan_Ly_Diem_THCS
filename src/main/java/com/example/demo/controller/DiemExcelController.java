package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.helper.MyUploadForm;
import com.example.demo.model.DauDiem;
import com.example.demo.model.Diem;
import com.example.demo.model.Lop_hs;
import com.example.demo.model.TaiKhoanGv;
import com.example.demo.repositories.DauDiemRepository;
import com.example.demo.repositories.DiemRepository;
import com.example.demo.repositories.HocSinh_LopRepository;
import com.example.demo.repositories.TaiKhoanGvRepository;

@Controller
public class DiemExcelController {
	private static final String SAMPLE_XLSX_FILE_PATH = "./employee1.xlsx";
	private static String[] odd_Array = { "Họ và tên" };
	private static String[] odd_Array_Diem = {};
	private static String[] header_Array = {};
	private static String[] colName_Array = {};

	@Autowired
	TaiKhoanGvRepository TKGvRepo;

	@Autowired
	DauDiemRepository rep;

	@Autowired
	HocSinh_LopRepository hslrepo;

	@Autowired
	DiemRepository Drepo;

	// POST: Sử lý Upload
	@RequestMapping(value = "/GV/uploadOneFile/{idglm}", method = RequestMethod.POST)
	public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model,
			@ModelAttribute("myUploadForm") MyUploadForm myUploadForm, Authentication authentication,
			@PathVariable("idglm") Integer idglm) {
		TaiKhoanGv tkgv = TKGvRepo.findAllDetail(authentication.getName());
		model.addAttribute("tkgv", tkgv);
		return this.doUpload(request, model, myUploadForm, idglm);

	}

	@SuppressWarnings("deprecation")
	private String doUpload(HttpServletRequest request, Model model, //
			MyUploadForm myUploadForm, Integer idglm) {

		String description = myUploadForm.getDescription();
		System.out.println("Description: " + description);

		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath("upload");
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);
		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}
		MultipartFile[] fileDatas = myUploadForm.getFileDatas();
		//
		List<File> uploadedFiles = new ArrayList<File>();
		List<String> failedFiles = new ArrayList<String>();

		for (MultipartFile fileData : fileDatas) {

			// Tên file gốc tại Client.
			String name = fileData.getOriginalFilename();
			System.out.println("Client File Name = " + name);

			if (name != null && name.length() > 0) {
				try {
					// Tạo file tại Server.
					File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);

					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(fileData.getBytes());
					stream.close();
					//
					uploadedFiles.add(serverFile);
					System.out.println("Write file: " + serverFile);

					// Creating a Workbook from an Excel file (.xls or .xlsx)
					Workbook workbook = WorkbookFactory.create(serverFile);

					// Retrieving the number of sheets in the Workbook
					System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

					/*
					 * ============================================================= Iterating over
					 * all the sheets in the workbook (Multiple ways)
					 * =============================================================
					 */

					// 1. You can obtain a sheetIterator and iterate over it
					Iterator<Sheet> sheetIterator = workbook.sheetIterator();
					System.out.println("Retrieving Sheets using Iterator");
					while (sheetIterator.hasNext()) {
						Sheet sheet = sheetIterator.next();
						System.out.println("=> " + sheet.getSheetName());
					}

					/*
					 * ================================================================== Iterating
					 * over all the rows and columns in a Sheet (Multiple ways)
					 * ==================================================================
					 */

					// Getting the Sheet at index zero
					Sheet sheet = workbook.getSheetAt(0);

					// Getting the evaluator
					FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

					// Create a DataFormatter to format and get each cell's value as String
					DataFormatter dataFormatter = new DataFormatter();

					// 1. You can obtain a rowIterator and columnIterator and iterate over them
					System.out.println("--DUYỆT ĐẦU ĐIỂM--");

					Iterator<Row> rowIterator = sheet.rowIterator();

					while (rowIterator.hasNext()) {
						Row row = rowIterator.next();

						// Now let's iterate over the columns of the current row
						Iterator<Cell> cellIterator = row.cellIterator();

						while (cellIterator.hasNext()) {

							Cell cell1 = row.getCell(0);
							Cell cell = cellIterator.next();

							// lấy dòng đầu
							if (!cell.equals(cell1) && row.getRowNum() == 0) {
								String idDauDiem = null;
								idDauDiem = cell.toString();
								String[] splitString = idDauDiem.split("-");
								System.out.println("+------------------------------+");
								System.out.println(cell);
								System.out.println(splitString[1].trim());
								List<String> oddlist = new ArrayList<String>(Arrays.asList(header_Array));

								// Add the new element
								oddlist.add(splitString[1].trim());

								// Convert the Arraylist back to array
								header_Array = oddlist.toArray(header_Array);

							}
							// check null:
							if (cell.getCellTypeEnum() == CellType.BLANK) {
								System.out.println("Blank cell!");
								model.addAttribute("message", "Không được để trống điểm!");
							} else {

								System.out.println("Dạng " + cell.getCellTypeEnum());

								String y = "";

								// lấy cột họ tên
								if (cell.getColumnIndex() == 0 && cell.getRowIndex() > 0) {

									String[] splitString2 = cell.toString().split("-");

									List<String> oddlist = new ArrayList<String>(Arrays.asList(colName_Array));
									// Add the new element
									oddlist.add(splitString2[1].trim());

									// Convert the Arraylist back to array
									colName_Array = oddlist.toArray(colName_Array);

									System.out.println(colName_Array.length);

									for (int j = 0; j < colName_Array.length; j++) {
										y = (String) Array.get(colName_Array, j);
										System.out.println("idhs: " + y);
									}

									System.out.println(
											"------------------------------KẾT THÚC THÊM ĐẦU ĐIỂM VÀO ARRAY--------------------------");
								} // e

								String z = "";

								List<Diem> list1 = new ArrayList<>();
								if (cell.getColumnIndex() > 0 && cell.getRowIndex() > 0) {

									for (int j = 0; j < colName_Array.length; j++) {
										z = (String) Array.get(colName_Array, j);
									}
									String idlophs = z.trim();
									String x = (String) Array.get(header_Array, cell.getColumnIndex() - 1);

									list1 = Drepo.HelpMe(idlophs, x);
									System.out.println("Số lg data có với đầu điểm " + x + ": " + list1.size()
											+ " idHocSinh: " + z);
								}

								// begin
								if (list1.size() > 0) {
									String x = (String) Array.get(header_Array, cell.getColumnIndex() - 1);
									System.out.println("-------------Delete data đầu điểm " + x + "-----------");

									Drepo.deleteAllByLopHS(z.trim(), x.trim());

									if (cell.getColumnIndex() > 0 && cell.getRowIndex() > 0) {

										List<Diem> list = Drepo.HelpMe(z.trim(), x);
										System.out.println(
												"@@@@@@@@@@ Số lg data có với " + x + ": " + list.size() + " @@@@@@@@");

										System.out.println("----Update khi co data-----");
										System.out.println("Điểm: " + cell);

										String y1 = "";

										System.out.println("Đầu điểm: " + x);

										for (int j = 0; j < colName_Array.length; j++) {
											y1 = (String) Array.get(colName_Array, j);
											Double diemCell = (Double) cell.getNumericCellValue();

											System.out.println("Mã HS_Lop: " + y1 + "/ Điểm: " + diemCell + "/ IDLopHS:"
													+ y1 + "/ IDDauDiem:" + x);
											Drepo.insertDiemIntoExcel(diemCell, y1.trim(), x.trim(), idglm);

										}

									}
								} else {
//							if(list1.size()==0){
									System.out.println(
											"--------------------------------BẮT ĐẦU THÊM MỚI----------------------------------------");

									if (cell.getColumnIndex() > 0 && cell.getRowIndex() > 0) {

										String x = (String) Array.get(header_Array, cell.getColumnIndex() - 1);
										List<Diem> list = Drepo.HelpMe(z.trim(), x);
										System.out.println("@-@-@-@-@- Số lg data có với " + x + ": " + list.size()
												+ " -@-@-@-@-@");

										System.out.println("----Insert khi 0 co data-----");
										System.out.println("Điểm: " + cell);

										String y1 = "";

										System.out.println("Đầu điểm: " + x);

										for (int j = 0; j < colName_Array.length; j++) {
											y1 = (String) Array.get(colName_Array, j);
											Double diemCell = (Double) cell.getNumericCellValue();
											System.out.println("Mã HS_Lop: " + y1 + "/ Điểm: " + diemCell + "/ IDLopHS:"
													+ y1 + "/ IDDauDiem:" + x);
											Drepo.insertDiemIntoExcel(diemCell, y1.trim(), x.trim(), idglm);

										}

									}
								}
							}
						}
						colName_Array = new String[] {};
					}

					System.out.println(
							"------------------------------KẾT THÚC CLASS NÀY-------------------------------------");
					colName_Array = new String[] {};
					workbook.close();

				} catch (Exception e) {
					System.out.println("Error Write file: " + name);
					failedFiles.add(name);
				}
			}

		}
		model.addAttribute("description", description);
		model.addAttribute("uploadedFiles", uploadedFiles);
		model.addAttribute("failedFiles", failedFiles);

		return "uploadResult";
	}

	@RequestMapping(value = "/GV/InputExcel", method = RequestMethod.POST)
	public void exportToExcel32(HttpServletResponse response)
			throws IOException, EncryptedDocumentException, InvalidFormatException {
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		/*
		 * ============================================================= Iterating over
		 * all the sheets in the workbook (Multiple ways)
		 * =============================================================
		 */

		// 1. You can obtain a sheetIterator and iterate over it
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		System.out.println("Retrieving Sheets using Iterator");
		while (sheetIterator.hasNext()) {
			Sheet sheet = sheetIterator.next();
			System.out.println("=> " + sheet.getSheetName());
		}

		/*
		 * ================================================================== Iterating
		 * over all the rows and columns in a Sheet (Multiple ways)
		 * ==================================================================
		 */

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Getting the evaluator
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// 1. You can obtain a rowIterator and columnIterator and iterate over them
		System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
		Iterator<Row> rowIterator = sheet.rowIterator();

		rowIterator.next();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// Now let's iterate over the columns of the current row
			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if (cell.getColumnIndex() > 2) {

					System.out.print(getCellValue(cell, evaluator) + "\t");
					System.out.println("b:" + cell);

					String jdbcURL = "jdbc:mysql://localhost:3306/qldiem";
					String username = "root";
					String password = "12345678";

					String excelFilePath = SAMPLE_XLSX_FILE_PATH;

					int batchSize = 20;

					Connection connection = null;

					try {
						long start = System.currentTimeMillis();

						FileInputStream inputStream = new FileInputStream(excelFilePath);

						Workbook workbook2 = new XSSFWorkbook(inputStream);

						Sheet firstSheet = workbook2.getSheetAt(0);
						Iterator<Row> rowIterator2 = firstSheet.iterator();

						connection = DriverManager.getConnection(jdbcURL, username, password);
						connection.setAutoCommit(false);

						String sql = "insert into diem (diem,trangthai,IDLopHS,IDDauDiem,IDGV_L_M) values(?,?,?,?,?)";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						preparedStatement = connection.prepareStatement(sql);
						int count = 0;

						rowIterator2.next(); // skip the header row

						while (rowIterator2.hasNext()) {
							Row nextRow = rowIterator2.next();
							Iterator<Cell> cellIterator2 = nextRow.cellIterator();

							while (cellIterator2.hasNext()) {
								Cell nextCell = cellIterator2.next();

								int columnIndex = nextCell.getColumnIndex();

								switch (columnIndex) {
								case 0:
									Double diemCell = (Double) cell.getNumericCellValue();

									preparedStatement.setDouble(1, diemCell);
									break;
								case 1:
//        						int trangthai = (int) nextCell.getNumericCellValue();
									int trangthai = 0;
									preparedStatement.setInt(2, trangthai);
								case 2:
//        						int IDLopHS = (int) nextCell.getNumericCellValue();
									int IDLopHS = 1;
									preparedStatement.setInt(3, IDLopHS);
								case 3:
//        						int IDDauDiem = (int) nextCell.getNumericCellValue();
									int IDDauDiem = 1;
									preparedStatement.setInt(4, IDDauDiem);
								case 4:
//        						int IDGV_L_M = (int) nextCell.getNumericCellValue();
									int IDGV_L_M = 1;
									preparedStatement.setInt(5, IDGV_L_M);
								}

							}

							preparedStatement.addBatch();

							if (count % batchSize == 0) {
								preparedStatement.executeBatch();
							}

						}

						workbook2.close();

						// execute the remaining queries
						preparedStatement.executeBatch();

						connection.commit();
						connection.close();

						long end = System.currentTimeMillis();
						System.out.printf("Import done in %d ms\n", (end - start));

					} catch (IOException ex1) {
						System.out.println("Error reading file");
						ex1.printStackTrace();
					} catch (SQLException ex2) {
						System.out.println("Database error");
						ex2.printStackTrace();
					}

				}
				System.out.println();
			}
		}
		// Closing the workbook
		workbook.close();
	}

	private static Object getCellValue(Cell cell, FormulaEvaluator evaluator) {
		CellValue cellValue = evaluator.evaluate(cell);
		switch (cellValue.getCellTypeEnum()) {
		case BOOLEAN:
			return cellValue.getBooleanValue();
		case NUMERIC:
			return cellValue.getNumberValue();
		case STRING:
			return cellValue.getStringValue();
		case BLANK:
			return "";
		case ERROR:
			return cellValue.getError(cell.getErrorCellValue()).getStringValue();
		// CELL_TYPE_FORMULA will never happen
		case FORMULA:
		default:
			return null;
		}

	}

	@GetMapping("/GV/SampleFileWithData/{idMon}/{tenLop}/{lop}/{idlhs}")

	public void exportToExcel32(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("idMon") Integer Idm, @PathVariable("tenLop") String tenLop,
			@PathVariable("lop") String idlop, @PathVariable("idlhs") String idlhs) throws IOException {

		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=SampleFile.xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook();

			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet

			XSSFSheet sheet = workbook.createSheet("Sheet 1");

			XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
			XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createNumericConstraint(
					XSSFDataValidationConstraint.ValidationType.DECIMAL,
					XSSFDataValidationConstraint.OperatorType.BETWEEN, "0", "10");

			CellRangeAddressList addressList = new CellRangeAddressList(1, 200, 1, 12);
			XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);

			validation.setSuppressDropDownArrow(false);

			validation.setShowErrorBox(true);

			validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
			validation.createErrorBox("Lỗi khi nhập điểm!", "Ô nhập điểm chỉ được phép nhập từ 0 đến 10");
			sheet.addValidationData(validation);

			List<DauDiem> list = rep.getDauDiemByMon(Idm);

			for (int i = 0; i < list.size(); i++) {
				list.get(i).getLoaiDauDiem();
				list.get(i).getIdDauDiem();
				// display the original array
				System.out.println("Original Array:" + Arrays.toString(odd_Array));

				// element to be added
				String val = list.get(i).getLoaiDauDiem() + " - " + list.get(i).getIdDauDiem();

				// convert array to Arraylist
				List<String> oddlist = new ArrayList<String>(Arrays.asList(odd_Array));

				// Add the new element
				oddlist.add(val);

				// Convert the Arraylist back to array
				odd_Array = oddlist.toArray(odd_Array);

				// display the updated array
				System.out.println("\nArray after adding element " + val + ":" + Arrays.toString(odd_Array));
			}

			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.RED.getIndex());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
			headerCellStyle.setLocked(true);
			headerCellStyle.setBorderBottom(BorderStyle.THIN);
			headerCellStyle.setBorderLeft(BorderStyle.THIN);
//            headerCellStyle.setLocked(false);

			// Create a Row
			Row headerRow = sheet.createRow(0);
//			sheet.protectSheet("Sheet1");
			// Create cells
			for (int i = 0; i < odd_Array.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(odd_Array[i]);
				cell.setCellStyle(headerCellStyle);
			}
			
			
			
			int rowNum = 1;
			String diem1 = "";
			List<Lop_hs> diemlist = hslrepo.getHS(tenLop);
			for (int k=0; k < diemlist.size();k++) {
			// Create Other rows and cells with employees data
			
			List<Diem> diemlistByid = Drepo.findDiemById(idlop, diemlist.get(k).getIdLopHs().toString());
			
			
			
			for (int j = 0; j < diemlistByid.size(); j++) {

				diem1 = diemlistByid.get(j).getDiem().toString();
				List<String> oddlist2 = new ArrayList<String>(Arrays.asList(odd_Array_Diem));

				// Add the new element
				oddlist2.add(diem1);

				// Convert the Arraylist back to array
				odd_Array_Diem = oddlist2.toArray(odd_Array_Diem);
				System.out.println(diemlistByid.get(j).getIdDiem() + " - " + diem1+" - "+diemlist.get(k).getIdLopHs());
				System.out.println("\nArray Diem after adding element " + diem1 + ":" + Arrays.toString(odd_Array_Diem));
			}


				Row row = sheet.createRow(rowNum++);
				CellStyle RowCellStyle = workbook.createCellStyle();
				RowCellStyle.setLocked(false);

				

				for (int i = 0; i < odd_Array_Diem.length; i++) {

					for (int z = 1; z < odd_Array.length; z++) {
						row.createCell(z).setCellValue(odd_Array_Diem[i]);
						
					}
				}
				
//				for (int i = 0; i < odd_Array.length; i++) {
//					row.createCell(i).setCellStyle(RowCellStyle);
//				}
				row.createCell(0).setCellValue(diemlist.get(k).getIdhs().getTenhocsinh() + " - " + diemlist.get(k).getIdLopHs());

			}

			
			
			
			sheet = workbook.getSheetAt(0);

			// Resize all columns to fit the content size
			for (int i = 0; i < odd_Array.length; i++) {
				sheet.autoSizeColumn(i);
			
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("SampleExcel.xlsx");
			workbook.write(fileOut);
			workbook.write(response.getOutputStream());

			workbook.close();
			fileOut.close();

			// Closing the workbook
			workbook.close();
			odd_Array = new String[] { "Họ và tên" };
		} catch (final Exception e) {

		}
	}

	@GetMapping("/GV/SampleFile/{idMon}/{tenLop}")

	public void exportToExcel3(HttpServletResponse response, HttpServletRequest request,
			@PathVariable("idMon") Integer Idm, @PathVariable("tenLop") String tenLop) throws IOException {

		try {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=SampleFile.xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook();

			CreationHelper createHelper = workbook.getCreationHelper();

			// Create a Sheet

			XSSFSheet sheet = workbook.createSheet("Sheet 1");

			XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
			XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createNumericConstraint(
					XSSFDataValidationConstraint.ValidationType.DECIMAL,
					XSSFDataValidationConstraint.OperatorType.BETWEEN, "0", "10");

			CellRangeAddressList addressList = new CellRangeAddressList(1, 200, 1, 12);
			XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);

			validation.setSuppressDropDownArrow(false);

			validation.setShowErrorBox(true);

			validation.setErrorStyle(DataValidation.ErrorStyle.STOP);
			validation.createErrorBox("Lỗi khi nhập điểm!", "Ô nhập điểm chỉ được phép nhập từ 0 đến 10");
			sheet.addValidationData(validation);

			List<DauDiem> list = rep.getDauDiemByMon(Idm);

			for (int i = 0; i < list.size(); i++) {
				list.get(i).getLoaiDauDiem();
				list.get(i).getIdDauDiem();
				// display the original array
				System.out.println("Original Array:" + Arrays.toString(odd_Array));

				// element to be added
				String val = list.get(i).getLoaiDauDiem() + " - " + list.get(i).getIdDauDiem();

				// convert array to Arraylist
				List<String> oddlist = new ArrayList<String>(Arrays.asList(odd_Array));

				// Add the new element
				oddlist.add(val);

				// Convert the Arraylist back to array
				odd_Array = oddlist.toArray(odd_Array);

				// display the updated array
				System.out.println("\nArray after adding element " + val + ":" + Arrays.toString(odd_Array));
			}

			// Create a Font for styling header cells
			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.RED.getIndex());

			// Create a CellStyle with the font
			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);
//            headerCellStyle.setLocked(false);

			// Create a Row
			Row headerRow = sheet.createRow(0);
			sheet.protectSheet("Sheet1");
			// Create cells
			for (int i = 0; i < odd_Array.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(odd_Array[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = workbook.createCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			// Create Other rows and cells with employees data
			int rowNum = 1;

			List<Lop_hs> diemlist = hslrepo.getHS(tenLop);
			for (Lop_hs diem : diemlist) {

				Row row = sheet.createRow(rowNum++);
				CellStyle RowCellStyle = workbook.createCellStyle();
				RowCellStyle.setLocked(false);

				for (int i = 0; i < odd_Array.length; i++) {
//					List<Diem> diemlistByid = Drepo.findDiemById();
					row.createCell(i).setCellStyle(RowCellStyle);
				}

				row.createCell(0).setCellValue(diem.getIdhs().getTenhocsinh() + " - " + diem.getIdLopHs());
			}

			sheet = workbook.getSheetAt(0);

			// Resize all columns to fit the content size
			for (int i = 0; i < odd_Array.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream("SampleExcel.xlsx");
			workbook.write(fileOut);
			workbook.write(response.getOutputStream());

			workbook.close();
			fileOut.close();

			// Closing the workbook
			workbook.close();
			odd_Array = new String[] { "Họ và tên" };
		} catch (final Exception e) {

		}
	}

}