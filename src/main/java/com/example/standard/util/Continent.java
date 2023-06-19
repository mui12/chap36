package com.example.standard.util;

import lombok.Getter;

public enum Continent {
	NorthAmerica("North America"),
	SouthAmerica("South America"),
	Asia("Asia"),
	Africa("Africa"),
	Europe("Europe"),
	Oceania("Oceania"),
	Antarctica("Antarctica");
	
	
	@Getter  
	private String symbol;   // symbol에 저장된 값을 불러옴 
	
	private Continent(String symbol) {
		this.symbol = symbol;
	}
	
	
	
}
