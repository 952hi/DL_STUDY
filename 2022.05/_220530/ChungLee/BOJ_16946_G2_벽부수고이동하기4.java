package _220530.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// mem : 132232kb, time : 1048ms
public class BOJ_16946_G2_벽부수고이동하기4 {
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
	}

	static Queue<int[]> q = new LinkedList<int[]>();
	static Queue<int[]> rslt = new LinkedList<int[]>();

	static int[][] dydx = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[][] bd, set, answer;
		boolean[][] visited;
		int row, col;
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		bd = new int[row][col];
		set = new int[row][col];
		answer = new int[row][col];
		visited = new boolean[row][col];
		int[][] numbering = new int[row][col];
		int[][][] numberingCheck = new int[row][col][4];
		for (int i = 0; i < row; i++) {
			Arrays.fill(set[i], -1);
		}

		for (int i = 0; i < row; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < col; j++) {
				bd[i][j] = tmp[j] - '0';
				Arrays.fill(numberingCheck[i][j], -1);
			}
		}
		int stack = 0;
		int number = -1;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// 검사하지 않았고 0번자리일 경우
				if (set[i][j] == -1 && bd[i][j] == 0) {
					number++;
					stack = 1;
					q.add(new int[] { i, j });
					rslt.add(new int[] { i, j });
					visited[i][j] = true;
					while (!q.isEmpty()) {
						int[] crntYX = q.poll();
						int crntY = crntYX[0];
						int crntX = crntYX[1];

						for (int k = 0; k < 4; k++) {
							int nextY = crntY + dydx[k][0];
							int nextX = crntX + dydx[k][1];
							if (nextY < 0 || nextX < 0 || row == nextY || col == nextX)
								continue;

							if (!visited[nextY][nextX] && bd[nextY][nextX] == 0) {
								stack++;
								visited[nextY][nextX] = true;
								q.add(new int[] { nextY, nextX });
								rslt.add(new int[] { nextY, nextX });
							}

						}

					}
				}
				while (!rslt.isEmpty()) {
					int tmp[] = rslt.poll();
					set[tmp[0]][tmp[1]] = stack;
					numbering[tmp[0]][tmp[1]] = number;
				}

			}

		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (bd[i][j] == 1) {
					answer[i][j]++;
					LOOP: for (int k = 0; k < 4; k++) {
						int nextY = i + dydx[k][0];
						int nextX = j + dydx[k][1];

						if (nextY < 0 || nextX < 0 || row == nextY || col == nextX)
							continue;
						if (bd[nextY][nextX] == 0) {

							number = numbering[nextY][nextX];

							// 4개 저장 포인트 중에 중복되는 게 있는지 검사
							for (int l = 0; l < 4; l++) {
								// 중복된다면 다음 방향으로 넘어감
								if (numberingCheck[i][j][l] == number)
									continue LOOP;
								// -1이 검사되었다는 것은 저장된 값들 중 중복되는 값이 없다.
								if (numberingCheck[i][j][l] == -1) {
									numberingCheck[i][j][l] = number;

									answer[i][j] += set[nextY][nextX];
									break;
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < col; j++) {
				sb.append(answer[i][j]%10);
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
