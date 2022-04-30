package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_9935_G4_문자열폴폭발 {

	static void make_table() {
		j = 0;
		int subL = subContext.length;
		sub_table = new int[subL];
		for (int i = 1; i < subL; i++) {
			while (j > 0 && subContext[i] != subContext[j])
				j = sub_table[j - 1];
			if (subContext[i] == subContext[j]) {
				sub_table[i] = ++j;
			}

		}
	}

	static boolean compare_patterns() {
		j = 0;
		cnt = 0;
		crnt = 0;
		check = false;
		int[] bomb = new int[37];
		int next = 0;
		int subContextL = subContext.length;
		for (int i = 0; i < mainContext.length; i++) {
			while (j > 0 && subContext[j] != mainContext[i]) {
				// 폭발된 문자는 건너띔
				if (mainContext[i] == '-') {
					j++;
					break;
				}
				j = sub_table[j - 1];
				next -= j;
			}
			if (mainContext[i] == subContext[j]) {
				if (j == subContext.length - 1) {
					check = true;
					point[cnt] = i - j;
					cnt++;
					j = 0;
					for (int k = 0; k < subContextL; k++) {
						mainContext[bomb[k]] = '-';
					}
				} else {
					j++;
					bomb[next++] = j;
				}
			}
		}
		for (int i = 0; i < cnt; i++) {
			crntStartPoint = point[i];
			sb.append(mainContextS.substring(crnt, crntStartPoint));
			crnt = crntStartPoint + subContext.length;
		}
		if (check) {
			crnt = crntStartPoint + subContext.length;
			sb.append(mainContextS.substring(crnt, mainContextS.length()));
			mainContextS = sb.toString();
			mainContext = mainContextS.toCharArray();
		}
		sb.delete(0, sb.length());
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
		make_table();
		while (true) {
			if (mainContext.length == 0) {
				bw.write("FRULA");
				break;
			}
			if (!compare_patterns()) {
				bw.write(mainContext);
				break;
			}
		}
		bw.flush();
	}
}
