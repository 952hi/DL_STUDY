package _220418.ChungLee;

import java.util.Scanner;

public class BOJ_16463_S4_13일의금요일 {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int[] monthArr = new int[] { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year = s.nextInt();
		int month = 1, day = 1, answer = 0;
		day += 12;
		for (int i = 2019; i <= year; i++) {
			for (int j = 1; j <= 12; j++) {

				if (day % 7 == 4)
					answer++;
				if (j != 2)
					day += monthArr[j];
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