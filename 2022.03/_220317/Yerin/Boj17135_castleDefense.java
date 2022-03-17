package _20220317;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17135_castleDefense {
	static int N, M, D, enemies, result;
	static int[] selected;
	static int[][] map;
	
	public static void combination(int start, int cnt) {
		if(cnt==3) {
			result = Math.max(result, simulation());
			return;
		}
		
		for(int i=start; i<M; i++) {
			selected[cnt] = i;
			combination(i+1, cnt+1);
		}
	}
	
	public static int simulation() {//bfs
		int enemySu = enemies;//적의 수
		int dx[] = {0, -1, 0}, dy[] = {-1, 0, 1};
		//map 복사
		int[][] copyMap = new int[N][M];
		for(int i=0;i<N;i++)
			copyMap[i] = Arrays.copyOf(map[i], M);
		
		//계산
		int castle = N;
		while(enemySu > 0 && castle > 0) {//적이 다 없을 때까지
			for(int i=0;i<3;i++) {//궁수 3명과 가까운 적(중 왼쪽) 찾아 없애기
				//bfs
				Queue<int[]> q = new LinkedList<>();
				q.add(new int[] {castle, selected[i]});//i번째 궁수의 위치 삽입
				boolean visited[][] = new boolean[N][M];//bfs용 방문기록
				int d = 0;//거리
				outloop: while(!q.isEmpty()) {
					if(++d > D)
						break;
					
					for(int k=0, size=q.size(); k<size; k++) {
						int[] node = q.poll();
						for(int j=0; j<3; j++) {//3방향 탐색
							int nx = node[0] + dx[j];
							int ny = node[1] + dy[j];
							if(0<=nx && nx<castle && 0<=ny && ny<M && !visited[nx][ny]) {//범위에 속하면
								if(copyMap[nx][ny]==1) {
									enemySu--;
									copyMap[nx][ny] = -castle;
									break outloop;
								}
								else if(copyMap[nx][ny]== -castle) {
									break outloop;
								}
								visited[nx][ny] = true;
								q.add(new int[] {nx, ny});
							}
						}
					}
				}
			}
			castle--;
		}
		
		return enemies-enemySu;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		selected = new int[3];//궁수 3명의 위치
		map = new int[N][M];
		enemies = 0;//적들의 수
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) enemies++;
			}
		}
		
		combination(0, 0);
		System.out.println(result);
	}
}