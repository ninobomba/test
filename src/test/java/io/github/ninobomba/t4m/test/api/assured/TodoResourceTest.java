package io.github.ninobomba.t4m.test.api.assured;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class TodoResourceTest {

	@BeforeAll
	static void config ( ) {
		RestAssured.filters ( new RequestLoggingFilter ( ) , new ResponseLoggingFilter ( ) );
	}

	@BeforeEach
	public void setUp ( ) {
		RestAssured.reset ( );
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
