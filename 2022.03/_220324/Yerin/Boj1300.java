import java.util.Scanner;
/*
 * int의 최대 2,147,483,647
 * N*N = 10^10
 */
public class Boj1300 {
	static int N, K;
	public static int binarySearch() {
		int left=1, right=K, mid;
		//right가 K인 이유 = K번째 수는 무조건 K보다 작음
		//
		
		while(left <= right) {
			mid = (left+right)/2;//K번째 값 후보 선택
			
			long count = 0;//K보다 같거나 작은 수들의 개수 count
			for(int i=1;i<=N;i++) {//각 열에 K보다 같거나 작은 수들의 개수를 구해서 더해줌
				count += Math.min(mid/i, N);
			}
			
			if(count < K)//K개보다 적으면
				left = mid+1;//수를 더 크게 잡자
			else
				right = mid-1;//같아도 더 작은수가 있을 수 있음
		}
		return left;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		System.out.println(binarySearch());
	}

}
