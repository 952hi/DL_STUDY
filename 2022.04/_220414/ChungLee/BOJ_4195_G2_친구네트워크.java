package _220414.ChungLee;

import java.io.*;
import java.util.*;

public class BOJ_4195_G2_친구네트워크 {
	static class Reader {
		int bfs = 1 << 6;
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

	static int[] setArr;

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
		boolean LFirst = false, RFirst = false;
		Map<String, Integer> rel;
		int[] setArr;
		int[] cnting = new int[200001];
		for (int TC = 0; TC < tc; TC++) {
			int F = r.nextInt();
			int fCnt = 0;
			int LFNUM = 0, RFNUM = 0;
			rel = new HashMap<>();
			setArr = new int[200001];

			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(r.readLine());
				String name1 = st.nextToken();
				if (!rel.containsKey(name1)) {
					rel.put(name1, fCnt);
					LFNUM = fCnt;
					setArr[fCnt] = -1;
					fCnt++;
					LFirst = true;
				} else {
					LFNUM = rel.get(name1);
				}
				String name2 = st.nextToken();
				if (!rel.containsKey(name2)) {
					rel.put(name2, fCnt);
					RFNUM = fCnt;
					setArr[fCnt] = -1;
					fCnt++;
					RFirst = true;
				} else {
					RFNUM = rel.get(name2);
				}

				// 연결된 친구 카운팅하기
				// 둘다 처음 들어왔을 때
				if (LFirst && RFirst) {
					sb.append(2).append("\n");
					cnting[RFNUM] = 2;
					cnting[LFNUM] = 2;
					UnionFind(RFNUM, LFNUM);
					LFirst = false;
					RFirst = false;

				}
				
				// 왼쪽 친구가 처음 들어왔을 때
				else if (LFirst) {
					// 부모 찾기
					int parentNum = FindSet(RFNUM);
					// 둘 중 더 큰 값으로 둘 다 최신화
					if (cnting[parentNum] < cnting[RFNUM]) {
						cnting[parentNum] = cnting[RFNUM];
					} else {
						cnting[RFNUM] = cnting[parentNum];
					}
					// 신규 노드가 들어왔기 때문에 +1
					if (RFNUM != parentNum)
						cnting[parentNum]++;
					cnting[RFNUM]++;

					// 뒤에 들어온 친구도 가장 큰 값으로 최신화
					cnting[LFNUM] = cnting[RFNUM];
					// 연결
					UnionFind(RFNUM, LFNUM);
					sb.append(cnting[RFNUM]).append("\n");
					LFirst = false;
				}
				// 오른쪽 친구가 처음 들어왔을 때
				else if (RFirst) {
					// 부모 찾기
					int parentNum = FindSet(LFNUM);
					if (cnting[parentNum] < cnting[LFNUM]) {
						cnting[parentNum] = cnting[LFNUM];
					} else {
						cnting[LFNUM] = cnting[parentNum];
					}
					if (LFNUM != parentNum)
						cnting[parentNum]++;
					cnting[LFNUM]++;

					cnting[RFNUM] = cnting[LFNUM];

					UnionFind(RFNUM, LFNUM);
					sb.append(cnting[LFNUM]).append("\n");
					RFirst = false;
				}
				//
				else {
					// 둘의 부모가 같을 때
					int LpN = FindSet(LFNUM);
					int RpN = FindSet(RFNUM);
					// 부모가 다를 때
					if (LpN != RpN) {
						int max = Math.max(cnting[LFNUM], cnting[LpN]) + Math.max(cnting[RFNUM], cnting[RpN]);
						cnting[LFNUM] = max;
						cnting[LpN] = max;
						cnting[RFNUM] = max;
						cnting[RpN] = max;
						UnionFind(LFNUM, RFNUM);
						sb.append(max).append("\n");
					} else {
						int parentNum = FindSet(LFNUM);
						int max = Math.max(cnting[RFNUM], Math.max(cnting[parentNum], cnting[LFNUM]));
						cnting[RFNUM] = max;
						cnting[LFNUM] = max;
						cnting[parentNum] = max;
						sb.append(cnting[parentNum]).append("\n");
					}
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
