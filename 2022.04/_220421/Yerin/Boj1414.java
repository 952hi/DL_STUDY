package _0421;

import java.io.*;
import java.util.*;

public class Boj1414 {
	static int parent[];
	
	public static class Edge implements Comparable<Edge> {//간선 정보
		int n1, n2, len;

		public Edge(int n1, int n2, int len) {
			this.n1 = n1;
			this.n2 = n2;
			this.len = len;
		}

		@Override
		public int compareTo(Edge o) {//오름차순
			return this.len - o.len;
		}
	}
	
	public static int findSet(int n) {
		if(parent[n]==0) {return n;}
		else {return parent[n] = findSet(parent[n]);}
	}
	
	public static boolean union(int n1, int n2) {
		n1 = findSet(n1);
		n2 = findSet(n2);
		if(n1==n2) {return false;}
		
		parent[n1] = n2;
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int map[][] = new int[n+1][n+1];
		parent = new int[n+1];//0이면 root
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int result = 0;
		
		for(int i=1; i<=n; i++) {
			String s = br.readLine();
			for(int j=1; j<=n; j++) {
				char c = s.charAt(j-1);
				if(c=='0') {
					map[i][j] = Integer.MAX_VALUE;
				}
				else {
					map[i][j] = (c <= 90) ? c-38 : c-96; //대문자 : 소문자
					result+=map[i][j];
				}
			}
		}
		
		//i=>j, j=>i 중 작은 값만 넣기
		for(int i=1; i<=n; i++) {
			for(int j=i+1; j<=n; j++) {
				int min = Math.min(map[i][j], map[j][i]);
				if(min==Integer.MAX_VALUE) {continue;}//연결이 없다
				pq.offer(new Edge(i, j, min));
			}
		}
		
		//다익스트라
		int cnt = 1;
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(!union(edge.n1, edge.n2)) {continue;}
			result-=edge.len;
			if(++cnt==n) {break;}//다 연결했으면 종료
		}
		
		result = cnt==n ? result : -1;
		System.out.println(result);
	}
}
