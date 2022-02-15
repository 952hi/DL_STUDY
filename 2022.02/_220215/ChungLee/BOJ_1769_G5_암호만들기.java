package _220215.ChungLee;

/**
 * @author LeeChung
 * @problem 암호 만들기
 * @link https://www.acmicpc.net/problem/1759
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1769_G5_암호만들기 {
	static int L = 0;
	static String[] save = null;
	static int C = 0;
	static String[] totalAlphabet = null;
	static StringBuilder sb = new StringBuilder();

	static void DFS_BackTracking(int arrCnt, int ptApb) {
		if(ptApb > C || (ptApb == C && arrCnt < L)) {
			return;
		}
		else if (arrCnt == L) {
			// 모음 수 카운트
			boolean isMoTrue = false;
			int isJaTrue = 0;

			// 모음과 자음의 수를 카운트
			for (int i = 0; i < save.length; i++) {
				if (save[i].equals("a") || save[i].equals("e") || save[i].equals("i") || save[i].equals("o")
						|| save[i].equals("u")) {
					isMoTrue = true;
				} else {
					isJaTrue++;
				}
			}

			// 모음이 최소 1개이거나 자음이 2개 이상일 때 출력
			if (isMoTrue && (isJaTrue > 1)) {
				for (int i = 0; i < save.length; i++)
					sb.append(save[i]);
				sb.append("\n");
			}
			return;
		}

		int last = 0;
		
		//빈칸을 찾아 값을 넣는 것
		for (int i = 0; i < save.length; i++) {
			if (save[i] == null || save[i].equals("")) {
				save[i] = totalAlphabet[ptApb];
				// 마지막으로 넣었던 값의 위치 저장
				last = i;
				break;
			}
		}

		// 이진 탐색
		DFS_BackTracking(arrCnt + 1, ptApb + 1);
		// 넣어주었던 값을 삭제
		save[last] = "";
		DFS_BackTracking(arrCnt, ptApb + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		// ex) 4 입력
		L = Integer.parseInt(st.nextToken());
		// ex) 6 입력
		C = Integer.parseInt(st.nextToken());

		// ex) a t c i s w 입력
		totalAlphabet = br.readLine().split(" ");

		// 가능한 암호를 저장할 배열 초기화
		save = new String[L];

		// 사전순이기 때문에 문자열 정렬
		Arrays.sort(totalAlphabet);

		DFS_BackTracking(0, 0);
		bw.write(sb.toString());
		bw.flush();
	}
}
