package _220211.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class pair implements Comparable<Object> {
	int nodeNum;
	int value;

	public pair(int nodeNum, int value) {
		this.nodeNum = nodeNum;
		this.value = value;
	}

	public int first() {
		return nodeNum;
	}

	public int second() {
		return value;
	}

	@Override
	public int compareTo(Object o) {
		pair p = (pair) o;
		return (value - p.value);
	}
}

public class BOJ4485녹색옷입은애가젤다지 {
	static int size;
	static int[][] board;
	static int[] dijkstra_value;
	static boolean[] dijkstra_visit;
	static int min = 0;
	static Map<Integer, List<pair>> dijk = new HashMap<>();
	static PriorityQueue<pair> pq = new PriorityQueue<>();

	static void run_dijkstra() {
		pq.offer(new pair(0, board[0][0]));
		while (!pq.isEmpty()) {
			int currentNodeNum = pq.peek().first();
			int distance = (pq.peek().second());
			pq.poll();
			if (dijkstra_value[currentNodeNum] < distance)
				continue;

			for (int i = 0; i < dijk.get(currentNodeNum).size(); i++) {
				int next = dijk.get(currentNodeNum).get(i).nodeNum;
				int nextDistance = distance + dijk.get(currentNodeNum).get(i).second();
				if (nextDistance < dijkstra_value[next]) {
					dijkstra_value[next] = nextDistance;
					pq.offer(new pair(next, nextDistance));
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		for(int k =1; ;k++) {
			size = Integer.parseInt(br.readLine());
			if(size == 0) {
				break;
			}
			board = new int[size][size];
			dijkstra_value = new int[size * size];
			dijkstra_visit = new boolean[size * size];

			// 데이터 입력
			for (int i = 0; i < size; i++) {
				String[] eachLine = br.readLine().split(" ");
				for (int j = 0; j < size; j++) {
					board[i][j] = Integer.parseInt(eachLine[j]);
				}
			}
			for (int i = 0; i < size; i++) {

				for (int j = 0; j < size; j++) {
					List<pair> p = new LinkedList<>();
					if (0 <= i - 1)
						p.add(new pair((i - 1) * size + j, board[i - 1][j]));
					if (i + 1 < size)
						p.add(new pair((i + 1) * size + j, board[i + 1][j]));

					if (j + 1 < size)
						p.add(new pair(i * size + (j + 1), board[i][j + 1]));
					if (0 <= j - 1)
						p.add(new pair(i * size + (j - 1), board[i][j - 1]));
					dijk.put(i * size + j, p);
				}
			}

			// 자기 이외 최대 값으로 설정
			for (int i = 0; i < size * size; i++)
				dijkstra_value[i] = Integer.MAX_VALUE;

			// 자기 자신은 거리 0
			dijkstra_value[0] = board[0][0];

			run_dijkstra();
			sb.append("Problem "+k+": "+dijkstra_value[size * size - 1]+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
