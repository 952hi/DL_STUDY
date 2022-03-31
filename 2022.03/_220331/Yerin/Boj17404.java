import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17404 {
	public static void update(int[] arr, int[] price) {
		int n1 = Math.min(arr[1], arr[2]);
		int n2 = Math.min(arr[0], arr[2]);
		arr[2] = Math.min(arr[0], arr[1])+price[2];
		arr[0] = n1+price[0];
		arr[1] = n2+price[1];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		final int INF = 999_999_999;
		
		int n = Integer.parseInt(br.readLine());
		int R[] = new int[3], G[] = new int[3], B[] = new int[3];
		
		//초기 세팅
		st = new StringTokenizer(br.readLine());
		R[0] = Integer.parseInt(st.nextToken());
		G[1] = Integer.parseInt(st.nextToken());
		B[2] = Integer.parseInt(st.nextToken());
		
		R[1] = R[2] = G[0] = G[2] = B[0] = B[1] = INF;
		
		int price[];
		for(int i=1;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			price = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			update(R, price);
			update(G, price);
			update(B, price);
		}
	
		System.out.println(Math.min(Math.min(Math.min(R[1], R[2]), Math.min(G[0], G[2])), Math.min(B[0], B[1])));
	}
}
