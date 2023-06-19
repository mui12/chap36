package com.example.imple.emp.mapper;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;	// 
import static org.assertj.core.api.Assertions.assertThatThrownBy;	//Assertions import 안줘도 됨.
import static org.junit.jupiter.api.Assertions.*;	//jupiter base  JUnit5

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.transaction.annotation.Transactional;

import com.example.imple.dept.mapper.DeptMapper;
import com.example.imple.dept.model.Dept;
import com.example.imple.emp.model.Emp;
import com.example.imple.emp.mapper.EmpMapper;
import com.example.standard.util.Gender;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class EmpMapperTest {

	@Autowired
	DeptMapper deptMapper;

	@Autowired
	EmpMapper empMapper;
	
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void countAll() {
		int cnt = empMapper.countAll();
		System.out.println("cnt = " + cnt);
		assertThat(cnt).isSameAs(14);
	}
	
	
	@Test
	void selectAllWithDept() throws IOException {
		var list = empMapper.selectAllWithDept();
		assertThat(list.size()).isSameAs(14);  
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getDept()).isNotNull();
		});
		
		objectMapper.createGenerator(System.out)
					.writeObject(list);
	}
	
	
	
	@Test
	void selectAll() throws IOException {
		var list = empMapper.selectAll();
//		System.out.println(list);
		assertThat(list.size()).isSameAs(14);  //select count(*) from emp;
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getDept()).isNull();
		});
		
		objectMapper.createGenerator(System.out)
//					.useDefaultPrettyPrinter()
					.writeObject(list);
	}
	
	
	
	
	
	@Test
	void selectByEmpno() {
		var emp = empMapper.selectByEmpno(1001);
		System.out.println(emp);

		//		Assertions.assertSame(10, dept.getDeptno());
		// import static org.junit.jupiter.api.Assertions.*;
//		assertThat(emp.getEmpno()).isSameAs(1001); 
//		assertSame(1001, emp.getEmpno());  // actual refer to the same object.
										   // error : expected: java.lang.Integer@39ac8c0c<1001> but was
										   // 1byte 127, -128~128보다 크면 안됨 
		assertEquals(1001, emp.getEmpno());
		assertThat(emp.getEmpno()).isEqualTo(1001);
		
		// 없는거
		emp = empMapper.selectByEmpno(9000); // select * from emp where empno=9000;
		System.out.println(emp);
		assertNull(emp);   					 // null이라고 확신한다.
		assertThat(emp).isNull();
		
	}
	
	
	@Test
	void selectByEmpnoWithDept() {
		var emp = empMapper.selectByEmpnoWithDept(1001);
		System.out.println(emp);
		
		assertEquals(1001, emp.getEmpno());
		assertThat(emp.getEmpno()).isEqualTo(1001);
		
		emp = empMapper.selectByEmpnoWithDept(9000);
		System.out.println(emp);
		assertNull(emp);   					 
		assertThat(emp).isNull();
	}

	
	
	
	
	

	@Test
	@Transactional
//	@Rollback(false)  
	void insertByEmpnoWithEname() {
		int cnt = empMapper.insertByEmpnoWithEname(9000, "김부산");
		System.out.println("cnt = " +cnt);
		assertEquals(1, cnt);  				// 기대값 1, cnt로 예상이 된다.
		
		assertThrows(DataIntegrityViolationException.class, () ->{
			empMapper.insertByEmpnoWithEname(9001, null);
		});
		
//		assertThatThrownBy(()-> {  import 한 경우			
		Assertions.assertThatThrownBy(()-> {  			//org.assertj.core.api
			empMapper.insertByEmpnoWithEname(9001, null);
		}).isInstanceOf(DataIntegrityViolationException.class);   
		
		assertThrows(DuplicateKeyException.class, ()-> {
			empMapper.insertByEmpnoWithEname(1001, "홍길동"); 
		});
	
	}
	
	
	@Test
	@Transactional
	void insertEmp() throws IOException {
		var emp = new Emp();
		emp.setEmpno(9000);
		emp.setEname("홍길동");
		emp.setDeptno(10);
		int cnt = empMapper.insertEmp(emp);
		assertThat(cnt).isEqualTo(1);
		
		var emp2 = empMapper.selectByEmpno(9000);
		System.out.println(emp2);
		assertThat(emp).isEqualTo(emp2);   // emp랑 emp2랑 비교했을 때 같냐?
		
		emp = new Emp();
		emp.setEmpno(9100);
		emp.setEname("홍참외");
		emp.setGender(Gender.M);
//		emp.setHiredate(LocalDate.now());
		emp.setHiredate(LocalDate.of(2023, 05, 26));
		empMapper.insertEmp(emp);
		
		emp2 = empMapper.selectByEmpno(9100);
		System.out.println(emp2);
		assertThat(emp).isEqualTo(emp2);
		
		assertThrows(DataIntegrityViolationException.class, ()-> {
			var e = new Emp();
			e.setEmpno(9100);
			e.setEname("콩순이");
			e.setGender(Gender.F);
			e.setDeptno(null);     // FK
			empMapper.insertEmp(e);
		});

	
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(empMapper.selectByEmpno(9000)); //.selectAll()
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(empMapper.selectByEmpno(9100));
	
	}
	
	
	
	
	@Test
	@Transactional
	void updateByEmpnoWithSal() throws IOException {
		int cnt = empMapper.updateByEmpnoWithSal(1001, 500.45);
		assertThat(cnt).isEqualTo(1);
		var emp = empMapper.selectByEmpno(1001);
		assertThat(emp.getSal()).isEqualTo(500.45);
		
		cnt = empMapper.updateByEmpnoWithSal(1000, 1000);
		assertThat(cnt).isEqualTo(0);

		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(empMapper.selectByEmpno(1001));
	}
	
	@Test
	@Transactional
	void updateByEmpnoWithDeptno() throws IOException {
		int cnt = empMapper.updateByEmpnoWithDeptno(1001, 40);
		assertThat(cnt).isEqualTo(1);
		
		var emp = empMapper.selectByEmpno(1001);
		assertThat(emp.getDeptno()).isEqualTo(40);
		
		cnt = empMapper.updateByEmpnoWithDeptno(1002, null);
		assertThat(cnt).isEqualTo(1);
		
		assertThrows(DataIntegrityViolationException.class, ()-> {
			empMapper.updateByEmpnoWithDeptno(1002, 90);			// 없는부서 
		});

		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(empMapper.selectByEmpno(1001));

		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(empMapper.selectByEmpno(1002));
	}
	
	
	@Test
	@Transactional
	void updateEmp() throws IOException {
		var emp = empMapper.selectByEmpno(1001);  // 정보먼저 불러와서 
		emp.setJob("developer");
		emp.setHiredate(LocalDate.now());
		emp.setDeptno(40);
		
		int cnt= empMapper.updateEmp(emp);
		assertThat(cnt).isEqualTo(1);
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(empMapper.selectByEmpno(1001));
	}
	
		
	@Test
	@Transactional
	void delete() throws IOException {
		int cnt = empMapper.delete(1001); 
		assertThat(cnt).isEqualTo(1);    

		cnt = deptMapper.delete(9000); // 없는번호 삭제
		assertThat(cnt).isEqualTo(0);     

		// FK걸린 key는 삭제할 수 없다. 에러 : 자식 레코드가 발견되었습니다

		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(empMapper.selectAll());
		
	}
	
	
	
	
	 
	
}
