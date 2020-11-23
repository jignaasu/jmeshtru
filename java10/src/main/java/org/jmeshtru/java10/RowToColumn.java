package org.jmeshtru.java10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RowToColumn {

	public static void main(String[] args) {
		List<List<String>> csvData = new ArrayList<>();
		csvData.add(Arrays.asList("fileName", "abc", "def"));
		csvData.add(Arrays.asList("card", "123", "456"));

		System.out.println(transpose(csvData));
	}

	static List<List<String>> transpose(List<List<String>> data) {
		ArrayList<List<String>> transposedData = new ArrayList<>(data.get(0).size());
		Collections.fill(transposedData, Collections.emptyList());
		for (int i = 0; i < transposedData.size(); i++) {
			for (List<String> row : data) {
				transposedData.get(i).add(row)
			}
		}
		return transposedData;
	}
}
