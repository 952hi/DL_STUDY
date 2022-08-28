package _0822;

import java.io.*;

//앞 = 1, 뒤 = 6 | 왼 = 2, 위 = 3, 오 = 4, 밑 = 5
public class Boj1917 {
	static int map[][], dice[];
	static boolean visited[], result;//1~6번
	
	//위0, 오1, 밑2, 왼3, 앞4, 뒤5
	public static void dfs(int x, int y) {
		//위에서부터 시계방향 탐색
		int dx[] = {-1, 0, 1, 0}, dy[] = {0, 1, 0, -1};
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=6 || ny<0 || ny>=6 || map[nx][ny]!='1') {continue;}
			if(visited[dice[i]]) {
				result = false;
				return;
			}
			map[nx][ny] = '0';
			visited[dice[i]] = true;
			turn(i);
			dfs(nx, ny);
			turn((i+2)%4);
		}
	}
	
	public static void turn(int n) {
		switch(n) {
		case 0://위로
			dice = new int[] {dice[5], dice[1], dice[4], dice[3], dice[0], dice[2]};
			break;
		case 1://오
			dice = new int[] {dice[0], dice[5], dice[2], dice[4], dice[1], dice[3]};
			break;
		case 2://아래로
			dice = new int[] {dice[4], dice[1], dice[5], dice[3], dice[2], dice[0]};
			break;
		case 3://왼
			dice = new int[] {dice[0], dice[4], dice[2], dice[5], dice[3], dice[1]};
			break;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		for(int T=1; T<=3; T++) {
			result = true;
			dice = new int[]{2, 3, 4, 5, 1, 6};//위0, 오1, 밑2, 왼3, 앞4, 뒤5

			//0. input
			map = new int[6][6];
			for(int i=0; i<6; i++) {
				String s = br.readLine();
				for(int j=0; j<12; j+=2) {
					map[i][j/2] = s.charAt(j);
				}
			}
			//1. dfs
			outloop:for(int i=0; i<6; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j]=='1') {
						map[i][j] = '0';
						visited = new boolean[7];
						visited[1] = true;
						dfs(i, j);
						break outloop;
					}
				}
			}
			//2. 출력
			sb.append(result==true?"yes\n":"no\n");
		}
		System.out.println(sb.toString());
	}

}