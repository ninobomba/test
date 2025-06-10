package io.github.ninobomba.t4m.test.utils;

import java.util.UUID;


public class IDGenerator {

	public static String nextId ( ) {
		return UUID.randomUUID ( ).toString ( );
	}

}
