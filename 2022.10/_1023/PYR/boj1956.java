package _1023;

import java.util.*;
import java.io.*;

public class boj1956 {
	public static LinkedList<int[]> adjList[];
	public static int V;
	public static final int INF = 999_999_999;
	
	public static int dijkstra(int start, int[] distance) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[1]-o2[1]);
		pq.add(new int[] {start, 0});
		Arrays.fill(distance, INF);
		distance[start] = INF;
		
		while(!pq.isEmpty()) {
			int now[] = pq.poll();
			if(now[1] > distance[now[0]]) {continue;}
			for(int next[] : adjList[now[0]]) {
				if(next[0] < start) {continue;}
				if(now[1]+next[1] < distance[next[0]]) {
					distance[next[0]] = now[1]+next[1];
					pq.add(new int[] {next[0], distance[next[0]]});
				}
			}
		}
		return distance[start];
	}
	
	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = null;
		 int E;
		 st = new StringTokenizer(br.readLine());
		 V = Integer.parseInt(st.nextToken());
		 E = Integer.parseInt(st.nextToken());
		 adjList = new LinkedList[V+1];
		 for(int i=1; i<=V; i++) {
			 adjList[i] = new LinkedList<>();
		 }
		 int from, to, w;
		 for(int i=0; i<E; i++) {
			 st = new StringTokenizer(br.readLine());
			 from = Integer.parseInt(st.nextToken());
			 to = Integer.parseInt(st.nextToken());
			 w = Integer.parseInt(st.nextToken());
			 adjList[from].add(new int[] {to, w});
		 }
		 //
		 int min = INF;
		 for(int i=1; i<=V; i++) {
			 int su = dijkstra(i, new int[V+1]);
			 min = Math.min(min, su);
		 }
		 if(min==INF) {
			 System.out.println(-1);
		 }
		 else {
			 System.out.println(min); 
		 }
	}

}

/*
3 4
1 2 1
3 2 1
1 3 5
2 3 2

3
 */