package _0421;

import java.io.*;
import java.util.*;

public class Boj1520 {
	static int N, M, map[][], cnt[][];
	
	public static int dfs(int x, int y) {
		int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
		
		if(x==N-1 && y==M-1) { return 1; }//목적지
		
		cnt[x][y] = 0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(0<=nx && nx<N && 0<=ny && ny<M && map[nx][ny] < map[x][y]) {//내리막길이면 가자
				if(cnt[nx][ny]!=-1) {//방문했다면
					cnt[x][y] += cnt[nx][ny];
				}
				else {//방문 안 했다면
					cnt[x][y]+=dfs(nx, ny);//왔던 길에 더하기
				}
			}
		}
		return cnt[x][y]; //네 방향 모두 둘러보고 업데이트 했다면
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cnt = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cnt[i][j] = -1;//가는 길의 개수, -1은 아직 방문 전
			}
		}
		dfs(0, 0);
		System.out.println(cnt[0][0]);
	}
}
