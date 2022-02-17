package _220217.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;


// 256ms
public class BOJ_2212_G5_센서 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 중복 제거 & 자동 정렬
		Set<Integer> set = new TreeSet<>();
		// 가장 작은 수부터 꺼내기 위해
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		// 센서 개수 입력
		int tc = Integer.parseInt(br.readLine());
		// 집중국 개수
		int sensorPart = Integer.parseInt(br.readLine());
		// 센서 좌표
		int[] abc = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		// 중복 제거
		for (int i = 0; i < tc; i++)
			set.add(abc[i]);

		int cnt = 0;
		int before = 0;
		long sum = 0;
		for (int i : set) {
			if (cnt != 0) {
				pq.add(i - before);
				before = i;
				cnt++;
			} else {
				before = i;
				cnt++;
			}
		}
		for (int i = 0, size = pq.size() - (sensorPart - 1); i < size; i++)
			sum += pq.poll();
		System.out.println(sum);
	}
}