package it.genchi.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ControllerPage {

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
}
