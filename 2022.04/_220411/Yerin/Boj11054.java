package _0410;

import java.io.*;
import java.util.*;

public class Boj11054 {
	
	public static int binarySearch(int[] temp, int n, int end) {
		int left=1, right=end;
		while(left <= right) {
			int mid = (left+right)/2;
			if(temp[mid] < n) {
				left = mid+1;
			}
			else {
				right = mid-1;
			}
		}
		
		return left;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int input[] = new int[N];
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int inc[][] = new int[N+1][2];
		int dec[][] = new int[N+1][2];
		//증가하는 수열
		//감소하는 수열 = 뒤에서 증가하는 수열
		int temp[] = new int[N+1];//증가용
		int temp2[] = new int[N+1];//감소용
		int idx = 0;
		int idx2 = 0;
		for(int i=0; i<N; i++) {
			//증가수열
			int n = input[i];
			if(temp[idx]<n) {
				temp[++idx] = n;
			}
			else if(temp[idx]>n) {//n이 더 작으면 이분탐색으로 적절한 위치 찾기
				temp[binarySearch(temp, n, idx)] = n;
			}
			inc[i+1][0] = idx;
			inc[i+1][1] = temp[idx];
			
			//감소 수열
			n = input[N-1-i];
			if(temp2[idx2]<n) {
				temp2[++idx2] = n;
			}
			else if(temp2[idx2]>n){
				temp2[binarySearch(temp2, n, idx2)] = n;
			}
			dec[i+1][0] = idx2;
			dec[i+1][1] = temp2[idx2];
		}
		
		//최대가 되는 곳 계산
		int max = 0;
		for(int i=0; i<N; i++) {
			int result = inc[i][0]+dec[N-i][0];
			if(inc[i][1] == dec[N-i][1]) {
				result--;
			}
			max = Math.max(max, result);
		}
		System.out.println(max);	
	}
}
