import java.util.Arrays;

public class BigIntegerTest {

	public static void main(String[] args) {

		String sysId = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println(Arrays.toString(sysId.getBytes()));
		System.out.println(sysId.hashCode());
		System.out.println(Arrays.toString(unhash(sysId.hashCode()).getBytes()));
	}

	/**
	 * @link {https://blogs.oracle.com/darcy/unhashing-a-string}
	 */
	public static String unhash(int target) {
		StringBuilder answer = new StringBuilder();
		if (target < 0) {
			// String with hash of Integer.MIN_VALUE, 0x80000000
			answer.append("\\u0915\\u0009\\u001e\\u000c\\u0002");
			if (target == Integer.MIN_VALUE)
				return answer.toString();
			// Find target without sign bit set
			target = target & Integer.MAX_VALUE;
		}
		unhash0(answer, target);
		return answer.toString();
	}
	private static void unhash0(StringBuilder partial, int target) {
		int div = target / 31;
		int rem = target % 31;
		if (div <= Character.MAX_VALUE) {
			if (div != 0)
				partial.append((char)div);
			partial.append((char)rem);
		} else {
			unhash0(partial, div);
			partial.append((char)rem);
		}
	}
}
