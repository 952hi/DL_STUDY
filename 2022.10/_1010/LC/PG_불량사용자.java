package _1010.LC;

import java.util.*;

import java.io.*;

class PG_불량사용자 {
	static class Solution {
		static String[] su, sb;
		static int answer = 0, umaxSize, bmaxSize;
		static boolean[] isVisit;
		static int[] saver;
		static Set<String> s = new HashSet<>();
		static StringBuilder stb;

		// 현재 응모자 아이디 순번, 불량 아이디 순번
		static void dfs(int crntPos, int cnt) {

			// 모든 불량 이용자와 비교되었을 시
			if (cnt == bmaxSize) {
				// 저장된 순서를 정렬
				Arrays.sort(saver);
				stb = new StringBuilder();

				// 정렬된 값을 문자열로 치환
				for (int i = 0; i < bmaxSize; i++) {
					stb.append(saver[i]);
				}
				// set에 저장되어있는지 확인 후 없다면 추가 및 정답 추가
				if (!s.contains(stb.toString())) {
					s.add(stb.toString());
					answer++;
				}
				// 마지막이기 때문에 리턴
				return;
			}
			// 모든 유저를 순회했을 시 리턴
			if (crntPos == umaxSize)
				return;

			// 현재 순번 유저가 불량 사용자인 경우
			for (int i = 0; i < bmaxSize; i++) {
				// 해당 불량 아이디는 이미 존재한 경우 pass
				if (isVisit[i])
					continue;
				// 해당 유저와 불량 아이디의 길이가 다른 경우 pass
				else if (su[crntPos].length() != sb[i].length())
					continue;

				boolean isNotSame = false;
				// 각 자리를 비교
				for (int j = 0; j < sb[i].length(); j++) {
					// 자리가 다른 경우 탐색
					if (su[crntPos].charAt(j) != sb[i].charAt(j)) {
						// * 자리일 경우 pass
						if (sb[i].charAt(j) == '*')
							continue;
						// 그 외에는 다른 것으로 판단
						isNotSame = true;
						break;
					}
				}
				// 같이 않은 경우 다른 불량 사용자와 비교
				if (isNotSame)
					continue;
				// 적합한 불량 사용자 발견 시
				else {
					// 해당 불량 사용자 사용 처리
					isVisit[i] = true;
					// 응모자 아이디 순번 저장
					saver[cnt] = crntPos;
					dfs(crntPos + 1, cnt + 1);
					isVisit[i] = false;
				}
			}

			// 현재 순번 유저가 불량 사용자가 아닌 경우
			dfs(crntPos + 1, cnt);
		}

		public int solution(String[] user_id, String[] banned_id) {
			// 입력받은 값 스태틱에 저장
			su = user_id;
			sb = banned_id;
			// 각 배열 크기 저장
			umaxSize = user_id.length;
			bmaxSize = banned_id.length;
			// 방문 배열 저장
			isVisit = new boolean[bmaxSize];
			// 순서 저장
			saver = new int[bmaxSize];
			dfs(0, 0);
			return answer;
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
//		System.out.println(Arrays.toString(s.Solution(5, new int[] { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 })));
	}
}
