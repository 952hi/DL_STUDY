package _220418.ChungLee;

import java.util.Scanner;

//31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
// 400의 배수 연도는 윤년이다.
// 400의 배수가 아니면서 100의 배수인 연도는 윤년이 아니다.
// 100의 배수가 아니면서 4의 배수인 연도는 윤년이다.
// 그 외의 연도는 윤년이 아니다
// 6 : 일, 0 : 월, 1 :화, 2 :수, 3 :목, 4 :금, 5 : 토
public class BOJ_16463_S4_13일의금요일 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// 처음에는 if문을 활용해서 1,3,5,7,8,10,12는 31을 더하는 방법을 사용했었는데 문제를 해결한 후
		// 이게 더 효율적인 방법인 것 같아서 수정했습니다.
		int[] monthArr = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = s.nextInt();
		int month = 1, day = 1, answer = 0;
		// 13일을 기준으로 맞춰주기 위해서 1일에 12일을 더해주었습니다.
		day += 12;
		for (int i = 2019; i <= year; i++) {
			for (int j = 1; j <= 12; j++) {
				// 0:월,1:화,2:수,3:목,4:금...으로 mod를 하면 금요일인지 확인할 수 있었습니다.
				if (day % 7 == 4)
					answer++;
				// 2월이 아니면 해당 월의 길이만큼 더해줍니다.
				if (j != 2)
					day += monthArr[j];
				// 나머지는 주어진 조건에 따라 2월의 길이를 더해주었습니다.
				else {

					if (i % 400 != 0 && i % 100 == 0)
						day += 28;

					else if (i % 400 == 0 || (i % 100 != 0 && i % 4 == 0))
						day += 29;
					else
						day += 28;
				}
			}
		}
		System.out.println(answer);
	}
}