import java.util.*;
//돌아오는 건 생각X = 재방문이 가능하므로 갔던 길 똑같이 되돌아오자 

class Solution {
    LinkedList<int[]> list[];
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {999_999_999, 999_999_999};
        int result[] = new int[n+1];
        boolean isSummit[] = new boolean[n+1];
        list = new LinkedList[n+1];
        //초기화
        for(int i=1; i<=n; i++){
            list[i] = new LinkedList<>();
            result[i] = 999_999_999;
        }
        for(int i=0; i<summits.length; i++){
            isSummit[summits[i]]= true;
        }
        //인접리스트 생성
        for(int i=0, size = paths.length; i<size; i++){
            int n1 = paths[i][0];
            int n2 = paths[i][1];
            int w = paths[i][2];
            list[n1].add(new int[]{n2, w});
            list[n2].add(new int[]{n1, w});
        }
        
        //다익스트라 변환
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[1]-o2[1]);
        for(int i=0, size=gates.length; i<size; i++){
            result[gates[i]] = 0;
            pq.add(new int[]{gates[i], 0});
        }
        
        while(!pq.isEmpty()){
            int now[] = pq.poll();
            if(now[1] > result[now[0]]){
                    continue;
            }
        
            for(int next[] :list[now[0]]){
                int w = Math.max(now[1], next[1]);
                if(w >= result[next[0]]){
                    continue;
                }
                result[next[0]] = w;
                //산봉우리면 pass
                if(isSummit[next[0]]){
                    continue;
                }
                pq.add(new int[]{next[0], w});
            }
        }
        
        //출력할 값 찾기
        Arrays.sort(summits);
        for(int i=0; i<summits.length; i++){
            if(result[summits[i]]<answer[1]){
                answer[0] = summits[i];
                answer[1] = result[summits[i]];
            }
        }
        return answer;
    }
}