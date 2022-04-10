import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj19236 {
	static int N = 4, result;
	
	public static class Node {
		int x, y, no, dir;

		public Node(int x, int y, int no, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.no = no;
			this.dir = dir;
		}
	}
	
	//-1 : shark, 0 : blank, 1~16 : fish
	public static void simulation(Node[][] mapOrigin, int sx, int sy, int sd, int sum) {//상어 위치
		int dx[] = {0, -1, -1, 0, 1, 1, 1, 0, -1}, dy[]= {0, 0, -1, -1, -1, 0, 1, 1, 1};//위에서부터 반시계(0번째는 쓰레기값)
		result = Math.max(result, sum);
		
		//1) map 복사, fish 복사
		Node[][] map = new Node[N][N];
		Node[] fish = new Node[17];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				map[i][j] = new Node(mapOrigin[i][j].x, mapOrigin[i][j].y, mapOrigin[i][j].no, mapOrigin[i][j].dir);
				if(map[i][j].no>0) {
					fish[map[i][j].no] = map[i][j];
				}
			}
		}
		
		//2) 물고기 이동 = 번호 순
		for(int i=1;i<=16;i++) {
			if(fish[i] == null) continue;//잡아 먹혔으면 넘어감
			int fx = fish[i].x;
			int fy = fish[i].y;
			int fd = fish[i].dir;
			for(int j=0; j<8; j++) {
				int nd = (fd+j-1)%8+1;//0~7을 주면 +1해서 1~8로
				int nx = fx + dx[nd];
				int ny = fy + dy[nd];
				if(0<=nx && nx<N && 0<=ny && ny<N && map[nx][ny].no!=-1) {//범위 안에 & 상어가 아님
					map[fx][fy].dir = nd;
					Node temp = map[fx][fy];
					map[fx][fy] = map[nx][ny];
					map[nx][ny] = temp;
					//x, y값 업데이트 => 물고기 이동용
					map[fx][fy].x = fx;
					map[fx][fy].y = fy;
					map[nx][ny].x = nx;
					map[nx][ny].y = ny;
					break;
				}
			}
		}
		
		//3) 상어 이동
		for(int i=1;i<4;i++) {
			int nsx = sx + dx[sd]*i;
			int nsy = sy + dy[sd]*i;

			if(0<=nsx && nsx<N && 0<=nsy && nsy<N) {
				if(map[nsx][nsy].no > 0) {//빈칸이 아니라면 잡으러!
					int no = map[nsx][nsy].no;
					map[nsx][nsy].no = 0;//잡아먹었으니 빈칸
					
					//방향 동일하게 전환
					map[sx][sy].dir = map[nsx][nsy].dir;
					//두 Node Swap
					Node temp = map[sx][sy];
					map[sx][sy] = map[nsx][nsy];
					map[nsx][nsy] = temp;
					
					//재귀 호출
					simulation(map, nsx, nsy, map[nsx][nsy].dir, sum+no);
					
					//되돌리기(Node Swap Back)
					temp = map[sx][sy];
					map[sx][sy] = map[nsx][nsy];
					map[nsx][nsy] = temp;
					map[sx][sy].dir = sd;
					map[nsx][nsy].no = no;
				}
			}
			else
				return;//범위 넘어가면 종료
		}
		return;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		Node[][] map = new Node[N][N];
		Node[] fish = new Node[17];
		result = 0;
		
		//입력
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = new Node(i, j, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				fish[map[i][j].no] = map[i][j];
			}
		}
		
		int temp = map[0][0].no;
		map[0][0].no = -1;//상어는 -1번으로, 빈칸은 0으로
		simulation(map, 0, 0, map[0][0].dir, temp);
		System.out.println(result);
	}
}
/*
7 6 2 6 15 7 9 3
3 5 1 4 14 1 10 6
6 4 13 3 4 6 11 1
16 5 8 7 5 2 12 2
답 = 88
*/
