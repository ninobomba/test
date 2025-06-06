package io.github.ninobomba.t4m.test.api;

import io.github.ninobomba.t4m.test.api.dto.PostDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@RestController
@RequestMapping ( "/posts" )
public class TodoResource {

	private static final String POST_URI_TEMPLATE = "/{id}";

	@Qualifier ( "postRestClient" )
	private final RestClient restClient;

	public TodoResource ( RestClient restClient ) {
		this.restClient = restClient;
	}

	@GetMapping ( "/{id}" )
	public HttpEntity < ? > findById ( @PathVariable Integer id ) {
		var postDto = getPostById ( id ).orElse ( null );
		if ( postDto != null ) {
			return ResponseEntity.ok ( postDto );
		} else {
			return ResponseEntity.notFound ( ).build ( );
		}
	}

	public Optional < PostDto > getPostById ( Integer id ) {
		return Optional.ofNullable ( restClient.get ( ).uri ( POST_URI_TEMPLATE , id ).retrieve ( ).body ( PostDto.class ) );
	}
}
