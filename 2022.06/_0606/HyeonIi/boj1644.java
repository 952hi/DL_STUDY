import java.io.*;
import java.util.ArrayList;
public class boj1644 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 목표 수 입력
		boolean[] prime = new boolean[N+1]; // 체크배열 생성
		ArrayList<Integer> list = new ArrayList<Integer>(); // 소수를 담을 리스트 생성
		prime[0] = true; // 0과 1은 소수가 아님 
		prime[1] = true; // 소수는 1보다 큰 자연수 중 약수가 1과 자기 자신인 수
		int max = (int) Math.sqrt(N); // 제곱근을 통해 가장큰 소수 범위지정
		for(int i=2;i<=max;i++) {
			if(!prime[i]) { // 체크안됐으면 소수 -> 소수의 배수는 모두 소수가 아님
				for(int j=i*i;j<=N;j=j+i) {
					prime[j]=true;
				}
			}
		}
		for(int i=2;i<=N;i++) { // 체크안된부분은 리스트로 관리
			if(!prime[i]) list.add(i);
		}
		int size = list.size(); // 최대 크기를 반복문안에서 받으면 계속 실행되므로 밖에서 구함
		int res = 0; // 정답 변수
		for(int i=0;i<size;i++) { // 소수의 개수만큼 반복
			int sum = list.get(i);
			if(sum==N) { // 합이 목표값이면 증가시키고 종료
				res++;
				break;
			}
			for(int j=i+1;j<size;j++) {
				sum += list.get(j); // I~J까지 합을 구함
				if(sum==N) { // 구한 합을 바탕으로 같으면 다음 소수로
					res++;
					break;
				}else if(sum>N) break; // 크면 다음 소수로 이동
			}
		}
		System.out.println(res);
	}
}