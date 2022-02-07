import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SWEA13432비서로소그래프 {
	// 0 미방문 1 방문
		static int visit[] = null;
		static int strt = 0;
		static int end = 0;
		static Queue<Integer> q = null;
		static Map<Integer, Set<Integer>> board = null;
		static int beforeNode = 0;
		static StringBuilder sb = new StringBuilder();
		static int[] answer;
		public static void bfs() {
			q.add(strt);
			int crntNode = 0;
			visit[strt] = 0;
			
			while (!q.isEmpty()) {
				crntNode = q.poll();
				answer[visit[crntNode]] = crntNode;
				if (crntNode == end) {
					sb.append(visit[crntNode] + "\n");
					return;
				} else {

					Set<Integer> ol = board.get(crntNode);
					
					// 노드에는 서로소인 수는 존재하지 않음. ol이 null인 경우는 첫 입력값이 노드로써 존재하지 않는 경우
					// 따라서 -1을 출력
					if (ol == null) {
						sb.append(-1 + "\n");
						return;
					}
					Iterator<Integer> iter = ol.iterator();

					// 연결된 노드 중 방문하지 않은 노드 넣는 역할
					while (iter.hasNext()) {
						int next = iter.next();
						if (visit[next] == 0) {
							q.add(next);
							visit[next] = visit[crntNode] + 1;
						}
					}
				}
			}
			sb.append(-1 + "\n");
		}

		public static void main(String[] args) throws NumberFormatException, IOException {
			// 1단계 배열
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			int TC = Integer.parseInt(br.readLine());

			// 이 문제는 1과 나머지 각각의 양의 정수를 서로소로 보고 있음. 1과 나머지 숫자 사이에 간선 존재하지 않음
			for (int i = 0; i < TC; i++) {
				sb.append("#" + (i + 1) + " ");
				String[] ol = br.readLine().split(" ");
				int size = Integer.parseInt(ol[0]);
				strt = Integer.parseInt(ol[1]);
				end = Integer.parseInt(ol[2]);
				answer = new int[size];
				if (strt == end) {
					sb.append(0 + "\n");
					continue;
				}
				visit = new int[size + 1];
				board = new HashMap<Integer, Set<Integer>>();
				q = new LinkedList<Integer>();
				for (int j = 2; j <= size; j++) {

					for (int j2 = j; j2 <= size; j2++) {
						BigInteger b1 = BigInteger.valueOf(j);
						BigInteger b2 = BigInteger.valueOf(j2);

						if ((j2 != j) && (b1.gcd(b2).intValue() != 1)) {
							// 값이 없으면 양 노드 값을 각각 넣은 Set을 board에 삽입
							if (board.containsKey(j) == false) {
								Set<Integer> leftEdge = new HashSet<Integer>();
								leftEdge.add(j2);
								board.put(j, leftEdge);

								Set<Integer> RightEdge = new HashSet<Integer>();
								RightEdge.add(j);
								board.put(j2, RightEdge);
							}
							//
							else {
								Set<Integer> leftEdge = board.get(j);
								leftEdge.add(j2);

								if (board.containsKey(j2) == false) {
									Set<Integer> rightEdge = new HashSet<Integer>();
									rightEdge.add(j);
									board.put(j2, rightEdge);
								} else {
									Set<Integer> rightEdge = board.get(j2);
									rightEdge.add(j);
								}
							}
						}
					}
				}
				bfs();
			}
			bw.write(sb.toString());
			bw.flush();
			for(int i : answer) {
				System.out.print(i + " ");
			}
		}
}
