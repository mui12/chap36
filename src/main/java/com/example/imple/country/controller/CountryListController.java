package com.example.imple.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.imple.country.mapper.CountryMapper;
import com.example.standard.controller.PageableController;
import com.example.standard.controller.ListController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/country")
@Slf4j
public class CountryListController implements ListController, PageableController {
	
	@Autowired
	CountryMapper mapper;
	
	@Override
	public void list(Model model, HttpServletRequest request) {
		log.trace("list(Model model) called");
		var list = mapper.selectAllWithCitys();
		
		/* 컨트롤러에서 뷰로 데이터를 전달하기 위한 메서드 ${list}*/
		model.addAttribute("list", list);
		list.forEach(e -> {
			log.debug(e.toString());
		});
	}

	@Override
	public String page(int pageNum, int pageSize, Model model) {
		log.trace("page(int pageNum, int pageSize, Model model) called");
		PageHelper.startPage(pageNum, pageSize);
		var list = mapper.selectPageWithCitys();
		var paging = PageInfo.of(list, 10);
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		log.debug(paging.toString());
		
		return "/country/page";
	}

}
