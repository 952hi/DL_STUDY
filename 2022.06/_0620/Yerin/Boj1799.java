package _0620;

import java.io.*;
import java.util.*;

public class Boj1799 {
	static int N, map[][];
	static int FIN, r, BoolLen;
	
	public static void go(boolean[] first, boolean[] second, int index, int cnt, int op) {
		if(index >= FIN) {
			r = Math.max(r, cnt);
			return;
		}
		int x = index/N;
		int y = index%N;
		//놓을 수 있다면
		if((x+y)%2==op && map[x][y]==1 && !first[x+y] && !second[(x-y+BoolLen)%BoolLen]) {
			//놓기
			first[x+y] = true;
			second[(x-y+BoolLen)%BoolLen] = true;
			go(first, second, index+1, cnt+1, op);
			//안 놓기
			first[x+y] = false;
			second[(x-y+BoolLen)%BoolLen] = false;
		}
		go(first, second, index+1, cnt, op);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		FIN = N*N;
		BoolLen = N*2;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 0;
		for(int i=0; i<2; i++) {
			go(new boolean[BoolLen], new boolean[BoolLen], i, 0, i);
			ans+=r;
			r = 0;
		}
		System.out.println(ans);
	}
}

/*
N <= 10

5
1 1 0 1 1
0 1 0 0 0
1 0 1 0 1
1 0 0 0 0
1 0 1 1 1

7
*/