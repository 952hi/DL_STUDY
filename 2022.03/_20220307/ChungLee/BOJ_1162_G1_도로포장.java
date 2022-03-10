package _20220307.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

//메모리 : 53660KB, 시간 : 568ms
class Reader {
	private int bfs = 1 << 16;
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

	public int nextInt() throws IOException {
		int rtn = 0;
		byte b = read();
		while (b <= ' ')
			b = read();
		boolean neg = (b == '-');
		if (neg)
			b = read();
		do
			rtn = rtn * 10 + b - '0';
		while ('0' <= (b = read()) && b <= '9');
		if (neg)
			return -rtn;
		return rtn;
	}
}

class FromTo implements Comparable<FromTo> {
	int to;
	long weight;
	int loadcnt;

	public FromTo(int to, long weight, int loadcnt) {
		this.to = to;
		this.weight = weight;
		this.loadcnt = loadcnt;
	}

	@Override
	public int compareTo(FromTo o) {
		return Long.compare(this.weight, o.weight);
	}
}

public class BOJ_1162_G1_도로포장 {

	public static void main(String[] args) throws IOException {
		Reader r = new Reader();
		int city = r.nextInt();
		int road = r.nextInt();
		int fined_road = r.nextInt();
		long[][] dist = new long[city + 1][21];
		List<FromTo>[] ListFromTo = new LinkedList[city + 1];
		long maxLong = Long.MAX_VALUE;
		for (int i = 1; i <= city; i++) {
			ListFromTo[i] = new LinkedList<>();
			Arrays.fill(dist[i], maxLong);
		}
		PriorityQueue<FromTo> pq = new PriorityQueue<>();

		for (int i = 0; i < road; i++) {
			int from = r.nextInt();
			int to = r.nextInt();
			int weight = r.nextInt();
			ListFromTo[from].add(new FromTo(to, weight, 0));
			ListFromTo[to].add(new FromTo(from, weight, 0));
		}

		pq.add(new FromTo(1, 0, 0));

		while (!pq.isEmpty()) {
			FromTo tmp = pq.poll();
			int crnt = tmp.to;
			long weight = tmp.weight;
			int roadcnt = tmp.loadcnt;
			if (dist[crnt][roadcnt] < weight)
				continue;
			if (ListFromTo[crnt] != null) {
				for (FromTo next : ListFromTo[crnt]) {
					int nextN = next.to;
					long nextDist = weight + next.weight;
					if (dist[nextN][roadcnt] > nextDist) {
						dist[nextN][roadcnt] = nextDist;
						pq.offer(new FromTo(nextN, nextDist, roadcnt));
					}
					if (roadcnt < fined_road && dist[nextN][roadcnt + 1] > weight) {
						dist[nextN][roadcnt + 1] = weight;
						pq.offer(new FromTo(nextN, weight, roadcnt + 1));
					}
				}
			}
		}
		long min = Long.MAX_VALUE;
		for (int i = 0; i <= fined_road; i++)
			min = Math.min(min, dist[city][i]);
		System.out.println(min);
	}
}
