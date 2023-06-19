package com.example.imple.country.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.example.standard.model.Modelable;
import com.example.standard.util.Continent;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Builder
public class CountryDTO implements Modelable<Country>{
	
	@NotBlank
	@Length(max = 3 )
	String     code;
	
	@NotBlank
	@Length(max = 52)
	String     name;	
	
	Continent  continent;	//enum
	
	@NotBlank
	@Length(max = 26)
	String     region;	
	
	@Digits(integer = 8, fraction = 2)
	Double     surfaceArea; 			
	
	@Range(max = 999999)
	Integer    indepYear;			
	
	@Range(min = 1, max = 99999999999L)
	Long	   population;			
	
	@Digits(integer = 2, fraction = 1)
	Double     lifeExpectancy;		
	
	@Digits(integer = 8, fraction = 2)
	Double     gnp;	
	
	@Digits(integer = 8, fraction = 2)
	Double     gnpOld;	
	
	@NotBlank
	@Length(max = 45)
	String	   localName;	
	
	@NotBlank
	@Length(max = 45)
	String     governmentForm;
	
	@NotBlank
	@Length(max = 60)
	String     headOfState;
	
	@Range(min = 1, max = 99999999999L)
	Long       capital;
	
	
	
	@Override
	public Country getModel() {
		return Country.builder()
				      .code(code)
				      .name(name)
				      .region(region)
				      .surfaceArea(surfaceArea)
				      .indepYear(indepYear)
				      .population(population)
				      .lifeExpectancy(lifeExpectancy)
				      .gnp(gnp)
				      .gnpOld(gnpOld)
				      .localName(localName)
				      .governmentForm(governmentForm)
				      .headOfState(headOfState)
				      .capital(capital)
				      .build();
	}
	
}

