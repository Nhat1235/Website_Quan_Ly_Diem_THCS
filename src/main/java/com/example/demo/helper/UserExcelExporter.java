package com.example.demo.helper;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.demo.model.Diem;

public class UserExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<com.example.demo.model.Diem> listUsers;

	public UserExcelExporter(List<Diem> listUsers) {
		this.listUsers = listUsers;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");
		int rowCount = 1;
		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		for (Diem user : listUsers) {
			for (int i = 0; i < user.getIDDauDiem().getLoaiDauDiem().length(); i++) {

				createCell(row, 0, "ID", style);
				createCell(row, 1, "Tên học sinh", style);
				createCell(row, 2, "Lớp", style);
				createCell(row, 3, "Học kỳ", style);
				createCell(row, i, user.getIDDauDiem().getLoaiDauDiem().toString(), style);
			}
		}
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {

		sheet.autoSizeColumn(columnCount);

		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Diem user : listUsers) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, user.getIdDiem(), style);
			createCell(row, columnCount++, user.getIDLopHS().getIdhs().getTenhocsinh().toString(), style);
			createCell(row, columnCount++, user.getIDLopHS().getIdLopc().getTenlop().toString(), style);
			createCell(row, columnCount++, user.getIDGV_L_M().getIDHocKy().getTenhocky().toString(), style);
			createCell(row, columnCount++, user.getDiem(), style);


		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}