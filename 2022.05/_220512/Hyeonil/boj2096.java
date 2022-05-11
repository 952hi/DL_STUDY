import java.io.*;
public class boj2096 {
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
		int[][][] comp = new int[n][3][2]; // 0 최대 1 최소
		int check;
		
		for(int i=0;i<n;i++) {
			comp[i][0][0] = in.nextInt();
			comp[i][0][1] = comp[i][0][0];
			
			comp[i][1][0] = in.nextInt(); 
			comp[i][1][1] = comp[i][1][0];
			
			comp[i][2][0] = in.nextInt();
			comp[i][2][1] = comp[i][2][0];
		}
		
		for(int i=1;i<n;i++) {
			comp[i][0][0] = Math.max(comp[i-1][0][0]+comp[i][0][0], comp[i-1][1][0]+comp[i][0][0]);
			comp[i][0][1] = Math.min(comp[i-1][0][1]+comp[i][0][1], comp[i-1][1][1]+comp[i][0][1]);
			
			check =Math.max(comp[i-1][0][0]+comp[i][1][0], comp[i-1][1][0]+comp[i][1][0]);
			comp[i][1][0] = Math.max(check, comp[i-1][2][0]+comp[i][1][0]);
			check =Math.min(comp[i-1][0][1]+comp[i][1][1], comp[i-1][1][1]+comp[i][1][1]);
			comp[i][1][1] = Math.min(check, comp[i-1][2][1]+comp[i][1][1]);
			
			comp[i][2][0] = Math.max(comp[i-1][1][0]+comp[i][2][0], comp[i-1][2][0]+comp[i][2][0]);
			comp[i][2][1] = Math.min(comp[i-1][1][1]+comp[i][2][1], comp[i-1][2][1]+comp[i][2][1]);
		}
		int min=comp[n-1][0][1],max=comp[n-1][0][0];
		for(int i=1;i<3;i++) {
			if(comp[n-1][i][1]<min) min =comp[n-1][i][1];
			if(comp[n-1][i][0]>max) max =comp[n-1][i][0];
		}
		System.out.println(max+" "+min);
	}
}