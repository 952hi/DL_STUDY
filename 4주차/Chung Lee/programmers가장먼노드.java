package problemSolving;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//i:0,j:0 ed:3
//i:0,j:1 ed:6
//i:1,j:0 ed:4
//i:1,j:1 ed:3
//i:2,j:0 ed:3
//i:2,j:1 ed:2
//i:3,j:0 ed:1
//i:3,j:1 ed:3
//i:4,j:0 ed:1
//i:4,j:1 ed:2
//i:5,j:0 ed:2
//i:5,j:1  ed:4
//i:6,j:0 ed:5
//i:6,j:1 ed:2
public class programmers가장먼노드 {
	static class Solution1 {
		public static int solution(int n, int[][] edge) throws FileNotFoundException {

			// 큐 라이브러리 활용
			Queue<Integer> q = new LinkedList<>();

			// n+1을 해서 이해하기 쉽게 구현
			boolean[][] edges = new boolean[n + 1][n + 1];
			// 노드 방문 확인 배열
			int[] node = new int[n + 1];
			// 두 간선들이 모두 연결되어 있음
			for (int i = 0; i < edge.length; i++) {
				edges[edge[i][0]][edge[i][1]] = true;
				edges[edge[i][1]][edge[i][0]] = true;
			}
			// 현재 노드와 1 사이의 거리
			int distance = 0;
			// 가장 먼 노드가 몇개인지 카운트
			int ans = 0;
			// 1을 넣고 시작
			q.add(1);
			node[1] = 1;
			distance++;
			int nextQ = 0;
			while (true) {
				// q.poll()은 아무 값이 없을 때 null을 반환하기
				try {
					nextQ = q.poll();
				} catch (NullPointerException e) {
					break;
				}
				distance = node[nextQ] + 1;
				for (int j = 1; j <= n; j++) {
					if (edges[nextQ][j] == true && node[j] == 0) {
						node[j] = distance;
						q.add(j);
					}
				}
			}
			// 가장 먼 노드를 카운트
			for (int i : node) {
				if (i == distance - 1) {
					ans++;
				}
			}

			// System.out.println(ans);
			return ans;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileReader(new File("4주차/problemSolving/input.txt")));
		int n = sc.nextInt();
		int[][] edge = new int[7][2];
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 2; j++) {
				edge[i][j] = sc.nextInt();
			}
		}
		Solution1.solution(n, edge);
	}
}
