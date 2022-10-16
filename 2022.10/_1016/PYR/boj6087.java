package _1016;
//상우하좌 0 1 2 3

import java.util.*;
import java.io.*;

public class boj6087 {
	static int h, w, visited[][], point[][];
	static char map[][];
	
	//입력값 : 시작점 C
	public static void bfs(int x, int y) {
		int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
		Queue<int[]> pq = new LinkedList<>();
		//좌표x, y, 거울 수, 들어온 방향
		//시작값 넣기
		for(int i=0; i<4; i++) {
			int nx = x + dx[i], ny = y + dy[i];
			if(0<=nx && nx<h && 0<=ny && ny<w && map[nx][ny]!='*') {
				visited[nx][ny] = 0;
				pq.add(new int[] {nx, ny, 0, i});
			}
		}
		visited[x][y] = 0;
		
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
 			x = now[0];
			y = now[1];
			//도착지면 stop
			if(point[1][0] == x && point[1][1] == y) { continue;}
			if(visited[x][y] < now[2]) {continue;}
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(0<=nx && nx<h && 0<=ny && ny<w && map[nx][ny]!='*') {
					if(now[3]==i && visited[nx][ny] >= now[2]) {//직진
						visited[nx][ny] = now[2];
						pq.add(new int[] {nx, ny, now[2], now[3]});
					}
					else if((now[3]+2)%4!=i && visited[nx][ny] >= now[2]+1) {//90도 회전
						visited[nx][ny] = now[2]+1;
						pq.add(new int[] {nx, ny, now[2]+1, i});
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		visited = new int[h][w];
		map = new char[h][w];
		point = new int[2][2];//C의 좌표값
		String s;
		int k = 0;
		for(int i=0; i<h; i++) {
			s = br.readLine();
			for(int j=0; j<w; j++) {
				map[i][j] = s.charAt(j);
				visited[i][j] = 999_999_999;
				//출발지 & 도착지 저장
				if(map[i][j]=='C') {
					point[k][0] = i;
					point[k][1] = j;
					k++;
				}
			}
		}
		//탐색
		bfs(point[0][0], point[0][1]);
		System.out.println(visited[point[1][0]][point[1][1]]);
	}

}
