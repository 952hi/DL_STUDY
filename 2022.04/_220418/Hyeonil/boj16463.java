import java.io.*;
public class boj16463 {
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
					// TODO Auto-generated catch block
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
	static int n,month[]= {0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	public static void main(String[] args) {
		Reader in = new Reader();
		n = in.nextInt();
		int day = 4;
		int res = 0;
year:	for(int year=2019;year<=n;year++) {
			checkyear(year);

	mon:	for(int i=1;i<=12;i++) {
				while(true) {
					if(day==13) {
						res++;
					}
					day+=7;
					if(day>month[i]) {
						day -= month[i];
						if(i==12) {
							continue year;
						}
						continue mon;
					}
				}
			}
		}
		System.out.println(res);
	}
	private static void checkyear(int year) {
		if(year%400==0) {
			month[2] = 29;
			return;
		}
		if(year%100!=0 && year%4==0) {
			month[2] = 29;
			return;
		}
		month[2] = 28;
	}
}