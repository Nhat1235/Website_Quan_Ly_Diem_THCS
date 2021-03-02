package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GVBMController {
 
	@GetMapping("/GVBM")
	public String gvbm(ModelMap model) {
		return "GVBM";
	}
}
