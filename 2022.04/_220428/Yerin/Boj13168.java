package _0428;

import java.io.*;
import java.util.*;

public class Boj13168 {
	public static void main(String[] args) throws Exception {
		final int INF = 999_999_999;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		HashMap<String, Integer> trans = new HashMap<>();
		trans.put("Mugunghwa", 0); trans.put("ITX-Saemaeul", 0); trans.put("ITX-Cheongchun", 0);
		trans.put("S-Train", 1); trans.put("V-Train", 1);
		trans.put("Subway", 2); trans.put("Bus", 2); trans.put("KTX", 2); trans.put("Taxi", 2); trans.put("Airplane", 2);
	
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); //전체 도시 수
		int R = Integer.parseInt(st.nextToken()); //티켓 가격
		
		int map[][] = new int[N][N]; //티켓 안 산 경우
		int discountMap[][] = new int[N][N]; //티켓 산 경우
		for(int i=0; i<N; i++) {
			Arrays.fill(map[i], INF);//최대로 초기화
			Arrays.fill(discountMap[i], INF);
			map[i][i] = 0;
			discountMap[i][i] = 0;
		}
		
		//전체 도시 이름
		HashMap<String, Integer> city = new HashMap<>();//도시이름 : index
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) { city.put(st.nextToken(), i);}
		
		//여행할 도시 수
		int M = Integer.parseInt(br.readLine());
		String travel[] = new String[M];
		st = new StringTokenizer(br.readLine()); //여행할 도시 이름
		for(int i=0; i<M; i++) { travel[i] = st.nextToken();}
		
		//교통수단의 수
		int K = Integer.parseInt(br.readLine());
		//교통수단의 정보
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int dis = trans.get(st.nextToken());
			int s1 = city.get(st.nextToken());
			int s2 = city.get(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			map[s1][s2] = Math.min(map[s1][s2], price*2);
			map[s2][s1] = map[s1][s2];
			discountMap[s1][s2] = Math.min(discountMap[s1][s2], price*dis);
			discountMap[s2][s1] = discountMap[s1][s2];
		}
		
		//플로이드워샬
		for(int k=0; k<N; k++) {//경유지
			for(int i=0; i<N; i++) {
				if(i==k) { continue;}
				for(int j=i+1; j<N; j++) {
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
					map[j][i] = map[i][j];
					discountMap[i][j] = Math.min(discountMap[i][j], discountMap[i][k]+discountMap[k][j]);
					discountMap[j][i] = discountMap[i][j];
				}
			}
		}
		
		int result = 0, dresult = R*2;
		for(int i=1; i<M; i++) {
            result += map[city.get(travel[i-1])][city.get(travel[i])];
			dresult += discountMap[city.get(travel[i-1])][city.get(travel[i])];
		}
		System.out.println((result <= dresult) ? "No" : "Yes");
	}
}
