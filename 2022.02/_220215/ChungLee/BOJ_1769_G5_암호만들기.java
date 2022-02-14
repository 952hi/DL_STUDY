package _220215.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1769_G5_암호만들기 {
	static int strCnt = 0;
	static String[] save = null;
	static int apbCnt = 0;
	static String[] alpa = null;

	static void a(int arrCnt, int ptApb) {
		int isMoTrue = 0;
		int isJaTrue = 0;
		if(ptApb > apbCnt || (ptApb == apbCnt && arrCnt < strCnt)) {
			return;
		}
		else if(arrCnt == strCnt) {
			for (int i = 0; i < save.length; i++) {
				if (save[i].equals("a") || save[i].equals("e") || save[i].equals("i") || save[i].equals("o")
						|| save[i].equals("u")) {
					isMoTrue = 1;
				}
				else {
					isJaTrue++;
				}
			}
			if(isMoTrue == 1 && isJaTrue > 1) {
				for (int i = 0; i < save.length; i++) 
					System.out.print(save[i]);
				System.out.println();
				return;
			}
			return;
		}

		int last = 0;
		for (int i = 0; i < save.length; i++) {
			if (save[i].equals("") || save[i].equals(null)) {
				save[i] = alpa[ptApb];
				last = i;
				break;
			}
		}

		a(arrCnt + 1, ptApb+1 );
		save[last] = "";
		a(arrCnt, ptApb+1 );
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		strCnt = Integer.parseInt(st.nextToken());
		apbCnt = Integer.parseInt(st.nextToken());
		alpa = br.readLine().split(" ");
		save = new String[strCnt];
		for (int i = 0; i < save.length; i++) {
			save[i] = "";
		}
		Arrays.sort(alpa);

		a(0, 0);
	}
}
