package _0428;

import java.io.*;
import java.util.*;

public class Boj20056 {
	static int N, M, K;
	static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1}, dy[] = {0, 1, 1, 1, 0, -1, -1, -1};
	static Node map[][];
	static Queue<int[]> balls; //파이어볼 위치
	
	static class Node{
		int m, s, d;//질량, 속력, 방향
		int cnt;//개수
		boolean isOdd, isEven;//홀,짝

		public Node(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
			this.cnt = 1;
			
			if(d%2==0) { isEven = true;}
			else { isOdd = true;}
		}
	}
	
	public static void insert(Node[][] mCopy, int x, int y, int m, int s, int d) {
		if(mCopy[x][y]==null) {
			mCopy[x][y] = new Node(m, s, d);
			balls.offer(new int[] {x, y});
		}
		else {
			mCopy[x][y].m += m;
			mCopy[x][y].s += s;
			mCopy[x][y].cnt++;
			if(d%2==0) {mCopy[x][y].isEven = true;}
			else {mCopy[x][y].isOdd = true;}
		}
	}
	
	public static void go() {
		Node mCopy[][] = new Node[N][N];
		//파이어볼 이동
		int size = balls.size();
		for(int i=0; i<size; i++) {
			int pos[] = balls.poll(); //파이어볼들의 위치
			int x = pos[0];
			int y = pos[1];
			Node node = map[x][y];
			int ballCnt = node.cnt;
			
			int nx = 0, ny = 0;
			if(ballCnt==1) {//파이어볼이 1개
				nx = (x + dx[node.d]*(node.s%N) + N)%N;
				ny = (y + dy[node.d]*(node.s%N) + N)%N;
				insert(mCopy, nx, ny, node.m, node.s, node.d);
			}
			else {//파이어볼이 1개 이상
				node.m/=5;
				if(node.m == 0) {continue;} //질량이 0이되면 pass
				node.s/=node.cnt;
				
				int start=0;
				if(node.isOdd && node.isEven) {//짝수, 홀수 섞여있다면 1, 3, 5, 7
					start = 1;
				}
				
				for(int j=start; j<8; j+=2) {
					nx = (x + dx[j]*(node.s%N) + N)%N;
					ny = (y + dy[j]*(node.s%N) + N)%N;
					insert(mCopy, nx, ny, node.m, node.s, j);
				}
			}
		}
		map = mCopy;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //NxN
		M = Integer.parseInt(st.nextToken()); //파이어볼 개수
		K = Integer.parseInt(st.nextToken()); //명령 수
		
		map = new Node[N][N];
		balls = new LinkedList<>();//파이어볼의 위치
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Node(m, s, d);
			balls.offer(new int[] {r, c});
		}
		
		//K번 명령 수행
		for(int i=0; i<K; i++) {
			if(balls.isEmpty()) { break;}//파이어볼이 없으면
			go();
		}
		
		//남은 파이어볼의 질량 합
		int result = 0;
		while(!balls.isEmpty()) {
			int pos[] = balls.poll();
			if(map[pos[0]][pos[1]].cnt==1) {result += map[pos[0]][pos[1]].m;} //1개면 그냥 더하기
			else {result += map[pos[0]][pos[1]].m/5*4;} //2개이상이면 (5로 나눈 값) * 4
		}
		System.out.println(result);
	}
}