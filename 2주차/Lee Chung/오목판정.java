package 연습장;
import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner scan = new Scanner(System.in);
		int inputTestCaseCount = scan.nextInt(), N = 0;
		char inputStone, board[][] = new char[21][21];
		int fiveGateCheck = 0, fiveGateAvail = 0;
		String input = null;

		// TestCase 만큼 반복
		for (int u = 0; u < inputTestCaseCount; u++) {
			fiveGateAvail = 0;
			N = scan.nextInt();
			scan.nextLine();
			for (int j = 0; j < N; j++) {
				input = scan.nextLine();
				board[j] = input.toCharArray();

			}
			for (int i = 0; i < N; i++) {// y
				for (int j = 0; j < N; j++) { // x
					if (board[i][j] == '.')
						continue;
					for (int k = 0; k < 2; k++) { // y
						for (int l = -1; l < 2; l++) { // x
							fiveGateCheck = 0;
							// 좌상, 상, 우상, 좌 라인은 검사할 필요 없음
							// 0,0 라인부터 우, 우하, 하,좌하를 검사하면 자연스럽게 모두 검사 완료 가능.
							if (k == 0 && l == -1 || k == 0 && l == 0)
								continue;
							if ((k + i < 0 || k + i >= N) || (j + l < 0 || j + l >= N))
								continue;
							if (board[i + k][j + l] == 'o') {
								for (int n = 0; n < N; n++) {
									int y = i + k * n;
									int x = j + l * n;
									if ((0 <= y && y < N) && (0<= x &&  x< N) && board[y][x] == 'o') {
										fiveGateCheck++;
										if (fiveGateCheck == 5) {
											System.out.println("#"+(u+1)+" YES");
											fiveGateAvail = 1;
											break;
										}
									} else {
										break;
									}
								}
							}
							if (fiveGateAvail == 1)
								break;
						}
						if (fiveGateAvail == 1)
							break;
					}
					if (fiveGateAvail == 1)
						break;
				}
				if (fiveGateAvail == 1)
					break;
			}
			if (fiveGateAvail == 0)
				System.out.println("#"+(u+1)+" NO");
		}
	}
}