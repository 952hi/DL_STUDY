class Solution {
    int n, answer;
    public void bfs(int k, int[][] dungeons, int visit, int cnt){
        answer = Math.max(answer, cnt);
        for(int i=0; i<n; i++){
            if((visit & (1<<i))!=0 || k<dungeons[i][0]){continue;}
            bfs(k-dungeons[i][1], dungeons, visit | (1<<i), cnt+1);
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        answer = -1;
        n = dungeons.length;
        bfs(k, dungeons, 0, 0);
        return answer;
    }
}