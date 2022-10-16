package _1010.LC;

import java.util.*;

// 어피치보다 많이 쏴야 고득점.
// 각 포인트별로 어피치보다 많이 맞춰야 득점 가능.
// 어피치가 맞추지 못한 곳은 1발만 맞추면 됨.
public class PG_양궁대회 {
	static class Solution {
		// 가장 큰 점수 차이
		static int maxNum = 0;
		// 최대, 라이언, 어치피 각각 포인트 별 맞춘 화살 갯수 기록
		static int[] maxEachArrow = new int[11], lionEachArrow = new int[11], peachEachArrow = new int[11];

		static public void dfs(int crntPos, int remainArrow) {
			// 마지막 0점 자리 순서인 경우
			if (crntPos == 10) {
				// 라이언의 남은 화살을 모두 0번 자리에 입력
				lionEachArrow[10] = remainArrow;

				// 두 선수 점수 계산 및 비교
				int lion = 0, peach = 0;
				for (int i = 0; i < 11; i++) {
					// 라이언 득점
					if (lionEachArrow[i] > peachEachArrow[i]) {
						lion += 10 - i;
					}
					// 어피치 득점
					else if (peachEachArrow[i] != 0) {
						peach += 10 - i;
					}
				}

				// 두 점수 간 차이가 기존 최대 점수 차이를 넘길 경우 값과 화살 맞춘 기록 갱신
				if (lion - peach > maxNum) {
					maxNum = lion - peach;
					maxEachArrow = Arrays.copyOf(lionEachArrow, lionEachArrow.length);
				}

				// 점수 차가 기존과 같은 경우
				else if (lion - peach == maxNum) {
					for (int i = 10; i >= 0; i--) {
						if (maxEachArrow[i] < lionEachArrow[i]) {
							maxEachArrow = Arrays.copyOf(lionEachArrow, lionEachArrow.length);
						}
						// 저장된 최대 점수의 최소 점수 갯수가 더 많은 경우 비교 취소
						else if (maxEachArrow[i] > lionEachArrow[i])
							break;
					}
				}
				// 마지막 자리이기 때문에 리턴
				return;
			}

			// 해당 자리를 이길 수 있을만큼 화살이 존재하고 이기기 위해 화살을 쏠 때
			if (peachEachArrow[crntPos] < remainArrow) {
				//해당 자리에 쏜 화살의 수를 기록
				lionEachArrow[crntPos] = peachEachArrow[crntPos] + 1;
				//다음 자리로 넘어갈 때 [다음 자리의 번호, 남은 화살 수] 를 인자로 넘겨줌.
				dfs(crntPos + 1, remainArrow - (peachEachArrow[crntPos] + 1));
				//방문 끝났으면 자리값 초기화
				lionEachArrow[crntPos] = 0;
			}

			// 화살을 쏘지 않을 떄
			dfs(crntPos + 1, remainArrow);
		}

		public int[] solution(int n, int[] info) {
			peachEachArrow = info;
			int[] answer = { -1 };

			dfs(0, n);
			//기록된 최대 차이 값이 없다면 -1반환
			if (maxNum != 0)
				answer = maxEachArrow;

			return answer;
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(Arrays.toString(s.solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })));
	}
}
