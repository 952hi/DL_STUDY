package _0519;

import java.util.*;
import java.io.*;

public class Boj1765 {
	static LinkedList<Integer> relation[][];
	static boolean visited[];
	
	//친구의 친구는 친구
	//원수의 원수는 친구
	public static void dfs(int n) {
		for(int friend: relation[1][n]) {
			if(visited[friend]) {continue;}
			visited[friend] = true;
			dfs(friend);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		relation = new LinkedList[2][N+1];
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			relation[0][i] = new LinkedList<>();
			relation[1][i] = new LinkedList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = st.nextToken().charAt(0)-'E';
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			relation[r][n1].offer(n2);
			relation[r][n2].offer(n1);
		}
		
		//원수의 원수 => 원수 = 0
		for(int i=1; i<=N; i++) {
			for(int j=0; j<relation[0][i].size(); j++) {
				int enemy = relation[0][i].get(j);//i의 원수
				for(int k=0; k<relation[0][enemy].size(); k++) {
					int f = relation[0][enemy].get(k);//i의 원수의 원수 = 친구
					if(i<f) {
						relation[1][i].add(f);
					}
				}
			}
		}
		
		//dfs
		int result = 0;
		for(int i=1; i<=N; i++) {
			if(visited[i]) {continue;}
			visited[i] = true;
			dfs(i);
			result++;
		}
		System.out.println(result);
	}
}