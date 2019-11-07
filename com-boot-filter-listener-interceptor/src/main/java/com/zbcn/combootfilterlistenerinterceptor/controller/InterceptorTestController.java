package com.zbcn.combootfilterlistenerinterceptor.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InterceptorTestController {

	@RequestMapping("/interceptor")
	public ResponseEntity index(Model model){
		model.addAttribute("content","hi , zbcn !");
		return ResponseEntity.ok(model);
	}
}
