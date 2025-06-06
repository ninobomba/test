package io.github.ninobomba.t4m.test.utils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.UUID;


public class IDGenerator {

	public static String nextId(){
		return UUID.randomUUID ( ).toString ();
	}

}
