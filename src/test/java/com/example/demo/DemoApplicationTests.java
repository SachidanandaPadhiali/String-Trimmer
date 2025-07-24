package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@ParameterizedTest
	@CsvSource({
			"eloquent,loquen",
			"country,ountr",
			"person,erso",
			"xyz,y",
			"_123_%qwerty+,123_%qwerty"
	})
	void inputWithThreeOrMoreCharacters_shouldReturnTrimmedString(String input, String expected) throws Exception {
		mockMvc.perform(get("/api/remove").param("input", input))
				.andExpect(status().isOk())
				.andExpect(content().string(expected));
	}

	@Test
	void twoCharacterInput_shouldReturnEmptyString() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "ab"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	void singleCharacterInput_shouldReturnBadRequest() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "_123_%qwerty+"))
				.andExpect(status().isOk())
				.andExpect(content().string("123_%qwerty"));
	}
}
