package io.github.ninobomba.t4m.test.api.assured;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@AutoConfigureMockMvc
@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class TodoResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@BeforeAll
	static void config ( ) {
		RestAssured.filters ( new RequestLoggingFilter ( ) , new ResponseLoggingFilter ( ) );
	}

	@BeforeEach
	public void setUp ( ) {
		RestAssuredMockMvc.mockMvc ( mockMvc );
	}

	@Test
	void shouldGet_200 ( ) throws Exception {
		var id = "1";
		given ( )
				.baseUri ( "https://jsonplaceholder.typicode.com" )
				.basePath ( "/todos" )
				.header ( "Accept" , MediaType.APPLICATION_JSON_VALUE )
				.header ( "Content-Type" , MediaType.APPLICATION_JSON_VALUE )
				.pathParam ( "id" , id )
				.when ( )
				.get ( "/{id}" )
				.then ( )
				.statusCode ( HttpStatus.OK.value ( ) )
				.contentType ( MediaType.APPLICATION_JSON_VALUE )
				.body ( "id" , equalTo ( Integer.parseInt ( id ) ) )
				.body ( "completed" , org.hamcrest.Matchers.notNullValue ( ) );
	}

}
