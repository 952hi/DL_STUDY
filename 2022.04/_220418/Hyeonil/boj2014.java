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
		public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
	}
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		int n = in.nextInt();
		int target = in.nextInt();
		long[] prime = new long[n];
		PriorityQueue<Long> q = new PriorityQueue<>();
		for(int i=0;i<n;i++) {
			prime[i] = in.nextLong();
			q.offer(prime[i]);
		}
		long comp = 0;
		long res =0;
		for(int i=0;i<target;i++) {
			comp = q.poll();
			for(int j=0;j<n;j++) {
				res = comp*prime[j];
				q.offer(res);
				if(comp % prime[j] == 0)break;
			}
		}
		System.out.println(comp);
	}
}