package _20220307;
/*
 * 백준 1162번 : 도로포장
 * 시간 : 748ms
 * 66% => int를 float로 : 
 * 거리 1,000,000 * 도시 수 10,000 = 10,000,000,000 > Integer 최대 약 2,000,000,000
 * 메모리초과 => int[]를 Node객체로 바꿈
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1162 {
	
	static class Node implements Comparable<Node>{
		int to;
		long weight;
		int packCnt;//포장한 도로수
		
		public Node(int to, long weight, int packCnt) {
			super();
			this.to = to;
			this.weight = weight;
			this.packCnt = packCnt;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	static int N, M, K;
	static ArrayList<Node>[] list;
	static boolean visited[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());//도시의 수
		M = Integer.parseInt(st.nextToken());//도로의 수
		K = Integer.parseInt(st.nextToken());//포장할 도로의 수
		list = new ArrayList[N+1];
		long[][] distance = new long[N+1][K+1];//다익스트라용 최소거리 배열
		
		//초기화
		for(int i=1; i<=N; i++) {
			list[i] = new ArrayList<>();
			Arrays.fill(distance[i], Long.MAX_VALUE);
		}
		//입력
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			list[from].add(new Node(to, weight, 0));
			list[to].add(new Node(from, weight, 0));
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		visited = new boolean[N+1][K+1];
		
		distance[1][0] = 0;//시작점이니까 거리는 0
		pq.offer(new Node(1, distance[1][0], 0));//1번부터 시작, 총 weight, 포장한 도로의 수
		long result = Long.MAX_VALUE;
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			//해당 노드의 해당 포장한 도로의 수인 경우를 방문했다면
			if(visited[current.to][current.packCnt])
				continue;
			//더 작은 값으로 업데이트 되었다면 pass
			if(distance[current.to][current.packCnt] < current.weight)
				continue;
			
			if(current.to==N) {//도착지면 최솟값 업데이트 후, 다음값 보기
				result = Math.min(result, current.weight);
				continue;
			}
			
			for(Node node : list[current.to]) {
				//다음 노드로 가는 도로를 포장 안했을 때, 원래 값보다 작으면
				if(!visited[node.to][current.packCnt] && current.weight+node.weight < distance[node.to][current.packCnt]) {
					distance[node.to][current.packCnt] = current.weight + node.weight;
					pq.offer(new Node(node.to, distance[node.to][current.packCnt], current.packCnt));
				}
				//다음 노드로 가는 도로를 포장할 수 있다면 && 원래 값보다 작으면
				if(current.packCnt < K && !visited[node.to][current.packCnt+1] && current.weight < distance[node.to][current.packCnt+1]) {
					distance[node.to][current.packCnt+1] = current.weight;
					pq.offer(new Node(node.to, current.weight, current.packCnt+1));
				}
			}
		}
		System.out.println(result);
	}
}
