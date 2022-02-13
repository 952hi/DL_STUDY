import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	public static int N;
	public static int M;
	public static int[][] map;
	public static int wall=0;
	public static int[][] temp;
	public static int Maxarea=0;

	public static void main(String[] args) throws Exception {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		// N,M 입력 및 map과 방문 배열 초기화
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		map = new int[N][M];
		temp = new int[N][M];
		
		// 행만큼 입력받아오기
		for(int q=0;q<N;q++) {
			map[q] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		
		dfs(0,0);
		System.out.println(Maxarea);
	}
	// 벽 3개 세우기 
	// -> 바이러스 퍼트리기 -> 지역계산 (max)
	public static void dfs(int x, int y) {
		if(wall == 3) {
			for(int i=0;i<N;i++) {
				System.arraycopy(map[i], 0,temp[i],0 ,map[i].length);
			}
			for(int k=0;k<N;k++) {
				for(int l=0;l<M;l++) {
					if(temp[k][l]==2){
						virus(k,l);
					}
				}
			}
			score();
			
			return;
		}
		// 벽의 모든경우 
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(map[i][j]==0) {
					wall += 1;
					map[i][j] = 1;
					dfs(i,j);
					map[i][j] = 0;
					wall -= 1;
				}
			}
		}
	}
	
	// 동서남북
	public static int[] dx = {0,0,1,-1};
	public static int[] dy = {1,-1,0,0};
	
	// 바이러스 퍼트리기
	public static void virus(int row,int col) {
		int nx = 0;
		int ny = 0;
		for(int i=0;i<4;i++) {
			nx = row+dx[i];
			ny = col+dy[i];
			if(0<=nx && nx<N && 0<=ny && ny<M && temp[nx][ny]==0) {
				temp[nx][ny]=2;
				virus(nx,ny);
			}
		}
		
	}
	// 안전지역 계산
	public static void score() {
		int cnt= 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(temp[i][j]==0) {
					cnt+=1;
				}
			}
		}
		Maxarea = Math.max(Maxarea, cnt);
	}
}
