import java.io.*;
import java.util.*;

public class boj2098 {
    public static int N;
    public static int answer = Integer.MAX_VALUE;
    public static int[][] map, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][1<<N];
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }
        dp[0][1] = 0; // 출발지는 상관이 없다 어짜피 순회하면 가장작은값은 하나다.
        			// 시작하기앞서 내자신을 방문처리해줌 0을 넣은 이유는 내자신에 비용이 들수없기때문 
        dfs(0, 1);
        System.out.println(answer);
    }
    public static void dfs(int now, int visited){
        if(visited == ((1<<N)-1)){ // N이 4라면 1111 보내주도록 
            if(map[now][0]==0) return; // 길이없으면 리턴
            int compare = dp[now][visited] + map[now][0];
            answer = Math.min(answer, compare); // 최소값
            return;
        }
        
        for(int i=0; i<N; i++){
            int next = (1<<i);
            if((visited | next) == visited) continue; //이미방문했으면 continue
            if(map[now][i] == 0) continue; // 길없으면 continue
            if(dp[now][visited] + map[now][i] < dp[i][visited|next]){ 
                dp[i][visited|next] = dp[now][visited] + map[now][i]; // 갱신하고 들어가기
                dfs(i, visited|next);
            }
        }
    }
}