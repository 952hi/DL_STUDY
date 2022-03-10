package _20220307.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1786_P5_찾기 {
	static class Reader {
		private int bfs = 1 << 20;
		private byte[] buffer = new byte[bfs];
		private DataInputStream dis = new DataInputStream(System.in);
		private int bufferLeft = 0, bufferState = 0;

		private byte read() throws IOException {
			if (bufferLeft == bufferState) {
				bufferState = dis.read(buffer, bufferLeft = 0, bfs);
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferLeft++];
		}

		public String readLine() throws IOException {
			byte[] save = new byte[bfs];
			byte b;
			int cnt = 0;
			while ((b = read()) != -1) {
				if (b == '\n' || b == '\r') {
					if (cnt == 0)
						continue;
					else
						break;
				}
				save[cnt++] = b;
			}
			return new String(save, 0, cnt);
		}
	}

	static char[] ptnChr;
	static char[] tChr;
	static int ptnLng;
	static int tLng;
	static int[] table;
	static int cnt = 0;
	static StringBuilder sb = new StringBuilder();
	static int maxPos = 0;

	static void MakeTable() {
		table = new int[ptnLng];
		int idx = 0;
		for (int i = 1; i < ptnLng; i++) {
			while (idx > 0 && ptnChr[i] != ptnChr[idx])
				idx = table[idx - 1];
			if (ptnChr[i] == ptnChr[idx]) {
				idx += 1;
				table[i] = idx;
				maxPos = Math.max(maxPos, idx);
			}
		}
	}

	static void KMP() {
		int idx = 0;
		for (int i = 0; i < tChr.length; i++) {
			while (idx > 0 && tChr[i] != ptnChr[idx])
				idx = table[idx - 1];
			if (tChr[i] == ptnChr[idx]) {
				if (idx + 1 == ptnLng) {
					cnt++;
					sb.append(i - (ptnLng - 1) + 1).append(" ");
					idx = table[idx];
				} else {
					idx += 1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		//Reader r = new Reader();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		tChr = r.readLine().toCharArray();
		ptnChr = r.readLine().toCharArray();
		ptnLng = ptnChr.length;
		tLng = tChr.length;
		if (ptnLng > tLng)
			System.out.println(0);
		MakeTable();
		KMP();
		System.out.println(cnt);
		bw.write(sb.toString());
		bw.flush();
	}
}
