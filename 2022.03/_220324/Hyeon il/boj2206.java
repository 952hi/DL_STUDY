import java.io.*;
import java.util.*;
public class boj2206 {
	static int row,col;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine()," ");
		row = Integer.parseInt(stz.nextToken());
		col = Integer.parseInt(stz.nextToken());
		map = new int[row][col];
		String comp;
		for(int i=0;i<row;i++) {
			comp = br.readLine();
			for(int j=0;j<col;j++) {
				if(comp.charAt(j)=='1') map[i][j] = -1;
			}
		}
		bfs();
	}
	static int dxdy[][] = {{0,0,1,-1},{-1,1,0,0}};
	private static void bfs() {
		boolean visited[][][] = new boolean[row][col][2];
		Queue<Data> q = new LinkedList<>();
		q.offer(new Data(0, 0,1,false));
		Data temp;
		while(!q.isEmpty()) {
			temp = q.poll();
			if(row-1 == temp.x && col-1 == temp.y) {
				System.out.println(temp.dist);
				return;
			}
			int nx,ny;
			for(int i=0;i<4;i++) {
				nx = temp.x +dxdy[0][i];
				ny = temp.y +dxdy[1][i];
				if(0<= nx && nx <row && 0<=ny && ny<col) {
					int val = temp.dist + 1;
					if(map[nx][ny]==0) {
						if(!temp.check && !visited[nx][ny][0]) {
							visited[nx][ny][0] = true;
							q.offer(new Data(nx, ny, val, false));
						}else if(temp.check && !visited[nx][ny][1]){
							visited[nx][ny][1] = true;
							q.offer(new Data(nx, ny, val, true));
						}
					}else if(map[nx][ny]== -1) {
						if(!temp.check) {
							visited[nx][ny][1] = true;
							q.offer(new Data(nx, ny, val, true));
						}
					}
				}
			}
		}
		System.out.println(-1);
	}
	static class Data{
		int x;
		int y;
		int dist;
		boolean check;
		public Data(int x, int y,int dist,boolean check) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.check = check;
		}
	}
}
