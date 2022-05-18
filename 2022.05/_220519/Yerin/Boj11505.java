package _0519;

import java.util.*;
import java.io.*;

public class Boj11505 {
	static long tree[];//segmentTree
	static long arr[];//N개의 값
	
	public static long init(int node, int start, int end) {
		if(start==end) {
			tree[node] = arr[start];
			return tree[node];
		}
		int mid = (start+end)/2;
		tree[node] = (init(node*2, start, mid)*init(node*2+1, mid+1, end))%1_000_000_007;
		return tree[node];
	}
	
	public static long change(int node, int start, int end, int n, int su) {//n번째 수를 su로 변경
		if(start==end && start==n) {
			tree[node]=su;
			return tree[node];
		}
		int mid = (start+end)/2;
		if(mid >= n) {
			tree[node]=(change(node*2, start, mid, n, su) * tree[node*2+1])%1_000_000_007;
		}
		else {
			tree[node]=(tree[node*2]*change(node*2+1, mid+1, end, n, su))%1_000_000_007;
		}
		return tree[node];
	}
	
	public static long getMul(int node, int start, int end, int from, int to) {
		if(from<=start && end<=to) {
			return tree[node];
		}
		
		if(start > to || end < from) {//범위밖
			return 1;
		}
		else {
			int mid = (start+end)/2;
			return (getMul(node*2, start, mid, from, to)*getMul(node*2+1, mid+1, end, from, to))%1_000_000_007;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //수의 개수 1,000,000
		int M = Integer.parseInt(st.nextToken()); //변경이 일어나는 횟수 10,000
		int K = Integer.parseInt(st.nextToken()); //구간의 곱 구하는 횟수 10,000
		//1,000,000,007 로 나눈 나머지
		int h = (int) Math.ceil(Math.log(N)/Math.log(2));
		tree = new long[(int) Math.pow(2, h+1)];
		arr = new long[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		init(1, 1, N);
		StringBuilder sb = new StringBuilder();
		for(int i=0, size=M+K; i<size; i++) {
			st = new StringTokenizer(br.readLine());
			switch(Integer.parseInt(st.nextToken())) {
			case 1://1 : b번째 수 => c로
				change(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case 2://2 : b~c까지 구간 곱
				sb.append(getMul(1, 1, N, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
				sb.append("\n");
				break;
			}
		}
		System.out.print(sb.toString());
	}

}

/*
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5

240
48
*/