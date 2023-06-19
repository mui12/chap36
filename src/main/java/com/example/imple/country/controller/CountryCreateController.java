package com.example.imple.country.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.country.mapper.CountryMapper;
import com.example.imple.country.model.CountryDTO;
import com.example.standard.controller.CreateController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/country")
@Slf4j
public class CountryCreateController implements CreateController<CountryDTO> {
	
	@Autowired
	CountryMapper mapper;
	
	@Override
	public void create(Model model, HttpServletRequest request) {
		var error = request.getParameter("error"); // 에러를 읽어서 
		
		if(Objects.isNull(error)) {
			var session = request.getSession();
			session.removeAttribute("city");
			session.removeAttribute("dto");
		}
	}

	@Override
	public String create(CountryDTO dto, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes attr) {
		
		var session = request.getSession();
		session.setAttribute("city", dto);
		session.setAttribute("binding", binding);
		
		if (binding.hasErrors()) {
			return "redirect:/city/create?error";
		}
		
		var country = dto.getModel();
//		mapper.
		
		return "redirect/country/success?create";
	}


}
