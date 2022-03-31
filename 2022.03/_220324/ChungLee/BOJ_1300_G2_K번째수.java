package _220325.ChungLee;

import java.util.Scanner;

public class BOJ_1300_G2_K번째수 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		long K = s.nextInt();

		long left = 1;
		long right = K;
		long ans = 0;
		while (true) {
			if (left > right)
				break;
			long mid = (left + right) / 2;

			long cnt = 0;
			for (int i = 1; i <= N; i++) {
				cnt += Math.min(mid / i, N);
			}
			if (cnt >= K) {
				ans = mid;
				right = mid - 1;
			} else if (cnt < K) {
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}
}
