package com.example.imple.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.imple.user.model.User;

@Mapper
public interface UserMapper {
	
	@Select("""
			select *
			  from users
			 where id = #{id}
			""")
	User selectById(String id);   //com.example.imple.user.model.User 
}
