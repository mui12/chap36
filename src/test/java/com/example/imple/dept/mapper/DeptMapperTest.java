package com.example.imple.dept.mapper;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.transaction.annotation.Transactional;

import com.example.imple.dept.mapper.DeptMapper;
import com.example.imple.dept.model.Dept;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class DeptMapperTest {

	@Autowired
	DeptMapper deptMapper; // 의존성 주입(DI/Dependency index) *spring 용어
	
	@Autowired
	ObjectMapper objectMapper;


	@Test
	void selectEmps() throws IOException {
		var list = deptMapper.selectEmps(10);
		System.out.println(list);
		objectMapper.createGenerator(System.out)
					.writeObject(list);
	}
	
	@Test
	void selectAllWithEmps() throws IOException {
		var list = deptMapper.selectAllWithEmps();
		System.out.println(list);
//		objectMapper.createGenerator(System.out)
//					.writeObject(list);
		assertThat(list).allMatch(e -> {
			return e.getEmps()!=null;
		});
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getEmps()).isNotNull();
			assertThat(e.getEmps().size()).isGreaterThanOrEqualTo(0); // 0이상 
		});
		
	}
	
	
	
	
	@Test
	void selectAll() throws IOException {
		var list = deptMapper.selectAll();
		System.out.println(list);
//		objectMapper.createGenerator(System.out)
//					.useDefaultPrettyPrinter()
//					.writeObject(list);
		assertThat(list).allMatch(e -> {
			return e.getEmps()==null;
		});
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getEmps()).isNull();
		});
	}
	
	
	@Test
	void selectByDeptno() {
		var dept = deptMapper.selectByDeptno(10);
		System.out.println(dept);

		//		Assertions.assertSame(10, dept.getDeptno());
		// import static org.junit.jupiter.api.Assertions.*;
		// assertSame(10, dept.getDeptno());
		assertThat(dept.getDeptno()).isEqualTo(10);
		
		
		// 없는거
		dept = deptMapper.selectByDeptno(90);
		System.out.println(dept);
		assertNull(dept);
	}

	
	@Test
	void selectByDeptnoWithemps() {
		var dept = deptMapper.selectByDeptnoWithEmps(10);
		assertThat(dept.getDeptno()).isEqualTo(10);
		assertThat(dept.getEmps()).isNotNull();
		
		dept = deptMapper.selectByDeptnoWithEmps(90);
		assertNull(dept);
		
	}
	
	
	
	
	
	/*  @Transactional
	 *
	 *   Test 실행시 자동으로 commit됨
	 *   @Transactional 처리를 하면 무조건 rollback 시킴
	 */
	
	@Test
	@Transactional
//	@Rollback(false)  
	void insert() {
		deptMapper.insert(50, "개발부", "부산");
		System.out.println(deptMapper.selectByDeptno(50));
		
		deptMapper.insert(60, "개발2부", null);
		System.out.println(deptMapper.selectByDeptno(60));
		
		try {
			deptMapper.insert(50, "개발3부", "서울");
		}catch (DuplicateKeyException e) {
			System.out.println("50번 부서는 사용할 수 없습니다.");
		}
		
		try {
			deptMapper.insert(70, null, null);
		} catch (DataIntegrityViolationException e) {
			System.out.println(e.getMessage());
		}
		
		
		//  exception이 발생하면 성공 (발생할exception, 람다식)
		assertThrows(DataIntegrityViolationException.class	, () -> {
			deptMapper.insert(100, "총무부", null);
		});
				
	}
	
	
	@Test
	@Transactional
	void insertDept() throws IOException {
//		var dept = new Dept (50, "개발1부", "서울");
		var dept = Dept.of(50, "개발1부", "서울", null);
//		deptMapper.insertDept(dept);
		int cnt = deptMapper.insertDept(dept);
		assertThat(cnt).isEqualTo(1);
		
		dept = Dept.of(60, "개발2부", null, null);
		cnt = deptMapper.insertDept(dept);		//반드시 성공한다.
		assertThat(cnt).isEqualTo(1);
		
		
		// exception이 발생하면 성공 (변경x)
		assertThrows(DuplicateKeyException.class, () -> {
			deptMapper.insertDept(Dept.of(60, "개발3부", null, null));
		});
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			deptMapper.insertDept(Dept.of(200, "개발4부", null, null));
		});
		
		
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(deptMapper.selectAll());
		
	}
	
	@Test
	@Transactional
	void update() throws IOException {
		int cnt = deptMapper.update(10, "account", "Seoul");
		assertThat(cnt).isEqualTo(1);
	
		
		// 실패하는 경우
		assertThrows(DataIntegrityViolationException.class, () -> {
			try {
				deptMapper.update(20, null, "Seoul");
			} catch (UncategorizedSQLException e) {
				throw new DataIntegrityViolationException(e.getMessage());
			} 
		});
		
			// 없는 로우를 지정해서 update하면 실패하지는 않는데 0개 행이 업데이트 된다.
		cnt = deptMapper.update(50, "account", "Seoul");
		assertThat(cnt).isEqualTo(0);	// insert문과 다름 0이될수이기 때문에 
		
		cnt = deptMapper.update(100, "개발4부", null);
		assertThat(cnt).isEqualTo(0);
		
		cnt = deptMapper.update(30, "개발4부", null);
		assertThat(cnt).isEqualTo(1);	// update 될 것이라고 확신한다.
		
		
		

		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(deptMapper.selectAll());
	}
	
	
	// MYSELF
	@Test
	@Transactional
	void updateDept() throws IOException {
		var dept = Dept.of(10, "account1", null, null);
		int cnt = deptMapper.updateDept(dept);
		assertThat(cnt).isEqualTo(1);

		assertThrows(UncategorizedSQLException.class, () -> {
			deptMapper.updateDept(Dept.of(40, null, null, null));
		});
		
		
		
		
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(deptMapper.selectAll());
	}
	
		
	@Test
	@Transactional
	void delete() throws IOException {
		int cnt = deptMapper.delete(90); // 단지0, update안됨.
		assertThat(cnt).isEqualTo(0);    // 0이라고 확신한다.

		cnt = deptMapper.delete(40); 
		assertThat(cnt).isEqualTo(1);    // 1이라고 확신한다. 소속있음 

		// FK걸린 key는 삭제할 수 없다. 에러 : 자식 레코드가 발견되었습니다

		assertThrows(DataIntegrityViolationException.class, () -> { // 삭제할수 없는게 맞는거다.
			deptMapper.delete(10);		// 이때는 cnt가 중요하지 않다. 
		});
		
		
		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(deptMapper.selectAll());
		
	}
		
	 
	
}
