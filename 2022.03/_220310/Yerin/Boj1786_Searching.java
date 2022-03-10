package _20220307;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 백준 1786번 : 찾기
 * 시간 : 516ms
 */
public class Boj1786_Searching {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String T = br.readLine();
		String P = br.readLine();
		int[] iter = new int[P.length()];
		
		//반복배열 만들기
		makeIter(iter, P);
		find(T, P, iter);
	}

	//반복 함수
	public static void makeIter(int[] iter, String P) {
		int j=0;
		for(int i=1;i<P.length();i++) {
			if(P.charAt(i)!=P.charAt(j)) {
				if(j!=0) {
					i--;
					j=iter[j-1];
				}
				continue;
			}
			iter[i] = ++j;
		}
	}
	
	//찾기
	public static void find(String T, String P, int[] iter) {
		StringBuilder sb = new StringBuilder();
		int j=0, cnt = 0;
		for(int i=0;i<T.length();i++) {
			if(T.charAt(i)!=P.charAt(j)) {
				if(j!=0) {
					i--;
					j=iter[j-1];
				}
				continue;
			}
			j++;
			if(j==P.length()) {
				cnt++;
				sb.append(i-P.length()+2).append(" ");//1부터 시작(=index +1)
				j=iter[j-1];//마지막까지 맞았으니 마지막의 반복배열 값부터 시작
			}
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
