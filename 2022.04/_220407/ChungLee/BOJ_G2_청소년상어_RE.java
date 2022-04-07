package _220407.ChungLee;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_G2_청소년상어_RE {
	static int[][] dydx = new int[][] { { 0, 0 }, //
			{ -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, //
			{ 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };

	static void movefishes(int[][] bd, fish[] fishList) {
		for (int i = 1; i <= 16; i++) {
			// 먹힌 물고기는 제외
			if (fishList[i].direct == 0)
				continue;

			findDirect(bd, fishList[i]);

			int crntY = fishList[i].y;
			int crntX = fishList[i].x;
			int crntD = fishList[i].direct;

			int destY = crntY + dydx[fishList[i].direct][0];
			int destX = crntX + dydx[fishList[i].direct][1];
			int destNum = bd[destY][destX];

			fishList[i].y = destY;
			fishList[i].x = destX;

			fishList[destNum].y = crntY;
			fishList[destNum].x = crntX;
			
			bd[crntY][crntX] = destNum;
			bd[destY][destX] = i;
		}
	}

	private static void findDirect(int[][] bd, fish fish) {
		int dir = fish.direct;
		int y = fish.y;
		int x = fish.x;
		for (int i = 0; i <= 8; i++) {
			int nD = dir + i;
			if (nD > 8)
				nD -= 8;

			int nY = y + dydx[nD][0];
			int nX = x + dydx[nD][1];

			if (nY < 0 || nY == 4 || nX < 0 || nX == 4 || bd[nY][nX] == 17)
				continue;
			else {
				fish.direct = nD;
				break;
			}
		}
	}

	static void hunting(int[][] bd, fish[] fishList, fish shark, int sum) {

		movefishes(bd, fishList);

		int sharkY = shark.y;
		int sharkX = shark.x;
		while (true) {
			sharkY += dydx[shark.direct][0];
			sharkX += dydx[shark.direct][1];

			// 범위를 벗어났으면
			if (sharkY < 0 || sharkY == 4 || sharkX < 0 || sharkX == 4)
				break;

			// 물고기가 0일 때
			else if (bd[sharkY][sharkX] == 0)
				continue;

			else {
				int[][] copybd = copyBd(bd);
				fish[] copyFishList = copyFishList(fishList);
				fish copyShark = new fish();
				// 이전에 상어가 있는 자리는 0으로 초기화
				copybd[shark.y][shark.x] = 0;

				// 복사 상어의 값 입력
				copyShark.y = sharkY;
				copyShark.x = sharkX;
				copyShark.direct = copyFishList[copybd[sharkY][sharkX]].direct;

				// 상어가 먹은 물고기의 데이터 0으로 초기화
				copyFishList[copybd[sharkY][sharkX]].y = -2;
				copyFishList[copybd[sharkY][sharkX]].x = -2;
				copyFishList[copybd[sharkY][sharkX]].direct = 0;

				copybd[sharkY][sharkX] = 17;

				hunting(copybd, copyFishList, copyShark, sum + bd[sharkY][sharkX]);
			}
		}
		maxSum = Math.max(maxSum, sum);
	}

	private static fish[] copyFishList(fish[] fishList) {
		fish[] copyFishList = Arrays.copyOf(fishList, 17);
		return copyFishList;
	}

	private static int[][] copyBd(int[][] bd) {
		int[][] copyBd = new int[4][4];
		for (int i = 0; i < 4; i++) {
			copyBd[i] = Arrays.copyOf(bd[i], 4);
		}
		return copyBd;
	}

	static class fish {
		int y;
		int x;
		int direct;

		public fish() {
			// TODO Auto-generated constructor stub
		}

		public fish(int y, int x, int direct) {
			super();
			this.y = y;
			this.x = x;
			this.direct = direct;
		}

	}

	static int maxSum = 0;

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[][] bd = new int[4][4];
		fish[] fishList = new fish[17];
		int cnt = 1;
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {

				int num = s.nextInt();
				bd[y][x] = num;
				fishList[num] = new fish(y, x, s.nextInt());
				cnt++;
			}
		}

		fish shark = new fish(0, 0, fishList[bd[0][0]].direct);
		fishList[bd[0][0]].direct = 0;
		fishList[bd[0][0]].y = -2;
		fishList[bd[0][0]].x = -2;
		bd[0][0] = 17;
		hunting(bd, fishList, shark, 0);

		System.out.println(maxSum);
	}
}
