package SWEXPERT.ps_D4_신도시전기연결하기;

import java.util.Arrays;
import java.util.Scanner;

public class 신도시전기연결하기_resolve {
	static int N;
	static int k;
	static int[] arrHome = null;
	static int[] distanceHome = null;
	
	static int quickSort(int i, int j) {
		int pivot = distanceHome[(i+j)/2];
		int left = i;
		int right = j;
		
		if(i >= j)
			return 0;
		while(left<=right) {
			while(distanceHome[left]<pivot) left++;
			while(distanceHome[right]>pivot) right--;
			if(left <= right) {
				int tmp = distanceHome[left];
				distanceHome[left] = distanceHome[right];
				distanceHome[right] = tmp;
				left++;
				right--;
			}
		}
		quickSort(i,right);
		quickSort(left,j);
		return 0;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int inputTC = sc.nextInt();
		
		for (int rot = 0; rot < inputTC; rot++) {
			N = sc.nextInt();
			k = sc.nextInt();
			arrHome = new int[N];
			for (int i = 0; i < N; i++)
				arrHome[i] = sc.nextInt();
			
			if (N <= k) {
				System.out.printf("#%d 0\n", (rot + 1));
				continue;
			}
			
			distanceHome = new int[N-1];
			for(int i = 0; i< N-1;i++) {
				distanceHome[i] = arrHome[i+1] - arrHome[i];
			}
			int [] a = Arrays.copyOf(distanceHome,distanceHome.length);
			quickSort(0,N-2);
			int answer = 0;
			for(int i = 0; i< N-k;i++) {
				answer += distanceHome[i];
			}
			System.out.printf("#%d %d\n",(rot+1),answer);
		}
		
	}

}
