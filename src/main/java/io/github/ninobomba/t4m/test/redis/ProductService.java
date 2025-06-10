package io.github.ninobomba.t4m.test.redis;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	public ProductService ( ProductRepository productRepository ) {
		this.productRepository = productRepository;
	}

	public Product save ( Product product ) {
		return productRepository.save ( product );
	}

	public Product get ( String id ) {
		return productRepository.findById ( id ).orElse ( null );
	}
}
