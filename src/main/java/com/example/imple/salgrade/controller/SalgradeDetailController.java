
package com.example.imple.salgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.imple.salgrade.mapper.SalgradeMapper;
import com.example.standard.controller.PageableController;
import com.example.standard.controller.DetailController;
import com.example.standard.controller.ListController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/salgrade")
@Slf4j
public class SalgradeDetailController implements DetailController<Integer> {

	@Autowired
	SalgradeMapper mapper;

	@Override
	public String detail(Integer key, Model model, HttpServletRequest request) {
		return "salgrade/detail";
	}
}
