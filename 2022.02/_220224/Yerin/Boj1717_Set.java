package day20;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백준 1717번 : 집합의 표현
 * 시간 : 1460ms
 * Expert에서 푼 문제 같다.
 */
public class Boj1717_Set {

	public static void main(String[] args) throws Exception {
		//입력 라인이 최대 100,000 => 한 줄에 3개씩 => 보조스트림사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); //vertex : 0 ~ N까지
		int m = Integer.parseInt(st.nextToken()); //연산 수
		int[] parent = new int[n+1];
		for(int i=1;i<=n;i++)
			parent[i] = i;
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			String c = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch(c) {
			case "0"://집합 합치기
				if(a!=b) //a와 b가 다르면!
					union(parent, a, b);
				break;
			case "1"://포함 여부 확인
				if(a==b || findSet(parent, a)==findSet(parent, b))
					System.out.println("YES");
				else
					System.out.println("NO");
				break;
			}
		}
	}

	public static int findSet(int[] parent, int a) {
		if(parent[a]==a) return a;
		return parent[a] = findSet(parent, parent[a]);
	}
	
	public static void union(int[] parent, int a, int b) {
		a = findSet(parent, a);
		b = findSet(parent, b);
		if(a==b) return; //a와 b의 집합이 같을 때
		parent[b] = a;
	}
}
