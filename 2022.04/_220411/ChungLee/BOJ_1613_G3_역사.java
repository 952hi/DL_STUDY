package _220411.ChungLee;

import java.io.*;
import java.util.*;

import com.sun.org.apache.regexp.internal.recompile;

public class BOJ_1613_G3_역사 {
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

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = r.nextInt();
		int k = r.nextInt();
		int[][] hist = new int[n + 1][n + 1];

		// 데이터 입력
		for (int i = 0; i < k; i++) {
			int first = r.nextInt();
			int last = r.nextInt();

			// 먼저 시작하는 역사 사건에는 -1 입력
			hist[first][last] = -1;
			// 대칭 위치에는 후에 시작하는 역사이기 때문에 1 입력
			hist[last][first] = 1;

		}
		// m : 경유지
		for (int m = 1; m <= n; m++) {
			// i : 출발지
			for (int i = 1; i <= n; i++) {
				// 경유지와 출발지가 같은 사건, 관련성을 알 수 없는 사건은 확인할 필요 없음
				if (i == m || hist[i][m] == 0)
					continue;
				// j : 도착지
				for (int j = 1; j <= n; j++) {
					// 출발지와 도착지가 같은 경우, 경유지와 도착지가 같은 경우,
					// 경유지와 도착지 사이의 관련성을 알 수 없는 사건은 확인할 필요 없음
					if (i == j || m == j || hist[m][j] == 0)
						continue;
					// 출발 역사, 경유 역사, 도착 역사가 일관되게 이어져있을 경우에 인과성을 추가
					else if (hist[i][m] == hist[m][j]) {
						hist[i][j] = hist[i][m];
					}
				}
			}
		}
		int s = r.nextInt();
		for (int i = 0; i < s; i++) {
			sb.append(hist[r.nextInt()][r.nextInt()]).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
