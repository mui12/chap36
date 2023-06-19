package com.example.imple.city.model;

import com.example.imple.country.model.Country;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor(staticName = "of")    // Dept를 매개변수로 하는 생성자를 만들어줌 
@NoArgsConstructor	   // 전체필드 매개변수생성자 만듬 default
@Builder
public class City {
	         Long    id;		     /* 11자리 int=최대10자리까지 가능 → long으로 해주면됨 Long은 ref type   
	         							DB가 번호를 만들게해서 null일 가능성이 있기 때문에 @NonNull을 빼줘야 한다.*/
	@NonNull String  name;
			 String  countryCode; // java : 카멜표기법, @Results({@Result(property="countryCode", column="country_code")})  
			 					 //  DB  : _ (Underscore), ※이름은 DB와 똑같이 줘야함 전역적 country_code
			 String  district;
			 Long    population;
			 Country country;
	
}





