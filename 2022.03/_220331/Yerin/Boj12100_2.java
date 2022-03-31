package _20220331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj12100_2 {
	static int N, max;
	static int order[], origin[][];
	public static void permutation(int cnt) {
		if(cnt==5) {
			//배열 복사
			int[][] carr = new int[N][N];
			for(int i=0;i<N;i++)
				carr[i] = origin[i].clone();
			
			for(int i=0;i<5;i++) 
				move(order[i], carr);
				
			return;
		}
		for(int i=0;i<4;i++) {//4방향 up, down, left, right
			order[cnt]=i;
			permutation(cnt+1);
		}
	}
	
	public static void move(int n, int arr[][]) {
		//up, down, left, right
		int start = (n%2==0) ? 0 : N-1;
		int inc = (n%2==0) ? 1 : -1;
		
		if(n<2) {//up & down
			for(int i=0;i<N;i++) {
				int prev = start;
				for(int j=start+inc; 0<=j && j<N; j+=inc) {
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
								prev+=inc;
							}
							else {
								prev+=inc;
								if(prev != j) {
									arr[prev][i] = arr[j][i];
									arr[j][i] = 0;
								}
							}
						}
					}
				}
			}
		}
		else {//left & right
			for(int i=0;i<N;i++) {
				int prev = start;
				for(int j=start+inc; 0<=j && j<N; j+=inc) {
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
								prev+=inc;
							}
							else {
								prev += inc;
								if(prev != j) {
									arr[i][prev] = arr[i][j];
									arr[i][j] = 0;
								}
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
		origin = new int[N][N];//벽세움
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
