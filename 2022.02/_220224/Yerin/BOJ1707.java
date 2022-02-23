package day20;

import java.util.Scanner;
/*
 * 백준 1707번 : 이분 그래프
 * 시간 : 2828ms
 */
public class BOJ1707 {
	static class Node{
		int to;
		Node link;
		
		public Node(int to, Node link) {
			super();
			this.to = to;
			this.link = link;
		}
	}
	static int V, E;
	static Node[] nodes;
	static String answer;
	static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			answer = "YES";
			V = sc.nextInt();
			E = sc.nextInt();
			visited = new boolean[V+1];
			nodes = new Node[V+1];
			//입력(인접리스트)
			for(int i=0;i<E;i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				nodes[from] = new Node(to, nodes[from]);
				nodes[to] = new Node(from, nodes[to]);
			}
			//정점단위가 아니라 dfs로 하나로 연결된 그래프 단위로 탐색
			for(int i=1;i<=V;i++) {
				//이미 방문했음 or 연결된 것이 없음
				if(visited[i] || nodes[i]==null) continue;
				//아직 방문안했으면 돌아보자
				visited[i] = true;
				dfs(i, 0, new int[V+1]);
			}
			System.out.println(answer);
		}
	}
	
	public static void dfs(int v, int cnt, int[] distance) {
		distance[v] = cnt;
		for(Node head = nodes[v]; head!=null; head = head.link) {
			if(visited[head.to]) {//방문했던 곳 =사이클
				int dis = cnt-distance[head.to];
				//사이클에 속한 노드의 개수가 홀수라면 (1<=>3과 같이 직접 연결된 경우는 제외하기 위해 >1조건 생성)
				if(dis>1 && dis%2==0) {
					answer = "NO";
					return;
				}
				continue; //방문했던 곳이면 더 가지 않아도 됨.
			}
			//방문 안했던 곳이면, 방문하자
			visited[head.to] = true;
			dfs(head.to, cnt+1, distance);
		}
	}
}
