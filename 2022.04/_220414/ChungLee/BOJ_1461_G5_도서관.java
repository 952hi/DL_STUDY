package _220414.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class BOJ_1461_G5_도서관 {
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

	static int sum = 0;

	public static void main(String[] args) {
		Reader r = new Reader();
		int bookCnt = r.nextInt();
		int handMax = r.nextInt();
		int[] bookArr = new int[bookCnt];
		int Lcnt = 0, Rcnt = 0;

		for (int i = 0; i < bookCnt; i++) {
			bookArr[i] = r.nextInt();
			if (bookArr[i] < 0)
				// 왼쪽 도서 권수
				Lcnt++;
		}
		// 오른쪽 도서 권수
		Rcnt = bookCnt - Lcnt;
		// 정렬
		Arrays.parallelSort(bookArr);
		
		//왼쪽에 최대 권수보다 더 많은 책이 있을 때
		if (Lcnt > handMax) {
			//최적 정리를 위해 먼저 정리해야할 도서 수
			int mod = Lcnt % handMax;
			if (mod != 0) {
				sum += bookArr[Lcnt - mod] * 2;
				Lcnt -= mod;
			}
			//왼쪽에 한 번에 들 수 있는 도서 권수만 남기고 모두 정리
			while (Lcnt != handMax) {
				sum += bookArr[Lcnt - handMax] * 2;
				Lcnt -= handMax;
			}
		}
		//음수기 때문에 값 반전
		sum = -sum;
		
		// 오른쪽 도서 정리
		int RbookStrtPos = bookCnt - Rcnt - 1;
		if (Rcnt > handMax) {
			int mod = Rcnt % handMax;
			if (Rcnt % handMax != 0) {
				sum += bookArr[RbookStrtPos + mod] * 2;
				RbookStrtPos += mod;
				Rcnt -= mod;
			}
			while (Rcnt != handMax) {
				sum += bookArr[RbookStrtPos + handMax] * 2;
				RbookStrtPos += handMax;
				Rcnt -= handMax;
			}
		}
		if (Rcnt > 0 && Lcnt > 0) {
			if (-bookArr[0] > bookArr[bookCnt - 1])
				sum += bookArr[bookCnt - 1] * 2 + -bookArr[0];
			else
				sum += bookArr[bookCnt - 1] + -(bookArr[0] * 2);
		} else if (Lcnt > 0)
			sum += -bookArr[0];
		else
			sum += bookArr[bookCnt - 1];

		System.out.println(sum);
	}
}
