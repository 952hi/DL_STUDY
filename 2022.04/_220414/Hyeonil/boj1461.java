import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
public class boj1461 {
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
	static PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder()), minus = new PriorityQueue<>(Collections.reverseOrder());
	static int n,m,res,pluscnt,minuscnt,plussum,minussum;
	public static void main(String[] args) {
		Reader in = new Reader();
		n = in.nextInt();
		m = in.nextInt();
		res =0;
		pluscnt = 0;
		minuscnt = 0;
		plussum = 0;
		minussum = 0;
		int temp;
		for(int i=0;i<n;i++) {
			temp = in.nextInt();
			if(temp>0) {
				plus.offer(temp);
				pluscnt++;
				
			}
			else {
				minus.offer(-temp);
				minuscnt++;
			}
		}
		int a=0,b=0,max=0;
		if(!plus.isEmpty()) {
			a= plus.peek();
			plus();
		}
		if(!minus.isEmpty()) {
			b= minus.peek();
			minus();
		}
		max = Math.max(a, b);
			
		System.out.println(res-max);
	}
	private static void minus() {
		int book=0;
		while(minuscnt>0) {
			book = minus.poll();
			minuscnt--;
			if(minuscnt>=1) {
				for(int i=0;i<m-1;i++) {
					minus.poll();
					minuscnt--;
					if(minuscnt==0) break;
				}
			}
			res += book*2;
		}
	}
	private static void plus() {
		int book=0;
		while(pluscnt>0) {
			book = plus.poll();
			pluscnt--;
			if(pluscnt>=1) {
				for(int i=0;i<m-1;i++) {
					plus.poll();
					pluscnt--;
					if(pluscnt==0) break;
				}
			}
			res += book*2;
		}
	}
}