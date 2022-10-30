package _1030.LC;

import java.util.*;
import java.io.*;

public class BOJ_G4_거짓말 {
	// 정답: 파티 최대 개수를 출력

	// 1. 우선 진실을 아는 사람들이 있는 파티에선 거짓말 불가
	// 2. 진실을 아는 사람들과 함께 있던 사람들은 파티에서 거짓말 불가
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int people = Integer.parseInt(st.nextToken());
		int party = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int truth = Integer.parseInt(st.nextToken());

		// 해당 사람이 진실을 말하는 사람인지 저장
		boolean[] truthArr = new boolean[people + 1];
		// 진실을 말하는 사람의 번호를 저장
		int[] truths = new int[people + 1];
		// 진실을 아는 사람들을 저장
		Queue<Integer> q = new LinkedList<>();
		// 진실을 아는 사람들을 구분
		// 파티별 방문 처리 및 거짓말 가능 표시 배열
		// 0: 미방문, 1: 방문했지만 진실을 아는 사람 없음, 2: 방문했고 진실을 아는 사람들 존재
		int[] visit = new int[party];
		int known;
		for (int i = 0; i < truth; i++) {
			known = Integer.parseInt(st.nextToken());
			truthArr[known] = true;
			truths[i] = known;
			q.add(known);
		}

		int players;
		// 사람별 참석 파티 저장
		Map<Integer, List<Integer>> peopleToParty = new HashMap<>();

		// 파티 별 사람의 수 출력
		Map<Integer, List<Integer>> partyToPeople = new HashMap<>();
		int eachPeople;

		for (int i = 0; i < party; i++) {
			st = new StringTokenizer(br.readLine());
			players = Integer.parseInt(st.nextToken());

			List<Integer> partyPeople = new LinkedList<>();
			for (int j = 0; j < players; j++) {
				// 파티별 참석자를 입력
				eachPeople = Integer.parseInt(st.nextToken());
				// 각 파티에 참석한 사람의 명단 출력
				partyPeople.add(eachPeople);

				// 각 사람별 참석 파티 리스트를 호출
				if (peopleToParty.containsKey(eachPeople)) {
					List<Integer> tmp = peopleToParty.get(eachPeople);
					tmp.add(i);
					peopleToParty.put(eachPeople, tmp);
				}
				// Hash에 해당 사람이 저장되어있지 않을 때
				else {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(i);
					peopleToParty.put(eachPeople, tmp);
				}
			}
			// 각 파티에 참석하는 사람의 목록을 저장
			partyToPeople.put(i, partyPeople);
		}

		while (!q.isEmpty()) {
			// 진실을 아는 사람
			known = q.poll();

			// 진실을 아는 사람들이 속한 파티 순회
			if (peopleToParty.get(known) == null)
				continue;

			for (int i : peopleToParty.get(known)) {
				if (visit[i] == 0) {
					visit[i] = 2;
					for (int j : partyToPeople.get(i)) {
						q.add(j);
					}
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < party; i++) {
			if (visit[i] == 0) {
				answer++;
			}
		}
		System.out.println(answer);
	}
}