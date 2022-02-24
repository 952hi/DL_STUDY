package _220224.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1707_G4_이분그래프 {
	static int[] isVisit;
	static int nodeCnt;
	static List<Integer>[] aL;

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		for (int k = 1; k <= nodeCnt; k++) {
			if (isVisit[k] == 0) {
				q.add(k);
				isVisit[k] = 1;
			}

			while (!q.isEmpty()) {
				int crnt = q.poll();

				for (int i : aL[crnt]) {
					if (isVisit[i] == 0) {
						q.add(i);
					}
					if (isVisit[crnt] == isVisit[i]) {
						System.out.println("NO");
						return;
					}
					if (isVisit[crnt] == 1 && isVisit[i] == 0)
						isVisit[i] = 2;
					else if (isVisit[crnt] == 2 && isVisit[i] == 0)
						isVisit[i] = 1;
				}
			}
		}
		System.out.println("YES");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int tc = Integer.parseInt(st.nextToken());

		for (int i = 0; i < tc; i++) {
			st = new StringTokenizer(br.readLine());
			nodeCnt = Integer.parseInt(st.nextToken());
			int vertexCnt = Integer.parseInt(st.nextToken());
			isVisit = new int[nodeCnt + 1];
			aL = new ArrayList[nodeCnt + 1];

			for (int j = 0; j <= nodeCnt; j++)
				aL[j] = new ArrayList<Integer>();

			for (int j = 0; j < vertexCnt; j++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				aL[start].add(end);
				aL[end].add(start);
			}
			bfs();
		}
	}
}
