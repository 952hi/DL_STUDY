package _0620;

//[를 만날 때 : 1)0이면 ]까지 pass, 2)0이 아니면 위치와 loop수 초기화 => INF넘어가면 끝

import java.io.*;
import java.util.*;

public class Boj3954 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());//배열(메모리) 크기
			int M = Integer.parseInt(st.nextToken());//명령 = 코드 크기
			int K = Integer.parseInt(st.nextToken());//입력 = 문자열
			int arr[] = new int[N];//배열(메모리)
			int p = 0, inp=0;//arr'spointer & input's pointer
			char[] codes = br.readLine().toCharArray();
			char[] inputs = br.readLine().toCharArray();
			
			//괄호 위치 저장
			int stoe[] = new int[M];
			int etos[] = new int[M];
			Stack<Integer> stack = new Stack<>();
			for(int i=0; i<M; i++) {
				if(codes[i]=='[') {stack.push(i);}
				else if(codes[i]==']') {
					int pos = stack.pop();
					stoe[pos] = i;
					etos[i] = pos;
				}
			}
			
			//명령어 실행
			int cnt = 0;//명령수행횟수
			int result = 0;
			for(int i=0; i<M; i++) {
				
				switch(codes[i]) {
				case '+':
					arr[p] = (arr[p]+1)%256;
					break;
				case '-':
					arr[p] = (arr[p]+255)%256;
					break;
				case '<':
					p = (p-1+N)%N;
					break;
				case '>':
					p = (p+1)%N;
					break;
				case '[':
					if(arr[p]==0) {//루프 안 해도된다.
						//']'찾기
						i = stoe[i];
					}
					break;
				case ']':
					if(arr[p]!=0) {
						if(cnt>50_000_000) {
							result = Math.max(result, i);
						}
						i=etos[i];//시작 위치로!
					}
					break;
				case ',':
					//입력이 더이상 없을 때
					arr[p]=(inp>=K)?255:inputs[inp++];
					break;
				default:
					continue;
				}
				cnt++;
				if(cnt>100_000_000) {
					break;
				}
			}
			//출력
			if(cnt>50_000_000) {
				sb.append("Loops ").append(etos[result]).append(" ").append(result).append("\n");
			}
			else{
				sb.append("Terminates\n");
			}
		}
		System.out.println(sb.toString());
	}
}
/*
2
3 124 1
+[-[[>+[>----------------------------------[+++]<+]++++++++++++++++++++++++++[+]<+]----------------------------------[+]]++]
.
1 8 1
+[-[]++]
.

Loops 1 123
Loops 3 4
*/