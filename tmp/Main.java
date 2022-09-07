import java.util.*;
import java.io.*;

public class Main {

	// 중간 문자열 : . 로 분리

//	static public int solution(String[] lines) {
//
//		// 0 : 시작 시간, 1 : 종료 시간
//		int[][] data = new int[lines.length][2];
//		int left = 0, right = 0, max = 1, cnt = 1, tmp = 0, mod = 100, mul = 100;
//		String[] split, times, size;
//		PriorityQueue<int[]> pq = new PriorityQueue<>((int o1[], int o2[]) -> o1[0] - o2[0]);
//		// 데이터 전처리
//		for (int i = 0; i < lines.length; i++) {
//			mod = 100;
//			mul = 100;
//			// 날짜, 시간, 타임아웃 분리
//			split = lines[i].split(" ");
//
//			// 시간 분리
//			times = split[1].split(":");
//
//			size = split[2].split("\\.");
//			// 초단위 하나만 존재하는 경우
//			if (size.length == 1) {
//				data[i][0] -= Integer.parseInt(size[0].substring(0, 1)) * 1000;
//			}
//			// 밀리초 포함
//			else {
//
//				// 앞자리 초 추가
//				data[i][0] -= Integer.parseInt(size[0]) * 1000;
//
//				// 0, 1, 2자리 추가해야 함
//				tmp = Integer.parseInt(size[1].substring(0, size[1].length() - 1));
//
//				if (size[1].length() >= 2)
//					data[i][0] -= (size[1].charAt(0) - '0') * 100;
//				if (size[1].length() >= 3)
//					data[i][0] -= (size[1].charAt(1) - '0') * 10;
//				if (size[1].length() >= 4)
//					data[i][0] -= size[1].charAt(2) - '0';
//
//				System.out.println(data[i][0]);
//			}
//
//			// 시, 분 추가
//			data[i][1] += Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60;
//
//			// 밀리조 분리
//			times = times[2].split("\\.");
//
//			// 초 추가
//			data[i][1] += Integer.parseInt(times[0]);
//
//			// 미리초 단위를 위해 추가
//			data[i][1] *= 1000;
//
//			// 미리초 추가
//			data[i][1] += Integer.parseInt(times[1]);
//			data[i][0] += data[i][1] + 1;
//
//			pq.add(new int[] { data[i][0], data[i][1] });
//			System.out.println(data[i][0] + " " + data[i][1]);
//		}
//
//		left = 1;
//		right = 2;
//
//		for (int i = 0; i < lines.length - 1; i++) {
//			// 두 값의 차이가 1000ms 이하일 때
//			if (data[right / 2][0] - data[left / 2][1] <= 999) {
//				cnt++;
//				right += 2;
//			}
//			// 모든 범위를 아우를 때
//			else if (data[right / 2][0] <= data[left / 2][1]) {
//				cnt++;
//				right += 2;
//			}
//			// 두 값의 차이가 1000ms 초과일 때
//			else {
//				if (cnt > 1)
//					cnt--;
//				// left 이동
//				left += 2;
//				// left가 right 보다 큰 경우 right 함께 이동
//				if (right < left)
//					right = left + 1;
//			}
//			// 최대 값 저장
//			max = Math.max(max, cnt);
//		}
//		return max;
//	}

	static public int solution(String[] lines) {

		// 0 : 시작 시간, 1 : 종료 시간
		int[][] data = new int[lines.length][2];
		int left = 0, right = 0, max = 1, cnt = 1, tmp = 0, mod = 100, mul = 100;
		String[] split, times, size;

		// 데이터 전처리
		for (int i = 0; i < lines.length; i++) {
			mod = 100;
			mul = 100;
			// 날짜, 시간, 타임아웃 분리
			split = lines[i].split(" ");

			// 시간 분리
			times = split[1].split(":");

			size = split[2].split("\\.");
			// 초단위 하나만 존재하는 경우
			if (size.length == 1) {
				data[i][0] -= Integer.parseInt(size[0].substring(0, 1)) * 1000;
			}
			// 밀리초 포함
			else {
				// 앞자리 초 추가
				data[i][0] -= Integer.parseInt(size[0]) * 1000;
				// 0, 1, 2자리 추가해야 함
				tmp = Integer.parseInt(size[1].substring(0, size[1].length() - 1));

				if (size[1].length() >= 2)
					data[i][0] -= (size[1].charAt(0) - '0') * 100;
				if (size[1].length() >= 3)
					data[i][0] -= (size[1].charAt(1) - '0') * 10;
				if (size[1].length() >= 4)
					data[i][0] -= size[1].charAt(2) - '0';
			}
			// 시, 분 추가
			data[i][1] += Integer.parseInt(times[0]) * 3600 + Integer.parseInt(times[1]) * 60;
			// 밀리조 분리
			times = times[2].split("\\.");
			// 초 추가
			data[i][1] += Integer.parseInt(times[0]);
			// 미리초 단위를 위해 추가
			data[i][1] *= 1000;
			// 미리초 추가
			data[i][1] += Integer.parseInt(times[1]);
			data[i][0] += data[i][1] + 1;
		}
		
		// 체크
		//0부터 n-1까지
		for (int i = 0; i < lines.length - 1; i++) {
			cnt = 1;
			//i+1부터 n까지
			for (int j = i + 1; j < lines.length; j++) {
				if(data[i][1] <= data[j][0] && data[j][0] - data[i][1] <= 999) {
					cnt++;
				}
				else if(data[i][1] + 999 >= data[j][0]) {
					cnt++;
				}
			}
			max = Math.max(max, cnt);
		}

		return max;
	}

	public static void main(String[] args) {
		System.out.println(solution(new String[] { "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s",
				"2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s",
				"2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s",
				"2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s" }));
	}
}
