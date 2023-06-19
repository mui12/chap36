package com.example.imple.dept.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.imple.dept.mapper.DeptMapper;
import com.example.imple.dept.model.DeptDTO;
import com.example.standard.controller.CreateController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dept")  // 상위 
@Slf4j
public class DeptCreateController implements CreateController<DeptDTO> {

	@Autowired
	DeptMapper mapper;

	
	// 1. get양식을받음
	@Override
	public void create(Model model, HttpServletRequest request) {
		log.info("GET create()...");
		
		/* error가 존재하지 않으면 지워버린다 */
		var error = request.getParameter("error"); // error를 읽어 봤더니 없으면 null일고 있다면 빈스트링
		if (error == null) {
			var session = request.getSession();
			session.removeAttribute("dept");
			session.removeAttribute("binding");
		}
	}

	// 2. Submit 하면 양식을 받아서 들어옴 
	@Override
	@Transactional  // 기능에 따라 DML작업을 여러번 할 수 있음 exception이 발생되면 rollback이다.
	public String create(DeptDTO dto, BindingResult binding, Model model, HttpServletRequest request, RedirectAttributes attr) {
		log.info("POST create()...");
		System.out.println(dto);
		
		/* 양식 실패시
		boolean failed = true;
		if (failed)
			return "redirect:/dept/create?error";  //error가 발생해서 redirect된거다. 쿼리스트링[?] 사용
		 */	

		var session = request.getSession();
		session.setAttribute("dept", dto);
		session.setAttribute("binding", binding);
		
		/*
		binding.hasErrors();
		binding.hasFieldErrors();
		binding.getFieldErrors().forEach(e -> {   // List<FieldError>
			e.getField();
			e.getDefaultMessage();
		});
		binding.hasFieldErrors("deptno");
		binding.getFieldError("deptno").getDefaultMessage();		
		
		binding.hasGlobalErrors();
		binding.getGlobalErrors().forEach(e -> {  // List<ObjectError>
			e.getCodes();
			e.getDefaultMessage();
		});	
		*/
		
		
		
			
		// binding 
		if (binding.hasErrors())
			return "redirect:/dept/create?error"; 
		
		
		// error가 안나면 DB에 넣기 + detp dto를 dept로 변환필요 
		var dept = dto.getModel();
		try {
			mapper.insertDept(dept);
		} catch (DuplicateKeyException e) {
			binding.reject("duplicate key", "부서번호가 중복됩니다.");   // reject global error (field가 아닌 외부)
			return "redirect:/dept/create?error"; 
		}
		
		
		// 양식 성공시
		return "redirect:/dept/success?create";
				// post 방식은 반드시 리다이렉트 해야함
	}


	

}
