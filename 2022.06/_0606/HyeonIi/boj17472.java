import java.io.*;
import java.util.*;
public class boj17472 {
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
	static int row,col,map[][],bridge[][],parent[];
	public static void main(String[] args) {
		Reader in = new Reader();
		row = in.nextInt();
		col = in.nextInt();
		map = new int[row][col];
		
		//입력받기
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = in.nextInt();
				if(map[i][j]==0)continue;
				else map[i][j] = -1;
			}
		}
		
		int groupval = 1;
		// 그룹나누기
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(map[i][j]==-1) {
					map[i][j] = groupval;
					group(i,j,groupval);
					groupval++;
				}
			}
		}
		// groupval 만큼 그룹이 존재
		// 각 그룹에서 다른 그룹으로 최소거리를 갱신시킬 배열 생성
		bridge = new int[groupval][groupval];
		
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if(map[i][j]!=0) { // 0이 아니다 => 그룹이다
					count(i,j,map[i][j]); // 간선생성
				}
			}
		}
		// 간적쿠간만프
		// 크루스칼 적용
		PriorityQueue<int[]> q = new PriorityQueue<>((int[] o1,int[] o2)->o1[2]-o2[2]);
		
		for (int i = 1; i < groupval; i++) {
			for (int j = 1; j < groupval; j++) {
				if(bridge[i][j]>=2) q.offer(new int[] {i,j,bridge[i][j]});
			}
		}
		if(q.size()==0) {
			System.out.println(-1);
		}else {
			parent = new int[groupval];
			for(int i=1;i<groupval;i++) parent[i]=i;
			int[] temp;
			int res = 0;
			while(!q.isEmpty()) {
				temp = q.poll();
				if(union(temp[0], temp[1])) {
					res += temp[2];
				}
			}
			int valuecheck = find(1);
			boolean onegroup = true;
			for(int i=2;i<groupval;i++) {
				parent[i]=find(i);
				if(valuecheck!=parent[i]) {
					onegroup = false;
					break;
				}
			}
			if(onegroup) System.out.println(res);
			else System.out.println(-1);
		}
	}
	// 거리 계산
	private static void count(int x, int y, int k) {
		
		// 열 계산
		// 1)우
		int ny = y;
		int val = 1;
		while(true) {
			int cost = val-1;
			if(0>val+ny || val+ny>=col || map[x][val+ny]==k) break; // 범위에 맞고 
			else if(map[x][val+ny]!=0) {
				if(cost>=2) {
					if(bridge[k][map[x][val+ny]]==0||bridge[k][map[x][val+ny]]>cost) {
						bridge[k][map[x][val+ny]]=cost;
						bridge[map[x][val+ny]][k]=cost;
					}
				}
				break;
			}
			val++;
		}
		
		int nx = x;
		val = 1;
		// 4)하
		nx = x;
		val = 1;
		while(true) {
			int cost = val-1;
			if(0>nx+val || nx+val>=row || map[nx+val][y]==k) break; // 범위에 맞고 
			else if(map[nx+val][y]!=0) {
				if(cost>=2) {
					if(bridge[k][map[nx+val][y]]==0) {
						bridge[k][map[nx+val][y]]=cost;
						bridge[map[nx+val][y]][k]=cost;
					}else if(bridge[k][map[nx+val][y]]>cost) {
						bridge[k][map[nx+val][y]]=cost;
					}else if(bridge[map[nx+val][y]][k]>cost) {
						bridge[map[nx+val][y]][k]=cost;
					}
				}
				break;
			}
			val++;
		}
	}
	// 그룹 넘버링
	static int[][] dxdy = { { 0, 0, 1, -1 }, { 1, -1, 0, 0 } };
	private static void group(int i, int j, int val) {
		int nx,ny;
		for(int k=0;k<4;k++) {
			nx = i+dxdy[0][k];
			ny = j+dxdy[1][k];
			if(0<=nx && nx<row && 0<=ny && ny<col && map[nx][ny]!=0 && map[nx][ny]!=val) {
				map[nx][ny] = val;
				group(nx, ny, val);
			}
		}
	}
	private static int find(int x) {
		if(parent[x]==x) return x;
		else return parent[x] = find(parent[x]);
	}
	
	private static boolean union(int a,int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		if(a>b) parent[a] = b;
		else parent[b] = a;
		return true;
	}
}
