package _0505;

import java.io.*;
import java.util.*;

//N개의 도시 모두 거쳐 돌아오기 (중간에 중복X)
//못가는 경우는 0
//남은 걸로 가는 걸 메모
public class Boj2098 {
	static int N, arr[][], memo[][];
	static final int INF = 999_999_999;
	
	public static int go(int now, int visited) {
		//다 방문했다면
		if(visited == (1<<N)-1) { return arr[now][0];}//출발지로 돌아가는 값
		
		//이미 계산이 끝난 값이라면
		if(memo[now][visited]!=0) { return memo[now][visited];}
		
		memo[now][visited]=INF;
		for(int i=1; i<N; i++) {//다음 갈 곳 선택 = 출발지가 0임을 아니까 1부터 시작
			if((visited & (1<<i)) != 0) { continue;} //이미 방문했으면
			if(arr[now][i]==0) { continue;}//연결된 길이 없으면
			memo[now][visited] = Math.min(memo[now][visited], arr[now][i]+go(i, visited | (1<<i)));
		}
		return memo[now][visited];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		memo = new int[N][1<<N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) {arr[i][j]=INF;}
			}
		}
		System.out.println(go(0, 1<<0)); //0부터 시작
	}
}
