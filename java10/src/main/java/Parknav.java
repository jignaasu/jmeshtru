import static java.lang.System.out;

import java.util.Arrays;
import java.util.BitSet;

public class Parknav {

	public static void main(String[] args) {
		Solution.solve(1, new int[]{2, 4, 5, 6, 7, 9, 11, 12});
		Solution.solve(2, new int[]{2, 4, 5, 6, 7, 9, 11, 12});
		Solution.solve(1, new int[]{1, 2, 3, 4, 5});
		Solution.solve(10, new int[]{1, 2, 3, 4, 5});
		Solution.solve(3, new int[]{1, 2, 3, 4, 5});
		Solution.solve(10, new int[]{1, 15, 30, 40, 50});
		Solution.solve(3, new int[]{1, 12, 15, 30, 40, 50});
		Solution.solve(4, new int[]{15, 30, 40, 50});
		Solution.solve(15, new int[]{15, 30, 40, 50});
		Solution.solve(18, new int[]{1, 15, 30, 40, 50});
		Solution.solve(8, new int[]{1, 15, 30, 40, 50});
		Solution.solve(6, new int[]{1, 15, 30, 40, 50});
		Solution.solve(9, new int[]{1, 15, 30, 40, 50});
	}

	// Previous bit + cr >= i - 1
//	static int solveWithoutBitSet(int cr, int[] pr) {
//		int num = 0;
//		BitSet bitSet = new BitSet(pr.length);
//		for (int i = 1; i < pr.length; i++) {
//			if (pr[i] - cr <= pr[i - 1]) {
//				int previousSetBit = bitSet.previousSetBit(i);
//				if (previousSetBit < 0 || pr[previousSetBit] + cr < pr[i])
//					bitSet.set(i);
//
//				if (previousSetBit > i - 1 && pr[previousSetBit] + cr >= pr[i - 1])
//					bitSet.clear(i - 1);
//			} else {
//				bitSet.set(i - 1);
//				bitSet.clear(i);
//			}
//		}
//
//		int lastSetBit = bitSet.previousSetBit(pr.length - 1);
//		if (pr[lastSetBit] + cr < pr[pr.length - 1])
//			bitSet.set(pr.length - 1);
//
//		out.println("PR : " + Arrays.toString(pr));
//		out.println("CR : " + cr);
//		out.println("Num: " + bitSet);
//		out.println("=======================================================");
//		out.println();
//
//		return num;
//	}

	// Previous bit + cr >= i - 1
//	static int solve(int cr, int[] pr) {
//		int num = 0;
//		BitSet bitSet = new BitSet(pr.length);
//		for (int i = 1; i < pr.length; i++) {
//			if (pr[i] - cr <= pr[i - 1]) {
//				int previousSetBit = bitSet.previousSetBit(i);
//				if (previousSetBit < 0 || pr[previousSetBit] + cr < pr[i])
//					bitSet.set(i);
//
//				if (previousSetBit > i - 1 && pr[previousSetBit] + cr >= pr[i - 1])
//					bitSet.clear(i - 1);
//			} else {
//				bitSet.set(i - 1);
//				bitSet.clear(i);
//			}
//		}
//
//		int lastSetBit = bitSet.previousSetBit(pr.length - 1);
//		if (pr[lastSetBit] + cr < pr[pr.length - 1])
//			bitSet.set(pr.length - 1);
//
//		out.println("PR : " + Arrays.toString(pr));
//		out.println("CR : " + cr);
//		out.println("Num: " + bitSet);
//		out.println("=======================================================");
//		out.println();
//
//		return num;
//	}

	static int solve2(int cr, int[] pr) {
		int num = 0;
		if (pr[pr.length - 1] <= cr)
			num = 1;
		else {

			int minDiff = Integer.MAX_VALUE;
			for (int i = 1; i < pr.length; i++) {
				int diff = pr[i] - pr[i - 1];
				if (minDiff > diff)
					minDiff = diff;
			}

			if (minDiff > cr)
				num = pr.length;
			else {

				int totalAngle = pr[pr.length - 1];
				int maxCamAngle = cr;

				num = (int) Math.rint(totalAngle / (cr * 2.0));

				if (num > pr.length)
					num = pr.length;
			}
		}

		out.println("PR : " + Arrays.toString(pr));
		out.println("CR : " + cr);
		out.println("Num: " + num);
		out.println("=======================================================");
		out.println();

		return num;
	}

	static int solve1(int cr, int[] pr) {
		if (pr[0] > cr)
			return pr.length;

		if (pr[pr.length - 1] <= cr)
			return 1;

		BitSet cam = new BitSet(pr.length);
		cam.set(0, pr.length);


		for (int i = 0; i < pr.length - 1; i++) {
			if (i == 0) {
				if (pr[1] <= pr[0] + cr)
					cam.clear(0);

				continue;
			}

			if (pr[i] - cr <= pr[i - 1]) {
				if (i > 1) {
					int previousSetBit = cam.previousSetBit(i);
					if (pr[previousSetBit] + cr >= pr[i - 1])
						continue;
				}

				cam.clear(i - 1);
				cam.set(i);
			}

			if (pr[i] + cr >= pr[i + 1]) {
				cam.clear(i + 1);
				cam.set(i);
			}
		}

		out.println("PR : " + Arrays.toString(pr));
		out.println("CR : " + cr);
		out.println("Num: " + cam);
		out.println("=======================================================");
		out.println();

		return cam.cardinality();
	}
}
