package _220221.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_1729_G1_이차원배열의합 {

	static int[][] board = new int[6][6];
	static int sum = 0;

	static void testDFS(int cnt) {
		if (cnt == 100) {
			if (sum == 273) {

				System.out.println("found!");
				return;
			}
			System.out.println("cnt == 100");
		}
		for (int i = 1; i <= 8; i++) {
			if (0 < i && i <= 4) {
				for (int j = 0; j < 6; j++) {
					boardChange(i, j);
					testDFS(cnt + 1);
				}
			} else {
				boardChange(i, 0);
				testDFS(cnt + 1);
			}
		}
	}

	static void boardChange(int k, int l) {
		sum = 0;
		if (k == 1) {
			for (int i = 0; i < 6; i++) {
				board[l][i]++;
				board[l][i] %= 10;

			}
		} else if (k == 2) {
			for (int i = 0; i < 6; i++) {
				board[l][i]--;
				if (board[l][i] < 0)
					board[l][i] = 9;

			}
		} else if (k == 3) {
			for (int i = 0; i < 6; i++) {
				board[i][l]++;
				board[i][l] %= 10;
			}
		} else if (k == 4) {
			for (int i = 0; i < 6; i++) {
				board[i][l]--;
				if (board[i][l] < 0)
					board[i][l] = 9;
			}
		} else if (k == 5) {
			for (int i = 0; i < 6; i++) {
				board[i][i]++;
				board[i][i] %= 10;
			}
		} else if (k == 6) {
			for (int i = 0; i < 6; i++) {
				board[i][5 - i]++;
				board[i][5 - i] %= 10;
			}
		} else if (k == 7) {
			for (int i = 0; i < 6; i++) {
				board[i][i]--;
				if (board[i][i] < 0)
					board[i][i] = 9;
			}
		} else if (k == 8) {
			for (int i = 0; i < 6; i++) {
				board[i][5 - i]--;
				if (board[i][5 - i] < 0)
					board[i][5 - i] = 9;
			}
		}
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				sum += board[i][j];
			}
		}
		sum = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner sc = new Scanner(System.in);

		int sum = 0;
		for (int i = 0; i < 6; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 6; j++) {

				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (true) {
			int k = 0;
			int l = 0;
			try {
				k = sc.nextInt();
				l = sc.nextInt();

			} catch (Exception e) {
				continue;
			}
			if (k == 1) {
				for (int i = 0; i < 6; i++) {
					board[l][i]++;
					board[l][i] %= 10;

				}
			} else if (k == 2) {
				for (int i = 0; i < 6; i++) {
					board[l][i]--;
					if (board[l][i] < 0)
						board[l][i] = 9;

				}
			} else if (k == 3) {
				for (int i = 0; i < 6; i++) {
					board[i][l]++;
					board[i][l] %= 10;
				}
			} else if (k == 4) {
				for (int i = 0; i < 6; i++) {
					board[i][l]--;
					if (board[i][l] < 0)
						board[i][l] = 9;
				}
			} else if (k == 5) {
				for (int i = 0; i < 6; i++) {
					board[i][i]++;
					board[i][i] %= 10;
				}
			} else if (k == 6) {
				for (int i = 0; i < 6; i++) {
					board[i][5 - i]++;
					board[i][5 - i] %= 10;
				}
			} else if (k == 7) {
				for (int i = 0; i < 6; i++) {
					board[i][i]--;
					if (board[i][i] < 0)
						board[i][i] = 9;
				}
			} else if (k == 8) {
				for (int i = 0; i < 6; i++) {
					board[i][5 - i]--;
					if (board[i][5 - i] < 0)
						board[i][5 - i] = 9;
				}
			}
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					System.out.print(board[i][j] + " ");
					sum += board[i][j];
				}
				System.out.println();
			}
			System.out.println(sum);
			sum = 0;
		}
	}
}
