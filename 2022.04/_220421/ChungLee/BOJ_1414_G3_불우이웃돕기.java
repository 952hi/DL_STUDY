package _220421.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_1414_G3_불우이웃돕기 {
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

		char nextChar() {
			byte c = read();
			while (c <= ' ')
				c = read();
			return (char) c;
		}
	}

	static int[][] bd;
	static int sum = 0, sub = 0;

	public static void main(String[] args) {
		Reader r = new Reader();
		int N = r.nextInt();
		bd = new int[N][N];
		// 보드 연결되었는지 확인
		int[][] chckbd = new int[N][N];
		int[] visit = new int[N];
		boolean[] boolchk = new boolean[N];
		char input = 0;
		PriorityQueue<int[]> q = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		Arrays.fill(visit, Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bd[i][j] = r.nextChar();
				if (bd[i][j] == '0') {
					bd[i][j] = 0;
				} else if (bd[i][j] >= 'a') {
					sum += bd[i][j] = bd[i][j] - 'a' + 1;
				} else {
					sum += bd[i][j] = bd[i][j] - 'A' + 27;
				}
			}
		}
		// 대칭 그래프로 변경하기
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				// 같은 자리 또는 이어져있지 않는 경우 패스
				if (i == j)
					continue;
				// 둘 다 이어진 경우
				if (bd[j][i] != 0 && bd[i][j] != 0) {
					chckbd[i][j] = Math.min(bd[i][j], bd[j][i]);
					chckbd[j][i] = chckbd[i][j];
				}
				// 한쪽만 이어져있는 경우
				else if (bd[i][j] != 0) {
					chckbd[i][j] = bd[i][j];
					chckbd[j][i] = chckbd[i][j];
				} else if (bd[j][i] != 0) {
					chckbd[i][j] = bd[j][i];
					chckbd[j][i] = chckbd[i][j];
				}
			}
		}
		q.add(new int[] { 0, 0 });
		visit[0] = 0;
		while (!q.isEmpty()) {
			int[] crnt = q.poll();
			int crntN = crnt[0];
			int crntV = crnt[1];
			if (visit[crntN] < crntV)
				continue;
			boolchk[crntN] = true;
			for (int i = 0; i < N; i++) {
				if (i == crntN || chckbd[crntN][i] == 0 || boolchk[i])
					continue;
				if (visit[i] > chckbd[crntN][i]) {
					visit[i] = chckbd[crntN][i];
					q.add(new int[] { i, chckbd[crntN][i] });
				}
			}
		}
		// 연결되어 있지 않는 경우 찾기 && 최소값 찾기
		for (int i = 0; i < N; i++) {
			sub += visit[i];
			if (visit[i] == Integer.MAX_VALUE) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(sum - sub);
	}
}
