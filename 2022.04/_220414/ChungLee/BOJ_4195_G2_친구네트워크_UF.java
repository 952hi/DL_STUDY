package _220414.ChungLee;

import java.io.*;
import java.util.*;

public class BOJ_4195_G2_친구네트워크_UF {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		public String readLine() throws IOException {
			byte[] buf = new byte[bfs]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n') {
					if (cnt != 0) {
						break;
					} else {
						continue;
					}
				}
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		byte read() {
			if (bufferPos == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferPos = 0, bfs);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];
		}

		int nextInt() {
			int rtn = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				rtn = rtn * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -rtn;
			return rtn;
		}
	}

	static Map<String, Integer> rel;
	static List<Integer> set = new LinkedList<>();
	static int[] setArr = new int[200001];

	static boolean UnionFind(int a, int b) {
		a = FindSet(a);
		b = FindSet(b);
		if (a == b)
			return false;
		if (setArr[a] > setArr[b]) {
			setArr[b] += setArr[a];
			setArr[a] = b;
		} else {
			setArr[a] += setArr[b];
			setArr[b] = a;
		}
		return true;
	}

	static int FindSet(int a) {
		if (setArr[a] < 0)
			return a;
		return setArr[a] = FindSet(setArr[a]);
	}

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tc = r.nextInt();
		Queue<Integer> q = new LinkedList<>();
		for (int TC = 0; TC < tc; TC++) {
			int F = r.nextInt();
			Arrays.fill(setArr, -1);
			int fCnt = 0;
			int LFNUM = 0, RFNUM = 0;
			rel = new HashMap<>();
			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(r.readLine());

				String name1 = st.nextToken();
				if (!rel.containsKey(name1)) {
					rel.put(name1, fCnt);
					LFNUM = fCnt;
					fCnt++;
				} else {
					LFNUM = rel.get(name1);
				}

				String name2 = st.nextToken();
				if (!rel.containsKey(name2)) {
					rel.put(name2, fCnt);
					RFNUM = fCnt;
					fCnt++;
				} else {
					RFNUM = rel.get(name2);
				}
				UnionFind(LFNUM, RFNUM);
				int center = (setArr[LFNUM] < 0) ? RFNUM : LFNUM;
				int cnt = 1;
				for (int j = 0; j < fCnt; j++) {
					FindSet(j);
					if (setArr[center] == setArr[j])
						cnt++;
				}
				sb.append(cnt).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}

}
