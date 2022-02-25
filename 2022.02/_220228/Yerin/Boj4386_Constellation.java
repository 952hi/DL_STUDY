package _20220224;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * 백준 4386번 : 별자리
 * 시간 : 132ms
 * MST - Prim알고리즘
 */

public class Boj4386_Constellation {

	//점과 점 사이 거리
	public static double getDistance(double x1, double y1, double x2, double y2) {
		return (Math.sqrt(Math.pow((x1-x2), 2)+Math.pow((y1-y2), 2)));
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		double[][] stars = new double[n][2];
		
		//다 연결돼있다고 생각 => 연결 여부 확인 필요 없음
		StringTokenizer st = null;
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			stars[i] = new double[] {Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())};
		}
		
		double result = 0;
		boolean visited[] = new boolean[n];
		double minEdge[] = new double[n];
		Arrays.fill(minEdge, Double.MAX_VALUE);
		//시작점을 0으로 (아무거나 상관X)
		minEdge[0] = 0;
		
		for(int i=0;i<n;i++) {//정점 수만큼 반복
			//1. 최소값 찾기
			int current = 0;
			double min = Double.MAX_VALUE;
			for(int j=0;j<n;j++) {
				if(!visited[j] && min > minEdge[j]) {
					min = minEdge[j];
					current = j;
				}
			}
			//2. 방문체크
			visited[current] = true;
			result += min;
			
			//3. 업데이트
			for(int j=1;j<n;j++) {//어차피 0부터 시작하니까 방문 확정 = 건너뛰자 1부터 시작
				if(!visited[j]) {
					double temp = getDistance(stars[current][0], stars[current][1], stars[j][0], stars[j][1]);
					minEdge[j] = Math.min(minEdge[j], temp);
				}
			}
		}
		System.out.printf("%.2f", result);
	}
}
