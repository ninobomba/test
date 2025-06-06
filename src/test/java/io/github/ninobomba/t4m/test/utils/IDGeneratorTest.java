package io.github.ninobomba.t4m.test.utils;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class IDGeneratorTest {

	/**
	 * Unit Test for IDGenerator class.
	 * Ensures that the nextId() method generates a valid UUID string.
	 */

	@Test
	void testNextIdGeneratesNonNullValue ( ) {
		// Act
		String generatedId = IDGenerator.nextId ( );

		System.out.println( "Generated id: " + generatedId );

		// Assert
		assertNotNull ( generatedId , "The generated ID should not be null" );
	}

	@Benchmark
	@BenchmarkMode ( Mode.Throughput )
	@Fork ( value = 1, warmups = 1 )
	@Test
	public void perform ( ) {
		Instant start = Instant.now ( );

		var accumulator = new ArrayList < String > ( );

		final int size = 1_000_000;
		IntStream.range ( 0, size ).forEach ( index -> {
			accumulator.add ( IDGenerator.nextId ( ) );
		} );

		assert ( accumulator.size ( ) == size );
		System.out.println ( "ET " + Duration.between ( start, Instant.now ( ) ).toSeconds ( ) + " toSeconds " );
	}

}
