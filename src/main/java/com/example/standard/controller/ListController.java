package com.example.standard.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

public interface ListController {
	
	@GetMapping("/list")
	void list(Model model, HttpServletRequest request);
		
	
	// /list 경로로 GET 요청이 들어오면 이 메서드가 실행되어 데이터를 조회하여 model에 담고, 
	// 해당하는 뷰로 전달될 것입니다. 이후 뷰는 model에 담긴 데이터를 이용하여 클라이언트에게 응답을 생성
	
}
