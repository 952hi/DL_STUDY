package day18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

/*
 * 백준 8983번 : 사냥꾼
 * 사대와, 동물의 x좌표 기준으로 정렬 후, 거리 안에 드는지 비교
 * 832ms
 */

public class Boj8983 {
	static class Animal implements Comparable<Animal>{
		int x, y;
		public Animal(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Animal o) {
			return this.x-o.x;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); //사대 수
		int N = Integer.parseInt(st.nextToken()); //동물 수
		int L = Integer.parseInt(st.nextToken()); //사정거리
		
		//입력
		//사대 위치
		int[] stations = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Animal[] animals = new Animal[N];
		for(int i=0;i<N;i++) {//동물수
			st = new StringTokenizer(br.readLine());
			animals[i] = new Animal(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
		}
		//정렬
		Arrays.sort(stations);
		Arrays.sort(animals);
		
		int j=0;//사대 위치 idx
		int answer =0;//결과
		for(int i=0;i<N;i++) {
			if(animals[i].y > L)//사정거리 밖에 위치
				continue;
			
			//더 가까운 사대로
			while(j<M-1 && animals[i].x > Math.min(stations[j]+L, (stations[j]+stations[j+1])/2)) {
				j++;
			}
			//거리 안에 들면 => 결과+1
			if(Math.abs(animals[i].x-stations[j]) <= L-animals[i].y) {
				answer++;
			}
		}
		System.out.println(answer);
	}

}
