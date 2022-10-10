import java.util.*;
//s=>w + w=>A + w=>B

class Solution {
    public LinkedList<int[]> list[];
    
    public void dijkstra(int start, int[] distance){
        //setting
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[1]-o2[1]);
        Arrays.fill(distance, 20_000_000);
        
        pq.offer(new int[]{start, 0});
        distance[start] = 0;
        //
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            if(distance[now[0]]<now[1]){continue;}
            for(int[] next : list[now[0]]){
                if(distance[now[0]]+next[1] < distance[next[0]]){
                    distance[next[0]] = distance[now[0]] + next[1];
                    pq.offer(new int[]{next[0], distance[next[0]]});
                }
            }
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        list = new LinkedList[n+1];
        //초기화 : 인접리스트
        for(int i=1; i<=n; i++){
            list[i] = new LinkedList<>();
        }
        for(int i=0; i<fares.length; i++){
            int n1 = fares[i][0], n2 = fares[i][1], cost = fares[i][2];
            list[n1].add(new int[]{n2, cost});
            list[n2].add(new int[]{n1, cost});
        }
        //다익스트라
        int start[] = new int[n+1];//S에서 최단거리
        int A[] = new int[n+1];//A에서 최단거리
        int B[] = new int[n+1];//B에서 최단거리
        dijkstra(s, start);
        dijkstra(a, A);
        dijkstra(b, B);
        
        //결과
        int answer = start[1] + A[1] + B[1];
        for(int i=2; i<=n; i++){
            answer = Math.min(answer, start[i] + A[i] + B[i]);
        }
        
        return answer;
    }
}