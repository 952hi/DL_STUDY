import java.io.*;
import java.util.*;
public class boj17135 {
	static int row,col,dist,map[][],count,res,rest;
	static boolean isSelected[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine()," ");
		row = Integer.parseInt(stz.nextToken());
		col = Integer.parseInt(stz.nextToken());
		dist = Integer.parseInt(stz.nextToken());
		map = new int[row+1][col];
		isSelected = new boolean[col];
		res =0;
		for(int i=0;i<row;i++) {
			stz = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<col;j++) {
				map[i][j] = Integer.parseInt(stz.nextToken());
			}
		}
		combi(0,0);
		System.out.println(res);
	}
	static int temp[][],hunter[];
	private static void combi(int idx,int cnt) {
		if(cnt == 3) {
			temp =new int[row+1][col];
			hunter = new int[3];
			int index =0;
			for(int i=0;i<row+1;i++) System.arraycopy(map[i], 0, temp[i], 0, col);
			for(int i=0;i<col;i++) {
				if(isSelected[i]) {
					hunter[index++]=i;
				}
			}
			count= 0;
			hunt();
			res = Math.max(count, res);
			return;
		}
		if(idx>=col) return;
		isSelected[idx] =true;
		combi(idx+1, cnt+1);
		isSelected[idx] = false;
		combi(idx+1, cnt);
	}
	private static void hunt() {
		Data target[] = {new Data(100,100, Integer.MAX_VALUE)
				,new Data(100,100, Integer.MAX_VALUE)
				,new Data(100,100, Integer.MAX_VALUE)};
		int check=0;
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(temp[i][j]==1) {
					for(int k=0;k<3;k++) {
						check=distance(hunter[k],i,j);
						if(check<=dist) {
							if(target[k].dist>check) {
								target[k].dist = check;
								target[k].x = i;
								target[k].y = j;
								
							}else if(target[k].dist==check) {
								if(target[k].y > j) {
									target[k].x = i;
									target[k].y = j;
								}
							}
						}
					}
				}
			}
		}
		for(int i=0;i<3;i++) {
			if(target[i].x == 100 || target[i].y==100) continue;
			if(temp[target[i].x][target[i].y] ==1) {
				temp[target[i].x][target[i].y] = 0 ;
				count++;
			}
		}
		if(down()) {
			hunt();
		}else return;
		
	}
	private static boolean down() {
		rest =0;
		for(int i=row-2;i>=0;i--) {
			for(int j=0;j<col;j++) {
				temp[i+1][j] = temp[i][j];
			}
		}
		for(int j=0;j<col;j++)temp[0][j] = 0;
		
		for(int i=1;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(temp[i][j]==1) rest++;
			}
		}
		if(rest==0) return false;
		else return true;
	}
	private static int distance(int y1, int x2, int y2) {
		return Math.abs(row-x2)+Math.abs(y1-y2);
	}
	static class Data{
		int x;
		int y;
		int dist;
		public Data(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}