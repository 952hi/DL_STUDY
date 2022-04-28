import java.io.*;
import java.util.*;
public class Main {
	static class Reader {
		int bfs = 1 << 16;
		byte[] buffer = new byte[bfs];
		int bufferPos = 0, bufferState = 0;
		DataInputStream dis = new DataInputStream(System.in);

		byte read() {
			if (bufferPos == bufferState) {
				try {
					bufferState = dis.read(buffer, bufferPos = 0, bfs);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bufferState == -1)
					buffer[0] = -1;
			}
			return buffer[bufferPos++];
		}

		int nextInt() {
			int rtn = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do
				rtn = rtn * 10 + c - '0';
			while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -rtn;
			return rtn;
		}
	}
	static int N,M,K,map[][],res;
	static boolean nofire = false;
	static List<int[]> fireball = new LinkedList<int[]>();
	static int[][] dxdy = { {-1,-1,0,1,1,1,0,-1}, {0,1,1,1,0,-1,-1,-1 } };
	static PriorityQueue<int[]> q= new PriorityQueue<>((o1,o2)->o1[0]-o2[0]);
	public static void main(String[] args) {
		Reader in = new Reader();
		
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		map= new int[N][N];
		// 리스트에 파이어볼 추가
		int r,c,m,s,d; //행 열 질량 속도 방향
		for(int i=0;i<M;i++) {
			r = in.nextInt()-1;
			c = in.nextInt()-1;
			m = in.nextInt();
			s = in.nextInt();
			d = in.nextInt();
			q.offer(new int[] {calcval(r, c),r,c,m,s,d});//
		}
		// 파이어볼 이동
		// 이동이 끝난후 2개이상 파이어볼이 겹친칸 확인
		// 하나로 합쳐진후 ->4개의 파이어볼로 나누어짐
		// 무게의합 /5 -> 질량이 0인 파이어볼은 소멸
		// 속력의합/합쳐진파이어볼수 
		// 방향은 합쳐지는 파이어볼이 모두 홀수나 짝수이면 0 2 4 6 아니면 1,3,5,7
		while(K!=0) {
			move();
			if(nofire) break;
		}
		System.out.println(res);
	}
//	private static void mapshow() {
//		for(int i=0;i<N;i++) System.out.println(Arrays.toString(map[i]));
//		System.out.println();
//	}
	// 파이어볼 이동
	private static void move() {
		
		int x,y;
		int size = q.size();
		for(int i=0;i<size;i++) {
			int temp[] = q.poll();
			x = calcxy(temp[1]+dxdy[0][temp[5]]*temp[4]);
			y = calcxy(temp[2]+dxdy[1][temp[5]]*temp[4]);
			map[x][y] +=1;
			q.offer(new int[] {temp[0]+6000+calcval(x, y),x,y,temp[3],temp[4],temp[5]});
		}
		
		check();
		K--;
	}
	private static void check() {
		int comp[];
		
		int size = q.size();
		res = 0;
		for(int i=0;i<size;i++) {
			comp = q.poll();
			if(map[comp[1]][comp[2]]==1) {
				comp[0] += 5001;
				q.offer(comp);
				res += comp[3];
			}
			else if(map[comp[1]][comp[2]]>=2){
				int sumM = comp[3];
				int sumS = comp[4];
				boolean check = true;
				int dircheck = comp[5]%2;
				for(int k=0;k<map[comp[1]][comp[2]]-1;k++) {
					size--;
					comp = q.poll();
					sumM += comp[3];
					sumS += comp[4];
					if(comp[5]%2!=dircheck) check = false;
				}
				int dir = 1;
				int val = calcval(comp[1], comp[2])+5001+comp[0];
				if(check) {
					dir = 0;
				}
				if(sumM/5*4==0) continue;
				res += sumM/5*4;
				for(int k=0;k<4;k++) {
					q.offer(new int[] {val,comp[1],comp[2],sumM/5,sumS/map[comp[1]][comp[2]],dir});
					dir+=2;
				}
			}
		}
		if(q.isEmpty()) {
			nofire = true;
		}
		else {
			map = new int[N][N];
		}
	}
	// 좌표 계산
	private static int calcxy(int i) {
		if(i==0) return 0;
		if(i>0) return i%N;
		else return N+i%N;
	}
	// 행열로 1~N^2까지의 수 생성(계산)
	private static int calcval(int x,int y) {
		int row = x;
		int col = y+1;
		return row*N+col;
	}
}