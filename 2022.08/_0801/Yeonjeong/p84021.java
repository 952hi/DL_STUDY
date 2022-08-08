package Programmers;

import java.util.*;
// 참고
//https://velog.io/@minchae75/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Java-%ED%8D%BC%EC%A6%90-%EC%A1%B0%EA%B0%81-%EC%B1%84%EC%9A%B0%EA%B8%B0
public class p84021 {
    public static void main(String[] args) {
        int[][] game_board = {
                {1,1,0,0,1,0},
                {0,0,1,0,1,0},
                {0,1,1,0,0,1},
                {1,1,0,1,1,1},
                {1,0,0,0,1,0},
                {0,1,1,1,0,0}
        };
        int[][] table = {
                {1,0,0,1,1,0},
                {1,0,1,0,1,0},
                {0,1,1,0,1,1},
                {0,0,1,0,0,0},
                {1,1,0,1,1,0},
                {0,1,0,0,0,0},
        };
        System.out.println(solution(game_board, table));
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int N; // board 크기
    static boolean[][] visited;
    static ArrayList<ArrayList<Point>> empty = new ArrayList<>(); // 게임보드 빈 공간
    static ArrayList<ArrayList<Point>> block = new ArrayList<>(); // 테이블 블록 저장

    public static int solution(int[][] game_board, int[][] table) {
        N = game_board.length;
        visited = new boolean[N][N];

        // 게임 보드 빈 공간
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && game_board[i][j] == 0){
                    empty.add(check(game_board, i, j, 0));
                }
            }
        }

        // visited 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }

        // 테이블 블록 체크
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && table[i][j] == 1){
                    block.add(check(table, i, j, 1));
                }
            }
        }

        boolean[] visitedBoard = new boolean[empty.size()];
        int answer = 0;
        // 빈공간에 퍼즐을 맞춰 넣기
        for (int i = 0; i < block.size(); i++) {
            ArrayList<Point> blockCheck = block.get(i);

            for (int j = 0; j < empty.size(); j++) {
                ArrayList<Point> emptyCheck = empty.get(j);
                // 블럭 사이즈가 서로 맞고, 방문 하지 않은 블럭이라면
                if (emptyCheck.size() == blockCheck.size() && !visitedBoard[j]){
                    if (isRotate(emptyCheck, blockCheck)){ // 블럭이 들어갈 수 있는지 확인해본다.
                        answer += blockCheck.size();
                        visitedBoard[j] = true;
                        break;
                    }
                }
            }
        }

        return answer;
    }

    // 블럭 회전, 게임보드에 들어가는지 확인
    private static boolean isRotate(ArrayList<Point> empty, ArrayList<Point> block) {
        for (int i = 0; i < 4; i++) { // 90도 회전
            int zeroX = block.get(0).x;
            int zeroY = block.get(0).y;

            for (int j = 0; j < block.size(); j++) { // 회전 시키면서 좌표가 달라지기 대문에 0,0을 기준으로 블록 좌표 변경
                block.get(j).x -= zeroX;
                block.get(j).y -= zeroY; 
            }
            
            boolean isCollect = true;
            // 서로 좌표를 비교한다
            for (int j = 0; j < empty.size(); j++) {
                Point emptyPoint = empty.get(j);
                Point blockPoint = block.get(j);
                // 하나라도 안맞으면 멈추기
                if (emptyPoint.x != blockPoint.x || emptyPoint.y != blockPoint.y){
                    isCollect = false;
                    break;
                }
            }
            // 다 맞으면 블록 끼우기!
            if (isCollect)
                return true;
            else {
                // 90도 회전 : (x, y) -> (y, -x)
                for (int j = 0; j < block.size(); j++) {
                    int temp = block.get(j).x;

                    block.get(j).x = block.get(j).y;
                    block.get(j).y = -temp;
                }
                Collections.sort(block);
            }
        }
        return false;
    }

    public static ArrayList<Point> check(int[][] board, int x, int y, int type){
        Queue<Point> queue = new LinkedList<>();
        ArrayList<Point> points = new ArrayList<>();

        queue.add(new Point(x, y));
        visited[x][y] = true;
        // 블록이나 빈 공간의 기준점을 0,0으로
        points.add(new Point(0, 0));

        while (!queue.isEmpty()){
            Point cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;

                if (visited[nx][ny] || board[nx][ny] != type)
                    continue;

                queue.add(new Point(nx, ny));
                visited[nx][ny] = true;

                points.add(new Point(nx - x, ny - y)); // 기준점이 0,0이기 때문에
            }
        }

        Collections.sort(points); // x를 기준으로 오름차순 정렬
        return points;
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        // 오름 차순 정렬
        @Override
        public int compareTo(Point o) {
            if (this.x == o.x)
                return this.y - o.y;
            return this.x - o.x;
        }
    }
}
