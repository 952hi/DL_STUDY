package _220211.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author LeeChung </br>
 * @version 1.0, 2022-02-09 최초작성 </br>
 * @version 1.1, 2022-02-10 수정 </br>
 * @version 1.2, 2022-02-11 수정
 * 
 * 
 * @problem 구역을 두개로 나눠 각 구역은 두 선거구 중 하나에 포함</br>
 *          모든 구역은 두 구역 중 하나에 포함</br>
 *          선거구는 구역을 적어도 하나 포함</br>
 *          한 선거구에 포함되어 있는 구역은 모두 연결</br>
 *          구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때, 두 구역은 연결</br>
 *          중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역
 * 
 * @see 최초아이디어 : 각 구획 정보(인구수, 정당 상태)가 담긴 배열로 </br>
 *      각 구획이 고립되었는지 아닌지 모든 예외를 처리한 다음 정당의 인구수 차이를 계산
 * 
 */
public class BOJ17471게리맨더링 {
	static boolean[][] board;
	// 빨강 : true, 파랑 : false;
	static boolean[] partyCheck;
	static int peopleData[];
	static int totalPeople;
	static Queue<Integer> q = new LinkedList<Integer>();
	static int NodeSize;
	static boolean[] isConn;
	static int apartedTwoPoint[] = new int[2];
	static int red = 0;
	static int blue = 0;
	// 최초 값은 모든 정당의 값을 합한 값
	static int minNum = 0;

	/**
	 * 빨강, 파랑 정당이 최소 1개의 지역구를 보장 받는지 확인
	 * 
	 * @return 독점
	 */
	static boolean isPartyHaveOne() {
		int cnt = 0;
		for (int i = 1; i <= NodeSize; i++) {
			if (partyCheck[i]) {
				cnt++;
			}
		}
		// 만약 cnt가 0(모두 파랑 정당)이거나 NodeSize(모두 빨강정당)만큼 있으면 독점 상태로 false 반환
		if (cnt == 0 || cnt == NodeSize) {
			return false;
		}
		// 독점 상태가 아니라면 true 반환
		else
			return true;
	}

	/**
	 * 
	 * @param crntNode 시작 노드 DFS로 시작노드와 연결되어 있는 모든 노드를 true로 변경
	 */
	static void checkNodeSetint(int crntNode) {
		isConn[crntNode] = true;
		for (int i = 1; i <= NodeSize; i++) {
			if (isConn[i] == false && board[crntNode][i]) {
				isConn[i] = true;
				checkNodeSetint(i);
			}
		}
	}

	/**
	 * 고립된 두 노드셋의 각 데이터 합을 구하기 위한 함수 queue 이용한 BFS 사용
	 * 
	 * @param crntNode 시작노드
	 * @return 시작노드로부터 이어진 모든 지역구의 합을 반환
	 */
	static int forTwoSetBfs(int crntNode) {
		boolean[] AlonePartyCheck = new boolean[NodeSize + 1];
		Queue<Integer> temq = new LinkedList<Integer>();
		temq.offer(crntNode);
		int sumP = 0;
		while (!temq.isEmpty()) {
			int crntN = temq.poll();
			sumP += peopleData[crntN];
			for (int i = 1; i <= NodeSize; i++) {
				if (crntNode != i && board[crntN][i] && AlonePartyCheck[i] == false) {
					AlonePartyCheck[i] = true;
					temq.offer(i);
				}
			}
		}
		return sumP;
	}

	/**
	 * 
	 * BFS를 수행하다가 인구 차이의 폭이 작아지면 OK, but 인구 차이폭이 0 or 커지면 STOP
	 * 
	 * @deprecated 모든 지역구가 이어진 상태일 때 탐색기 ver 1
	 * @param crntNode 시작노드
	 */
	static void bfs(int crntNode) {
		boolean queueCheck[] = new boolean[NodeSize + 1];
		q.offer(crntNode);
		queueCheck[crntNode] = true;
		while (!q.isEmpty()) {
			int crntN = q.poll();
			partyCheck[crntN] = true;
			//
			if (isPartyHaveOne() == true && isAloneNode0() == true) {
				for (int i = 1; i <= NodeSize; i++) {
					if (partyCheck[i])
						red += peopleData[i];
					else
						blue += peopleData[i];
				}
				minNum = Math.min(minNum, Math.abs(red - blue));
				red = 0;
				blue = 0;
			}

			for (int i = 1; i <= NodeSize; i++) {
				if (queueCheck[i] == false && board[crntN][i]) {
					q.offer(i);
					queueCheck[i] = true;
				}
			}
		}
	}

