package _220519.ChungLee;

import java.io.BufferedWriter;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BOJ_11505_G1_구간곱구하기 {

	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() throws IOException {
			if (bufferPos == bufferState) {
				bufferState = dis.read(buffer, bufferPos = 0, bfs);

				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];

		}

		int nextInt() throws IOException {
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

	static int[] data;
	static long[] tree;
	static long[] lazy;
	static int MOD = 1000000007;

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = data[start];
			return;
		}
		int mid = (start + end) / 2;
		init(node * 2 + 1, start, mid);
		init(node * 2 + 2, mid + 1, end);
		tree[node] = (tree[node * 2 + 1] * tree[node * 2 + 2]) % MOD;
	}

	static long mul(int node, int start, int end, int left, int right) {

		if (right < start || end < left)
			return 1;
		else if (left <= start && end <= right)
			return tree[node];
		int mid = (start + end) / 2;
		long one = mul(node * 2 + 1, start, mid, left, right) % MOD;
		long two = mul(node * 2 + 2, mid + 1, end, left, right) % MOD;
		return (one * two) % MOD;
	}

	static long mulV = 0;

	static void update(int node, int start, int end, int index, long diff) {
		// 포함되지 않는 경우
		if (index < start || end < index)
			return;
		else if (start == end) {
			tree[node] = diff;
			return;
		}

		update(node * 2 + 1, start, (start + end) / 2, index, diff);
		update(node * 2 + 2, (start + end) / 2 + 1, end, index, diff);
		tree[node] = (tree[node * 2 + 1] * tree[node * 2 + 2]) % MOD;
	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int size = br.nextInt();
		int changeCnt = br.nextInt();
		int mulCnt = br.nextInt();
		data = new int[size];
		for (int i = 0; i < size; i++)
			data[i] = br.nextInt();

		int treeSize = 1;
		while (treeSize < size)
			treeSize <<= 1;

		tree = new long[treeSize * 2];
//		lazy = new long[treeSize * 2];

		init(0, 0, size - 1);

		int turn, from, to;
		for (int i = 0; i < changeCnt + mulCnt; i++) {
			turn = br.nextInt();
			from = br.nextInt();
			to = br.nextInt();
			// change
			if (turn == 1) {
				update(0, 0, data.length - 1, from - 1, to);
			}
			// mul
			else {
				sb.append(mul(0, 0, data.length - 1, from - 1, to - 1)).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}

}
