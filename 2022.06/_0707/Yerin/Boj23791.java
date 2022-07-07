package _0707;

import java.io.*;
import java.util.*;

public class Boj23791 {
	static int n, cnt[][];//한식 수, 양식 수
	
	public static int binarySearch(int n1, int n2, int k) {//n1 + n2 ==k인 경우..
		int s = 0, e = 2*n-1;
		while(s<=e) {
			int m = (s+e)/2;
			int temp = Math.min(n1, cnt[m][0])+Math.min(n2, cnt[m][1]);
			if(temp<k) {
				s = m+1;
			}
			else {
				e = m-1;
			}
		}
		return s;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int arr[][] = new int[2][n];
		int pos[][] = new int[2*n][2];//위치
		cnt = new int[2*n][2];
		for(int i=0; i<2; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//병합 정렬
		int x=0, y=0, idx=0;
		int han = 0, yang = 0;//한식 수, 양식 수
		while(x<n || y<n) {
			if(y>=n || (x<n && arr[0][x]<=arr[1][y])) {
				cnt[idx][0] = ++han;
				cnt[idx][1] = yang;
				pos[idx][0] = 1;
				pos[idx++][1] = ++x;
			}
			else if(x>=n || (y<n && arr[1][y]<arr[0][x])) {
				cnt[idx][0] = han;
				cnt[idx][1] = ++yang;
				pos[idx][0] = 2;
				pos[idx++][1] = ++y;
			}
		}
		
		//이진탐색
		StringBuilder sb = new StringBuilder();
		int q = Integer.parseInt(br.readLine());
		for(int i=0; i<q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int ans = binarySearch(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(pos[ans][0]).append(" ").append(pos[ans][1]).append("\n");
		}
		System.out.print(sb.toString());
	}

}

/*
7
1 5 10 15 18 20 30
2 3 8 11 14 40 50
1
1 7 8

2 7
*/