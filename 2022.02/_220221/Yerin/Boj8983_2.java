package day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 8983번 : 사냥꾼
 * 사대 정렬 후, 이진탐색
 * 684ms
 * ---L-y--- x ---L-y---
 */

public class Boj8983_2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); //사대 수
		int N = Integer.parseInt(st.nextToken()); //동물 수
		int L = Integer.parseInt(st.nextToken()); //사정거리
		
		//사대 위치
		st = new StringTokenizer(br.readLine());
		int[] stations = new int[M];
		for(int i=0; i<M; i++) {
			stations[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(stations);
		
		int answer =0;//결과
		for(int i=0;i<N;i++) {//동물수
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			if(y > L)//사정거리 밖에 위치
				continue;
			
			int left = 0, right = M-1;
			int low = x - (L-y);
			int high = x + (L-y);
			while(left <= right) {
				int mid = (left+right)/2;
				if(low <= stations[mid] && stations[mid] <= high) {
					answer++;
					break;
				}
				
				if(stations[mid] < low)
					left = mid+1;
				else
					right = mid-1;
			}
		}
		System.out.println(answer);
	}

}
