package io.github.ninobomba.t4m.test.api.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
@AutoConfigureMockMvc
@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class TodoWireMockTest {


	private static final String MOCK_API_URI_REGEX = "/posts.*";

	@RegisterExtension
	private static final WireMockExtension wireMockExtension = WireMockExtension.newInstance ( )
			.options ( wireMockConfig ( )
					.disableRequestJournal ( )
					.dynamicPort ( )
					.notifier ( new ConsoleNotifier ( true ) )
			)
			.build ( );

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@DynamicPropertySource
	static void configureProperties ( DynamicPropertyRegistry registry ) {
		registry.add ( "https://jsonplaceholder.typicode.com" , wireMockExtension :: baseUrl );
		log.info ( "WireMock - baseUrl: {}" , wireMockExtension.baseUrl ( ) );
	}

	@BeforeAll
	static void config ( ) {
		RestAssured.filters ( new RequestLoggingFilter ( ) , new ResponseLoggingFilter ( ) );
	}

	@BeforeEach
	public void setUp ( ) {
		RestAssuredMockMvc.mockMvc ( mockMvc );
	}


	@Test
	void shouldGetJsonPlaceHolderMockPost_200 ( ) throws Exception {

		var userId = "1";
		var id = "2";

		var MOCK_POST_RESPONSE =
				"""
						{
						    "userId": "%s",
						    "id": "%s",
						    "title": "qui est esse",
						    "body": "est rerum tempore vitae nulla"
						}
						""".formatted ( userId , id );

		wireMockExtension
				.stubFor ( WireMock.get ( urlMatching ( MOCK_API_URI_REGEX ) )
						.willReturn ( aResponse ( )
								.withHeader ( HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON_VALUE )
								.withStatus ( HttpStatus.OK.value ( ) )
								.withBody ( MOCK_POST_RESPONSE )
						)
				);


		given ( )
				.port ( port )
				.relaxedHTTPSValidation ( )
				.header ( HttpHeaders.ACCEPT , MediaType.APPLICATION_JSON_VALUE )
				.pathParam ( "id" , id )
				.when ( )
				.get ( "/posts/{id}" )
				.then ( )
				.statusCode ( HttpStatus.OK.value ( ) )
				.body ( "id" , equalTo ( id ) );
	}

}
