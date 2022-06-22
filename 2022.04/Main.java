import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() throws IOException {
			if (bufferPos == bufferState) {
				bufferState = dis.read(buffer, bufferPos = 0, bfs);

				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];

		}

		int nextInt() throws IOException {
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

		byte nextByte() throws IOException {
			byte c = read();
			while (c <= ' ')
				c = read();
			return c;
		}

		String readLine() throws IOException {
			byte[] tmp = new byte[bfs];
			byte c = read();
			int cnt = 0;
			while (true) {
				if (c != '\r' || c != '\n') {
					if (cnt == 0)
						continue;
					else
						break;
				} else {
					tmp[cnt++] = c;
					c = read();
				}
			}
			return new String(tmp);
		}
	}

	public static void main(String[] args) throws IOException {
		Reader br = new Reader();
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = br.nextInt();
		int m, c, i, point, op, otherSideCnt, opTmp = 0, opTmp2, charPoint, runCnt, max;
		boolean isRunOnce;
		// 크기 제한 0~255
		int[] marr;
		boolean[] carrCnt;
		char[] carr, iarr;

		for (int tc = 0; tc < N; tc++) {
			runCnt = 0;
			charPoint = 0;
			point = 0;
			op = 0;
			isRunOnce = false;
			m = br.nextInt();
			c = br.nextInt();
			i = br.nextInt();
			marr = new int[m];
			carr = new char[c];
			carrCnt = new boolean[c + 1];
			iarr = new char[i + 1];

			for (int j = 0; j < c; j++) {
				carr[j] = (char) br.nextByte();
			}

			for (int j = 0; j < i; j++) {
				iarr[j] = (char) br.nextByte();
			}
			
			//명령어 갯수만큼 실행되었다면 종료
			while (op != c) {
				carrCnt[op] = true;
				if (carr[op] == '-') {

					if (marr[point] == 0) {
						marr[point] = 255;
					} else {
						marr[point]--;
					}
				} else if (carr[op] == '+') {
					marr[point] = (marr[point] + 1) % 256;

				} else if (carr[op] == '<') {
					if (point == 0)
						point = m - 1;
					else
						point--;
				} else if (carr[op] == '>') {
					if (point == m - 1)
						point = 0;
					else
						point++;
				} else if (carr[op] == '[') {
					if (marr[point] == 0) {
						opTmp = op + 1;
						otherSideCnt = 1;
						while (true) {
							if (carr[opTmp] == '[')
								otherSideCnt++;
							else if (carr[opTmp] == ']')
								otherSideCnt--;

							if (otherSideCnt == 0 && carr[opTmp] == ']') {
								op = opTmp;
								break;
							}
							opTmp++;
						}
					}

				} else if (carr[op] == ']') {
					if (marr[point] != 0) {
						opTmp = op - 1;
						otherSideCnt = 1;
						while (true) {
							if (carr[opTmp] == ']')
								otherSideCnt++;
							else if (carr[opTmp] == '[')
								otherSideCnt--;

							if (otherSideCnt == 0 && carr[opTmp] == '[') {
								op = opTmp;
								break;
							}

							opTmp--;
						}
					}
				} else if (carr[op] == ',') {
					if (charPoint == i)
						marr[point] = 255;
					else {
						marr[point] = iarr[charPoint];
						charPoint++;
					}
				}
				runCnt++;
				op++;
				// 5천만번 명령어 실행
				if (runCnt >= 50000000) {

					// 명령어의 마지막에 위치해있고 포인터가 가르키는 수가 0일 때[반복이 되지 않을 때]
					if (marr[point] == 0 && op == c) {
						sb.append("Terminates").append("\n");
						break;
					}
					// 진짜 무한 루프를 찾기위해 한 번 더 5천만번 실행한다.
					if (!isRunOnce) {
						carrCnt = new boolean[c + 1];
						runCnt = 0;
						isRunOnce = true;
						continue;
					}

					sb.append("Loops ");
					max = 0;
					otherSideCnt = 1;

					// 가장 cnt가 높은 bracket 2개를 출력
					for (int j = c; j >= 0; j--) {
//						// 가장 오른쪽에 있는 ]가 아니고 ]이면서 그 후의 명령어 실행은 없는 경우
						if (j != c && (carr[j] == ']' && carrCnt[j] && !carrCnt[j + 1])) {
							opTmp = j;
							break;
						}
					}
					
					opTmp2 = opTmp - 1;
					otherSideCnt = 1;
					while (true) {
						if (carr[opTmp2] == ']')
							otherSideCnt++;
						else if (carr[opTmp2] == '[')
							otherSideCnt--;

						if (otherSideCnt == 0 && carr[opTmp2] == '[') {
							sb.append(opTmp2).append(" ").append(opTmp).append("\n");
							break;
						}
						opTmp2--;
					}
					break;
				}
			}
			if (runCnt < 50000000) {
				sb.append("Terminates").append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
