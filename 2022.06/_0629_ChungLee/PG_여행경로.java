package _0629_ChungLee;

import java.io.*;
import java.util.*;

public class PG_여행경로 {
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

		String readLine() throws IOException {
			byte[] tmp = new byte[bfs];
			byte b = read();
			int cnt = 0;
			while ((b = read()) != -1) {
				if (b == '\n' || b == '\r') {
					if (cnt != 0)
						break;
					else
						continue;
				}

				tmp[cnt++] = b;
			}
			return new String(tmp, 0, cnt);
		}
	}

	static public String[] solution(String[][] tickets) {
		ticketRow = tickets.length;
		Set<String> s = new TreeSet<String>();
		String[] Ssort = new String[tickets.length * tickets[0].length];
		AirportCnt = 0;
		boolean more = false;

		// 공항명을 중복없이 저장
		for (int i = 0; i < ticketRow; i++) {
			for (int j = 0; j < 2; j++) {
				if (!s.contains(tickets[i][j])) {
					s.add(tickets[i][j]);
					Ssort[AirportCnt++] = tickets[i][j];
				}
			}
		}
		Ssort = Arrays.copyOf(Ssort, AirportCnt);
		
		//정렬
		Arrays.sort(Ssort);
		Map<String, Integer> m = new HashMap<String, Integer>();

		//이름 순서대로 저장
		for (int i = 0; i < Ssort.length; i++) {
			m.put(Ssort[i], i);
		}
		
		// 도시 간 루트를 저장 배열
		bd = new int[Ssort.length][Ssort.length];

		// 편도행, 중복 저장 OK
		for (int i = 0; i < ticketRow; i++) {
			bd[m.get(tickets[i][0])][m.get(tickets[i][1])]++;
		}

		// ICN을 처음으로 저장 후 백트래킹 시작
		save = new int[ticketRow + 1];
		save[0] = m.get("ICN");

		//백트래킹
		dfs(save[0], 1);
		
		String[] answer = new String[ticketRow + 1];
		
		for (int i = 0; i < ticketRow + 1; i++) {
			answer[i] = Ssort[save[i]];
		}
		return answer;
	}

	static boolean dfs(int crnt, int cnt) {
		if (cnt == ticketRow + 1) {
			return true;
		}
		for (int i = 0; i < AirportCnt; i++) {
			if (bd[crnt][i] > 0) {
				bd[crnt][i]--;
				save[cnt] = i;
				if (dfs(i, cnt + 1)) {
					return true;
				}
				bd[crnt][i]++;
			}
		}
		return false;
	}

	static int[] save;
	static int Size, ticketRow, AirportCnt;
	static int[][] bd;

	public static void main(String[] args) throws IOException {
		//선행 값 입력
		Reader br = new Reader();
		StringTokenizer st;
		ticketRow = br.nextInt();
		int col = 2;
		String[][] tickets = new String[ticketRow][2];
		for (int i = 0; i < ticketRow; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				tickets[i][j] = st.nextToken();
			}
		}

		//코드 실행
		String[] ans = solution(tickets);
		
		//출력
		for(int i = 0; i < ans.length;i++) {
			System.out.print(ans[i] + " ");
		}
	}
}
