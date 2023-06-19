package com.example.imple.dept.model;

import java.util.List;

import com.example.imple.emp.model.Emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//@Data
//@AllArgsConstructor(staticName = "of")   // Dept를 매개변수로 하는 생성자를 만들어줌 
//@NoArgsConstructor	   // 전체필드 매개변수생성자 만듬 default
//@Builder
//public class Dept {
//                     //	int deptno;
//	@NonNull Integer deptno;
//	@NonNull String  dname;  //NonNull(lombok)
//			 String loc;
//			 List<Emp> emps;  // 직원들 모델링 (1 : N 관계)
//	
//}

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class Dept { 
	
	@NonNull Integer deptno;
	@NonNull String  dname;
			 String  loc;
			 List<Emp> emps;
}