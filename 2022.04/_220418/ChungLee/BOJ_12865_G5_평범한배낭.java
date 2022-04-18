package _220418.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;

public class BOJ_12865_G5_평범한배낭 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferLeft = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() {
			if (bufferLeft == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferLeft = 0, bfs);
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferLeft++];
		}

		int nextInt() {
			int n = 0;
			byte b = read();
			while (b <= ' ')
				b = read();
			boolean neg = (b == '-');
			if (neg)
				b = read();
			do
				n = n * 10 + b - '0';
			while ('0' <= (b = read()) && b <= '9');
			if (neg)
				return -n;
			return n;
		}
	}

	static int max = 0;
	static int K, N;
	static int[][] items;

	public static void main(String[] args) {
		Reader r = new Reader();
		N = r.nextInt();
		K = r.nextInt();
		int[][] dp = new int[N][K + 1];
		items = new int[N][2];
		int weight = r.nextInt(), value = r.nextInt();
		int cnt = 0, max = 0;
		for (int i = 1; i <= K; i++) {
			if (i >= weight) {
				dp[0][i] = value;
			}
		}
		for (int n = 1; n < N; n++) {
			weight = r.nextInt();
			value = r.nextInt();
			for (int j = 1; j <= K; j++) {
				
				if (j >= weight) {
					
					if (dp[n - 1][j] < value) {
						dp[n][j] = value;
					}else if(dp[n][j] > dp[n-1][j])
						continue;
					else{
						dp[n][j] = dp[n-1][j];
					}
				}
				//현재 무게보다 작은 곳을 확인할 때 물건이 있으
				if(dp[n-1][j] != 0) {
					if(j + weight <= K && dp[n-1][j+weight] < value + dp[n-1][j]) {
						dp[n][j+weight] = value + dp[n-1][j];
					}
				}
			}
		}
		for (int i = 1; i <= K; i++) {
			max = Math.max(dp[N - 1][i], max);
		}
		System.out.println(max);
	}

}
