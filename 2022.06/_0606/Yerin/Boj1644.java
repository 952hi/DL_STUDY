package _0606;

import java.io.*;

public class Boj1644 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean notPrime[] = new boolean[N+1];//false는 소수
		notPrime[0] = notPrime[1] = true;
		int values[] = new int[N+1], start = -1, end=-1, result=0, limit = (int)Math.sqrt(N);
		//에라토스테네스의 체
		for(int i=2; i<=N; i++) {
			if(notPrime[i]) {continue;}
			if(i<=limit) {
				for(int j=i+i; j<=N; j+=i) {notPrime[j]=true;}
			}
			for(int j=start; j>end; j--) {
				values[j]+=i;
				if(values[j]>N) {
					end=j;
					break;
				}
                if(values[j]==N){result++;}
			}
            if(i==N){result++;}
			values[++start]=i;
		}
		System.out.println(result);
	}
}
