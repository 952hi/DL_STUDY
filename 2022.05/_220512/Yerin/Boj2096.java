package _0512;
//내려가기
import java.util.*;
import java.io.*;

public class Boj2096 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int INF = 999_999_999;
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[2][3];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<3; i++) {//첫 줄 받기 <= min,max 계산 필요X
			arr[0][i] = arr[1][i] = Integer.parseInt(st.nextToken());
		}
		
		for(int x=1; x<N; x++) {
			st = new StringTokenizer(br.readLine());
			int max[] = new int[3];
			int min[] = new int[3];
			Arrays.fill(min, INF);
			for(int y=0; y<3; y++) {
				int now = Integer.parseInt(st.nextToken());
				for(int d=-1; d<=1; d++) {//위에 3개
					int ny = y+d;
					if(0<=ny && ny<3) {
						max[y] = Math.max(max[y], arr[0][ny]+now);
						min[y] = Math.min(min[y], arr[1][ny]+now);
					}
				}
			}
			arr[0] = max;
			arr[1] = min;
		}
		
		int maxV = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2])), minV = Math.min(arr[1][0], Math.min(arr[1][1], arr[1][2]));
		System.out.println(maxV+" "+minV);
	}
}
