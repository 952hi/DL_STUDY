package _0530;

import java.io.*;
import java.util.*;

public class Boj16946 {
	static int N, M, map[][], no;
	static int dx[] = {-1, 0, 1, 0}, dy[]= {0, 1, 0, -1};
	
	public static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		int result = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int i=0; i<4; i++) {
				int nx = now[0]+dx[i];
				int ny = now[1]+dy[i];
				if(0<=nx && nx<N && 0<=ny && ny<M) {
					if(map[nx][ny]==0) {
						map[nx][ny] = no;
						result++;
						q.add(new int[] {nx, ny});
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		int group[] = new int[N*M+2];
		no=1;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j)-'0';
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==0) {
					map[i][j] = ++no;
					group[no] = bfs(i, j);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) {
					int result = 1;
					Set<Integer> set = new HashSet<>();
					for(int k=0; k<4;k++) {
						int nx = i+dx[k];
						int ny = j+dy[k];
						if(0<=nx && nx<N && 0<=ny && ny<M && map[nx][ny]>1) {
							if(!set.contains(map[nx][ny])) {
								set.add(map[nx][ny]);
								result+=group[map[nx][ny]];
							}
						}
					}
					sb.append(result%10);
				}
				else {
					sb.append(0);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
