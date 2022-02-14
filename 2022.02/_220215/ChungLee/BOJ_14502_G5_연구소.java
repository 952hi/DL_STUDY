package _220215.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 차례대로 벽을 세운다.
 * 2-1. 원본 땅의 형태를 복사
 * 2-2. 가스를 퍼뜨린다.
 * 3. 안전한 땅을 찾는다.
 * 4. 원본 땅으로 돌아간다.
 * 5. 다음 벽을 세워본다.
 */

public class BOJ_14502_G5_연구소 {
	static int maxY = 0;
	static int maxX = 0;
	static int[][] board = null;
	static int MaxsafeZn = 0;
	static Queue<Integer[]> gasQ = new LinkedList<Integer[]>();
	static int[][] dxdy = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	
	static void countspreadGas() {
		int crntSfZn = 0;
		//2-1. 원본 땅의 형태를 복사한다.
		int [][]copyBoard = new int[maxY][maxX];
		for(int i = 0; i< maxY;i++) {
			copyBoard[i] = Arrays.copyOf(board[i], board[i].length);
		}
		
		for (int i = 1; i < maxY - 1; i++) {
			for (int j = 1; j < maxX - 1; j++) {
				if (copyBoard[i][j] == 2)
					gasQ.add(new Integer[] { i, j });
			}
		}
		//2-2. 가스를 퍼뜨린다.
		while (!gasQ.isEmpty()) {
			int crntGY = gasQ.peek()[0];
			int crntGX = gasQ.peek()[1];
			int gasC = copyBoard[crntGY][crntGX];
			gasQ.poll();

			for (int i = 0; i < 4; i++) {
				if (copyBoard[crntGY + dxdy[i][0]][crntGX + dxdy[i][1]] == 0) {
					copyBoard[crntGY + dxdy[i][0]][crntGX + dxdy[i][1]] = gasC + 1;
					gasQ.add(new Integer[]{crntGY + dxdy[i][0], crntGX + dxdy[i][1]});
				}
			}
		}
		//3. 안전한 땅을 찾는다.
		for(int i = 1;i < maxY-1;i++) {
			for (int j = 1; j < maxX-1; j++) {
				if(copyBoard[i][j] == 0)
					crntSfZn++;
			}
		}
		MaxsafeZn = Math.max(crntSfZn, MaxsafeZn);
	}
	//1. 차례대로 벽을 세운다.
	static void buildWall(int y, int x, int wall) {
		//x가 보드사이즈보다 크면 y에 더해주며 0을 찾을 때까지 반복
		
		//x, y 크기보다 커지면 벽 백트래킹
		if ((maxY - 2<y)) {
			return;
		}
		else if (wall == 3){
			 countspreadGas();
			 return;
		}
		// 0일 때만 탐색
		
		//5. 1로 돌아간다.
		while (y < maxY-1) {
			if (maxX - 1 <= x) {
				y++;
				x = 1;
				if(maxY -1 == y)
					break;
			}
			else {
				if (!(board[y][x] == 0))
					x++;
				else {
					board[y][x] = 1;
					//2단계, 3단계
					buildWall(y,x+1,wall+1);
					//4. 원본 땅으로 돌아간다.
					board[y][x] = 0;
					x++;
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		maxY = y + 2;
		maxX = x + 2;
		MaxsafeZn = 0;
		board = new int[maxY][maxX];

		// 구현
		for (int i = 0; i < board.length; i++) {
			if (1 <= i && i < y + 1)
				st = new StringTokenizer(br.readLine());
			for (int j = 0; j < board[i].length; j++) {
				if (j == 0 || j == x + 1 || i == 0 || i == y + 1)
					board[i][j] = 1;
				else
					board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		buildWall(1,1,0);
		System.out.println(MaxsafeZn);
	}
}
