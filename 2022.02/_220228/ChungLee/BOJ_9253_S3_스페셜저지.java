package _220228.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;

/*
abcdefberhgnbcxhabcdefabcabcdefabcd
abcdefberhgnbcxhabcdefabcabcdefabcd
abcdefabcd
 */
//참고 블로그 : https://loosie.tistory.com/192
class BOJ_9253_S3_스페셜저지 {
	// fastIO
	static class Reader {
		int bfs = 1 << 18;
		byte[] buffer = new byte[bfs];
		byte[] tmp;
		DataInputStream dis = new DataInputStream(System.in);
		int bufferLeft = 0, bufferState = 0;

		byte read() throws IOException {
			if (bufferLeft == bufferState) {
				bufferState = dis.read(buffer, bufferLeft = 0, bfs);
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferLeft++];
		}

		String readLine() throws IOException {
			int cnt = 0;
			tmp = new byte[bfs];
			byte b;

			while ((b = read()) != -1) {
				if (b == '\n' || b == '\r') {
					if (cnt == 0) {
						continue;
					} else {
						break;
					}
				}
				tmp[cnt++] = b;
			}
			return new String(tmp, 0, cnt);
		}
	}

	// KMP 테이블 생성
	static void makeTable() {
		char[] ptrnChr = pattern.toCharArray();
		int ptrnlngth = ptrnChr.length;
		table = new int[ptrnlngth];

		int idx = 0;
		for (int i = 1; i < ptrnlngth; i++) {
			// 일치하는 문자가 발생했을 때(idx>0), 연속적으로 더 일치하지 않으면 idx = table[idx-1]로 돌려준다.
			while (idx > 0 && ptrnChr[i] != ptrnChr[idx]) {
				idx = table[idx - 1];
			}
			// 해당 차례
			if (ptrnChr[i] == ptrnChr[idx]) {
				idx += 1;
				table[i] = idx;
			}
		}
	}

	// KMP 알고리즘 비교
	static boolean KMP(String parent) {
		char[] arrParent = parent.toCharArray();
		char[] arrPattern = pattern.toCharArray();

		int idx = 0;

		for (int i = 0; i < arrParent.length; i++) {
			while (idx > 0 && arrParent[i] != arrPattern[idx]) {
				idx = table[idx - 1];
			}
			if (arrParent[i] == arrPattern[idx]) {
				if (idx == arrPattern.length - 1) {
					return true;
				} else {
					idx += 1;
				}
			}
		}
		return false;
	}

	static int cl = 0;
	static String c = null;
	static String pattern = null;
	static int max = 0;
	static int[] table;

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		String a = r.readLine();
		String b = r.readLine();
		pattern = r.readLine();
		makeTable();
		if (KMP(a) && KMP(b))
			System.out.println("YES");
		else
			System.out.println("NO");
	}
}
