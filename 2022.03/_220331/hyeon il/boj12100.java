import java.io.*;
import java.util.*;
public class boj12100 {
	static int N,map[][],res;
	static boolean checked[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		res =0;
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				map[i][j]= Integer.parseInt(stz.nextToken());
			}
		}
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				for(int k=0;k<4;k++) {
					for(int l=0;l<4;l++) {
						for(int q=0;q<4;q++) {
							mover(new int[] {i,j,k,l,q});
						}
					}
				}
			}
		}
		System.out.println(res);
		
	}
	// 0123 상하좌우
	static int [][] temp;
	private static void mover(int[] dir) {
		int val = 0;
		temp =new int[N][N];
		for(int i=0;i<N;i++) temp[i] = Arrays.copyOf(map[i], N);
		
		for(int i=0;i<5;i++) {
			if(dir[i]==0) {
				top(temp);
			}else if(dir[i]==1) {
				bottom(temp);
			}else if(dir[i]==2) {
				left(temp);
			}else if(dir[i]==3) {
				right(temp);
			}
		}
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(val<temp[i][j]) {
					val =temp[i][j];
				}
			}
		}
		res = Math.max(val, res);
	}
	private static void right(int[][] temp) {
		int comp;
		checked =new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=N-2;j>=0;j--) {
				// 영이면 무시
				if(temp[i][j]==0) continue;
				// 영이 아닌 숫자라면 이동 준비
				else {
					// 숫자를 만날떄 까지 전진 but 0까지만
					// 만났을때 같으면 더함 다름 면 정지
					comp = 1;
					while(true) {
						if(j+comp<N) {
							if(temp[i][j+comp]==0) {
								//0만나면 한칸더 전진
								comp++;
								continue;
							}else {
								// 숫자를 만나면 같은가 비교
								// 같으면 더함 다르면 전칸으로 이동 후 종료
								if(temp[i][j+comp]==temp[i][j] && !checked[i][j+comp]) {
									checked[i][j+comp] = true;
									temp[i][j+comp] += temp[i][j];
									temp[i][j] = 0;
									break;
								}else {
									//수가 다르면 
									
									if(j+comp-1==j) break;
									else {
										temp[i][j+comp-1] = temp[i][j];
										temp[i][j] = 0;
										break;
									}
									
								}
							}
						}else {
							//최대로 전진했다면
							if(j+comp-1==j) break;
							else {
								temp[i][j+comp-1] = temp[i][j];
								temp[i][j] = 0;
								break;
							}
						}
					}
				}
			}
		}
	}
	private static void left(int[][] temp) {
		int comp;
		checked =new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=1;j<N;j++) {
				// 영이면 무시
				if(temp[i][j]==0) continue;
				// 영이 아닌 숫자라면 이동 준비
				else {
					// 숫자를 만날떄 까지 전진 but 0까지만
					// 만났을때 같으면 더함 다름 면 정지
					comp = 1;
					while(true) {
						if(j-comp>=0) {
							if(temp[i][j-comp]==0) {
								//0만나면 한칸더 전진
								comp++;
								continue;
							}else {
								// 숫자를 만나면 같은가 비교
								// 같으면 더함 다르면 전칸으로 이동 후 종료
								if(temp[i][j-comp]==temp[i][j] && !checked[i][j-comp]) {
									checked[i][j-comp] =true;
									temp[i][j-comp] += temp[i][j];
									temp[i][j] = 0;
									break;
								}else {
									//수가 다르면 
									if(j-comp+1==j) break;
									else {
										temp[i][j-comp+1] = temp[i][j];
										temp[i][j] = 0;
										break;
									}
								}
							}
						}else {
							//최대로 전진했다면
							if(j-comp+1==j) break;
							else {
								temp[i][j-comp+1] = temp[i][j];
								temp[i][j] = 0;
								break;
							}
						}
					}
				}
			}
		}
	}
	private static void bottom(int[][] temp) {
		int comp;
		checked =new boolean[N][N];
		for(int i=N-2;i>=0;i--) {
			for(int j=0;j<N;j++) {
				// 영이면 무시
				if(temp[i][j]==0) continue;
				// 영이 아닌 숫자라면 이동 준비
				else {
					// 숫자를 만날떄 까지 전진 but 0까지만
					// 만났을때 같으면 더함 다름 면 정지
					comp = 1;
					while(true) {
						if(i+comp<N) {
							if(temp[i+comp][j]==0) {
								//0만나면 한칸더 전진
								comp++;
								continue;
							}else {
								// 숫자를 만나면 같은가 비교
								// 같으면 더함 다르면 전칸으로 이동 후 종료
								if(temp[i+comp][j]==temp[i][j] && !checked[i+comp][j]) {
									checked[i+comp][j] = true;
									temp[i+comp][j] += temp[i][j];
									temp[i][j] = 0;
									
									break;
								}else {
									//수가 다르면 
									if(i+comp-1==i) break;
									else {
										temp[i+comp-1][j] = temp[i][j];
										temp[i][j] = 0;
										break;
									}
									
								}
							}
						}else {
							//최대로 전진했다면
							if(i+comp-1==i) break;
							else {
								temp[i+comp-1][j] = temp[i][j];
								temp[i][j] = 0;
								break;
							}
						}
					}
				}
			}
		}
	}
	private static void top(int[][] temp) {
		int comp;
		checked =new boolean[N][N];
		for(int i=1;i<N;i++) {
			for(int j=0;j<N;j++) {
				// 영이면 무시
				if(temp[i][j]==0) continue;
				// 영이 아닌 숫자라면 이동 준비
				else {
					// 숫자를 만날떄 까지 전진 but 0까지만
					// 만났을때 같으면 더함 다름 면 정지
					comp = 1;
					while(true) {
						if(i-comp>=0) {
							if(temp[i-comp][j]==0) {
								//0만나면 한칸더 전진
								comp++;
								continue;
							}else {
								// 숫자를 만나면 같은가 비교
								// 같으면 더함 다르면 전칸으로 이동 후 종료
								if(temp[i-comp][j]==temp[i][j] && !checked[i-comp][j]) {
									checked[i-comp][j] =true;
									temp[i-comp][j] += temp[i][j];
									temp[i][j] = 0;
									break;
								}else {
									//수가 다르면 
									if(i-comp+1==i) break;
									else {
										temp[i-comp+1][j] = temp[i][j];
										temp[i][j] = 0;
										break;
									}
								}
							}
						}else {
							//최대로 전진했다면 
							if(i-comp+1==i) break;
							else {
								temp[i-comp+1][j] = temp[i][j];
								temp[i][j] = 0;
								break;
							}
						}
					}
				}
			}
		}
	}
}
