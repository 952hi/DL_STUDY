package _0418;

import java.io.*;
import java.util.*;

public class Boj2014_2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());//k개의 수
		int N = Integer.parseInt(st.nextToken());//n번째
		
		int prime[] = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			prime[i] = Integer.parseInt(st.nextToken());
		}
		
		int result[] = new int[200000];
		int p[] = new int[K];
		result[0] = 1;
		for(int i=1; i<=N; ) {
			int min = Integer.MAX_VALUE;
			int minIdx = 0;
			for(int j=0; j<K; j++) {
				int temp = result[p[j]]*prime[j];
				if(temp < min) {
					min = temp;
					minIdx = j;
				}
			}
			if(result[i-1]!=min) {
				result[i++] = min;
			}
			p[minIdx]++;//순서대로 곱하도록! 
		}
		System.out.println(result[N]);
	}

}
