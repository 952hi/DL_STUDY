import java.io.*;
import java.util.*;

public class Boj1461 {
	public static int go(int[] arr, int size, int m) {
		int step = 0, start = -1;
		if(size%m != 0) {//m개로 나눠떨어지지 않을 때, 가까운 걸 pick
			start = size%m-1;
			step += arr[start]*2;
		}
		for(int i=start+m; i<size; i+=m) {
			step+=arr[i]*2;
		}
		return step;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//책의 개수
		int M = Integer.parseInt(st.nextToken());//들 수 있는 책의 수
		
		st = new StringTokenizer(br.readLine());
		
		int[] left = new int[N];//음수
		int[] right = new int[N];//양수
		Arrays.fill(left, Integer.MAX_VALUE);
		Arrays.fill(right, Integer.MAX_VALUE);
		
		int lIdx = 0, rIdx = 0;
		for(int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if(n<0) {
				left[lIdx++] = -n;
			}
			else {
				right[rIdx++] = n;
			}
		}
		
		int max = 0;
		int result = 0;
		if(lIdx > 0) {
			Arrays.sort(left);
			result += go(left, lIdx, M);
			max = left[lIdx-1];
		}
		
		if(rIdx > 0) {
			Arrays.sort(right);
			result += go(right, rIdx, M);
			max = Math.max(max, right[rIdx-1]);
		}
		System.out.println(result-max);
	}
}
