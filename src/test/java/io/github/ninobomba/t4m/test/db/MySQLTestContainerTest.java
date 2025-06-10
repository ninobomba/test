package io.github.ninobomba.t4m.test.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Collections;

@AutoConfigureWebTestClient
@Testcontainers
@SpringBootTest ( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class MySQLTestContainerTest {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	private IUserRepository iUserRepository;

	static final MySQLContainer MY_SQL_CONTAINER;

	static {
		MY_SQL_CONTAINER = new MySQLContainer ( "mysql:8.0.38" );
		MY_SQL_CONTAINER.withTmpFs ( Collections.singletonMap ( "/var/lib/mysql" , "rw" ) );
		MY_SQL_CONTAINER.withDatabaseName ( "demo" );
		MY_SQL_CONTAINER.start ( );
	}

	@DynamicPropertySource
	static void configureTestProperties ( DynamicPropertyRegistry registry ) {
		registry.add ( "spring.datasource.url" , MY_SQL_CONTAINER :: getJdbcUrl );
		registry.add ( "spring.datasource.username" , MY_SQL_CONTAINER :: getUsername );
		registry.add ( "spring.datasource.password" , MY_SQL_CONTAINER :: getPassword );
		registry.add ( "spring.jpa.hibernate.ddl-auto" , ( ) -> "create" );
	}

	@BeforeEach
	public void beforeEach ( ) {
		UserEntity entity = new UserEntity ( );
		entity.setEmail ( "fernando@demo.org" );
		entity.setFirstName ( "Fernando" );
		entity.setLastName ( "farfan" );
		entity.setPassword ( "password" );
		entity.setUsername ( "abc" );
		iUserRepository.save ( entity );
	}

	@AfterEach
	public void afterEach ( ) {
		iUserRepository.deleteAll ( );
	}

	@Test
	void getEntity ( ) {
		webTestClient.get ( )
				.uri ( "/users" )
				.exchange ( )
				.expectHeader ( )
				.contentType ( MediaType.APPLICATION_JSON )
				.expectStatus ( )
				.is2xxSuccessful ( )
				.expectBodyList ( UserEntity.class )
				.consumeWith ( listOfObject -> {
					var list = listOfObject.getResponseBody ( );
					Assertions.assertNotNull ( list );
					Assertions.assertEquals ( 1 , list.size ( ) );
				} );
	}
}
