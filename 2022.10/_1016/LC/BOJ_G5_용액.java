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
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0, right = N - 1, mid, minSum = Integer.MAX_VALUE, ansL = 0, ansR = 0;

		while (left < right) {
			mid = (left + right) / 2;

			if(arr[left] + arr[right] == 0) {
				System.out.println(arr[left] + " " + arr[right]);
				return;
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
