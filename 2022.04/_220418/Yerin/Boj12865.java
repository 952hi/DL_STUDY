package _0418;

import java.io.*;
import java.util.*;

public class Boj12865 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());//물품 수
		int K = Integer.parseInt(st.nextToken());//최대무게
		int things[][] = new int[N][2];//무게, 가치
		int value[] = new int[K+1];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken());
			things[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=K; j>=things[i][0]; j--) {//뒤에서부터 업데이트
				value[j] = Math.max(value[j-things[i][0]]+things[i][1], value[j]);
			}
		}
		
		int max = 0;
		for(int i=1; i<K+1; i++) {
			max = Math.max(max, value[i]);
		}
		System.out.println(max);
	}
}