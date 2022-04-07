import java.io.*;
import java.util.*;
public class boj19236 {
	static int row=4,col=4,res=0;
	static fish map[][] = new fish[4][4];
	//0 상 1 좌상 2 좌 3 좌하 4 하 5 우하 6 우 7 우상
	static int[][] dxdy = { {-1,-1,0,1,1,1,0,-1 }, {0,-1,-1,-1,0,1,1,1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < row; i++) {
			StringTokenizer stz = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < col; j++) {
				map[i][j] = new fish(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())-1);
			}
		}
		
		int val = map[0][0].num;
		map[0][0] = new fish(17, map[0][0].dir );
		sharkfeed(copyarrays(map),val);
		// 불가능시 브레이크 및 출력
		// 가능 시 여러개면 모두 재귀
		// 하나면 바로 끝
		System.out.println(res);
		//물고기 번호 찾는 함수
		//물고기 이동시키는 함수
		//상어 이동시키는 변수
	}
	private static fish[][] copyarrays(fish[][] ori) {
		fish temp[][]= new fish[4][4];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(ori[i][j]==null) {
					temp[i][j] = null;
					continue;
				}
				temp[i][j] = new fish(ori[i][j].num, ori[i][j].dir);
			}
		}
		return temp;
	}
	private static void sharkfeed(fish[][] temp,int value) {
		// 물고기 이동
		res = Math.max(res, value);
		
		findFish(1,temp);
		
 		// 상어 이동 가능 불가능?
out:	for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(temp[i][j]==null) continue;
				if(temp[i][j].num==17) {
					int nx=i,ny=j;
					fish[][] comp;
					while(true) {
						nx = nx+dxdy[0][temp[i][j].dir];
						ny = ny+dxdy[1][temp[i][j].dir];
						if(0<=nx && nx<row && 0<=ny && ny<col) {
							if(temp[nx][ny]==null) continue;
							comp = copyarrays(temp);
							comp[nx][ny]= new fish(temp[i][j].num, temp[nx][ny].dir);
//							comp[nx][ny].num = 17; // 
							comp[i][j]=null;
							sharkfeed(comp, value+temp[nx][ny].num);
							
						}else {
							break;
						}
					}
					break out;
				}
			}
		}
	}
	private static void findFish(int idx,fish[][] temp) {
		if(idx == 17) return;
		
	out: for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(temp[i][j]==null) continue;
				if(temp[i][j].num==idx) {
					moveFish(i,j,temp);
					break out;
				}
			}
		}
		findFish(idx+1,temp);
	}
	private static void moveFish(int x, int y,fish[][] temp) {
		// 이동
		// 8회 이동 후에도 움직이지 못하면 제자리
		// 상어 자리나 보드 벗어나면 다음 방향으로 변경
		//0 상 1 좌상 2 좌 3 좌하 4 하 5 우하 6 우 7 우상
		int cnt =0;
		int nx,ny;
		fish comp;
		while(true) {
			if(cnt==8) break;
			int dirr =(temp[x][y].dir+cnt)%8;
			nx = x+dxdy[0][dirr];
			ny = y+dxdy[1][dirr];
			if(0<=nx && nx<row && 0<=ny && ny<col ) {
				if(temp[nx][ny]==null) {
					temp[nx][ny] = temp[x][y];
					temp[nx][ny].dir = dirr;
					temp[x][y] = null;
					break;
				}
				if(temp[nx][ny].num!=17) {
					comp = temp[nx][ny];
					temp[nx][ny] = temp[x][y];
					temp[x][y] = comp;
					temp[nx][ny].dir = dirr;
					break;
				}
			}
			cnt++;
		}
	}
	static class fish{
		int num;
		int dir;
		public fish(int num, int dir) {
			super();
			this.num = num;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "["+num + " " + dir+"]";
		}
		
	}
}