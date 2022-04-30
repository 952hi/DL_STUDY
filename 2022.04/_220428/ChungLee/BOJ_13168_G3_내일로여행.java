package _220428.ChungLee;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class BOJ_13168_G3_내일로여행 {
	static Map<String, Integer> cityMap = new HashMap<>();
	static Map<String, Integer> dscnt = new HashMap<>();
	static float[][] city_conn_arr;
	static float[][] city_conn_arr2;
	static int N, V, i, j, cross, method, intFrom, intTo, intTrans, k;
	static float value, sum, sum2;
	static int[] crossCity;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

//		Map<String, Integer> cityMap = new HashMap<>();
//		Map<String, Integer> dscnt = new HashMap<>();
//		float[][] city_conn_arr;
//		float[][] city_conn_arr2;
		// 할인 없음
		dscnt.put("Subway", 0);
		dscnt.put("Bus", 0);
		dscnt.put("Taxi", 0);
		dscnt.put("Airplane", 0);
		dscnt.put("KTX", 0);
		// 반값 할인
		dscnt.put("S-Train", 1);
		dscnt.put("V-Train", 1);
		// 무료
		dscnt.put("ITX-Saemaeul", 2);
		dscnt.put("ITX-Cheongchun", 2);
		dscnt.put("Mugunghwa", 2);
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			cityMap.put(st.nextToken(), i);
		}

		city_conn_arr = new float[N][N];
		city_conn_arr2 = new float[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				city_conn_arr[i][j] = Float.MAX_VALUE;
				city_conn_arr2[i][j] = Float.MAX_VALUE;
			}
		}
		for (int i = 0; i < N; i++) {
			city_conn_arr[i][i] = 0;
			city_conn_arr2[i][i] = 0;
		}

		// 지나쳐야할 도시의 개수
		cross = Integer.parseInt(br.readLine());
		crossCity = new int[cross];
		// 매핑된 도시 번호를 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < cross; i++) {
			crossCity[i] = cityMap.get(st.nextToken());
		}

		method = Integer.parseInt(br.readLine());
		
		
		for (int i = 0; i < method; i++) {
			st = new StringTokenizer(br.readLine());
			// 목적지, 가격, 할인룰
			intTrans = dscnt.get(st.nextToken());
			intFrom = cityMap.get(st.nextToken());
			intTo = cityMap.get(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			// 할인 못받을 때 아무 값도 들어있지 않다면
			city_conn_arr2[intFrom][intTo] = Math.min(city_conn_arr2[intFrom][intTo], value);
			city_conn_arr2[intTo][intFrom] = Math.min(city_conn_arr2[intTo][intFrom], value);

			// 내일로 할인 받을 때
			// 할인 없는 경우
			if (intTrans == 0) {
				city_conn_arr[intFrom][intTo] = Math.min(city_conn_arr[intFrom][intTo], value);
				city_conn_arr[intTo][intFrom] = Math.min(city_conn_arr[intTo][intFrom], value);
			} else if (intTrans == 1) {
				city_conn_arr[intFrom][intTo] = Math.min(city_conn_arr[intFrom][intTo], value / 2f);
				city_conn_arr[intTo][intFrom] = Math.min(city_conn_arr[intTo][intFrom], value / 2f);
			} else {
				city_conn_arr[intFrom][intTo] = 0;
				city_conn_arr[intTo][intFrom] = 0;
			}
		}

		for (k = 0; k < N; k++) {
			for (i = 0; i < N; i++) {
				for (j = 0; j < N; j++) {
					city_conn_arr2[i][j] = Math.min(city_conn_arr2[i][j], city_conn_arr2[i][k] + city_conn_arr2[k][j]);
					city_conn_arr[i][j] = Math.min(city_conn_arr[i][j], city_conn_arr[i][k] + city_conn_arr[k][j]);
				}
			}
		}

		// 내일로 끊었을 때
		sum = V;
		sum2 = 0;
		for (int i = 1; i < cross; i++) {
			sum += city_conn_arr[crossCity[i - 1]][crossCity[i]];
			sum2 += city_conn_arr2[crossCity[i - 1]][crossCity[i]];
		}
		if (sum < sum2) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
