import java.util.*;

class Solution {
    LinkedList<Integer> list[];
    boolean visited[];
    int cnt;
    
    public void dfs(int n){
        for(int su : list[n]){
            if(visited[su]){continue;}
            visited[su] = true;
            cnt+=1;
            dfs(su);
        }
    }
    
    public int solution(int n, int[][] wires) {
        int answer = n-1;
        //1. 데이터 세팅
        list = new LinkedList[n+1];
        for(int i=1; i<=n; i++){
            list[i] = new LinkedList<>();
        }
        for(int[] wire : wires){
            list[wire[0]].add(wire[1]);
            list[wire[1]].add(wire[0]);
        }
        //2. 탐색
        for(int i=1; i<=n; i++){
            int size = list[i].size();
            for(int j=0; j<size; j++){
                visited = new boolean[n+1];
                visited[i] = true;
                visited[list[i].get(j)] = true;
                cnt = 1;
                dfs(i);
                answer = Math.min(answer, Math.abs(2*cnt-n));
            }
        }
        return answer;
    }
}