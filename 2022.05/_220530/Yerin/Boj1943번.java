package _0530;

import java.io.*;
import java.util.*;

//1. 해당 동전들로만 가능한 값 계산(0)
//2. + (다른 동전들로 나오는 값들) for문 반복
public class Boj1943번 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<3; t++) {
			int total = 0;
			int n = Integer.parseInt(br.readLine());
			int coin[] = new int[n];
			int cnt[] = new int[n];
			int max = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				coin[i] = Integer.parseInt(st.nextToken());
				cnt[i] = Integer.parseInt(st.nextToken());
				total+=(coin[i]*cnt[i]);
				max = Math.max(max, coin[i]);
			}
			if(total%2==1) {
				sb.append("0\n");
				continue;
			}
			total/=2;
			if(max>total) {
				sb.append("0\n");
				continue;
			}
			int[] coins = new int[total+1];//유효한 숫자만 저장
			int size = 0;
			boolean[] money = new boolean[total+1];
			coins[size++]=0;
			money[0] = true;//0원이 1개있다하자
			for(int i=0; i<n; i++) {
				for(int k=0, temp=size; k<temp;k++) {
					int available = Math.min(cnt[i], (total-coins[k])/coin[i]);
					for(int j=1; j<=available;j++) {
						int newCoin = coins[k]+coin[i]*j;
						if(!money[newCoin]) {
							coins[size++] = newCoin;
						}
						money[newCoin]=true;
					}
				}
			}
			if(!money[total]) {
				sb.append("0\n");
			}
			else {
				sb.append("1\n");
			}
		}
		System.out.println(sb.toString());
	}
}
