package com.taetaetae.springrestdocs.books;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
@AutoConfigureRestDocs
public class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_책을_조회하면_null이_아닌_객체를_리턴한다() throws Exception {
		mockMvc.perform(get("/book/{id}", 1)
			.accept(MediaType.APPLICATION_JSON))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(document("book",
				pathParameters(
					parameterWithName("id").description("book unique id")
				),
				responseFields(
					fieldWithPath("id").description("book unique id"),
					fieldWithPath("title").description("title"),
					fieldWithPath("author").description("author")
				)
			))
			.andExpect(jsonPath("$.id", is(notNullValue())))
			.andExpect(jsonPath("$.title", is(notNullValue())))
			.andExpect(jsonPath("$.author", is(notNullValue())));
	}
}