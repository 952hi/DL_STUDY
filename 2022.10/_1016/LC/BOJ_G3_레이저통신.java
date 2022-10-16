package _1016.LC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1. pq를 사용해 가장 꺽인 횟수가 적은 것부터 처리를 해주어서 거울이 적게 필요한 경로를 탐색하려고 했음.
// 2. 그런데 꺽인 횟수가 같은 것 경로 중에서 더 안좋은 쪽으로 가는 경로가 최적 경로를 침범해서 최적 경로로 가야하는 경우가 무시되는 경우가 발생
// 3. 따라서 isVisit을 boolean으로 처리하는 것이 아닌 int형으로 더 적은 꺽인 횟수를 저장. 더 적은 횟수로 해당 자리를 방문가능하면 다시 방문하고
// 이전에 해당 자리로부터 시작한 경우는 제외 처리
// 4. 
public class BOJ_G3_레이저통신 {
	static char[][] bd;
	static int[][] SE = new int[2][2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 상,우,하,좌
		int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		// 꺽인 수가 적은 것부터 확인
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);
		bd = new char[H][W];
		SE[0][0] = -1;
		char[] tmp;
		for (int i = 0; i < H; i++) {
			tmp = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				bd[i][j] = tmp[j];
				if (bd[i][j] == 'C') {
					if (SE[0][0] == -1) {
						SE[0][0] = i;
						SE[0][1] = j;
					} else {
						SE[1][0] = i;
						SE[1][1] = j;
					}
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		int[][] isVisit;
		int nextY, nextX;
		int[] crnt;
		
		//시작 C의 네 방향을 pq에 입력
		for (int i = 0; i < 4; i++) {
			nextY = SE[0][0] + dydx[i][0];
			nextX = SE[0][1] + dydx[i][1];
			
			//바깥 제외
			if (nextY < 0 || nextY == H || nextX < 0 || nextX == W)
				continue;
			//벽 제외
			if (bd[nextY][nextX] == '*')
				continue;
			// 시작 C의 상,하,좌,우의 y, x, 현재 방향 , 꺽인 수
			pq.add(new int[] { nextY, nextX, i, 0 });
			// 방문 확인 및 꺽인 수 저장하는 배열 선언
			isVisit = new int[H][W];

			// -1로 초기화
			for (int j = 0; j < H; j++)
				Arrays.fill(isVisit[j], -1);

			//시작 C위치와 시작 C의 상,하,좌,우 시작 위치를 방문 처리
			isVisit[SE[0][0]][SE[0][1]] = 0;
			isVisit[nextY][nextX] = 0;

			while (!pq.isEmpty()) {
				crnt = pq.poll();

				// pq에 저장된 값보다 해당 자리에 더 짧은 값이 저장되어있는 경우
				if (isVisit[crnt[0]][crnt[1]] != crnt[3])
					continue;
				//목적 C에 도착한 경우
				if (crnt[0] == SE[1][0] && crnt[1] == SE[1][1]) {
					answer = Math.min(crnt[3], answer);
					break;
				}
				// 사방탐색
				for (int j = 0; j < 4; j++) {
					nextY = crnt[0] + dydx[j][0];
					nextX = crnt[1] + dydx[j][1];

					if (nextY < 0 || nextY == H || nextX < 0 || nextX == W)
						continue;
					if (bd[nextY][nextX] == '*')
						continue;
					// 이미 방문한 경우
					if (isVisit[nextY][nextX] != -1) {
						// 꺽여져서 거울을 사용하더라도 더 짧게 갈 수 있을 때
						if (crnt[2] != j && isVisit[nextY][nextX] > crnt[3] + 1) {
							isVisit[nextY][nextX] = crnt[3] + 1;
							pq.add(new int[] { nextY, nextX, j, crnt[3] + 1 });
						}
						// 꺽여지지 않고 더 짧게 갈 수 있을 때
						else if (crnt[2] == j && isVisit[nextY][nextX] > crnt[3]) {
							isVisit[nextY][nextX] = crnt[3];
							pq.add(new int[] { nextY, nextX, j, crnt[3] });
						}
					}
					// 방문한 적 없는 경우
					else {
						// 서로 방향성이 다를 경우
						if (crnt[2] != j) {
							isVisit[nextY][nextX] = crnt[3] + 1;
							pq.add(new int[] { nextY, nextX, j, crnt[3] + 1 });
						} else {
							isVisit[nextY][nextX] = crnt[3];
							pq.add(new int[] { nextY, nextX, j, crnt[3] });
						}
					}
				}
			}
		}
		if (answer == Integer.MAX_VALUE)
			System.out.println(0);
		else
			System.out.println(answer);
	}
}
