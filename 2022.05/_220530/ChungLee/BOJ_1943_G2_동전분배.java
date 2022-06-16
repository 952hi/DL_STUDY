package _220530.ChungLee;

import java.io.*;
import java.util.*;

public class BOJ_1943_G2_동전분배 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		//3번 반복
		for (int l = 0; l < 3; l++) {
			int N = Integer.parseInt(br.readLine());
			int[][] coinz = new int[N + 1][2];
			boolean[] dp = new boolean[50001];

			int total = 0;

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int value = Integer.parseInt(st.nextToken());
				int quantity = Integer.parseInt(st.nextToken());
				coinz[i][0] = value;
				coinz[i][1] = quantity;
				total += value * quantity;
				for (int j = 1; j <= quantity; j++) {
					dp[value * j] = true;
				}
			}

			if (total % 2 == 1) {
				sb.append(0).append("\n");
				continue;
			} else if (dp[total / 2]) {
				sb.append(1).append("\n");
				continue;
			}

			dp[0] = true;

			for (int i = 1; i <= N; i++) {
				int v = coinz[i][0];
				int q = coinz[i][1];

				for (int j = total / 2; j >= v; j--) {
					if (dp[j - v]) {

						for (int k = 1; k <= q; k++) {
							if (j - v + v * k > total / 2)
								break;
							dp[j - v + v * k] = true;
						}
					}
				}
			}
			sb.append(dp[total / 2] ? 1 : 0).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}