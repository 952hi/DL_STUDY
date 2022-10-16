package _1016.LC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_G5_용액 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 값 입력
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0, right = N - 1, minSum = Integer.MAX_VALUE, ansL = 0, ansR = 0;
		//같은 용액을 사용할 수 없기 때문에 < 사용
		while (left < right) {
			if(arr[left] + arr[right] == 0) {
				ansL = arr[left];
				ansR = arr[right];
				break;
			}
			if(Math.abs(arr[left] + arr[right]) < minSum) {
				minSum = Math.abs(arr[left] + arr[right]);
				ansL = arr[left];
				ansR = arr[right];
			}
			
			if(arr[left] + arr[right] < 0) {
				left++;
			}else {
				right--;
			}
		}
		System.out.println(ansL + " " + ansR);
	}
}
