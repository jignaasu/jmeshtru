import static java.lang.System.out;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.teeing;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;
import java.util.stream.IntStream;

public class FunWithVarAndTeeing {

	public static void main(String[] args) {
		int i = new Random().nextInt();
		out.println(i);
		var response = IntStream.rangeClosed(1, 100)
				.boxed()
				.collect(teeing(maxBy(Integer::compareTo), minBy(Integer::compareTo), (m, mi) -> new Object() {
					int max = m.get();
					int min = mi.get();
				}));

		var object = new Object() {
			int test = 123;
			String name = "Jaga";

		};
		System.out.println(object.test + " " + object.name);
		System.out.println(object.getClass());
		out.println(response.getClass());
		out.println(" Max: " + response.max);
		out.println(" Min: " + response.min);

		NumberFormat compactNumberInstance = NumberFormat.getCompactNumberInstance(Locale.forLanguageTag("nl_NL"), NumberFormat.Style.LONG);
		out.println(compactNumberInstance.format(1132132231.12312312313123));

	}
}
