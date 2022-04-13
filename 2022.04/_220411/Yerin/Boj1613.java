package _0410;

import java.io.*;
import java.util.*;

public class Boj1613 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		boolean adjMatrix[][] = new boolean[N+1][N+1];
		
		int K = Integer.parseInt(st.nextToken());
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			adjMatrix[n1][n2] = true;
		}
		
		//플로이드
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				if(i==k || !adjMatrix[i][k]) continue;
				for(int j=1; j<=N; j++) {
					if(adjMatrix[k][j]) {
						adjMatrix[i][j] = true;
					}
				}
			}
		}
		
		//출력
		int S = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<S;i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			if(adjMatrix[n1][n2]) {//n1이 먼저 일어났다면
				sb.append(-1).append("\n");
			}
			else if(adjMatrix[n2][n1]) {
				sb.append(1).append("\n");
			}
			else {
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
