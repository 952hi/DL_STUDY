package _220331.ChungLee;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_12100_G2_2048_easy {
	static int Sboard[][];
	static int N;
	static int max = 0;

	// 상, 우, 하, 좌
	static boolean move(int direct, int board[][]) {
		int mvCnt = 0;
		boolean isAdded = false;
		// 상 4행 => 1행
		if (direct == 1) {
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//현재 자리가 숫자일 때만
					if (board[i][j] != 0) {
						// 위로 이동하며 숫자를 만나거나 마지막 자리까지 이동
						for (int k = 1; k <= i; k++) {
							// 숫자를 마주쳤을 때
							if (board[i - k][j] != 0) {
								// 나와 같은 숫자일 때
								if (board[i - k][j] == board[i][j] && isAdded==false) {
									board[i - k][j] += board[i][j];
									board[i][j] = 0;
									mvCnt++;
									max = Math.max(max, board[i - k][j]);
									break;
								}
								// 나와 같은 숫자가 아니거나 이미 한번 합쳐졌을 때
								else {
									// 한칸도 이동하지 못한 경우
									if (k == 1) {

									}
									// 한 칸 이상 이동 가능한 경우
									else {
										board[i - k + 1][j] = board[i][j];
										board[i][j] = 0;
										mvCnt++;

									}
									break;
								}
							}
							// 빈칸을 마주쳤을 때
							else {
								// 최상단이라면
								if (k == i) {
									board[i - k][j] = board[i][j];
									board[i][j] = 0;
									mvCnt++;
									break;
								}
							}
						}
					}
				}
			}
		}
		// 우1열 => 4열
		else if (direct == 2) {
			int start = N - 2;
			for (int i = 0; i < N; i++) {
				for (int j = start; j >= 0; j--) {

					// 0보다 큰 값일 때
					if (board[i][j] != 0) {
						int next = 1;
						while (j + next < N) {
							// 다음 숫자가 0이 아닐 때
							if (board[i][j + next] != 0) {
								// 같은 숫자라면
								if (board[i][j + next] == board[i][j]) {
									board[i][j + next] += board[i][j];
									board[i][j] = 0;
									mvCnt++;
									max = Math.max(max, board[i][j + next]);
								}
								// 같은 숫자가 아니면
								else {
									// 한칸 이상 이동했다면
									if (next != 1) {
										board[i][j + next - 1] = board[i][j];
										board[i][j] = 0;
										mvCnt++;
									}
								}
								break;
							}
							// 다음 숫자가 0일 때
							else {
								// 마지막 자리라면
								if (j + next == N - 1) {
									board[i][j + next] = board[i][j];
									board[i][j] = 0;
									mvCnt++;
								}
							}
							next++;
						}
					}
				}
			}
		}

		// 하 1행 => 4행
		else if (direct == 3) {
			for (int i = N - 2; i >= 0; i--) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] != 0) {
						// 아래로 이동하며 숫자를 만나거나 마지막 자리까지 이동
						for (int k = 1; k <= N -1 - i; k++) {
							// 숫자를 마주쳤을 때
							if (board[i + k][j] != 0) {
								// 나와 같은 숫자일 때
								if (board[i + k][j] == board[i][j]) {
									board[i + k][j] += board[i][j];
									board[i][j] = 0;
									mvCnt++;
									max = Math.max(max, board[i + k][j]);
								}
								// 나와 같은 수자가 아닐 때
								else {
									if (k == 1) {

									} else {
										board[i + k - 1][j] = board[i][j];
										board[i][j] = 0;
										mvCnt++;
									}

								}
								break;
							}
							// 빈칸을 마주쳤을 때
							else {
								// 최하단이라면
								if (k == N -1) {
									board[i + k][j] = board[i][j];
									board[i][j] = 0;
									mvCnt++;
									break;
								}
							}
						}
					}
				}
			}
		}
		// 좌 4열 => 1열
		else {
			for (int i = 0; i < N; i++) {
				for (int j = 1; j < N; j++) {
					if (board[i][j] != 0) {
						// 오른쪽으로 이동하며 숫자를 만나거나 마지막 자리까지 이동
						for (int k = 1; k <= j; k++) {
							// 숫자를 마주쳤을 때
							if (board[i][j - k] != 0) {
								// 나와 같은 숫자일 때
								if (board[i][j - k] == board[i][j]) {
									board[i][j - k] += board[i][j];
									board[i][j] = 0;
									mvCnt++;
									max = Math.max(max, board[i][j - k]);
									break;
								}
								// 나와 같은 수자가 아닐 때
								else {
									if (k == 1) {
									} else {
										board[i][j - k + 1] = board[i][j];
										board[i][j] = 0;
										mvCnt++;

									}
									break;
								}
							}
							// 빈칸을 마주쳤을 때
							else {
								// 최좌단이라면
								if (k == j) {
									board[i][j - k] = board[i][j];
									board[i][j] = 0;
									mvCnt++;
									break;
								}
							}
						}
					}
				}
			}
		}
		if (mvCnt == 0)
			return false;
		return true;
	}

	static void dfs(int cnt, int crntBoard[][]) {
		if (cnt == 5)
			return;

		int[][] copyBoard = new int[N][];

		for (int i = 1; i <= 4; i++) {
			for (int j = 0; j < N; j++) {
				copyBoard[j] = Arrays.copyOf(crntBoard[j], crntBoard[j].length);
			}
			if (move(i, copyBoard)) {
				dfs(cnt + 1, copyBoard);
			}
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		N = s.nextInt();

		Sboard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Sboard[i][j] = s.nextInt();
			}
		}
		dfs(0, Sboard);
		System.out.println(max);
	}
}
