import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;
/*
 * 백준 2212번 : 센서
 * 전체탐색으로 풀다가 아닌 것 같아서 블로그 찾음..
 * 시간 : 204ms
 */

public class BOJ2212 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] sensors = new int[N];
		int[] dis = new int[N-1];
		
		sensors = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(sensors);
		
		for(int i=1;i<N;i++)
			dis[i-1] = sensors[i]-sensors[i-1];
		
		//기지국은 한쪽만 된다고 생각 => 어차피 양쪽으로 해도 거리는 똑같음
		//1 3<=| 6 6 7 9<=|
		int result=0;
		Arrays.sort(dis);
		for(int i=0;i<N-K;i++)
			result += dis[i];
		
		System.out.println(result);
	}
}
