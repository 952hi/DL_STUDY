package SWEXPERT.PS_D3_10726_이진수표현;

import java.util.Scanner;

public class 이진수표현_현일님_솔루션 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int inputTC = sc.nextInt();
		for (int i = 0; i < inputTC; i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			// 우선 출력
			System.out.printf("#%d ", (i + 1));

			// M이 N보다 작다면 항상 OFF
			if (N > M)
				System.out.print("OFF");
			else {
				int sum = 0;
				for(int j = 0;j<N;j++) sum+=Math.pow(2, j);
				
				if ((sum & M) == sum)
					System.out.print("ON");
				else
					System.out.print("OFF");
			}
		}
	}
}
