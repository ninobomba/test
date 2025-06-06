package io.github.ninobomba.t4m.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

	@Bean
	WebClient webclient(){
		return WebClient.builder().build();
	}

}
