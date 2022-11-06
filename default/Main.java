import java.io.*;
import java.util.*;

public class Main {
	static boolean[][] check;
	static int row, col;
	
	
	
	static void dfs() {
		
		// 모든 보드를 돌면서 생성 가능한 부메랑을 확인
		for(int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
			}
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		int[][]bd = new int[row][col];
		check = new boolean[row][col];
		
		for(int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				bd[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs();
	}
}
