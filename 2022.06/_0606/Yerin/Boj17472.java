package _0606;

import java.util.*;
import java.io.*;

public class Boj17472 {
	static int N, M, map[][];
	
	//번호를 2~섬의 개수+1
	public static void dfs(int x, int y, int no) {
		int dx[] = {-1, 0, 1, 0}, dy[]= {0, 1, 0, -1};
		for(int i=0; i<4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			if(0<=nx && nx<N && 0<=ny && ny<M && map[nx][ny]==1) {
				map[nx][ny] = no;
				dfs(nx, ny, no);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//1. 섬 번호 붙이기
		int no=2;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1) {
					map[i][j]=no;
					dfs(i, j, no++);
				}
			}
		}
		//2. 거리 측정
		int distance[][] = new int[no][no]; //i => j까지 거리저장
		for(int i=2; i<no; i++) {
			Arrays.fill(distance[i], 999_999_999);
		}
		//2-1. 가로
		int prev = 0, zero = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]>1) {
					if(prev==0) {
						prev = map[i][j];
					}
					else {
						if(prev!=map[i][j] && zero > 1) {
							int now = map[i][j];
							distance[prev][now] = Math.min(distance[prev][now], zero);
							distance[now][prev] = distance[prev][now];
						}
						prev = map[i][j];
					}
					zero=0;
				}
				else {
					zero++;
				}
			}
			prev = 0;//이전 없음
			zero = 0;
		}
		//2-2. 세로
		prev = 0; 
		zero = 0;
		for(int j=0; j<M; j++) {
			for(int i=0; i<N; i++) {
				if(map[i][j]>1) {
					if(prev==0) {
						prev = map[i][j];
					}
					else {
						if(prev!=map[i][j] && zero>1) {
							int now = map[i][j];
							distance[prev][now] = Math.min(distance[prev][now], zero);
							distance[now][prev] = distance[prev][now];
						}
						prev = map[i][j];
					}
					zero=0;
				}
				else {
					zero++;
				}
			}
			prev = 0;//이전 없음
			zero = 0;
		}
		//3. MST 구하기
		int result = 0;
		boolean visited[] = new boolean[no];
		visited[2] = true;//2부터 시작
		for(int i=3; i<no; i++) {
			//최소값
			int idx = -1;
			int min = 999_999_999;
			for(int j=3; j<no; j++) {
				if(!visited[j] && distance[2][j]<min) {
					min = distance[2][j];
					idx = j;
				}
			}
			
			//
			if(min==999_999_999) {
				result = -1;
				break;
			}
			result+=min;
			visited[idx] = true;
			
			//업데이트
			for(int j=3; j<no; j++) {
				if(!visited[j]) {
					distance[2][j] = Math.min(distance[2][j], distance[idx][j]);
				}
			}
		}
		System.out.println(result);
	}
}