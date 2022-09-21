package _0913.ChungLee;

import java.util.*;

public class PG_등산코스정하기 {
	// 방문 노드 중 최소 값으로 항상 저장
	// 중복 방문 되었을 때 해당 값보다 큰 값으로 방문한다면 더 이상 방문 X
	// 더 작은 값으로 방문 가능하면 재방문 가능
	// 목적지 도착하면 다시 돌아오는 검사 필요 없음.
	public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
		//각 노드별 최소 값 저장
		int[] minV = new int[n + 1];
		boolean[] checkSummits = new boolean[n + 1];
		
		for (int i = 0; i < summits.length; i++) {
			// 정상 노드 위치에 true로 표시
			checkSummits[summits[i]] = true;
		}

		Queue<int[]> q = new LinkedList<>();
		// 최고 높은 산, 가장 크게 걸리는 시간
		int minTop = 50000, minRoute = 10000001;

		List<int[]>[] list = new ArrayList[n + 1];

		// 초기화
		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}

		// 초기 데이터 입력
		for (int i = 0; i < paths.length; i++) {
			list[paths[i][0]].add(new int[] { paths[i][1], paths[i][2] });
			list[paths[i][1]].add(new int[] { paths[i][0], paths[i][2] });
		}
		
		//게이트 수만큼 시작 위치 추가
		for (int i = 0; i < gates.length; i++)
			q.add(new int[] { gates[i], 0 });

		int[] crnt;
		while (!q.isEmpty()) {
			crnt = q.poll();
			// 정상이라면 종료
			if (checkSummits[crnt[0]])
				continue;

			// 방문 가능한 노드를 모두 검사
			for (int[] i : list[crnt[0]]) {

				// 한 번도 방문하지 않은 곳은
				if (minV[i[0]] == 0) {

					// 해당 자리에 값 입력
					minV[i[0]] = Math.max(i[1], crnt[1]);
					// 해당 자리 방문 예정
					
					q.add(new int[] { i[0], minV[i[0]] });
					// System.out.println(q.toString());

				}
				// 방문 해본 곳은
				else {
					// 다음 자리 값보다 더 적은 값으로 방문 가능할 때
					if (minV[i[0]] > Math.max(i[1], crnt[1])) {
						// 최소 값 갱신
						minV[i[0]] = Math.max(i[1], crnt[1]);
						// 해당 자리 다시 방문
						q.add(new int[] { i[0], minV[i[0]] });
					}
				}
			}
		}

		for (int i = 0; i < summits.length; i++) {
			// System.out.println(minV[summits[i]]);
			if (minV[summits[i]] != 0 && minV[summits[i]] < minRoute) {
				minRoute = minV[summits[i]];
				minTop = summits[i];
			} else if (minV[summits[i]] == minRoute && minTop > summits[i]) {
				minTop = summits[i];
			}
		}
		return new int[] { minTop, minRoute };
	}
}
