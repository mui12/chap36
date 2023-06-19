package com.example.imple.dept.model;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;   // Assertions.밑에 있는건 다 쓴다.
import org.junit.jupiter.api.Test;

public class DeptTest {

	@Test
	void of() {
		var dept1 = new Dept();
//		var dept2 = new Dept(10, "xxx", "yyy"); //(x) 
		var dept2 = Dept.of(10, "개발1부", "서울", null);

		System.out.println(dept1);
		System.out.println(dept2);
		
		assertThat(dept1).matches(v -> {   // matches : true return해야 성공 
//			boolean tr = v.getDeptno()==0;
			boolean tf = false;
			tf = v.getDeptno() == 0 &&
			     v.getDname() == null &&
			     v.getLoc() == null;
			return tf;		// return false면 assert error
		});
		
		assertThat(dept2).matches(v -> {
			return v.getDeptno() == 10 &&
				   v.getDname().equals("개발1부") &&
				   v.getLoc().equals("서울");				   
	});
		
		
	}
	
	@Test
	void builder() {
		var dept = Dept.builder()
				 	   .deptno(10)
				 	   .dname("개발1부")
				 	   .loc("창원").build();   // build해야 dept return
		
		assertThat(dept).satisfies(v ->{
			assertThat(v.getDeptno()).isEqualTo(10);
			assertThat(v.getDname()).isEqualTo("개발1부");
			assertThat(v.getLoc()).isEqualTo("창원");
		});
		
	}
	
	
	@Test
	void nonNull() {
//		Assertions.assertThrows(NullPointerException.class,	() -> {  //jupiter
		assertThrows(NullPointerException.class,	() -> {  //jupiter
			var dept = Dept.builder()
						   .deptno(null)
						   .dname("개발1부")
						   .loc("부산").build();
		});
		assertThrows(NullPointerException.class,	() -> {  
			var dept = Dept.builder()
						   .deptno(10)
						   .dname(null)
					       .loc("부산").build();
		});
		Dept.builder().deptno(10).dname("개발1부").build();            // no error

	}
	
	
	
}
