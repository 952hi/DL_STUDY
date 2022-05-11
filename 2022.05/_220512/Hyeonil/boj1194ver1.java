import java.io.*;
import java.util.*;
public class boj1194ver1 {
	static int row,col,cnt,temp[][];
	static char[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		row = Integer.parseInt(stz.nextToken());
		col = Integer.parseInt(stz.nextToken());
		temp = new int[row][col];
		map = new char[row][col];
		cnt = Integer.MAX_VALUE;
		
		for(int i=0;i<row;i++) {
			Arrays.fill(temp[i], -1);
			map[i] = br.readLine().toCharArray();
		}
		// 사이즈 1일때 예외처리
		if(row==0 && col==0) {
			System.out.println(0);
		}else {
		out:for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					if(map[i][j]=='0') {
						temp[i][j] = 0;
						map[i][j]='.';
						moon(i,j,0,new boolean[26],0);
						break out;
					}
				}
			}
		}
		if(cnt==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(cnt);
	}
	static int[][] dxdy = { { 0, 0, 1, -1 }, { 1, -1, 0, 0 } };
	private static void moon(int x, int y,int move,boolean[] key,int keyval) {
		//이미 커져버린경우 컷
		if(move>cnt) return;
		// 탈출하는 경우 갱신
		if(map[x][y]=='1') {
			cnt = Math.min(cnt, move);
			return;
		}
		int nx,ny,comp;
		for(int i=0;i<4;i++) {
			nx = x+dxdy[0][i];
			ny = y+dxdy[1][i];
			// 범위 및 벽 처리
			if(0<=nx && nx<row && 0<=ny && ny<col && map[nx][ny]!='#' && move<cnt) {
				// 대문자 처리
				if(map[nx][ny]-0>=65 && map[nx][ny]-0<97) {
					if(!key[map[nx][ny]-'A']) continue;
				}
				// 중복방문 제외
				if(temp[nx][ny]<keyval) {
					comp = temp[nx][ny];
					temp[nx][ny] = keyval;
					
					//소문자 처리 A~Z 확인
					if(map[nx][ny]-0>=97 && map[nx][ny]-0<=122) {
						if(!key[map[nx][ny]-'a']) {
							keyval += 1;
							key[map[nx][ny]-'a'] = true;
							moon(nx,ny,move+1,key,keyval);
							keyval -= 1;
							key[map[nx][ny]-'a'] = false;
						}
					}else moon(nx,ny,move+1,key,keyval);
					temp[nx][ny] = comp;
				}
			}
		}
	}
}