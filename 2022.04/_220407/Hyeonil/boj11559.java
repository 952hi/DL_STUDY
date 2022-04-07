import java.io.*;
import java.util.*;
public class boj11559 {
	static int row=12,col=6,map[][]=new int[12][6],res=0;
	static int[][] dxdy = {{0,0,1,-1},{1,-1,0,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp;
		for(int i=0;i<12;i++) {
			temp = br.readLine();
			for(int j=0;j<6;j++) {
				if(temp.charAt(j)=='R') {
					map[i][j] = 1;
				}else if(temp.charAt(j)=='G') {
					map[i][j] = 2;
				}else if(temp.charAt(j)=='B') {
					map[i][j] = 3;
				}else if(temp.charAt(j)=='Y') {
					map[i][j] = 4;
				}else if(temp.charAt(j)=='P') {
					map[i][j]=5;
				}
			}
		}
		//check -> 4개 이상이 있는지 있으면 폭발
		//폭발 후 내리고 또 체크
		//체크할때 한번도 일어나지않았다는 말은 전체 보드에서 4개짜리가 없다는말
		//브레이크 후 종료
		for(int i=0;i<row;i++) System.out.println(Arrays.toString(map[i]));
		System.out.println();
		while(true) {
			int value=0;
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(map[i][j]!=0) {
						if(check(i,j,0)) {
							check(i,j,1);
							value++;
						}
					}
				}
			}
			down();
			if(value>=2) res-= value-1;
			if(value==0) {
				break;
			}
			for(int i=0;i<row;i++) System.out.println(Arrays.toString(map[i]));
			System.out.println();
		}
		System.out.println(res);
	}
	private static void down() {
		for (int i = row-1; i >= 0; i--) {
			for (int j = 0; j < col; j++) {
				if(map[i][j]==0) {
					int dx=i-1;
					while(dx>=0) {
						if(map[dx][j]!=0) {
							map[i][j] = map[dx][j];
							map[dx][j]=0;
							break;
						}
						dx--;
					}
				}
			}
		}
	}
	private static boolean check(int x,int y, int mod) {
		boolean visited[][] = new boolean[12][6];
		int check = 1;
		int val =map[x][y];
		if(mod == 1) {
			map[x][y] = 0;
		}
		Queue<Data> q = new LinkedList<>();
		visited[x][y] =true;
		q.offer(new Data(x, y));
		Data comp;
		int nx,ny;
		while(!q.isEmpty()) {
			comp = q.poll();
			for(int i=0;i<4;i++) {
				nx = comp.x+dxdy[0][i];
				ny = comp.y+dxdy[1][i];
				if(0<=nx && nx<row && 0<=ny && ny<col && !visited[nx][ny] && map[nx][ny]==val) {
					if(mod == 0) {
						visited[nx][ny] = true;
						q.offer(new Data(nx, ny));
					}else if(mod == 1){
						visited[nx][ny] = true;
						map[nx][ny] = 0;
						q.offer(new Data(nx, ny));
					}
					check++;
					
				}
			}
		}
		if(check>=4) {
			if(mod==1) res++;
			return true;
		}else return false;
	}
	static class Data{
		int x;
		int y;
		public Data(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}