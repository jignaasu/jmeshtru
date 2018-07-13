package org.jmeshtru.java10;

import static java.lang.System.out;

import java.util.List;

public class Collection {

	public static void main(String[] args) {
		var objectList = List.of("abc", 1, "def", "0456", new Object() {
			int a = 1;

			@Override
			public String toString() {
				return "a --> " + a;
			}
		});
		out.println(objectList);

		for (var object : objectList) {
			out.print(object);

		}
	}
}
