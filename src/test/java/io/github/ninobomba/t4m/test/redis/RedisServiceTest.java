package io.github.ninobomba.t4m.test.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest
public class RedisServiceTest {

	private static final int PORT = 6379;

	@Container
	static final GenericContainer < ? > REDIS_CONTAINER = new GenericContainer <> ( DockerImageName.parse ( "redis:7-alpine" ) )
			.withExposedPorts ( PORT );

	@DynamicPropertySource
	private static void register ( DynamicPropertyRegistry registry ) {
		registry.add ( "spring.redis.host" , REDIS_CONTAINER :: getHost );
		registry.add ( "spring.redis.port" , ( ) -> REDIS_CONTAINER.getMappedPort ( PORT ).toString ( ) );
	}

	//---
	@MockBean
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@Test
	void saveProductTest ( ) {
		String id = "1";
		String name = "soap";

		Product product = new Product ( );
		product.setId ( id );
		product.setName ( name );

		when ( productRepository.save ( product ) ).thenReturn ( product );

		Product savedProduct = productService.save ( product );

		verify ( productRepository ).save ( product );

		assertAll ( "Product Props" ,
				( ) -> Assertions.assertNotNull ( savedProduct ) ,
				( ) -> Assertions.assertEquals ( id , savedProduct.getId ( ) ) ,
				( ) -> Assertions.assertEquals ( name , savedProduct.getName ( ) )
		);
	}
}
