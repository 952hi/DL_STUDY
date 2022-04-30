package _220505.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9935_G4_문자열폭발_me {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		char[] main = br.readLine().toCharArray();
		char[] sub = br.readLine().toCharArray();
		int mainL = main.length, subL = sub.length;
		
		//각 문자를 저장
		char[] stackC = new char[mainL + 1];
		//문자가 패턴의 몇 번째 자리인지 저장
		int[] stackI = new int[mainL + 1];

		int cnt = 0;
		stackI[0] = -1;

		int stackIcnt = 1, stackCcnt = 0;
		for (int i = 0, size = mainL; i < size; i++) {
			if (main[i] != sub[cnt])
				cnt = 0;

			if (main[i] == sub[cnt]) {
				stackI[stackIcnt++] = cnt++;
			} else {
				stackI[stackIcnt++] = -1;

			}

			stackC[stackCcnt++] = main[i];
			//배열로 관리할 경우 pop 과정 단축 가능
			if (stackI[stackIcnt - 1] == subL - 1) {
				stackIcnt -= subL;
				stackCcnt -= subL;
				cnt = stackI[stackIcnt - 1] + 1;
			}
		}
		if (stackCcnt != 0)
			// new String으로 문자열을 생성하면 더 빠름
			bw.write(new String(stackC, 0, stackCcnt));
		else
			bw.write("FRULA");

		bw.flush();
	}

}
