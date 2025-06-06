package io.github.ninobomba.t4m.test.api.mvc;

import io.github.ninobomba.t4m.test.api.PokemonResource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles ( "test" )
class PokemonResourceTest {

	@Autowired
	private PokemonResource pokemonResource;

	@MockBean
	private WebClient webClient;

	@Test
	void testCall_ReturnsValidResponse ( ) {
		// Arrange
		String expectedResponseBody = "{\"name\":\"ditto\"}";
		HttpHeaders headers = new HttpHeaders ( );
		headers.add ( "Content-Type" , "application/json" );
		ResponseEntity < String > expectedResponse = new ResponseEntity <> ( expectedResponseBody , headers , HttpStatus.OK );

		RequestHeadersUriSpec requestHeadersUriSpec = Mockito.mock ( RequestHeadersUriSpec.class );
		ResponseSpec responseSpec = Mockito.mock ( ResponseSpec.class );

		when ( webClient.get ( ) ).thenReturn ( requestHeadersUriSpec );
		when ( requestHeadersUriSpec.uri ( anyString ( ) ) ).thenReturn ( requestHeadersUriSpec );
		when ( requestHeadersUriSpec.retrieve ( ) ).thenReturn ( responseSpec );
		when ( responseSpec.toEntity ( String.class ) ).thenReturn ( Mono.just ( expectedResponse ) );

		// Act
		HttpEntity < ? > response = pokemonResource.call ( );

		// Assert
		assertEquals ( HttpStatus.OK , ( ( ResponseEntity < ? > ) response ).getStatusCode ( ) );
		assertEquals ( expectedResponseBody , ( ( ResponseEntity < ? > ) response ).getBody ( ) );
		assertEquals ( "application/json" , Objects.requireNonNull ( ( ( ResponseEntity < ? > ) response ).getHeaders ( ).getContentType ( ) ).toString ( ) );
	}

}
