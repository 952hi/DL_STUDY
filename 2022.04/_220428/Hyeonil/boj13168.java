import java.io.*;
import java.util.*;
public class boj13168 {
	// boj13168
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(stz.nextToken());
		int won = Integer.parseInt(stz.nextToken());
		Map<String, Integer> map = new HashMap<String, Integer>();
		stz = new StringTokenizer(br.readLine());
		String chk;
		
		//도시별 인덱스 생성
		for(int i=0;i<N;i++) {
			chk = stz.nextToken();
			map.put(chk, i);
		}
		int M = Integer.parseInt(br.readLine());
		int[] res = new int[M];
		stz = new StringTokenizer(br.readLine());
		// 여행경로 저장
		for(int i=0;i<M;i++) {
			chk = stz.nextToken();
			res[i] = map.get(chk);
		}
		
		int board[][][] = new int[N][N][2];//0 기본 1 내일로
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				// 모두 선택안된길 초기화 무한값쓰면 오버돼서 -1이 될수도 있어서 
				// 1000000으로 초기화
				board[i][j][0] = 1000000;
				board[i][j][1] = 1000000;
			}
		}
		
		String vehecle;
		int val,start,end,val2;
		
		for(int i=0;i<t;i++) {
			stz = new StringTokenizer(br.readLine());
			vehecle = stz.nextToken();
			start = map.get(stz.nextToken());
			end = map.get(stz.nextToken());
			val = Integer.parseInt(stz.nextToken());
			val2 = discount(val*2,vehecle);
			
			// 같은 도시 다른운송이용시 다른 가격일때 더 작은 것만
			if(board[start][end][0]>val*2) {
				board[start][end][0]=val*2;
				board[end][start][0]=val*2;
			}
			// 같은 도시 다른운송이용시 다른 가격일때 더 작은 것만
			if(board[start][end][1] > val2) {
				board[start][end][1] = val2;
				board[end][start][1] = val2;
			}
		}
		// 경출도 모든 경우의수 탐색 진행
		for(int k=0;k<N;k++) {
			for(int i=0;i<N;i++) {
				if(i==k) continue;
				for(int j=0;j<N;j++) {
					if(i==j || j==k) continue;
					if(board[i][j][0]>board[i][k][0]+board[k][j][0])
						board[i][j][0]=board[i][k][0]+board[k][j][0];
					if(board[i][j][1]>board[i][k][1]+board[k][j][1])
						board[i][j][1] = board[i][k][1]+board[k][j][1];
				}
			}
		}
		// one 기본가격
		// two 내일로 티켓사용시
		int one=0,two=won*2;
		for(int i=1;i<M;i++) {
			one += board[res[i-1]][res[i]][0];
			two += board[res[i-1]][res[i]][1];
		}
		if(one<=two) System.out.println("No");
		else System.out.println("Yes");
	}
	// 교통 수단별 할인 함수
	private static int discount(int val,String vehecle) {
		if(vehecle.equals("ITX-Saemaeul") || vehecle.equals("Mugunghwa") || vehecle.equals("ITX-Cheongchun")) return 0;
		else if(vehecle.equals("S-Train")|| vehecle.equals("V-Train") ) return val/2;
		return val;
	}
}