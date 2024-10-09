package com.gildedrose;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalculadoraTest {
	Calculadora calc;
	
	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculadora();
	}

	@ParameterizedTest
	@CsvSource({
		"1, 1, 2",
		"-1, 1, 0",
		"2, -3, -1",
		"0.1, 0.2, 0.3",
		"1, -0.9, 0.1",
		"0, 0, 0"
	})
	void testSuma(double a, double b, double previsto) {
		double rslt = calc.suma(a, b);
		
		assertEquals(previsto, rslt);
	}

}
