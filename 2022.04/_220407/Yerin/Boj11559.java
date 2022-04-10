import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj11559 {
	static int N, M;
	static char map[][];
	static boolean visited[][];
	static Queue<int[]> puyo, nVisit;
	
	public static void dfs(char c, int x, int y) {
		nVisit.add(new int[] {x, y});
		
		int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
		int nx, ny;
		for(int i=0; i<4; i++) {
			nx = x + dx[i];
			ny = y + dy[i];
			if(0<=nx && nx<N && 0<=ny && ny<M && map[nx][ny]==c && !visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(c, nx, ny);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 12; 
		M = 6;
		map = new char[N][M];
		puyo = new LinkedList<>();
		for(int i=0;i<N;i++) {
			String s = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]!='.')
					puyo.offer(new int[] {i, j});
			}
		}
		
		int result = 0;
		boolean pop = false;
		while(!puyo.isEmpty()) {
			int len = puyo.size();
			visited = new boolean[N][M];
			for(int i=0;i<len;i++) {
				int[] pos = puyo.poll();
				
				if(!visited[pos[0]][pos[1]]) {
					visited[pos[0]][pos[1]] = true;
					nVisit = new LinkedList<>();
					dfs(map[pos[0]][pos[1]], pos[0], pos[1]);
					if(nVisit.size()>=4) {//같은 색 뿌요가 모인 개수
						for(int[] p : nVisit) {
							map[p[0]][p[1]] = '.';
						}
						pop = true;
					}
				}
			}
			//터진게 없으면 종료
			if(pop) {
				result++;
				pop = false;
			}
			else break;
			//아래로 떨어뜨리기 => 방문했던 곳만 check
			int step = 0;
			for(int i=0; i<M; i++) {
				step = 0;
				for(int j=N-1; j>=0; j--) {
					if(visited[j][i]==false) break;
					if(map[j][i]=='.') {
						step++;
					}
					else {
						puyo.add(new int[] {j+step, i});
						if(step!=0) {
							map[j+step][i] = map[j][i];
							map[j][i] = '.';
						}
					}
				}
			}
		}
		System.out.println(result);
	}
}
