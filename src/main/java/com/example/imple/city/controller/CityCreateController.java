package com.example.imple.city.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.city.mapper.CityMapper;
import com.example.imple.city.model.CityDTO;
import com.example.standard.controller.CreateController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/city")
@Slf4j  
public class CityCreateController implements CreateController<CityDTO> {

	@Autowired
	CityMapper mapper;

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
	public String create(@Valid CityDTO dto, BindingResult binding, Model model, HttpServletRequest request,
			RedirectAttributes attr) {
		
		var session = request.getSession();
		session.setAttribute("city", dto);
		session.setAttribute("binding", binding);
		
		
		var countryCode = dto.getCountryCode().trim();
		
		
		//  dto에 countryCode에 공백을 제고하고 equals에 ""null String이 아니면 
		if (!countryCode.equals("")) 
			// dto에 countryCode의 길이가 3이 아니면 에러를 일으켜라  
			if (countryCode.length() !=3) {    
				binding.reject("countryCode", "3자리로 입력하세요");
			}
		
		if (binding.hasErrors()) {
			return "redirect:/city/create?error";
		}
		
		var city = dto.getModel();
		try {
			mapper.insertCity(city);
		}catch (DataIntegrityViolationException e) {
			binding.reject("foreign Key", "입력한 국가 코드는 존재하지 않습니다.");
			return "redirect:/city/create?error";
		} 
		
		/* insert 성공시에 생선된 번호가 city model에 있는데 이 번호를 dto에 넣어야 생성된 번호를 알 수 있음 */
		dto.setId(city.getId());
		
		
		return "redirect:/city/success?create";
	}

	
	
	
	
}
