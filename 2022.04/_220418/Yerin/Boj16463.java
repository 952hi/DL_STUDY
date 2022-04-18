package _0418;

import java.io.*;

public class Boj16463 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int month[] = {31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
		int N = Integer.parseInt(br.readLine());
		int start = 13%7;
		int m = 1, cnt = 0;
		
		if(start == 4) {
			cnt++;
		}
		
		for(int i=2019; i<=N; i++) {
			month[2] = 28;
			if(i%4==0 && (i%100!=0 || i%400==0)) {
				month[2] = 29;
			}
			m++;
			while(m!=0) {
				start = (start+month[m-1])%7;
				if(start==4) {//월, 화, 수, 목, 금, 토, 일
					cnt++;
				}
				m = (m+1)%13;
			}
		}
		System.out.println(cnt);
	}
}
