package io.github.ninobomba.t4m.test.redis;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash ( "product" )
public class Product {

	private String id;
	private String name;
	private double price;

}
