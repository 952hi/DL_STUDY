package problemSolving;

import java.util.Scanner;

public class programmers_N으로표현 {
	static int N;
	static int min = 9;
	static int answer = 0;
	static boolean isfirstArrived = false;
	static int[] sic = new int[9];
	static int number;
	static int BFS(int origin, int shortest, int before) {

		if (shortest > 8)
			return 0;

		// 들어온 놈이 N,NN,NNN,NNNN,...의 형태면 정답
		// 정답의 형태인지 파악하기 위해
		int tmp = origin;
		if (tmp % N == 0 && tmp != 1) {
			tmp = origin / N;
			while (true) {
				if (tmp == 1) {
					String a = Integer.toString(origin);
					if (isfirstArrived == false) {
						isfirstArrived = true;
						answer = a.length() + shortest;
					} else if (answer > a.length() + shortest)
						answer = a.length() + shortest;
					
					System.out.print(number);
					
					for (int i = 0; i < sic.length; i++) {
						if (sic[i] != 0) {
							if (sic[i] == 1)
								System.out.print(" + "+ N);
							else if (sic[i] == 2) {
								System.out.print(" - " + N);
							} else if (sic[i] == 3) {
								System.out.print(" * " + N);
							} else if (sic[i] == 4) {
								System.out.print(" / " + N);
							}
						}
					}
					System.out.print(" = "+origin+", answer : " + (a.length() + shortest)+"\n");
					return 0;
				} else if (tmp % 10 == 1) {
					tmp /= 10;
					continue;
				}
				break;
			}
		}
		// 이전
		if (before != 2) {
			sic[shortest] = 1;
			BFS(origin + N, shortest + 1, 1);
			sic[shortest] = 0;
		}

		if (origin - N > 0 && before != 1) {
			sic[shortest] = 2;
			BFS(origin - N, shortest + 1, 2);
			sic[shortest] = 0;
		}
		if (before != 4) {
			sic[shortest] = 3;
			BFS(origin * N, shortest + 1, 3);
			sic[shortest] = 0;
		}

		if (origin % N == 0 && before != 3) {
			sic[shortest] = 4;
			BFS(origin / N, shortest + 1, 4);
			sic[shortest] = 0;
		}
		return 0;
	}

	public int solution(int N, int number) {
		this.N = N;
		this.number= number;
		
		BFS(number, 0, -1);
		if (isfirstArrived == false) {
			System.out.println(-1);
			return -1;
		} else {
			System.out.println(answer);
			return answer;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		programmers_N으로표현 a = new programmers_N으로표현();
		int N = sc.nextInt();
		int number = sc.nextInt();
		
		a.solution(N, number);
	}

}
