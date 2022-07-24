package _0721.ChungLee;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BOJ_1027_G4_고층건물 {
	static int N;
	static int[] building;
	static int[] visible;

	// 보이는 빌딩 카운팅
	private static void count() {
		// 빌딩이 하나인 경우는 제외하기 위해 N - 1
		for (int i = 0; i < N - 1; i++) {
			visible[i] += 1; // 바로 옆 건물은 무조건 보임
			visible[i + 1] += 1;
			
			//높이 차이
			double slope = building[i + 1] - building[i];
			
			//다다음 빌딩부터 비교
			for (int j = i + 2; j < N; j++) {
				// 기울기 계산 
				double nextSlope = (double) (building[j] - building[i]) / (j - i);
				
				//이전 기울기보다 작거나 같으면 안보이는 것이기 때문에 패스
				if (nextSlope <= slope)
					continue;
				
				//더 크다면 해당 기울기 저장
				slope = nextSlope;
				
				//두 빌딩은 서로 보이는 것이기 때문에 각각 ++
				visible[i]++;
				visible[j]++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		// 빌딩 정보 저장
		building = new int[N];
		// 빌딩이 보이는지 여부 저장
		visible = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			building[i] = Integer.parseInt(st.nextToken());
		}

		count();

		Arrays.sort(visible);
		System.out.println(visible[visible.length - 1]);
	}

}