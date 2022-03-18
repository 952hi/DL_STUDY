import java.io.*;
import java.util.*;
public class boj1726 {
	static int row,col,map[][][],objdir,endx,endy;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine()," ");
		row = Integer.parseInt(stz.nextToken());
		col = Integer.parseInt(stz.nextToken());
		int dir = 0;
		map = new int[row][col][2];
		int val =0;
		for(int i=0;i<row;i++) {
			stz = new StringTokenizer(br.readLine());
			for(int j=0;j<col;j++) {
				val =Integer.parseInt(stz.nextToken());
				if(val==1) map[i][j][0] = -1;
			}
		}
		stz = new StringTokenizer(br.readLine());
		int startx=Integer.parseInt(stz.nextToken())-1;
		int starty=Integer.parseInt(stz.nextToken())-1;
		dir = Integer.parseInt(stz.nextToken())-1;
		
		stz = new StringTokenizer(br.readLine());
		endx =Integer.parseInt(stz.nextToken())-1;
		endy = Integer.parseInt(stz.nextToken())-1;
		objdir = Integer.parseInt(stz.nextToken())-1;
		if(startx != endx || endy != starty) {
			bfs(startx,starty,dir);
			System.out.println(map[endx][endy][1]);
		}else {
			System.out.println(checkdir(dir, objdir)-1);
		}
	}
	static int dxdy[][] = {{0,0,1,-1},{1,-1,0,0}};
	private static void bfs(int startx, int starty, int dir) {
		map[startx][starty][0] = dir;
		Queue<xy> q = new LinkedList<>();
		q.offer(new xy(startx,starty));
		xy comp;
		while(!q.isEmpty()) {
			comp = q.poll();
			int pointer = map[comp.x][comp.y][0];
			int nx,ny;
			int cnt =0;
			int value=0;
			while(true) {
				for(int i=1;i<=3;i++) {
					nx = comp.x + dxdy[0][pointer]*i;
					ny = comp.y + dxdy[1][pointer]*i;
					if(0<=nx && nx<row && 0<=ny && ny<col ) {
						if(map[nx][ny][0] == -1) break;
						value = map[comp.x][comp.y][1] +checkdir(map[comp.x][comp.y][0],pointer);
						
						if(map[nx][ny][1] == 0 || map[nx][ny][1] > value) {
							map[nx][ny][0] = pointer;
							map[nx][ny][1] = value;
							q.offer(new xy(nx, ny));
						}
						if(nx==endx && ny==endy) {
							if(map[nx][ny][0] != objdir) {
								map[nx][ny][1] += (checkdir(objdir,map[nx][ny][0])-1);
								map[nx][ny][0] = objdir;
							}
						}
					}
				}
				pointer= (pointer+1)%4; 
				if(cnt == 3) break;
				cnt ++;
			}
			
		}
	}
	static class xy{
		int x;
		int y;
		public xy(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	static int checkdir(int before, int after) {
		int result= 0;
		if(after == before)	return 1;
		
		if(before == 0) {
			if(after == 1) return 3;
			else return 2;
		}
		else if(before == 1) {
			if(after == 0) return 3;
			else return 2;
		}
		else if(before == 2) {
			if(after == 3) return 3;
			else return 2;
		}
		else if(before == 3) {
			if(after == 2) return 3;
			else return 2;
		}
		return result;
	}
}
