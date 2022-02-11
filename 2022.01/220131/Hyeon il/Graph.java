import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	int Sol(int n, int[][] arr) {
		int answer = 0;
		int a = 0, b = 0;
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < arr.length; i++) {
			graph.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < arr.length; i++) {
			a = arr[i][0];
			b = arr[i][1];
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		int distance[] = new int[n + 1];
		int visited[] = new int[n + 1];

		Queue<Integer> q = new LinkedList<>();
		visited[0] = 1;
		visited[1] = 1;
		int nowNode = 1;
		q.add(nowNode);
		while (!q.isEmpty()) {
			nowNode = q.poll();
			for (int v : graph.get(nowNode)) {
				if (visited[v] == 0) {
					visited[v] = 1;
					distance[v] = distance[nowNode] + 1;
					q.add(v);
				}
			}
		}
//		System.out.println(Arrays.toString(distance));
//		System.out.println(Arrays.toString(visited));
		
		int temp = 0;
		for (int i : distance) {
			if (i > temp) {
				temp = i;
				answer = 1;
			} else if (i == temp) {
				answer += 1;
			}
		}

		return answer;
	}

	public static void main(String[] args) {
		Graph gp = new Graph();
		int arr[][] = { { 3, 6 }, { 4, 3 }, { 3, 2 }, { 1, 3 }, { 1, 2 }, { 2, 4 }, { 5, 2 } };
		int n = 6;
		System.out.println(gp.Sol(n, arr));
	}

}
