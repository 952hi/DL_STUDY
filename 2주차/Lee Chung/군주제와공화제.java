package SWEXPERT.PS_10993군주제와공화제;

import java.util.Scanner;

public class 군주제와공화제 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		//전체 데이터 반복 횟수 입력
		int inputTotalTestCase = scan.nextInt();

		// 전체 데이터 반복 횟수만큼 반복
		for (int total = 0; total < inputTotalTestCase; total++) {
			// 각 테스트 케이스의 각 열을 입력
			int inputEachTestCase = scan.nextInt();
			int[][] board = new int[inputEachTestCase][3];
			float[][] InfluenceBoard = new float[inputEachTestCase][inputEachTestCase];
			Object[] answer = new Object[inputEachTestCase];
			for (int Each = 0; Each < inputEachTestCase; Each++) {
				// 각 열의 [x], [y], [영향력]을 입력
				for (int threeData = 0; threeData < 3; threeData++) {
					board[Each][threeData] = scan.nextInt();
				}
			}
			//N * N 배열에 모든 도시가 각각의 도시에 미치는 영향력을 계산 후 저장
			for (int i = 0; i < inputEachTestCase; i++) {
				for (int j = 0; j < inputEachTestCase; j++) {
					//자기 자신과는 계산하지 않음
					if (i == j)
						continue;
					//영향력이 그 도시의 지배력을 넘어섰을 경우 지배 도시가 피지배 도시에게 미치는 영향력을 저장
					if ((float) (board[i][2] / (Math.pow(board[i][0] - board[j][0], 2)
							+ Math.pow(board[i][1] - board[j][1], 2))) > board[j][2]) {
						InfluenceBoard[i][j] = (float) (board[i][2]
								/ (Math.pow(board[i][0] - board[j][0], 2) + Math.pow(board[i][1] - board[j][1], 2)));
					} 
					//영향력이 지배력을 넘어서지 못할 경우 0을 저장
					else
						InfluenceBoard[i][j] = 0;
				}
			}
			//N*N배열을 Y축부터 순회하면서 그 도시가 군주제인지 공화제인지 피지배 도시인지 검사
			for (int i = 0; i < inputEachTestCase; i++) {
				float maxInf = 0;
				int mostInf = 0;
				int ruledCity = 0;
				for (int j = 0; j < inputEachTestCase; j++) {
					//자기 도시의 지배력보다 영향력이 높은 도시 발견
					if (InfluenceBoard[j][i] > maxInf) {
						//가장 큰 영향력을 최신화
						maxInf = InfluenceBoard[j][i];
						//가장 큰 영향력을 가진 첫 도시여서 1로 초기화
						mostInf = 1;
						//그 도시가 몇 번째 도시인지 저장
						ruledCity = j;	
					} 
					//가장 지배력이 높았던 도시와 같은 영향력을 끼치는 도시 발견
					else if (InfluenceBoard[j][i] == maxInf) {
						mostInf++;
					}
				}
				//한 도시가 어떤 도시로부터 자유로울 때 군주제
				if (maxInf == 0) {
					answer[i] = 'K';
				} 
				// 두 도시 이상으로 부터 동일한 영향력을 받을 때 공화제
				else if (mostInf > 1) {
					answer[i] = 'D';
				}
				//한 도시로부터 피지배를 당하면 지배당하는 도시의 번호를 저장
				else {
					answer[i] = ruledCity;
				}
			}
			//피지배를 당하는 도시를 지배하는 도시가 피지배 도시일 때 가장 최상위 도시를 찾아가기 위한 탐색
			for (int i = 0; i < answer.length; i++) {
				//최상위 도시를 찾아갈 때까지 무한 탐색
				while (true) {
					//탐색하려는 도시가 이미 최상위 도시이거나 공화제 도시라면 다음 도시 탐색
					if (answer[i] == (Object) 'K' || answer[i] == (Object) 'D')
						break;
					//탐색하려는 도시에 저장된 지배 도시가 최상위 도시일 때 탐색 완료
					if (answer[(int) answer[(int) i]] == (Object) 'K'
							|| answer[(int) answer[(int) i]] == (Object) 'D') {
						break;
					} 
					//탐색하려는 도시에 저장된 지배 도시가 피지배 도시일 경우 지배 도시에 저장된 지배도시의 번호를 탐색하려는 도시에 저장
					else {
						answer[i] = answer[(int) answer[(int) i]];
					}
				}
			}
			System.out.printf("#%d ", (total + 1));
			for (int i = 0; i < answer.length; i++) {
				
				if (answer[i] == (Object) 'K' || answer[i] == (Object) 'D')
					System.out.print(answer[i] + " ");
				else
					System.out.print(((int) answer[i] + 1) + " ");
			}
			System.out.println();
		}
	}
}
