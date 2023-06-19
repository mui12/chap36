package com.example.imple.emp.model;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.standard.model.Modelable;
import com.example.standard.util.Gender;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor (staticName = "of")
@NoArgsConstructor	  
@Builder
public class EmpDTO implements Modelable<Emp>{
	
	@NotNull
	@Range(min = 1000, max = 9999)
	Integer   empno;
	
	@NotBlank
	@Length(max = 10)
	String    ename;
	
	@Pattern(regexp = "M|F|{0}", message = "M | F | null 만 허용됩니다.") // {0}=null은 들어가지만 M아니면 F만된다. Gender(x), String(o)
	String    gender;
	
	@Length(max = 9)
	String    job;
	
	@Range(min = 1000, max = 9999)
	Integer   mgr; 	 
	
	@PastOrPresent
	LocalDate hiredate; 
	
	@Digits(integer = 5, fraction = 2)
	Double    sal;

	@Digits(integer = 5, fraction = 2)
	Double    comm;
	
	@Range (min = 10, max = 99)
	Integer   deptno;

	@Override
	public Emp getModel() {
		
		/* test
		if(Objects.isNull(gender))
			System.out.println("nullllllllll");
		
		if(gender.equals(""))
			System.out.println("null string...."); */
		
		Gender gender=null;
		if (!this.gender.equals(""))
			gender = Gender.valueOf(this.gender);  // this의 gender를 문자열 gender로 변환해야한다.
		return Emp.builder()
				  .empno(empno)
				  .ename(ename)
				  .gender(gender)
				  .job(job.trim())
				  .mgr(mgr)
				  .hiredate(hiredate)
				  .sal(sal)
				  .comm(comm)
				  .deptno(deptno)
				  .build();
	} 	 

}



	