package SWEXPERT.PS_D3_10726_이진수표현;

import java.util.Scanner;

public class 이진수표현 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int inputTC = sc.nextInt();
		for (int i = 0; i < inputTC; i++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			//우선 출력
			System.out.printf("#%d ", (i + 1));
			
			//M이 N보다 작다면 항상 OFF
			if (M < N)
				System.out.println("OFF");
			else {
				//비트마스킹 변수
				int k = 1;
				//비트 일치 수
				int cnt = 0;
				for (int j = 0; j < N; j++) {
					//둘 다 비트 1일 때
					if ((M & k) > 0) {
						cnt++;
						k <<= 1;
					}
					//한 번이라도 비트가 0이면 OFF
					else
						break;
				}
				//비트 개수가 N과 일치 시 ON, 그 외 OFF
				if(cnt == N)
					System.out.println("ON");
				else
					System.out.println("OFF");
			}
		}

	}

}
