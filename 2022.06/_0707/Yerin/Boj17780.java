package _0707;

import java.io.*;
import java.util.*;

public class Boj17780 {
	static int N, K, map[][], nodes[][];//node정보
	static LinkedList<Integer> nodeMap[][];//node들의 위치
	
	public static int go() {
		int turn = 0;
		int dx[] = {0, 0, 0, -1, 1}, dy[] = {0, 1, -1, 0, 0};//1번부터 우좌상하
		while(turn++<=1000) {
			//말 0번부터 시뮬레이션
			for(int i=0; i<K; i++) {
				int x = nodes[i][0];
				int y = nodes[i][1];
				if(nodeMap[x][y].get(0)!=i) {
					continue;//제일 밑에 i번 말이 없으면 pass
				}
				int nx = x+dx[nodes[i][2]];
				int ny = y+dy[nodes[i][2]];
				if(nx<=0 || nx>N || ny<=0 || ny>N || map[nx][ny]==2) {//범위 밖 | 파란색 = 방향 전환
					//방향 전환
					nodes[i][2] = (nodes[i][2]%2==1)? nodes[i][2]+1:nodes[i][2]-1;
					//이동
					nx = x+dx[nodes[i][2]];
					ny = y+dy[nodes[i][2]];
					if(nx<=0 || nx>N || ny<=0 || ny>N || map[nx][ny]==2){continue;}
				}
				
				if(map[nx][ny] == 1) {//빨간색 = 뒤집어 옮기기
					int size = nodeMap[x][y].size();
					for(int j=size-1; j>=0; j--) {
						int su = nodeMap[x][y].get(j);
						nodes[su][0] = nx;
						nodes[su][1] = ny;
						nodeMap[nx][ny].add(su);
					}
					nodeMap[x][y] = new LinkedList<>();
				}
				else {//흰색 = 옮기기
					for(int su : nodeMap[x][y]) {
						nodes[su][0] = nx;
						nodes[su][1] = ny;
						nodeMap[nx][ny].add(su);
					}
					nodeMap[x][y] = new LinkedList<>();//옮기고 리셋
				}
				//4개 이상 쌓이면 종료
				if(nodeMap[nx][ny].size()>=4) {
					return turn;
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		nodes = new int[K][3];//row, c, d
		nodeMap = new LinkedList[N+1][N+1];
		
		//체스판 입력 => 0:흰, 1:빨, 2:파
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				//초기화
				nodeMap[i][j] = new LinkedList<>();
			}
		}
		
		//말 정보 => 행, 열, 방향(우좌상하)
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			nodeMap[r][c].add(i);
			nodes[i] = new int[] {r, c, d};
		}
		
		System.out.println(go());
	}

}