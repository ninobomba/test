package io.github.ninobomba.t4m.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Slf4j
@Configuration
public class RestClientConfiguration {

	@Bean ( name = "postRestClient" )
	public RestClient postRestClient ( ) {
		String postUri = "https://jsonplaceholder.typicode.com";
		log.info ( "PostsBeanRestClientConfiguration::postRestClient - Initializing RestClient with base URL: {}" , postUri );
		return RestClient.builder ( ).baseUrl ( postUri.concat ( "/posts" ) ).build ( );
	}

}
