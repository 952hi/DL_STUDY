import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Boj4485 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		int[][] map;
		int[][] distance;
		// 우 , 하 방향 -> X
		// 상하좌우
		int tc=1;
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		while ((N = Integer.parseInt(br.readLine())) != 0) {
			
			map = new int[N][N];
			distance = new int[N][N];
			int[] xy = new int[2];
			int nx=0,ny=0;
			for (int k = 0; k < N; k++) {
				map[k] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			}
			for (int l = 0; l < N; l++) {
				for(int m=0;m<N;m++) {
					distance[l][m] = Integer.MAX_VALUE;
				}
			}
			Queue<int[]> q = new LinkedList<>();
			q.offer(new int[]{0,0});
			distance[0][0] = map[0][0];
			while(!q.isEmpty()) {
				xy = q.poll();
				
				for(int i=0;i<dx.length;i++) {
					nx = xy[0]+dx[i];
					ny = xy[1]+dy[i];
					if(0<=nx && nx<N && 0<=ny && ny<N ) {
						if(distance[nx][ny] > distance[xy[0]][xy[1]]+map[nx][ny]) {
							distance[nx][ny] = distance[xy[0]][xy[1]]+map[nx][ny];
							q.offer(new int[] {nx,ny});
						}
							
					}
				}
				
			}
			System.out.println("Problem "+tc+": "+distance[N-1][N-1]);
			tc++;
		}
	}

}
