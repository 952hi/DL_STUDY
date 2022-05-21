package _220519.ChungLee;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ_G1_1765_닭싸움팀정하기 {
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

		int nextChar() throws IOException {
			byte c = read();
			while (c <= ' ')
				c = read();
			return c;
		}
	}

	static List<int[]>[] list;
	static int[] set;

	static int FindSet(int a) {
		if (set[a] < 0)
			return a;
		else
			return set[a] = FindSet(set[a]);
	}

	static boolean Union(int a, int b) {
		a = FindSet(a);
		b = FindSet(b);
		if (a == b)
			return false;
		if (set[a] > set[b]) {
			set[b] += set[a];
			set[a] = b;
		} else {
			set[a] += set[b];
			set[b] = a;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		Queue<int[]> q = new LinkedList<>();
		Reader r = new Reader();
		int N = r.nextInt();
		boolean[] visited = new boolean[N + 1];
		list = new List[N + 1];
		for (int i = 1; i <= N; i++)
			list[i] = new LinkedList<>();

		set = new int[N + 1];
		Arrays.fill(set, -1);

		int ConnCnt = r.nextInt();
		int EofF, from, to = 0;

		// 데이터 입력
		for (int i = 0; i < ConnCnt; i++) {

			// E일 경우
			if (r.nextChar() - 'A' == 4) {
				EofF = 0;
			}
			// F일 경우
			else
				EofF = 1;
			from = r.nextInt();
			to = r.nextInt();
			list[from].add(new int[] { to, EofF });
			list[to].add(new int[] { from, EofF });
		}

		// union 시작
		for (int i = 1; i <= N; i++) {
			visited[i] = true;
			for (int j = 0; j < list[i].size(); j++) {
				int[] next = list[i].get(j);
				// 현재 노드 ㅑ에서 j가 친구일 때만 두 수를 결합

				// from, to, chance 0 == enermy, 1 == friend
				//인근이 친구일 때
				if (next[1] == 1) {
					Union(i, next[0]);
					q.add(new int[] { i, list[i].get(j)[0], 1 });
				}
				//인근이 적일 때
				else
					q.add(new int[] { i, list[i].get(j)[0], 0 });

			}
			
			while (!q.isEmpty()) {
				int[] crnt = q.poll();
				int crnt_from = crnt[0];
				int crnt_to = crnt[1];
				int crnt_chance = crnt[2];
				if (visited[crnt_to])
					continue;
				visited[crnt_to] = true;
				// 도착지에서 다음 도착지들을 검사
//				for (int k = 0; k < list[crnt_to].size(); k++) {
				for (int[] l : list[crnt_to]) {
					// 이미 방문을 했다면
					if(visited[l[0]])
						continue;
					// 시작점이거나 이전 노드가 적이었을 경우
					if (crnt_chance == 0) {
						// 적의 적일 경우
						if (l[1] == 0) {
							Union(crnt_from, l[0]);
							q.add(new int[] { crnt_to, l[0], 1 });
						}
						// 적의 친구인 경우
						else {
							continue;
						}
					}
					// 이전 노드가 친구였을 경우
					else {
						// 친구의 적일 경우
						if (l[1] == 0) {
							q.add(new int[] { crnt_to, l[0], 0 });
						}
						// 친구의 친구일 경우
						else {
							Union(crnt_to, l[0]);
							q.add(new int[] { crnt_to, l[0], 1 });
						}
					}
				}
//				}
			}
			visited = new boolean[N + 1];
		}
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (set[i] < 0)
				answer++;
		}
		System.out.println(answer);
	}
}
