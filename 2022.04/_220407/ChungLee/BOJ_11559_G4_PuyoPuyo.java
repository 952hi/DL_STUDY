package _220407.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11559_G4_PuyoPuyo {

	// 밑으로 이동
	static void gravity(char[][] bd) {
		boolean anyMore = false;
		//왼쪽 열부터 조사
		for (int i = 0; i < 6; i++) {
			//가장 아래 행부터 위로 탐색
			for (int j = 11; j >= 0; j--) {
				anyMore = false;
				//.을 발견했을 때 
				if (bd[j][i] == '.') {
					//그 위로 뿌요가 있다면 .으로 옮겨줌
					for (int k = j - 1; k >= 0; k--) {
						if (bd[k][i] != '.') {
							bd[j][i] = bd[k][i];
							bd[k][i] = '.';
							anyMore = true;
							break;
						}
					}
					//가장 윗 행까지 조사했는데 뿌요가 없으면 다음 열로 넘어가기 위해 break
					if (!anyMore)
						break;
				}
			}
		}
	}

	static boolean breaker(char[][] bd, int y, int x) {
		q.add(new int[] { y, x });
		char sameColor = bd[y][x];
		boolean[][] visit = new boolean[12][6];
		visit[y][x] = true;
		savePt[0][0] = y;
		savePt[0][1] = x;
		int cnt = 1;
		while (!q.isEmpty()) {
			int[] crnt = q.poll();
			for (int i = 0; i < 3; i++) {
				int nY = crnt[0] + dydx[i][0];
				int nX = crnt[1] + dydx[i][1];
				if (nY < 0 || nX < 0 || nX == 6)
					continue;

				if (bd[nY][nX] == sameColor && !visit[nY][nX]) {
					visit[nY][nX] = true;
					savePt[cnt][0] = nY;
					savePt[cnt][1] = nX;
					cnt++;
					q.add(new int[] { nY, nX });
				}
			}
		}
		if (cnt >= 4) {
			for (int i = 0; i < cnt; i++) {
				bd[savePt[i][0]][savePt[i][1]] = '.';
			}
			return true;
		} else {
			return false;
		}
	}

	static void checker(char[][] bd) {
		int chainCnt = 0;

		boolean hit;
		// 한번은 무조건 실행되어야하기 때문에
		do {
			hit = false;
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (bd[i][j] != '.')
						//한번이라도 파괴할 수 있다면 한 번 더 반복
						if (breaker(bd, i, j))
							hit = true;
				}
			}
			if (hit) {
				chainCnt++;
			}
			//중력 적용
			gravity(bd);
		} while (hit == true);
		System.out.println(chainCnt);
	}

	static int[][] savePt = new int[72][2];
	static Queue<int[]> q = new LinkedList<int[]>();
	static int[][] dydx = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] bd = new char[12][6];
		for (int i = 0; i < 12; i++) {
			bd[i] = br.readLine().toCharArray();
		}
		checker(bd);
	}
}
