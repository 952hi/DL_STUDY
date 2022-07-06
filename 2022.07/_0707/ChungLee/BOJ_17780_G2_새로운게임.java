package _0707.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17780_G2_새로운게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] dydx = new int[][] { {}, { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
		// 0:보드 상태, 1:최대높이, 2~K+1:저장된 말
		int[][][] bd = new int[N + 2][N + 2][K + 2];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				bd[i][j][1] = 2;
			}
		}
		int[][] horse = new int[K + 1][4];
		for (int i = 0; i < N + 2; i++) {
			if (i == 0 || i == N + 1) {
				for (int j = 0; j < N + 2; j++) {
					bd[i][j][0] = 2;
				}
				continue;
			}

			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N + 2; j++) {
				if (j == 0 || j == N + 1) {
					bd[i][j][0] = 2;
					continue;
				}

				bd[i][j][0] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			// 행
			horse[i][0] = Integer.parseInt(st.nextToken());
			// 열
			horse[i][1] = Integer.parseInt(st.nextToken());
			// 이동방향
			horse[i][2] = Integer.parseInt(st.nextToken());
			// 몇 번째 높이인지 확인, 최소 0, 최대 k-1
			horse[i][3] = bd[horse[i][0]][horse[i][1]][1];
			bd[horse[i][0]][horse[i][1]][bd[horse[i][0]][horse[i][1]][1]++] = i;

		}
		int dir, crntY, crntX, nextY, nextX, nextBdColor, nextBdStack, crntBdStack, changeHorseNum;
		for (int CNT = 1; CNT <= 1000; CNT++) {
			for (int i = 1; i <= K; i++) {
				// 가장 아래있는 말이 아니면 이동 불가
				if (horse[i][3] != 2)
					continue;
				// 현재 말의 방향
				dir = horse[i][2];

				crntY = horse[i][0];
				crntX = horse[i][1];
				nextY = horse[i][0] + dydx[dir][0];
				nextX = horse[i][1] + dydx[dir][1];

				nextBdColor = bd[nextY][nextX][0];
				crntBdStack = bd[horse[i][0]][horse[i][1]][1];
				nextBdStack = bd[nextY][nextX][1];

				// 흰색
				if (nextBdColor == 0) {
					// 1. 말 상태 저장 배열을 최신화
					// 2.
					for (int j = 2; j < crntBdStack; j++) {

						changeHorseNum = bd[crntY][crntX][j];

						// 다음 보드에 이전 보드 말들을 쌓아올림
						bd[nextY][nextX][nextBdStack] = changeHorseNum;

						// 옮긴 말들의 현재 위치를 최신화
						horse[changeHorseNum][3] = nextBdStack;
						nextBdStack++;

						// 해당 말의 현재 좌표 최신화
						horse[changeHorseNum][0] = nextY;
						horse[changeHorseNum][1] = nextX;

						// 해당 말의 이전 위치 삭제
						bd[crntY][crntX][j] = 0;
						// 쌓인 말이 4개 이상인 경우 종료
						if (nextBdStack >= 6) {
							System.out.println(CNT);
							return;
						}
					}

					// 다음 칸은 말이 추가되었기 때문에 해당 값으로 최신화
					bd[nextY][nextX][1] = nextBdStack;

					// 옮긴 후 이전 위치 초기화
					bd[crntY][crntX][1] = 2;
				}
				// 빨간색
				else if (nextBdColor == 1) {
					// 흰 타일과 다르게 쌓이는 순서가 반대
					for (int j = crntBdStack - 1; j >= 2; j--) {
						changeHorseNum = bd[crntY][crntX][j];

						bd[nextY][nextX][nextBdStack] = bd[crntY][crntX][j];
						horse[changeHorseNum][3] = nextBdStack;
						nextBdStack++;

						// 해당 말의 현재 좌표 최신화
						horse[changeHorseNum][0] = nextY;
						horse[changeHorseNum][1] = nextX;

						// 해당 말의 이전 위치 삭제
						bd[crntY][crntX][j] = 0;

						// 쌓인 말이 4개 이상인 경우 종료
						if (nextBdStack >= 6) {
							System.out.println(CNT);
							return;
						}
					}

					// 다음 칸은 말이 추가되었기 때문에 해당 값으로 최신화
					bd[nextY][nextX][1] = nextBdStack;

					// 옮긴 후 이전 노드의 카운트 초기화
					bd[crntY][crntX][1] = 2;
				}
				// 파란색
				else {
					// 1:우,2:좌,3:상,4:하
					if (horse[i][2] == 1)
						horse[i][2] = 2;
					else if (horse[i][2] == 2)
						horse[i][2] = 1;
					else if (horse[i][2] == 3)
						horse[i][2] = 4;
					else
						horse[i][2] = 3;
					
					dir = horse[i][2];

					crntY = horse[i][0];
					crntX = horse[i][1];
					nextY = horse[i][0] + dydx[dir][0];
					nextX = horse[i][1] + dydx[dir][1];


					crntBdStack = bd[horse[i][0]][horse[i][1]][1];
					nextBdStack = bd[nextY][nextX][1];
					// 다음 칸이 흰색일 때
					if (bd[nextY][nextX][0] == 0) {
						// 1. 말 상태 저장 배열을 최신화
						// 2.
						for (int j = 2; j < crntBdStack; j++) {

							changeHorseNum = bd[crntY][crntX][j];

							// 다음 보드에 이전 보드 말들을 쌓아올림
							bd[nextY][nextX][nextBdStack] = changeHorseNum;

							// 옮긴 말들의 현재 위치를 최신화
							horse[changeHorseNum][3] = nextBdStack;
							nextBdStack++;

							// 해당 말의 현재 좌표 최신화
							horse[changeHorseNum][0] = nextY;
							horse[changeHorseNum][1] = nextX;

							// 해당 말의 이전 위치 삭제
							bd[crntY][crntX][j] = 0;
							// 쌓인 말이 4개 이상인 경우 종료
							if (nextBdStack >= 6) {
								System.out.println(CNT);
								return;
							}
						}

						// 다음 칸은 말이 추가되었기 때문에 해당 값으로 최신화
						bd[nextY][nextX][1] = nextBdStack;

						// 옮긴 후 이전 위치 초기화
						bd[crntY][crntX][1] = 2;
					}
					// 다음 칸이 빨간색일 때
					else if (bd[nextY][nextX][0] == 1) {
						// 흰 타일과 다르게 쌓이는 순서가 반대
						for (int j = crntBdStack - 1; j >= 2; j--) {
							changeHorseNum = bd[crntY][crntX][j];

							bd[nextY][nextX][nextBdStack] = bd[crntY][crntX][j];
							horse[changeHorseNum][3] = nextBdStack;
							nextBdStack++;

							// 해당 말의 현재 좌표 최신화
							horse[changeHorseNum][0] = nextY;
							horse[changeHorseNum][1] = nextX;

							// 해당 말의 이전 위치 삭제
							bd[crntY][crntX][j] = 0;

							// 쌓인 말이 4개 이상인 경우 종료
							if (nextBdStack >= 6) {
								System.out.println(CNT);
								return;
							}
						}

						// 다음 칸은 말이 추가되었기 때문에 해당 값으로 최신화
						bd[nextY][nextX][1] = nextBdStack;

						// 옮긴 후 이전 노드의 카운트 초기화
						bd[crntY][crntX][1] = 2;
					}
				}
			}
		}
		System.out.println(-1);
	}
}
