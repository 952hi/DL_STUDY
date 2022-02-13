import java.util.LinkedList;
import java.util.Scanner;

/*
 * 백준 14502번 : 연구소
 * - 예전에 Python으로 풀었던 문제 풀다보니 기억났다. => combination함수 사용
 * - Java는 2차원 배열 조합을 직접 구현하는게 조금 어려웠다.
 */
public class BOJ14502 {
	static int N, M;
	static char map[][];
	static LinkedList<int[]> virusPos;
	static int wall, infected, temp;
	
	//3개 조합
	public static void combination(int x, int y, int cnt) {
		if(cnt==3) {
			//배열 복사
			char[][] map2 = new char[N+2][M+2];
			for(int i=0;i<N+2;i++) {
				for(int j=0;j<M+2;j++) {
					map2[i][j] = map[i][j];
				}
			}
			//감염시키기
			temp = 0;
			for(int[] pos : virusPos) {
				dfs(map2, pos[0], pos[1]);//감염된 갯수 반환
			}
			infected = Math.min(infected, temp);
			return;
		}
		
		int i = x;
		int j = y;
		while(i<=N) {
			if(j>M) {
				i++;
				j=1;
				continue;
			}
			
			if(map[i][j]=='0') {
				map[i][j] = '1';
				combination(i, j+1, cnt+1);
				map[i][j] = '0';
			}
			j++;
		}
		return;
	}
	
	public static void dfs(char[][] map2, int x, int y) {
		//감염시키기(감염된 구역 count)
		int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};//상하좌우
		//4방위 탐색
		int nx, ny;
		for(int i=0;i<4;i++) {
			nx = x+dx[i];
			ny = y+dy[i];
			if(map2[nx][ny]=='0') {
				map2[nx][ny] = '2';
				temp++;
				dfs(map2, nx, ny);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new char[N+2][M+2];
		virusPos = new LinkedList<>();
		infected = N*M;//전체
		
		//입력
		sc.nextLine();//Enter
		for(int i=1;i<=N;i++) {
			String s = sc.nextLine().replace(" ", "");
			for(int j=1;j<=M;j++) {
				map[i][j] = s.charAt(j-1);
				if(map[i][j]=='1')//벽의 개수
					wall++;
				else if(map[i][j]=='2')//감염 구역(고정값)
					virusPos.add(new int[] {i, j});
			}
		}
		
		//벽세우기
		for(int i=0;i<M+2;i++) {
			map[0][i] = '1';
			map[N+1][i] = '1';
		}
		for(int i=0;i<N+2;i++) {
			map[i][0] = '1';
			map[i][M+1] = '1';
		}
		
		//3개 조합
		combination(1, 1, 0);
		wall+=3;
		
		System.out.println(N*M-infected-wall-virusPos.size());
		//끝
	}
}
