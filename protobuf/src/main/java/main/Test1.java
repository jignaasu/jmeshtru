package main;

import java.io.IOException;

import jmeshtru.protobuf.Sample;

public class Test1 {

	public static void main(String[] args) throws IOException {
		Sample sample = Sample.newBuilder()
				.setMessage("Jai Sri Rama")
				.build();

		Sample.parseFrom(sample.toByteArray())
		.writeTo(System.out);
	}
}
