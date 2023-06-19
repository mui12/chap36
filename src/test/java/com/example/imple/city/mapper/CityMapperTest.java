package com.example.imple.city.mapper;

import static org.assertj.core.api.Assertions.assertThat;	// 
//jupiter base  JUnit5
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import com.example.imple.city.model.City;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.validation.constraints.AssertTrue;

@SpringBootTest
public class CityMapperTest {

	@Autowired
	CityMapper cityMapper;
	
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void countAll() {
		int cnt = cityMapper.countAll();
		System.out.println("cnt = " + cnt);
		assertThat(cnt).isSameAs(4079);
	}
	
	
	@Test
	void selectAll() throws IOException {
		var list = cityMapper.selectAll();
		System.out.println(list);
		assertThat(list.size()).isEqualTo(4079);
		
//		objectMapper.createGenerator(System.out)
//					.useDefaultPrettyPrinter()
//					.writeObject(list);
	}
	
	@Test
	void selectAllWithCountry() throws IOException {
		var list = cityMapper.selectAllWithCountry();
		assertThat(list.size()).isEqualTo(4079);
		
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getCountry()).isNotNull();
		});
	}
	
	
	
	@Test
	void selectById() {							//select id from city order by id;
		var city = cityMapper.selectById(10);
		System.out.println(city);
		//assertSame(10, city.getId());			// 1byte 내에서만 가능
		assertEquals(10, city.getId());
	}
	
	@Test
	void selectByIdWithCountry() throws IOException {							//select id from city order by id;
		var city = cityMapper.selectByIdWithCountry(1000);
		assertEquals(1000, city.getId());
		
		objectMapper.createGenerator(System.out)
					.writeObject(city);
	
	}

	
	@Test
	void selectByCountryCode() {
		var list = cityMapper.selectByCountryCode("KOR");
		System.out.println(list);
	}

	
	@Test
	void selectPage() throws IOException {
		PageHelper.startPage(1, 20);    	// (보고싶은)1page에 (리스트)20개 호출
		var list = cityMapper.selectPage(); // page호출
		System.out.println(list.size());
		assertThat(list.size()).isEqualTo(20);
		
		var paging = PageInfo.of(list, 20);	// Page 계산 (list, page당크기)
		System.out.println(paging);
		paging.getTotal();
		paging.getList();
		paging.getPageNum();
		paging.getSize();
		paging.getStartRow();   // 쭉 나열했을 때 246번째
		paging.getEndRow();	    // Page 끝 번호
		paging.getPages();	    // 전체 Page수
		paging.getPrePage();    // 이전페이지 번호
		paging.getNextPage();   // 다음페이지 번호
		paging.isIsFirstPage(); // 1page일때 0Page는 없게 함 false
		paging.isIsLastPage(); 
		paging.isHasPreviousPage(); // PreviousPage가 있냐 없냐
		paging.isHasNextPage();

		objectMapper.createGenerator(System.out)
					.useDefaultPrettyPrinter()
					.writeObject(paging);
		
	}
	
	@Test
	void selectPageWithCountry() throws IOException {
		PageHelper.startPage(50, 5);    	
		var list = cityMapper.selectPageWithCountry();
		//assertThat(list.size()).isEqualTo(5);
		
		assertThat(list).allSatisfy(e -> {
			assertThat(e.getCountry()).isNotNull();
		});
		
		var paging = PageInfo.of(list, 10);	
		
		objectMapper.createGenerator(System.out)
					.writeObject(paging);

		assertThat(paging).satisfies( e -> {
			assertThat(e.getTotal()).isEqualTo(4079);
			assertThat(e.getList().size()).isEqualTo(5);
			long pages = e.getTotal()/5 + (e.getTotal()%5 !=0 ? 1 : 0) ;
			assertThat(e.getPages()).isEqualTo(pages);
		});

	}
	
	
	@Test
	@Transactional
	void insertCity() {
		var city = City.builder()
//					   .id(9000L)     /*id는 주지 않았지만 순번발급기에서 순서발급되서 에러가 나지 않아야 한다.*/
					   .name("xxx")
					   .build();
		cityMapper.insertCity(city);
		
		System.out.println(city);
		assertThat(city.getId()).isNotNull();
	
		
		/*없는 countryCode를 넣으면 error*/
		
		assertThrows(DataIntegrityViolationException.class, () -> { 
			var c = City.builder()
				   .name("서울")
				   .countryCode("xxx")
				   .build();
			cityMapper.insertCity(c);
		});
		
		var c = City.builder()
				   .name("서울")
				   .countryCode("KOR")
				   .build();
		cityMapper.insertCity(c);
		System.out.println(c);
			
	}
	
	
	
	@Test
	@Transactional
	void updateCity() {
		var seoul = cityMapper.selectById(2331);
		System.out.println(seoul);
		
		
		/*countryCode → DB country_code*/
		seoul.setName("서울");
		cityMapper.updateCity(seoul);
		System.out.println(seoul);
		
		/*소문자 error*/
		
		assertThrows(DataIntegrityViolationException.class, () -> {
			seoul.setCountryCode("kor");
			cityMapper.updateCity(seoul);
		});
		
	}
	
	
	@Test
	@Transactional
	void delete() {
		var cnt = cityMapper.delete(2331L);
		assertThat(cnt).isEqualTo(1);
		
		var city = cityMapper.selectById(2331);
		assertThat(city).isNull();
		System.out.println(city);
		
		cnt = cityMapper.delete(900000L);
		assertThat(cnt).isEqualTo(0);

	}
	
	
	
}
