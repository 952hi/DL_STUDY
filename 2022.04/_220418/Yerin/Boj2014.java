package _0418;

import java.io.*;
import java.util.*;

public class Boj2014 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());//k개의 수
		int N = Integer.parseInt(st.nextToken());//n번째
		
		long prime[] = new long[K];
		PriorityQueue<Long> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<K; i++) {
			prime[i] = Long.parseLong(st.nextToken());
			pq.offer(prime[i]);
		}
		
		for(int i=0; i<N-1; i++) {
			long n = pq.poll();
			for(int j=0; j<K; j++) {
				long temp = n*prime[j];
				if(temp >= (long)2<<30) {
					continue;
				}
				pq.offer(temp);
				if(n%prime[j]==0) {//순서대로 곱해진 것만 받겠다.
					break;
				}
			}
		}
		System.out.println(pq.poll());
	}
}