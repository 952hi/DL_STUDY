package _1016;

import java.util.*;
import java.io.*;

public class boj2467 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int arr[] = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int l = 0, r = n-1, min = Math.abs(arr[l]+arr[r]);
		int[] answer = new int[] {arr[l], arr[r]};
		while(l<r) {
			int su = arr[l]+arr[r];
			if(Math.abs(su)<min) {
				min = Math.abs(su);
				answer[0] = arr[l];
				answer[1] = arr[r];
			}
			
			if(su<0) {
				l++;
			}
			else if(su==0) {
				break;
			}
			else {
				r--;
			}
		}
		System.out.println(answer[0]+" "+answer[1]);
	}

}

/*
5
-99 -2 -1 4 98

-99 98

4
-100 -2 -1 103
-2 -1
*/