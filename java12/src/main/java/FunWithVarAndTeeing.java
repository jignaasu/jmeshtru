import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.teeing;

import java.util.stream.IntStream;

public class FunWithVarAndTeeing {

	public static void main(String[] args) {
		var response = IntStream.rangeClosed(1, 100)
				.boxed()
				.collect(teeing(maxBy(Integer::compareTo), minBy(Integer::compareTo), (m, mi) -> new Object() {
					int max = m.get();
					int min = mi.get();
				}));

		System.out.println(response);
		System.out.println(" Max: " + response.max);
		System.out.println(" Min: " + response.min);

	}
}
