package com.example.imple.salgrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.example.imple.salgrade.model.Salgrade;
import com.github.pagehelper.Page;

@Mapper
public interface SalgradeMapper {
	int 		   countAll();
//	int 		   insertSalgrade
	List<Salgrade> selectAll();
	Page<Salgrade> selectPage();
	Salgrade	   selectByGrade();
	
	@Insert("""
			insert into salgrade
			values (
				#{s.grade},
				#{s.losal, jdbcType=INTEGER},
				#{s.hisal, jdbcType=INTEGER}
			)
			""")
	int insertSalgrade(@Param("s") Salgrade salgrade);
	
	
	@Update("""
			update salgrade
			   set grade = #{s.grade},
			       losal = #{s.losal, jdbcType=INTEGER},
			       hisal = #{s.hisal, jdbcType=INTEGER}
			 where grade = #{s.grade}
			""")
	int updateSalgrade(@Param("s") Salgrade salgrade);
	
	
}
