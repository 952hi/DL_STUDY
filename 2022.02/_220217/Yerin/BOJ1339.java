import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 1339번 : 단어 수학
 * 236ms
 */

public class BOJ1339 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int cnt[] = new int[26];
		//AAA
		//100 10 1 (자리수만큼 더하기)
		for(int i=0;i<N;i++) {
			char[] arr = sc.next().toCharArray();
			for(int j = arr.length-1, p = 1; j >=0; j--, p*=10) {
				cnt[arr[j]-'A']+=p;
			}
		}
		Arrays.sort(cnt);
		int i=25, su = 9, result =0;
		while(i>=0 && cnt[i]!=0) {
			//자릿수의 합에 greedy로 수 선택한 걸 곱하기
			result += su*cnt[i];
			su--;
			i--;
		}
		System.out.println(result);
	}

}
