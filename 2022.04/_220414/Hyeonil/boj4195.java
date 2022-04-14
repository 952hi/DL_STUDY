import java.io.*;
import java.util.*;
public class acm4195 {
	static class Reader {
	      int bfs = 1 << 16;
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
	static int parents[],n,sum[];
	public static void main(String[] args) throws IOException {
		Reader in = new Reader();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = in.nextInt();
		StringTokenizer stz;
		StringBuilder sb = new StringBuilder();
		HashMap<String, Integer> name;
		for(int t=0;t<tc;t++) {
			n = in.nextInt();
			parents = new int[2*n+1];
			sum = new int[2*n+1];
			for(int i=1;i<2*n+1;i++) {
				parents[i]=i;
				sum[i] = 1;
			}
			name = new HashMap<>();
			int cnt = 1;
			String temp,comp;
			for(int i=0;i<n;i++) {
				int res=0;
				stz = new StringTokenizer(in.readLine());
				temp = stz.nextToken();
				if(!name.containsKey(temp)) {
					name.put(temp, cnt++);
				}
				comp = stz.nextToken();
				if(!name.containsKey(comp)) {
					name.put(comp, cnt++);
				}
				
				if(findset(name.get(temp))!=findset(name.get(comp))) {
					union(name.get(temp), name.get(comp));
				}
				sb.append(Math.max(sum[parents[name.get(temp)]], sum[parents[name.get(comp)]])).append("\n");
			}
			name.clear();
		}
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
	}
	public static int findset(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findset(parents[a]); // 경로 압축
	}
	public static void union(int a,int b) {
		a = findset(a);
		b = findset(b);
		if(a==b) return;
		if(a<b) {
			sum[a] += sum[b];
			parents[b] = a;
		}
		else {
			sum[b] += sum[a];
			parents[a] = b;
		}
		return;
	}
}