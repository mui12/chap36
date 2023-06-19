package com.example.imple.dept.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.imple.dept.model.Dept;
import com.example.imple.emp.model.Emp;

@Mapper
public interface DeptMapper {
	
	// Emp 조회 기능 추가 
	
	@Select("""
			select * 
			  from  emp
			  where deptno = #{deptno}
			""")
	List<Emp> selectEmps(int deptno); 
	
	@Select("select * from dept")
	@Results({
		@Result(property = "deptno", column = "deptno"),   
		//deptno column이 mapping 되는데 아래와 중복되서 중복사용되게 함 (중복되면 null로 나오기 때문) 
		@Result(property = "emps", 
				  column = "deptno", 
				    many = @Many(select = "selectEmps")) //1:N
	})
	List<Dept> selectAllWithEmps();  // selectEmps가 4번 반복함 
	

	
	@Select("select * from dept")
	List<Dept> selectAll();
	
	
	@Select("""
			select *
				from dept
				where deptno = #{deptno}      		
			""")									//#{xxx}
	Dept selectByDeptno(@Param("deptno")int deptno);//("xxx")

	
	
	
	@Select("""
			select *
				from dept
				where deptno = #{deptno}      		
			""")
	@Results({
		@Result(property = "deptno", column = "deptno"),   
		//deptno column이 mapping 되는데 아래와 중복되서 중복사용되게 함 (중복되면 null로 나오기 때문) 
		@Result(property = "emps", 
				  column = "deptno", 
				    many = @Many(select = "selectEmps")) //1:N
	})
	Dept selectByDeptnoWithEmps(int deptno);
	
	
	
	
	
	/*
	 * DML문은 정수 return
	   int = insert 된 개수 return
	 
	 * Oracle에서 test해보고 입력하기  
	      예)insert into dept values (50,'개발부','서울');
		    ROLLBACK;
	    
	 * 매개변수가 2개이상이면 반드시 @Param을 줘야함 
	   그래야 int deptno값이 #{deptno}에 입력이 된다.
	   	  error : BindingException:  Parameter{} not found발생
	   	  
	 * 무결성 제약조건 error
	     	DuplicateKeyException : 키 중복
	     	
	 * null값이 허용된 경우 반드시 넣어줘야함 jdbcType=VARCHAR
	   		PK인 경우 null이 허용될 수 없기 때문에 굳이 안 넣어줘도 된다.
	 *    	
	 */
	
	@Insert("""
            insert into dept 
            values (
                #{deptno, jdbcType=INTEGER}, 
                #{dname,  jdbcType=VARCHAR}, 
                #{loc,    jdbcType=VARCHAR}
            )
            """)
    int insert(@Param("deptno") int deptno, 
               @Param("dname") String dname, 
               @Param("loc") String loc);

	
	
	@Insert("""
            insert into dept 
            values (
                #{d.deptno, jdbcType=INTEGER}, 
                #{d.dname,  jdbcType=VARCHAR}, 
                #{d.loc,    jdbcType=VARCHAR}
			)
			""")
	int insertDept(@Param ("d") Dept dept);
	
	
	@Update("""
			update dept 
				set dname    = #{dname, jdbcType=VARCHAR},
				    loc      = #{loc,   jdbcType=VARCHAR}
				where deptno = #{deptno}
			""")
	int update(@Param("deptno") int deptno, 
               @Param("dname") String dname, 
               @Param("loc") String loc);
	
	
	@Update("""
			update dept 
				set dname    = #{d.dname, jdbcType=VARCHAR},
				    loc      = #{d.loc,   jdbcType=VARCHAR}
				where deptno = #{d.deptno}
			""")
	int updateDept(@Param ("d") Dept dept);

	
	@Delete("delete from dept where deptno=#{deptno}")
	int delete(int deptno);
	
}
