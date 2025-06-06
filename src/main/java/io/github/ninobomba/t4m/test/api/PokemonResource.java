package io.github.ninobomba.t4m.test.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PokemonResource {

	private final WebClient webClient;

	@GetMapping ("/pokemon")
	public HttpEntity < ? > call() {
		var response = webClient.get()
				.uri( "https://pokeapi.co/api/v2/pokemon/ditto" )
				.retrieve()
				.toEntity( String.class )
				.block();
		log.info("pokemon response: {}", response );
		return response;
	}

}
