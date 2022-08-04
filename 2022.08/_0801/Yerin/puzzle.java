import java.util.*;

class Block{
    int cnt, start[], pos[][];
    boolean done;
    
    public Block(int N){
        cnt = 1;
        pos = new int[6][2];
        done = false;
    }
}

class Solution {
    int N;
    Block block;
    
    public void turn(Block block){
        block.start = new int[]{N, N};//최대로 초기화
        for(int i=0; i<block.cnt; i++){
            int[] now = block.pos[i];
            int gap = N;
            if(now[0]<=now[1]){
                gap = Math.min(now[0]-0, N-1-now[1]);
            }
            else{
                gap = Math.min(now[1]-0, N-1-now[0]);
            }
            //한 번에 이동해야 되는 거리
            int move = N-1-2*gap, temp=0, min = gap, max = N-1-gap;
            while(move>0){
                if(now[0]==min && now[1]<max){
                    temp = Math.min(max-now[1], move);
                    now[1] += temp;
                }
                else if(now[1]==max && now[0]<max){
                    temp = Math.min(max-now[0], move);
                    now[0] += temp;
                }
                else if(now[0]==max && now[1]>min){
                    temp = Math.min(now[1]-min, move);
                    now[1]-=temp;
                }
                else if(now[1]==min && now[0]>min){
                    temp = Math.min(now[0]-min, move);
                    now[0] -= temp;
                }
                move-=temp;
            }
            block.pos[i] = now;
            if(now[0]<block.start[0]){
                block.start[0] = now[0];
                block.start[1] = now[1];
            }
            else if(now[0]==block.start[0]){
                block.start[1] = Math.min(block.start[1], now[1]);
            }
        }
    }
    
    public void dfs(int x, int y, int[][] board, int flag){
        int dx[] = {1, 0, -1, 0}, dy[] = {0, 1, 0, -1};
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<N && board[nx][ny]==flag){
                board[nx][ny] = 2;
                block.pos[block.cnt]=new int[]{nx, ny};
                block.cnt++;
                dfs(nx, ny, board, flag);
            }
        }
    }
    
    public void getInfo(List<Block> list, int[][] board, int flag){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(board[i][j]==flag){
                    board[i][j] = 2;
                    block = new Block(N);
                    block.pos[0] = new int[]{i, j};
                    block.start = new int[]{i, j};
                    dfs(i, j, board, flag);
                    list.add(block);
                }
            }
        }
    }
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        N = game_board.length;
        List<Block> blanks = new LinkedList<>();
        List<Block> blocks = new LinkedList<>();
        //1. 빈 칸 찾기
        getInfo(blanks, game_board, 0);
        
        //2. 퍼즐 찾기
        getInfo(blocks, table, 1);
        
        //3. 회전하면서 맞는 곳 찾기
        int size = blanks.size(), k=0, filled = 0;
        //빈칸이 다 찼거나, 회전 다 확인했으면 종료
        while(filled<size){
            //원래 + 3번 회전
            for(Block blank : blanks){
                if(blank.done){continue;}
                for(Block block : blocks){
                    //블럭을 썼거나 || 빈 칸 개수와 다르면
                    if(block.done || blank.cnt != block.cnt){continue;}
                    int a = (blank.cnt==1)? blank.cnt : 0;
                    // 평행 이동 확인
                    //x값이 작고 y값이 작은 것이 기준!(왼쪽 위부터 출발했기 때문)
                    int dx = block.start[0] - blank.start[0];
                    int dy = block.start[1] - blank.start[1];
                    for(; a<blank.cnt; a++){
                        int fx = blank.pos[a][0] + dx;
                        int fy = blank.pos[a][1] + dy;
                        int b = 0;
                        for(;b<block.cnt; b++){
                            //평행 이동된 블럭이 있으면
                            if(block.pos[b][0]==fx && block.pos[b][1]==fy){
                                break;
                            }
                        }
                        //평행 이동이 아니다.
                        if(b==block.cnt){
                            break;
                        }
                    }
                    if(a==blank.cnt){
                        blank.done = true;
                        block.done = true;
                        filled++;
                        answer+=blank.cnt;
                        break;
                    }
                }
            }
            if(k++>=3){
                break;
            }
            //90도 시계 방향 회전
            for(Block block : blocks){
                if(block.done){continue;}
                turn(block);
            }
        }
        
        return answer;
    }
}
