package io.github.ninobomba.t4m.test.api.assured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;

@AutoConfigureMockMvc
@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
class TimeResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@BeforeEach
	void setUp ( ) {
		RestAssured.reset ( );
		RestAssured.port = this.port;
	}

	@Test
	public void testGetRequest ( ) {
		given ( )
				.when ( )
				.get ( "/time" )
				.then ( )
				.statusCode ( 200 )
				.contentType ( MediaType.TEXT_PLAIN_VALUE )
				.body ( matchesPattern ( "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}" ) );
	}

}
