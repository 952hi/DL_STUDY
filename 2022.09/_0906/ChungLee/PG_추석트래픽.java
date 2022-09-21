package _0906.ChungLee;

public class PG_추석트래픽 {
	public int solution(String[] lines) {

		// 0 : 시작 시간, 1 : 종료 시간
		int[][] data = new int[lines.length][2];
		int max = 1, cnt = 1, tmp = 0, mod = 100, mul = 100;
		String[] split, times, size;

		// **********************
		// 데이터 전처리 시작
		// 1초 => 1000으로 변환
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
		// 데이터 전처리 끝
		// **********************
		
		
		// 체크
		// 0부터 N-1까지를 기준
		// 1부터 N까지 기준값과 비교
		// 1. 비교 데이터의 끝 값이 기준 값의 시작 값보다 작거나 같고 두 값의 간격이 999이하라면 겹치는 것
		// 2. 혹은 기준 데이터의 끝값에서 비교데이터의 시작 값 차이가 999ms 이하라도 가능
		for (int i = 0; i < lines.length - 1; i++) {
			cnt = 1;
			// i+1부터 n까지
			for (int j = i + 1; j < lines.length; j++) {
				
				if (data[i][1] <= data[j][0] && data[j][0] - data[i][1] <= 999) {
					cnt++;
				} else if (data[i][1] + 999 >= data[j][0]) {
					cnt++;
				}
			}
			max = Math.max(max, cnt);
		}
		return max;
	}
}
