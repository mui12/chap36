package com.example.imple.emp.model;

import java.time.LocalDate;

import com.example.imple.dept.model.Dept;
import com.example.standard.util.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor (staticName = "of")
@NoArgsConstructor	  
@Builder
public class Emp {
	@NonNull Integer   empno;
	@NonNull String    ename;
			 Gender    gender;
			 String    job;
			 Integer   mgr; 	 // null일수 있어서 Integer
			 LocalDate hiredate; // LocalDate : 날짜만 저장
			 Double    sal;
			 Double    comm;
			 Integer   deptno; 	 // 소속이 null일수 있음 
			 Dept	   dept;
}



	