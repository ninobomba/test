package io.github.ninobomba.t4m.test.api.mvc;

import io.github.ninobomba.t4m.test.api.TimeResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest ( TimeResource.class )
class TimeResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnFormattedCurrentTime ( ) throws Exception {
		mockMvc
				.perform ( get ( "/time" ).accept ( MediaType.TEXT_PLAIN ) )
				.andExpect ( status ( ).isOk ( ) )
				.andExpect ( content ( ).contentTypeCompatibleWith ( MediaType.TEXT_PLAIN ) )
				.andExpect ( content ( ).string ( matchesPattern ( "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}" ) ) );
	}

}
