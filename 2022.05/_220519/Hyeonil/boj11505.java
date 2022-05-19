import java.io.*;
public class boj11505 {
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
	static int n,MOD=1000000007,input[];
	static long tree[];
	public static void main(String[] args) throws IOException {
		StringBuilder sb =new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Reader in = new Reader();
		n = in.nextInt();//리프노드의 개수
		int m = in.nextInt();
		int k = in.nextInt();
		int size = (int)Math.pow(2, (int)Math.ceil(Math.log(n)/Math.log(2))+1);
		input = new int[n+1]; // 1번 인덱스부터 한 이유는 자식이 2n , 2n+1 // 0이면 2n+1 , 2n+2
		tree = new long[size];
		
		for(int i=1;i<=n;i++) input[i] = in.nextInt();
		init(1,n,1); // 세그먼트 트리 생성
		
		int type,one,two;
		for(int i=0;i<m+k;i++) {// m와 k가 번갈아 나오기 때문에 총횟수만큼 반복하고 1,2냐에 따라 특정 함수 호출
			type = in.nextInt();
			one = in.nextInt();
			two = in.nextInt();
			if(type == 1) {
				input[one] = two;
				update(1,n,1,one,two);
			}else if(type==2) {
				sb.append(mul(1,n,1,one,two)).append("\n");
			}
		}
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	// 구간값구하기
	private static long mul(int start, int end, int nodeidx, int minidx, int maxidx) {
		if(end<minidx || maxidx<start) return 1;
		if(minidx<=start && end<=maxidx ) return tree[nodeidx];
		int mid = (start+end)/2;
		return (mul(start,mid,nodeidx*2,minidx,maxidx)*mul(mid+1, end, nodeidx*2+1, minidx, maxidx))%MOD;
	}
	// 값변경 리프노드에서 루트노드까지
	private static long update(int start, int end, int nodeidx, int idx, int val) {
		if(end<idx || idx<start) return tree[nodeidx];
		if(start==end) return tree[nodeidx]=val;
		int mid = (start+end)/2;
		return tree[nodeidx] = (update(start, mid, nodeidx*2, idx, val)*update(mid+1, end, nodeidx*2+1, idx, val))%MOD;
	}
	// 세그먼트 트리 초기화
	private static long init(int start, int end, int nodeidx) {
		if(start==end) return tree[nodeidx]=input[start];
		int mid = (start+end)/2;
		return tree[nodeidx] = (init(start, mid, nodeidx*2)*init(mid+1,end,nodeidx*2+1))%MOD;
	}
}