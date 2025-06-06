package io.github.ninobomba.t4m.test.api.assured;

import io.github.ninobomba.t4m.test.api.TimeResource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest ( TimeResource.class )
class TimeResourceTest {

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
