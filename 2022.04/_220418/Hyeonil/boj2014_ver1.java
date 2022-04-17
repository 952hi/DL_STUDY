import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
	static int target,prime[],max=2147483647;
	public static void main(String[] args) {
		Reader in = new Reader();
		int n = in.nextInt();
		target = in.nextInt();
		prime = new int[n];
		for(int i=0;i<n;i++) prime[i] = in.nextInt();
		HashMap<Integer, Integer> mul= new HashMap<>();
		
		
		for(int i=0;i<n;i++) {
			if(mul.containsKey(prime[i])) continue;
			mul.put(prime[i], i);
			for(int j=prime[i]*prime[i];j<=max;j+=prime[i]) {
				mul.put(j, i);
			}
		}
		System.out.println(1);
	}
}