	/**
	 * 
	 * 각 정당 노드들의 연결 가능성을 반환
	 * 
	 * @return 하나의 정당이라도 그 정당에 소속된 구역 노드가 연결될 수 없을 때 false 반환
	 * @see boolean partyCheck[]로는 각각의 정당의 노드가 연결될 수 있는지 확인 불가능</br>
	 */
	static boolean isAloneNode0() {
		Queue<Integer> temq = new LinkedList<Integer>();
		boolean isFirst = true;
		// 현재 상태를 빨강, 빨강이 지나간 땅, 파랑, 파랑이 지나간 땅, 미점령으로 표시하기 위해
		// 기존 빨강 땅 = 1, 빨지땅 = 2. 파랑 땅, 0, 파지땅 -1
		int[] AlonePartyCheck = new int[NodeSize + 1];
		int start_Blue = 0;
		int start_Red = 0;
		// partyCheck배열에 true(빨간 정당)일 경우 1로 변경
		for (int i = 1; i <= NodeSize; i++) {
			if (partyCheck[i]) {
				start_Red = i;
				AlonePartyCheck[i] = 1;
			}
			else
				start_Blue = i;
		}
		// BFS로 빨간 정당이 가져야 할 땅이 모두 연결되었는지 확인
		temq.offer(start_Red);
		AlonePartyCheck[start_Red] = 2;
		while (!temq.isEmpty()) {
			int crntN = temq.poll();
			for (int j = 1; j <= NodeSize; j++) {
				if (/* i != j && */ board[crntN][j] && AlonePartyCheck[j] == 1) {
					AlonePartyCheck[j] = 2;
					temq.offer(j);
				}
			}
		}
		// BFS로 빨간 정당이 가지고 남은 땅이 모두 연결되었는지 확인
		temq.offer(start_Blue);
		AlonePartyCheck[start_Blue] = -1;
		while (!temq.isEmpty()) {
			int crntN = temq.poll();
			for (int j = 1; j <= NodeSize; j++) {
				if (/* i != j && */ board[crntN][j] && AlonePartyCheck[j] == 0) {
					AlonePartyCheck[j] = -1;
					temq.offer(j);
				}
			}
		}
		// 빨간색 정당과 파란색 정당이 지나가지 못한 땅
		for (int i = 1; i <= NodeSize; i++) {
			if (AlonePartyCheck[i] == 0 || AlonePartyCheck[i] == 1)
				return false;
		}
		return true;
	}

	/**
	 * 백트래킹으로 가능한 모든 경우의 수를 돌며 그 경우가 성립가능한지 판단</br>
	 * 시간복잡도 O(N^2) 예상
	 * 
	 * @param crntNode 시작 노드 => 현재 노드
	 */
	static void findAllWay(int crntNode) {
		if (crntNode == NodeSize + 1) {
			return;
		}
		for (int i = crntNode; i <= NodeSize; i++) {
			// 모두 파란 경우(all false)는 존재하지 않음
			partyCheck[i] = true;

			if (isPartyHaveOne() == true && isAloneNode0() == true) {
				for (int j = 1; j <= NodeSize; j++) {
					if (partyCheck[j])
						red += peopleData[j];
					else
						blue += peopleData[j];
				}
				// 이전 비교값보다 가장
				minNum = Math.min(minNum, Math.abs(red - blue));
				red = 0;
				blue = 0;
			}
			findAllWay(i + 1);
			partyCheck[i] = false;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 초기화 구간
		NodeSize = Integer.parseInt(br.readLine());
		partyCheck = new boolean[NodeSize + 1];
		isConn = new boolean[NodeSize + 1];
		board = new boolean[NodeSize + 1][NodeSize + 1];
		peopleData = new int[NodeSize + 1];
		int isLinkNotAvailable = 0; // 링크가 없는 [노드] 혹은 [노드셋] 카운트
		
		//노드별 인구수 입력
		String[] pplData = br.readLine().split(" ");
		for (int j = 1; j <= pplData.length; j++) {

			peopleData[j] = Integer.parseInt(pplData[j - 1]);

			totalPeople += peopleData[j];
		}
		
		//minNum의 최소값은 최대인구수의 -1 크기
		minNum = totalPeople - 1;
		
		// 노드가 2개면 이어져있든 말든 하나씩 차지해야하기 때문에 두 노드의 차이를 반환
		if (NodeSize == 2) {
			System.out.println(Math.abs(peopleData[1] - peopleData[2]));
			return;
		}

		// 각 노드별 데이터 입력
		for (int i = 1; i <= NodeSize; i++) {
			String[] linkData = br.readLine().split(" ");

			// 모든 노드가 인접한 구획이 없다면 -1, 하나의 노드만 인접하지 않다면 인구수 차이는 나머지 노드와 그 노드
			if (Integer.parseInt(linkData[0]) == 0) {
				isLinkNotAvailable++;
			}
			// 인접한 구획이 있는 노드의 데이터 입력
			else {
				for (int j = 1; j <= Integer.parseInt(linkData[0]); j++) {
					board[i][Integer.parseInt(linkData[j])] = true;
				}
			}
		}
		// 인접한 구획이 없는 노드가 2개 이상이라면 구획에 포함되지 않는 
		//노드셋이 3개 이상(이전에 노드가 2개인 경우 예외처리) 이므로 반환
		if (isLinkNotAvailable >= 2) {
			System.out.println(-1);
			return;
		}

		// 이어진 상태의 고립된 노드셋이 3쌍 이상인 경우
		int partedCount = 0;
		for (int j = 1; j <= NodeSize; j++) {
			// 2번 이상 돌렸는데도 검사되지 않은 노드가 존재하면 연결되지 않는 노드셋이 3개
			if (isConn[j] == false && partedCount == 2) {
				System.out.println(-1);
				return;
			}
			// isConn 배열을 돌며 연결이 확인되지 않는 노드부터 체크 시작
			else if (isConn[j] == false) {
				checkNodeSetint(j);
				apartedTwoPoint[partedCount] = j;
				partedCount++;
			}
		}
		// 2번째 검사 시행 == 고립된 노드셋이 2개 있다는 의미
		if (apartedTwoPoint[1] != 0) {
			System.out.println(Math.abs(forTwoSetBfs(apartedTwoPoint[0]) - forTwoSetBfs(apartedTwoPoint[1])));
			return;
		}

		// 모든 노드셋이 연결되어 있을 때 모든 노드를 돌며 두 정당의 최소차이 값 도출
//		for (int i = 1; i <= NodeSize; i++) {
//
//			partyCheck = new boolean[NodeSize + 1];
//			bfs(i);
//		}

		findAllWay(1);
		System.out.println(minNum);
	}
}