class Solution {
    int lion[][], max, result;
    //사용할 수 있는 화살 수, 맞힌 과녁, 점수합, 살펴본 점수
    public void dfs(int cnt, int selected, int sum, int now){
        if(cnt>=0 && max<=sum){
            if(max<sum || (max==sum && selected>result)){
                max = sum;
                result = selected;
            }
        }
        if(cnt==0){return;}
        for(int i=now; i<=10; i++){
            if(cnt-lion[i][0]<0){
                continue;
            }
            dfs(cnt-lion[i][0], selected | (1<<i), sum+lion[i][1], i+1);
        }
    }
    
    public int[] solution(int n, int[] info) {
        int[] answer = new int[11];
        int peach = 0;
        lion = new int[11][2];
        for(int i=0; i<=10; i++){
            lion[i][0] = info[i]+1;
            lion[i][1] = 10-i;
            if(lion[i][0]>1){
                peach += 10-i;
                lion[i][1]+=10-i;
            }
        }
        dfs(n, 0, 0, 0);
        if(max-peach<=0){
            return new int[]{-1};
        }
        int cnt = n;
        for(int i=0; i<=10; i++){
            if((result&(1<<i))!=0){
                answer[i] = lion[i][0];
                cnt-=answer[i];
            }
        }
        answer[10] += cnt;
        return answer;
    }
}