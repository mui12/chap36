package com.example.imple.dept.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.standard.model.Modelable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class DeptDTO implements Modelable<Dept>{ 
	
	/* import validation
				   null   ""   "   "      (X면 Error)
		@NotNull    X      O     O
		@NotEmpty   X      X     O
		@NotBlank   X      X     X
		
		CreateController에 @Valid DTO dto, 어노테이션을 줘야 인식됨 
	*/
	
	@NotNull		
	@Range(min = 10, max =99)	//수치의 범위 제한 10이상 99이하
	Integer deptno;
	
	@NotBlank		
	@Length(min = 1, max = 14)
	String  dname;
	
	@Length(min = 0, max = 13)
	String  loc;

	@Override
	public Dept getModel() {
		return Dept.builder()
				   .deptno(deptno)
				   .dname(dname.trim())  
				   .loc(loc.trim())
				   .build();
	}
	
	
	/* standard / model / interface로 만듬
	public Dept getModel() {   //getModel을 호출하면 Dept를 호출하도록
		return Dept.builder()
				   .deptno(deptno)
				   .dname(dname.trim())  // trim() : 스페이스가 앞,뒤에있으면 없애줌 
				   .loc(loc.trim())
				   .build();
				   
	}
				   */
	
	
}