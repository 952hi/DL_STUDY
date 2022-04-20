import java.io.*;
import java.util.*;
public class boj1414 {
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
	static int p[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> q = new PriorityQueue<>((o1,o2)->o1[2]-o2[2]);
		int cnt=0,res=-1;
		int a = 0;
		int cable = 0;
		p = new int[n+1];
		for(int i=1;i<n+1;i++) p[i]=i;
		String temp;
		for(int i=0;i<n;i++) {
			temp = br.readLine();
			for(int j=0;j<n;j++) {
				a = temp.charAt(j)-0;
				if(a>=97) a-= 96;
				else a -=38;
				if(temp.charAt(j)=='0') continue;
				cable += a;
				if(i==j) continue;
				q.offer(new int[]{i,j,a});
			}
		}
		a=0;
		if(!q.isEmpty()) {
			int[] comp;
			while(!q.isEmpty()) {
				comp = q.poll();
				if(unionp(comp[0],comp[1])) {
					cnt++;
					a+=comp[2];
				}
				if(cnt==n-1) {
					res = cable - a;
					break;
				}
			}
		}
		if(n==1) res = cable;
		System.out.println(res);
	}
	private static boolean unionp(int i, int j) {
		i = findp(i);
		j = findp(j);
		if(i==j) return false;
		if(i>j) p[i]= j;
		else p[j] = i;
		return true;
	}
	private static int findp(int x) {
		if(p[x]==x) return x;
		return p[x]=findp(p[x]);
	}
}