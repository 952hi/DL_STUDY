package _220418.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

// 중복없이 정렬되는 자료구조 TreeSet을 활용해서 가능한 모든 경우의 수를 입력넣어주려 했습니다.

// 1. 처음에 입력받는 하나의 소수마다 전체 입력받는 소수를 순차적으로 곱하는 과정을 계속 반복합니다. 
// 2. 큐를 활용해서 연산을 한 값을 TreeSet과 queue에 넣어줘서 계속해서 연산과 입력이 가능하게 합니다.
// 3. 입력받은 한 소수를 10만개 입력받을 때까지 혹은 연산 값이 2^31승에 도달할 때까지 반복합니다.

// 시간 복잡도 : 첫 입력으로 주어지는 소수의 갯수는 최대 100개이기 때문에 100 * 10만 = 1000만으로 충분히 수용 가능하리라 생각했습니다.
public class BOJ_2014_G1_소수의곱 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

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

	static Set<Integer> set = new TreeSet<>();

	public static void main(String[] args) {
		Reader r = new Reader();
		System.out.println();
		int K = r.nextInt();
		int N = r.nextInt();
		int[] V = new int[K];

		for (int i = 0; i < K; i++) {
			V[i] = r.nextInt();
			set.add(V[i]);
		}
		for (int i = 0; i < K; i++) {
			for (int j : set) {
				System.out.println(j);
			}
		}

	}

}
