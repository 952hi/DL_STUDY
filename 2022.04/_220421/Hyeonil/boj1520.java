import java.io.*;
public class boj1520 {
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
	static int row,col,map[][][];
	static boolean visited[][];
	public static void main(String[] args) {
		Reader in = new Reader();
		row = in.nextInt();
		col = in.nextInt();
		// 0 -> 오르내리막 정보
		// 1 -> 메모이제이션 길 왕복횟수
		map = new int[row][col][2];
		visited = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j][0] = in.nextInt();
			}
		}
		dfs(0,0);
		System.out.println(map[0][0][1]);
	}
	static int[][] dxdy = { { 0, 0, 1, -1 }, { 1, -1, 0, 0 } };
	private static int dfs(int i, int j) {
		if(i==row-1 && j==col-1) {
			return 1;
		}
		if(map[i][j][1]!=0) return map[i][j][1];
		
		if(!visited[i][j]) {
			visited[i][j] = true;
			int nx,ny;
			for(int k=0;k<4;k++) {
				nx = i + dxdy[0][k];
				ny = j + dxdy[1][k];
				if(0<=nx && 0<=ny && nx<row && ny<col && map[nx][ny][0] < map[i][j][0]) {
					map[i][j][1] += dfs(nx,ny);
				}
			}
		}
		return map[i][j][1];
	}
}