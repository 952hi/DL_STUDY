package _220407.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class BOJ_G2_청소년상어 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() throws IOException {
			if (bufferPos == bufferState) {
				bufferState = dis.read(buffer, bufferPos = 0, bfs);
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];
		}

		int nextInt() throws IOException {
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

	static final int shkNum = -1;

	// 0 : 상, 1 : 좌상, ... 7 : 우상
	static int dydx[][] = new int[][] { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 },
			{ -1, 1 } };
	static int bd[][] = new int[4][4];

	// 물고기 회전
	static void fishRot(int[][] fish, int fishNum, int[][] bd) {

		// 8방향 탐색하며 진입가능한지 확인
		for (int i = 0; i < 8; i++) {
			// 처음 설정된 화살표 방향
			int arrow = (fish[fishNum][0] + i) % 8;
			// 그 화살표 방향으로 진행했을 때
			int nextY = fish[fishNum][1] + dydx[arrow][0];
			int nextX = fish[fishNum][2] + dydx[arrow][1];

			// 진입가능한 방향 발견
			if (0 <= nextY && nextY < 4 && 0 <= nextX && nextX < 4) {
				// 상어가 아닐 때
				if (bd[nextY][nextX] != shkNum) {
					// 진입 가능한 방향으로 업데이트
					fish[fishNum][0] = arrow;
					break;
				}
			}

		}
	}

	// 물고기 움직임
	static void moveFish(int[][] fish, int[][] bd) {

		System.out.print("");

		for (int i = 1; i <= 16; i++) {
			// 물고기 방향이 0이 아니라면
			if (fish[i][0] != -1) {
				// 물고기가 이동할 수 있는 방향을 탐색
				fishRot(fish, i, bd);

				// 움직일 물고기의 상태를 최신화
				int arrow = fish[i][0];
				// 해당 물고기가 가르키는 물고기의 위치
				int endFishY = fish[i][1] + dydx[arrow][0];
				int endFishX = fish[i][2] + dydx[arrow][1];

				// 보드에서 두 번호를 교환
				int tmpNum = bd[endFishY][endFishX];
				bd[endFishY][endFishX] = bd[fish[i][1]][fish[i][2]];
				bd[fish[i][1]][fish[i][2]] = tmpNum;

				// 목적지 물고기의 y x좌표를 도착지 y,x로 변경
				fish[tmpNum][1] = fish[i][1];
				fish[tmpNum][2] = fish[i][2];

				// 출발지 물고기의 y, x좌표를 목적지 물고기 y,x로 변경
				fish[i][1] = endFishY;
				fish[i][2] = endFishX;
			}
		}
	}

	// 현재 보드 복사
	static int[][] copyB(int[][] oriB) {
		int[][] tmpB = new int[4][4];
		for (int i = 0; i < tmpB.length; i++) {
			tmpB[i] = Arrays.copyOf(oriB[i], oriB[i].length);
		}
		return tmpB;
	}

	// 현재 물고기 상태 복사
	static int[][] copyF(int[][] oriF) {
		int[][] tmpF = new int[17][3];
		for (int i = 0; i < tmpF.length; i++) {
			tmpF[i] = Arrays.copyOf(oriF[i], oriF[i].length);
		}
		return tmpF;
	}

	// 디버깅용 출력 코드
	static void printState(int[][] bd, int[][] fish, int[] shk) {
		System.out.println();
		for (int j = 0; j < 4; j++) {
			for (int j2 = 0; j2 < 4; j2++) {
				System.out.print(bd[j][j2] + " ");
			}
			System.out.println();
		}
		for (int j = 0; j < 4; j++) {
			for (int j2 = 0; j2 < 4; j2++) {
				if (j == shk[1] && j2 == shk[2])
					System.out.print(9 + " ");
				else if (bd[j][j2] == 0) {
					System.out.print(0 + " ");
				} else if (fish[bd[j][j2]][0] == -1) {
					System.out.print(0 + " ");
				} else if (fish[bd[j][j2]][0] == 0) {
					System.out.print("↑ ");
				} else if (fish[bd[j][j2]][0] == 1) {
					System.out.print("↖ ");
				} else if (fish[bd[j][j2]][0] == 2) {
					System.out.print("← ");
				} else if (fish[bd[j][j2]][0] == 3) {
					System.out.print("↙ ");
				} else if (fish[bd[j][j2]][0] == 4) {
					System.out.print("↓ ");
				} else if (fish[bd[j][j2]][0] == 5) {
					System.out.print("↘ ");
				} else if (fish[bd[j][j2]][0] == 6) {
					System.out.print("→ ");
				} else if (fish[bd[j][j2]][0] == 7) {
					System.out.print("↗ ");
				}
			}
			System.out.println();
		}
	}

	// 상어 움직임
	static void dfs(int[] shk, int[][] fish, int[][] bd) {
		moveFish(fish, bd);
		int SnextY = shk[1];
		int SnextX = shk[2];
		while (true) {
			SnextY += dydx[shk[0]][0];
			SnextX += dydx[shk[0]][1];
			// 이동 가능 범위
			if (0 <= SnextY && SnextY < 4 && 0 <= SnextX && SnextX < 4) {
				// 물고기가 존재한다면
				if (bd[SnextY][SnextX] != 0) {
					// 물고기 상태 복사
					int[][] tmpF = copyF(fish);
					// 보드 복사
					int[][] tmpB = copyB(bd);

					// 다음 상태의 상어는 먹은 물고기의 방향, 다음 Y,X 방향 세팅
					int nextShkArrow = fish[bd[SnextY][SnextX]][0];

					int[] nextShk = new int[] { nextShkArrow, SnextY, SnextX };
					// 복사한 물고기 상태에서 잡아먹힌 물고기의 방향을 -1으로 초기화
					tmpF[bd[SnextY][SnextX]][0] = -1;

					// 움직인 상어에 위치 표시
					tmpB[SnextY][SnextX] = shkNum;

					// 기존 상어위치는 0으로 초기화
					tmpB[shk[1]][shk[2]] = 0;

					// 먹은 물고기의 번호 더해줌
					sum += bd[SnextY][SnextX];

					// 다음 상태로 이동
					dfs(nextShk, tmpF, tmpB);
					sum -= bd[SnextY][SnextX];
				}
				// 다음 칸에 물고기가 없다면
				else 
					continue;
			}
			// 상어가 범위를 벗어난다면
			else {
				maxSum = Math.max(maxSum, sum);
				break;
			}
		}
	}

	static int sum = 0;
	static int maxSum = 0;

	public static void main(String[] args) throws IOException {
		Reader s = new Reader();

		// 1~16까지 물고기의 번호, 화살표 방향, 좌표 저장
		// 0: 화살표 방향, 1: y, 2: x
		int[][] fish = new int[17][3];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				// 번호
				bd[i][j] = s.nextInt();

				// 전진 방향 저장
				// 편하게 계산하기 위해 인풋 -1을 해줌
				fish[bd[i][j]][0] = s.nextInt() - 1;
				// y값 입력
				fish[bd[i][j]][1] = i;
				// x값 입력
				fish[bd[i][j]][2] = j;
			}
		}
		// 첫 상어의 화살표는 0,0위치 물고기의 값
		int[] shk = new int[] { fish[bd[0][0]][0], 0, 0 };

		// 0,0위치 물고기 0으로 초기화
		fish[bd[0][0]][0] = -1;
		sum += bd[0][0];
		// 상어의 위치는 -1
		bd[0][0] = shkNum;

		// 화살표, y,x
		dfs(shk, fish, bd);

		System.out.println(maxSum);
	}
}
