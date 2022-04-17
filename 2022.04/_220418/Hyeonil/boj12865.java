import java.io.*;

public class boj12865 {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferLeft = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() {
			if (bufferLeft == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferLeft = 0, bfs);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferLeft++];
		}

		int nextInt() {
			int n = 0;
			byte b = read();
			while (b <= ' ')
				b = read();
			boolean neg = (b == '-');
			if (neg)
				b = read();
			do
				n = n * 10 + b - '0';
			while ('0' <= (b = read()) && b <= '9');
			if (neg)
				return -n;
			return n;
		}
	}
	public static void main(String[] args) {
		Reader in = new Reader();
		int m = in.nextInt();
		int wei = in.nextInt();
		int[] value = new int[wei+1];
		int comp[][] = new int[m][2];
		for(int i=0;i<m;i++) {
			comp[i][0] = in.nextInt();
			comp[i][1] = in.nextInt();
		}
		int temp;
	out:for(int k=0;k<m;k++) {
			for(int i=wei;i>0;i--) {//무
				if(i - comp[k][0]<0) continue out;
				temp = comp[k][1]+value[i-comp[k][0]];
				if(temp>value[i]) {
					value[i] = temp;
				}
			}
		}
		System.out.println(value[wei]);
	}
}