package io.github.ninobomba.t4m.test.api.assured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.matchesPattern;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PokemonResourceTest {

    @LocalServerPort
    private int port;

    @MockBean
    private WebClient webClient;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;

        // Mock the WebClient response
        String mockResponseBody = "{\"name\":\"ditto\",\"base_experience\":101,\"height\":3,\"weight\":40}";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        ResponseEntity<String> mockResponse = new ResponseEntity<>(mockResponseBody, headers, HttpStatus.OK);

        RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock(RequestHeadersUriSpec.class);
        ResponseSpec responseSpec = Mockito.mock(ResponseSpec.class);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString())).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.toEntity(String.class)).thenReturn(Mono.just(mockResponse));
    }

	@Test
	public void testGetRequest() {
		given()
				.when()
				.get("/pokemon")
				.then()
				.statusCode(200)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body("name", matchesPattern("ditto"))
				.body("base_experience", org.hamcrest.Matchers.greaterThan(0))
				.body("height", org.hamcrest.Matchers.greaterThan(0))
				.body("weight", org.hamcrest.Matchers.greaterThan(0));
	}
}
