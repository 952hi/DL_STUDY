package _220325.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2206_G4_벽부수고이동하기 {
	static class yx implements Comparable<yx> {
		int y;
		int x;
		int bkW; // breakWall
		int V;

		public yx(int y, int x, int bkW, int V) {
			super();
			this.y = y;
			this.x = x;
			this.bkW = bkW;
			this.V = V;
		}

		@Override
		public int compareTo(yx o) {
			return this.V - o.V;
		}
	}

	static char board[][];
	static int[][] dist;
	static int N, M;
	static PriorityQueue<yx> pq = new PriorityQueue<>();
	static int[][] dydx = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우

	static boolean dijkstra() {
		dist[0][0] = 1;
		dist[0][1] = 1;
		int maxBkW = 1;
		pq.add(new yx(1, 1, 0, 1));
		while (!pq.isEmpty()) {
			yx crnt = pq.poll();
			int crntY = crnt.y;
			int crntX = crnt.x;
			int crntbkW = crnt.bkW;
			int V = crnt.V;
			if (crntY == N && crntX == M) {
				System.out.println(Math.min(dist[N * M - 1][0], dist[N * M - 1][1]));
				return true;
			}
			// 방문하려는데 이미 더 작은 값이 저장되어 있다면
			if (dist[(crntY-1) * M + (crntX-1)][crntbkW] < V) {
				continue;
			}
			// 사방탐색
			for (int i = 0; i < 4; i++) {
				int nextY = crntY + dydx[i][0];
				int fdY = nextY - 1;
				int nextX = crntX + dydx[i][1];
				int fdX = nextX - 1;
				int nextV = V + 1;
				// 외벽이면
				if (board[nextY][nextX] == '2')
					continue;
				if (dist[fdY * M + fdX][crntbkW] <= V)
					continue;
				// 벽이면
				if (board[nextY][nextX] == '1') {
					// 벽통과 기회 존재 && 그 칸 값을 전보다 단축시킬 수 있을 때
					if (crntbkW == 0 && nextV < dist[fdY * M + fdX][1]) {
						dist[fdY * M + fdX][1] = nextV;
						pq.add(new yx(nextY, nextX, crntbkW + 1, nextV));
					} else
						continue;
				}
				// 길이라면
				else if (nextV < dist[fdY * M + fdX][crntbkW]) {
					dist[fdY * M + fdX][crntbkW] = nextV;
					pq.add(new yx(nextY, nextX, crntbkW, nextV));
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N + 2][M + 2];
		dist = new int[N * M][2];
		for (int i = 0; i < N * M; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i <= N + 1; i++) {
			if (i == 0 || i == N + 1) {
				Arrays.fill(board[i], '2');
				continue;
			}
			char[] nl = br.readLine().toCharArray();
			for (int j = 0; j <= M + 1; j++) {
				if (j == 0 || j == M + 1) {
					board[i][j] = '2';
					continue;
				}
				board[i][j] = nl[j - 1];
			}
		}
		if(!dijkstra())
			System.out.println(-1);
	}
}
