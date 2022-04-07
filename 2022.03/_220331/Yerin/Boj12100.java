package _20220331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12100 {
	static int N, max;
	static int order[], origin[][];
	public static void permutation(int cnt) {
		if(cnt==5) {
			//배열 복사
			int[][] carr = new int[N][N];
			for(int i=0;i<N;i++)
				carr[i] = origin[i].clone();
			
			for(int i=0;i<5;i++) {
				switch(order[i]) {
				case 0:
					goUp(carr);
					break;
				case 1:
					goDown(carr);
					break;
				case 2:
					goLeft(carr);
					break;
				case 3:
					goRight(carr);
					break;
				}
			}
			
			return;
		}
		for(int i=0;i<4;i++) {//4방향 up, down, left, right
			order[cnt]=i;
			permutation(cnt+1);
		}
	}
	
	public static void goUp(int arr[][]) {
		for(int i=0;i<N;i++) {
			int prev = 0;
			for(int j=1;j<N;j++) {
				if(arr[j][i]==0) {
					continue;
				}
				else {
					if(arr[prev][i]==0) {
						arr[prev][i] = arr[j][i];
						arr[j][i] = 0;
					}
					else {
						if(arr[prev][i] == arr[j][i]) {//값이 같으면
							arr[prev][i]*=2;
							arr[j][i] = 0;
							max = Math.max(max, arr[prev][i]);
							prev++;
						}
						else {
							if(++prev != j) {
								arr[prev][i] = arr[j][i];
								arr[j][i] = 0;
							}
						}
					}
				}
			}
		}
	}
	
	public static void goDown(int arr[][]) {
		for(int i=0;i<N;i++) {
			int prev = N-1;
			for(int j=N-2;j>=0;j--) {
				if(arr[j][i]==0) {
					continue;
				}
				else {
					if(arr[prev][i]==0) {
						arr[prev][i] = arr[j][i];
						arr[j][i] = 0;
					}
					else {
						if(arr[prev][i] == arr[j][i]) {//값이 같으면
							arr[prev][i]*=2;
							arr[j][i] = 0;
							max = Math.max(max, arr[prev][i]);
							prev--;
						}
						else {
							if(--prev != j) {
								arr[prev][i] = arr[j][i];
								arr[j][i] = 0;
							}
						}
					}
				}
			}
		}
	}
	
	public static void goLeft(int arr[][]) {
		for(int i=0;i<N;i++) {
			int prev = 0;
			for(int j=1;j<N;j++) {
				if(arr[i][j]==0) {
					continue;
				}
				else {
					if(arr[i][prev]==0) {
						arr[i][prev] = arr[i][j];
						arr[i][j] = 0;
					}
					else {
						if(arr[i][prev] == arr[i][j]) {//값이 같으면
							arr[i][prev]*=2;
							arr[i][j] = 0;
							max = Math.max(max, arr[i][prev]);
							prev++;
						}
						else {
							if(++prev != j) {
								arr[i][prev] = arr[i][j];
								arr[i][j] = 0;
							}
						}
					}
				}
			}
		}
	}
	
	public static void goRight(int arr[][]) {
		for(int i=0;i<N;i++) {
			int prev = N-1;
			for(int j=N-2;j>=0;j--) {
				if(arr[i][j]==0) {
					continue;
				}
				else {
					if(arr[i][prev]==0) {
						arr[i][prev] = arr[i][j];
						arr[i][j] = 0;
					}
					else {
						if(arr[i][prev] == arr[i][j]) {//값이 같으면
							arr[i][prev]*=2;
							arr[i][j] = 0;
							max = Math.max(max, arr[i][prev]);
							prev--;
						}
						else {
							if(--prev != j) {
								arr[i][prev] = arr[i][j];
								arr[i][j] = 0;
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		origin = new int[N][N];
		order = new int[5];//방향 5개 선택
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				origin[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, origin[i][j]);
			}	
		}
		permutation(0);
		System.out.println(max);
	}
}
