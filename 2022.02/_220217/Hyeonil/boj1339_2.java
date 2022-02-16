import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1339_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] alpha = new int[26];
		for (int i = 0; i < n; i++) {
			char[] comp = br.readLine().toCharArray();
			int pos = 1;
			// 각 자리수별로 0 10 100 .... 1000000 곱해줌
			for (int j = comp.length - 1; j >= 0; j--) {
				alpha[comp[j] - 'A'] += Integer.valueOf(pos);
				pos *= 10;
			}
		}
		Arrays.sort(alpha);
		int digit = 9;
		int res = 0;
		// 곱해준 크기가 큰 알파벳에 9부터 ... 0까지 할당
		for (int i = 25; i >= 0; i--) {
			if (alpha[i] == 0)	break;
			res = res + (alpha[i] * digit);
			digit--;
		}
		System.out.println(res);
		br.close();
	}
}