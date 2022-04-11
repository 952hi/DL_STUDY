package _220411.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class BOJ_11054_G3_가장긴바이토닉부분수열 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() {
			if (bufferPos == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferPos = 0, bfs);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];
		}

		int nextInt() {
			int rtn = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				rtn = rtn * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -rtn;
			return rtn;
		}
	}

	public static void main(String[] args) {
		Reader r = new Reader();
		int N = r.nextInt();

		int[] arr = new int[N];
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		int[] dp2 = new int[N];
		Arrays.fill(dp2, 1);
		
		for (int i = 0; i < N; i++) {
			arr[i] = r.nextInt();
		}

		for (int i = 1; i < N; i++) {
			int max = 0;
			for (int j = 0; j < i; j++) {
				if(arr[i] > arr[j] && dp[i]<dp[j]+1) {
					dp[i] = dp[j] + 1;
					max = arr[j];
				}
			}
		}
		for (int i = N - 2; i >= 0; i--) {
			int max = 0;
			for (int j = N - 1; j > i; j--) {
				if (arr[i] > arr[j] && dp2[i]<dp2[j]+1) {
					dp2[i] = dp2[j] + 1;
					max = arr[j];
				}
			}
		}
		int max = 0;
		for(int i =0; i <N;i++) {
			max = Math.max(max, dp[i]+dp2[i] - 1);
		}
		System.out.println(max);
	}
	
}
