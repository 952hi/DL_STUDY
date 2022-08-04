package _0801;

import java.io.*;
import java.util.*;

//루프가 없다면, 미래가 같다.
//루프가 있는지만 따로 check해주자
public class Boj1103 {
	static int N, M, map[][], result, dp[][];
	static boolean flag, visited[][];

	public static void dfs(int x, int y, int cnt) {
		result = Math.max(result, cnt);
		if(flag) { return;}
		int dx[] = { -1, 0, 1, 0 }, dy[] = { 0, 1, 0, -1 };
		for(int i=0; i<4; i++) {
			int nx = x + map[x][y]*dx[i];
			int ny = y + map[x][y]*dy[i];
			if(0<=nx && nx<N && 0<=ny && ny<M && map[nx][ny]<10) {
				if(visited[nx][ny]) {
					flag = true;
					return;
				}
				if(cnt+1 <= dp[nx][ny]) {continue;}
				dp[nx][ny] = cnt+1;
				visited[nx][ny] = true;
				dfs(nx, ny, cnt+1);
				visited[nx][ny] = false;
				
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		dp[0][0] = 1;
		visited[0][0] = true;
		result = 1;
		flag = false;//사이클이 없다.
		dfs(0, 0, 1);
		if(flag) {
			System.out.println(-1);
		}
		else {
			System.out.println(result);
		}
	}

}
/*
4 3
133
5HH
HHH
21H

5
 */