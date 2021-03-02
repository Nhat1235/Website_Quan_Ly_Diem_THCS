package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TaiKhoanGvController {

	@GetMapping("/login")
	public String login(ModelMap model) {
		return "login";
	}
}
