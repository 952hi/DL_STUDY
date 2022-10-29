package _1030;

import java.io.*;
import java.util.*;
/*
 * 1. 자식의 수
 * 2. 자식에서 계산된 minute + 시작 순서 => 이 중에서 최소를 부모에 저장
 */

public class Boj1135 {
	static int n, kids[];
	
	public static void updateKids(int kid, int[] p) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=0; i<n; i++) {
			if(p[i]==kid) {
				pq.add(kids[i]);
			}
		}
		int size = pq.size();//자식 개수
		int result = 0;
		for(int i=size; i>0; i--) {
			result = Math.max(result, i+pq.poll());
		}
		kids[kid] = result;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		kids = new int[n];
		int p[] = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		//
		for(int i=n-1; i>=0; i--) {
			updateKids(i, p);
		}
		System.out.println(kids[0]);
	}

}
