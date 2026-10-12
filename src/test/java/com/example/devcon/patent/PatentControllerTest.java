package com.example.devcon.patent;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PatentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void returnsOriginalUrlForValidPatentId() throws Exception {
		mockMvc.perform(get("/api/patents/original").param("patentId", "TEST-001"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.originalUrl").value("/original.html?patentId=TEST-001"));
	}

	@Test
	void rejectsInvalidPatentId() throws Exception {
		mockMvc.perform(get("/api/patents/original").param("patentId", "../secret"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void servesViewerPage() throws Exception {
		mockMvc.perform(get("/index.html"))
				.andExpect(status().isOk())
				.andExpect(result -> assertThat(
						result.getResponse().getContentAsString(UTF_8),
						containsString("특허 원문 보기")));
	}
}
