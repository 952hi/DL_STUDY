import java.io.*;
import java.util.*;
public class boj2014 {
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
	public static void main(String[] args) {
		Reader in = new Reader();
		int n = in.nextInt();
		int target = in.nextInt();
		int[] prime = new int[n];
		PriorityQueue<Integer> q = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<n;i++) {
			prime[i] = in.nextInt();
			q.offer(prime[i]);
		}
		int res = 0;
		int temp = 0;
		for(int i=0;i<target-1;i++) {
			res = q.poll();
			for(int j=0;j<n;j++) {
				temp = res*prime[j];
				if(map.containsKey(temp)) continue;
				q.offer(temp);
				map.put(temp,j);
			}
		}
		System.out.println(q.poll());
	}
}