package io.github.ninobomba.t4m.test.redis;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository < Product, String > {
}
