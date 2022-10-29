package _1023;
//세로 길이는 무조건 밑에서부터 붙이므로, 최소 길이가 정해져있다.
//column에 빈칸 여부만 1차원 배열로 표시
//binary search?
import java.util.*;
import java.io.*;

public class boj2539 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int R, C, n, wrong, left, right;
		boolean arr[];
		//0. init
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(br.readLine().trim());
		wrong = Integer.parseInt(br.readLine().trim());
		arr = new boolean[C+1];
		left = 0;
		right = Math.max(R, C);
		
		for(int i=0; i<wrong; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			left = Math.max(left, r);
			arr[c] = true;
		}
		
		//1. binary search
		while(left<=right) {
			int mid = (left+right)/2;
			//색종이 크기 = mid값 -> 필요한 색종이 개수 cnt
			int cnt = 0;
			
			for(int i=0; i<C; i++) {
				if(arr[i]) {
					cnt++;
					i+=mid;
					i--;
				}
			}
			if(cnt<=n) {
				right = mid-1;
			}
			else {
				left = mid+1;
			}
		}
		System.out.println(left);
	}

}
