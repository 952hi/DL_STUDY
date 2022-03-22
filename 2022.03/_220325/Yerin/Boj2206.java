package _20220324;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2206 {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	
	public static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y, 0});//좌표, 부순 벽 개수
		int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
		
		int[] node;
		int d = 0, result=-1;
		outloop : while(!q.isEmpty()) {
			d++;
			for(int j=0, size = q.size();j<size;j++) {
				node = q.poll();
				if(node[0]==N-1 && node[1]==M-1) {
					result = d;
					break outloop;
				}
				for(int i=0;i<4;i++) {
					int nx = node[0] + dx[i];
					int ny = node[1] + dy[i];
					//벽을 안 뚫고 먼저 방문했다면 pass, 한번도 방문안했으면 무조건 들어감
					if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny] > node[2]) {
						if(map[nx][ny]==0) {
							visited[nx][ny] = node[2];
							q.offer(new int[] {nx, ny, node[2]});
						}
						else if(map[nx][ny]==1 && node[2]==0){//벽인데, 아직 안 뚫고 갔다면
							visited[nx][ny] = 1;
							q.offer(new int[] {nx, ny, 1});
						}
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
		visited = new int[N][M];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				map[i][j] = br.read()-'0';
				visited[i][j] = 2;
			}
			br.readLine();
		}
		System.out.println(bfs(0, 0));
	}
}
