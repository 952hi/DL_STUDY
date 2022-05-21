package _220506.ChungLee;


import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class BOJ_2098_G1_외판원순회 {
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
	
	static int dfs(int city, int visitBit) {
		// 모든 도시 방문했다면
		if (visitBit == (1 << N) - 1) {
			if(bd[city][0] == 0)
				return intmax;
			//현재 도시에서 0번째 도시의 이동 거리를 return
			return bd[city][0];
		}
		
		// dp값이 존재할 때
		if (dp[city][visitBit] != intmax) {
			return dp[city][visitBit];
		}

		for (int i = 0; i < N; i++) {
			//방문하지 않은 도시이고 연결되어 있을 때
			if ((visitBit & (1 << i)) == 0 && bd[city][i] != 0) {
				dp[city][visitBit] = Math.min(dp[city][visitBit], dfs(i, visitBit | (1 << i)) + bd[city][i]);
			}
		}
		return dp[city][visitBit];
	}

	static int N;
	static int bd[][];
	static int dp[][];
	static int intmax = 11000000;

	public static void main(String[] args) {
		Reader r = new Reader();
		N = r.nextInt();
		bd = new int[N][N];
		dp = new int[N][(1 << N) - 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bd[i][j] = r.nextInt();
			}
		}
		//dp배열 초기화
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], intmax);
		}
		System.out.println(dfs(0, 1));
	}
}
