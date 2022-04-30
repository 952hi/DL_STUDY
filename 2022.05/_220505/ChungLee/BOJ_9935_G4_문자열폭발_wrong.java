package _220505.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9935_G4_문자열폭발_wrong {

	static boolean compare_patterns() {
		j = 0;
		cnt = 0;
		crnt = 0;
		check = false;
		int[] bomb = new int[37];
		int next = 0;
		int subContextL = subContext.length;
		for (int i = 0; i < mainContext.length; i++) {
			if (mainContext[i] == '-') {
				continue;
			}
			while (j > 0 && subContext[j] != mainContext[i]) {
				// 폭발된 문자는 건너띔

				j = 0;
				next = 0;
			}
			if (mainContext[i] == subContext[j]) {
				if (j == subContext.length - 1) {
					bomb[next] = i;
					check = true;
					point[cnt] = i - j;
					cnt++;
					j = 0;
					next = 0;
					for (int k = 0; k < subContextL; k++) {
						mainContext[bomb[k]] = '-';
					}

				} else {
					j++;
					bomb[next++] = i;
				}
			}
		}
		return check;
	}

	static int j, crntStartPoint = 0, crnt, cnt;
	static boolean check;
	static int[] point;
	static StringBuilder sb = new StringBuilder();
	static char[] mainContext;
	static String mainContextS, subContextS;
	static int[] sub_table;
	static char[] subContext;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		mainContextS = br.readLine();
		subContextS = br.readLine();
		mainContext = mainContextS.toCharArray();
		subContext = subContextS.toCharArray();
		point = new int[mainContextS.length()];
//		while (true) {
//			if (!compare_patterns()) {
//				for (int i = 0; i < mainContext.length; i++) {
//					if (mainContext[i] != '-')
//						sb.append(mainContext[i]);
//				}
//				if (sb.length() == 0) {
//					sb.append("FRULA");
//				}
//				bw.write(sb.toString());
//				break;
//			}
//		}
		int L = mainContextS.length();
		while (true) {
			mainContextS = mainContextS.replace(subContextS, "");
			if (mainContextS.length() == L)
				break;
			L = mainContextS.length();
		}
		if (L == 0)
			System.out.println("FRULA");
		else
			System.out.println(mainContextS);
//		bw.flush();
	}
}
