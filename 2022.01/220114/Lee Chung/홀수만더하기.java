package 연습장;

import java.util.Scanner;
import java.io.FileInputStream;

class Solution {
	public static void main(String args[]) throws Exception {

		Scanner scan = new Scanner(System.in);
		int testCaseCount;
		testCaseCount = scan.nextInt();

		for (int i = 0; i < testCaseCount; i++) {
			int sum = 0;
			for (int j = 0; j < 10; j++) {
				int eachTestCase = scan.nextInt();
				if (eachTestCase % 2 == 1)
					sum += eachTestCase;
			}
			System.out.printf("#%d %d\n", i + 1, sum);
		}
	}
}