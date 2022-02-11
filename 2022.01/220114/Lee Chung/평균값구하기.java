package 연습장;

import java.util.Scanner;

class Solution {
	public static void main(String args[]) throws Exception {
		Scanner scan = new Scanner(System.in);
		int testCaseCount;
		testCaseCount = scan.nextInt();

		for (int i = 0; i < testCaseCount; i++) {
			float sum = 0;
			for (int j = 0; j < 10; j++) {
				int eachTestCase = scan.nextInt();
				sum += eachTestCase;
			}
			int asnwer = Math.round(sum / 10);
			System.out.printf("#%d %d\n", i + 1, asnwer);
		}
	}
}