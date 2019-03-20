import java.util.Arrays;
import java.util.BitSet;

/**
 * @author abhijith.nagarajan
 */
public class Solution {

	/**
	 *
	 * <p>
	 *     Algorithm tries to optimize the installation by finding the highest parking space which spans more spaces. We
	 *     use {@code BitSet} implementation available in JDK.
	 *
	 *     There were 2 reasons to use BitSet.
	 *     <ol>
	 *         <li>Easy to navigate to previously installed spot using {@code BitSet.previousSetBit(i)}</li>
	 *         <li>If need be, {@code BitSet.toString()} will also give the spots at which camera can be installed</li>
	 *     </ol>
	 *
	 *     The algorithm has O(n log n) complexity. One iteration of the parking spaces and operations {@code BitSet}
	 *     offers near constant time operation for small sizes and at worse <code>log n</code> for bigger arrays.
	 * </p>
	 *
	 *
	 */
	static int solve(int cr, int[] ps) {
		if (cr < 0)
			throw new IllegalArgumentException("Invalid camera range. Expected: > 1, Actual: " + cr);

		if (ps == null || ps.length == 0)
			throw new IllegalArgumentException("Invalid Parking spaces. Expected: Non empty array, Actual: " + Arrays.toString(ps));

		BitSet bitSet = new BitSet(ps.length);
		for (int i = 1; i < ps.length; i++) {
			if (ps[i] - cr <= ps[i - 1]) {
				int previousSetBit = bitSet.previousSetBit(i);
				if (previousSetBit < 0 || ps[previousSetBit] + cr < ps[i])
					bitSet.set(i);

				if (previousSetBit > i - 1 && ps[previousSetBit] + cr >= ps[i - 1])
					bitSet.clear(i - 1);
			} else {
				bitSet.set(i - 1);
				bitSet.clear(i);
			}
		}

		int lastSetBit = bitSet.previousSetBit(ps.length - 1);
		if (ps[lastSetBit] + cr < ps[ps.length - 1])
			bitSet.set(ps.length - 1);

		return bitSet.cardinality();
	}
}
