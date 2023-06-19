package com.example.imple.city.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.imple.city.model.City;
import com.example.imple.country.model.Country;
import com.github.pagehelper.Page;


@Mapper
public interface CityMapper {
	
	
	
/*	
 * application.properties
 * mybatis.configuration.map-underscore-to-camel-case=true 
 * 
	@Results({
		@Result(property="countryCode", column="country_code")
		})
	@Select("select * from city")
	List<City> selectAll();
*/
	
	
	int countAll();
	Country		selectCountry();
	List<City>  selectAll();
	List<City>  selectAllWithCountry();
	Page<City>  selectPage();		      // Page 라이브러리 
	Page<City>  selectPageWithCountry();		
	City selectById(int id);
	City selectByIdWithCountry(int id);
	List<City>  selectByCountryCode(String countryCode); // 하나가 아니니까, 
	
	int insertCity(City city);
	int updateCity(City city);
	int delete(Long id);
	
	
	
}
