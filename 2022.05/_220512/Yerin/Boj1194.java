package _0512;

//달이 차오른다, 가자
//A = 65, a = 97
import java.util.*;
import java.io.*;

public class Boj1194 {
	static int N, M;
	static char map[][];
	static boolean visited[][][];//가지고 있는 열쇠에 따른 방문체크
	
	public static int bfs(int x, int y) {
		int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
		int result = 0;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x, y, 0});//출발 = 아무 열쇠도 없음
		visited[x][y][0] = true;
		
		while(!q.isEmpty()) {
			int size = q.size();
			result+=1;
			while(size-- > 0) {
				int now[] = q.poll();
				if(map[now[0]][now[1]]=='1') {
					return result-1;
				}
				for(int i=0; i<4; i++) {
					int nx = now[0]+dx[i];
					int ny = now[1]+dy[i];
					if(0<=nx && nx<N && 0<=ny && ny<M) {
						if(map[nx][ny]=='#' || visited[nx][ny][now[2]]) { continue;}//벽 || 이미 같은 키로 방문 => pass
						
						int keys = now[2];
						if(65<=map[nx][ny] && map[nx][ny]<71) {//문
							int num = map[nx][ny]-65;
							if((keys & (1<<num))==0) {continue;}//열쇠가 없다면 pass
						}
						else if(97<=map[nx][ny] && map[nx][ny]<103) {//열쇠
							keys = keys | (1<<(map[nx][ny]-97));
						}
						visited[nx][ny][keys] = true;
						q.offer(new int[] {nx, ny, keys});
					}
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][1<<6];//a, b, c, d, e, f + 1
		
		int x =0, y = 0;//현재 위치
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j]=='0') {
					x=i;
					y=j;
				}
			}
		}
		System.out.println(bfs(x, y));
	}
}