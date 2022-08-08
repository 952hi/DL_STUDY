package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1103 {
    private static int[][] map, dp;
    private static boolean[][] visited;
    private static int n, m, result;
    private static boolean infinite;
    private static int[] dx = { 0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 세로
        m = Integer.parseInt(st.nextToken()); // 가로

        // input
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                if (input.charAt(j) == 'H'){
                    map[i][j] = -1;
                }else{
                    map[i][j] = input.charAt(j) - '0';
                }
            }
        }

        // 가장 왼쪽 위 부터 시작 - (0, 0) 부터
        result = 0;
        dp = new int[n][m];
        visited = new boolean[n][m];
        dp[0][0] = 1;
        dfs(0, 0);
        System.out.println(infinite ? -1 : result);
    }

    private static void dfs(int x, int y){
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * map[y][x];
            int ny = y + dy[i] * map[y][x];

            if (nx < 0 || ny < 0 || nx >= m || ny >= n || map[ny][nx] == -1){
                result = Math.max(dp[y][x], result);
               continue;
            }

            if (visited[ny][nx]){
                infinite = true;
                return;
            }

            if (dp[ny][nx] >= dp[y][x] + 1){
                continue;
            }

            dp[ny][nx] = Math.max(dp[y][x] + 1, dp[ny][nx]);
            result = Math.max(dp[ny][nx], result);

            visited[ny][nx] = true;
            dfs(nx, ny);
            visited[ny][nx] = false;
        }
    }
}
