package com.example.demo;

import org.junit.jupiter.api.Test;
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

	@Test
	void validInput_shouldReturnTrimmedString() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "eloquent"))
				.andExpect(status().isOk())
				.andExpect(content().string("loquen"));
	}

	@Test
	void input_country_shouldReturn_ountr() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "country"))
				.andExpect(status().isOk())
				.andExpect(content().string("ountr"));
	}

	@Test
	void input_person_shouldReturn_erso() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "person"))
				.andExpect(status().isOk())
				.andExpect(content().string("erso"));
	}

	@Test
	void twoCharacterInput_shouldReturnEmptyString() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "ab"))
				.andExpect(status().isOk())
				.andExpect(content().string(""));
	}

	@Test
	void input_xyz_shouldReturn_y() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "xyz"))
				.andExpect(status().isOk())
				.andExpect(content().string("y"));
	}

	@Test
	void singleCharacterInput_shouldReturnBadRequest() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "a"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void encodedSpecialCharacters_shouldBeHandledCorrectly() throws Exception {
		mockMvc.perform(get("/api/remove").param("input", "_123_%qwerty+"))
				.andExpect(status().isOk())
				.andExpect(content().string("123_%qwerty"));
	}
}
