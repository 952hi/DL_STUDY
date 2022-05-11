import java.io.*;
import java.util.*;
public class boj1194ver2 {
	static int row,col,movecnt;
	static char map[][];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(stz.nextToken());
		col = Integer.parseInt(stz.nextToken());
		movecnt = Integer.MAX_VALUE;
		map = new char[row][col];
		
		for(int i=0;i<row;i++)map[i] = br.readLine().toCharArray();
		if(row == 1 && col == 1) System.out.println(0);
		else {
		out:for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(map[i][j]=='0') {
						bfs(i,j);
						if(movecnt==Integer.MAX_VALUE) System.out.println(-1);
						else System.out.println(movecnt);
						break out;
					}
				}
			}
		}
	}
	static int[][] dxdy = { { 0, 0, 1, -1 }, { 1, -1, 0, 0 } };
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.offer(new int[] {x,y,0,0});//0 : x 1: y 2: bit 3:move
		boolean visited[][][] = new boolean[row][col][65];
		visited[x][y][0] = true;
		
		int[] comp;
		int nx,ny,check;
		while(!q.isEmpty()) {
			comp = q.poll();
			if(map[comp[0]][comp[1]]=='1') {
				movecnt = Math.min(movecnt, comp[3]);
			}
			for(int i=0;i<4;i++) {
				nx = comp[0] + dxdy[0][i];
				ny = comp[1] + dxdy[1][i];
				// 범위 안이고 벽이 아니지?
				if(0<=nx && nx<row  && 0<=ny && ny<col && map[nx][ny]!='#' && !visited[nx][ny][comp[2]]) {
					// 대문자 체크
					if(map[nx][ny]-0>=65 && map[nx][ny]-0<=70 && (comp[2]&1<<map[nx][ny]-'A')==0) continue;
					//소문자 체크
					if(map[nx][ny]-0>=97 && map[nx][ny]-0<=102) {
						if((comp[2]&1<<map[nx][ny]-'a')==0) {
							check = comp[2]|1<<map[nx][ny]-'a';
							q.offer(new int[] {nx,ny,check,comp[3]+1});
							visited[nx][ny][check] = true;
							continue;
						}
					}
					q.offer(new int[] {nx,ny,comp[2],comp[3]+1});
					visited[nx][ny][comp[2]]=true;
				}
			}
		}
	}
}