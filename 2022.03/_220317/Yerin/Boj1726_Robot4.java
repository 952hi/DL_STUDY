package _20220317;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*bfs*/
public class Boj1726_Robot4 {

	static int N, M;
	static int goal[];
	static char map[][];
	static int dx[], dy[];
	
	public static int getTurn(int from, int to) {
		int dxy[] = {0, 1, 3, 2, 4};//동서남북 => 시계방향과 매칭
		int dir = Math.abs(dxy[from]-dxy[to]);
		if(dir==3) dir=1;
		return dir;
	}

	public static int bfs(int x, int y, int d) {
		int result = Integer.MAX_VALUE;
		boolean visited[][][] = new boolean[N+2][M+2][4];//동서남북
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y, d, 0});//x좌표, y좌표, 방향, 거리
		
		while(!q.isEmpty()) {
			int[] node = q.poll();
			
			//도착지 오면
			if(node[0]==goal[0] && node[1]==goal[1] && node[2]==goal[2]) {
				result = node[3] + getTurn(node[2], goal[2]);
				break;
			}
			
			//1칸, 2칸, 3칸 전진
			for(int i=1;i<=3;i++) {
				int nx = node[0]+i*dx[node[2]];
				int ny = node[1]+i*dy[node[2]];
				
				if(map[nx][ny]=='1') break;//벽있으면 전진 멈춤
				
				if(!visited[nx][ny][node[2]-1]) {
					visited[nx][ny][node[2]-1] = true;
					q.offer(new int[] {nx, ny, node[2], node[3]+1});
				}
			}
			
			//4방향 탐색
			for(int i=1; i<=4; i++) {
				//방향 전환값
				int dir = getTurn(node[2], i);
				if(!visited[node[0]][node[1]][i-1]) {
					visited[node[0]][node[1]][i-1] = true;
					q.offer(new int[] {node[0], node[1], i, node[3]+dir});
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		//동서남북 = 오왼밑위
		dx = new int[] {0, 0, 0, 1, -1}; //맨 앞 칸 버림
		dy = new int[] {0, 1, -1, 0, 0};
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N+2][M+2];
		for(int i=1;i<=N;i++) {
			map[i][0] = '1';
			map[i][M+1] = '1';
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=M;j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		//벽세우기
		//가로
		for(int i=0; i<M+2; i++) {
			map[0][i] = '1';
			map[N+1][i] = '1';
		}
		
		//현재 로봇의 위치와 방향
		st = new StringTokenizer(br.readLine());
		int robot[] = new int[3];
		robot[0] = Integer.parseInt(st.nextToken());
		robot[1] = Integer.parseInt(st.nextToken());
		robot[2] = Integer.parseInt(st.nextToken());
		//도착지점 로봇의 위치와 방향
		goal = new int[3];
		st = new StringTokenizer(br.readLine());
		goal[0] = Integer.parseInt(st.nextToken());
		goal[1] = Integer.parseInt(st.nextToken());
		goal[2] = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs(robot[0], robot[1], robot[2]));
	}
}
