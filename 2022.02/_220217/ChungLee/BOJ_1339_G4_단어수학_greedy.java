package _220217.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// first try : 무지성 DFS를 사용했지만 시간초과
// second try : 가중치 따른 비교
// 문자를 숫자로 치환하는 과정에서 가중치를 따로 저장하거나 정렬하는 과정이 번거로웠음.
public class BOJ_1339_G4_단어수학_greedy {
	static class charWeight implements Comparable<charWeight> {
		char C;
		int weight;
		int num;

		public charWeight(char C, int weight) {
			this.C = C;
			this.weight = weight;
		}

		@Override
		public int compareTo(charWeight o) {
			return o.weight - this.weight;
		}
	}

	static List<charWeight> L = new ArrayList<>();
	static Map<Character, Integer> map = new HashMap<>();
	static Map<Character, String> fnl = new HashMap<>();
	static String[] words = null;
	static int sum = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 단어수 입력
		int wordCnt = Integer.parseInt(br.readLine());
		words = new String[wordCnt];
		int cnt = 0;
		for (int i = 0; i < wordCnt; i++) {
			// 각 단어 입력
			String word = br.readLine();
			// 따로 배열에 저장
			words[i] = word;
			// 각 단어를 이루는 알파벳을 중복 없이 저장하기 위해 map에 저장
			for (int j = word.length() - 1; j >= 0; j--) {
				if (!map.containsKey(word.charAt(j))) {
					map.put(word.charAt(j), cnt);
					cnt++;
				}
			}
		}
		// 맵 크기만큼 가중치 배열 생성
		int[] weight = new int[map.size()];
		// 단어수만 반복하며 각 단어의 알파벳이 가지는 가중치를 가중치 배열에 저장
		// 저장 위치는 map으로 key와 함께 저장했던 value를 이용
		for (int i = 0; i < words.length; i++) {
			int tenTimes = 1;
			for (int j = words[i].length() - 1; j >= 0; j--) {
				weight[map.get(words[i].charAt(j))] += tenTimes;
				tenTimes *= 10;
			}
		}

		// 정렬을 위해 참조형 자료로 이뤄진 배열 생성
		charWeight[] cw = new charWeight[map.size()];
		cnt = 0;

		// map에 저장되어있는 값을 순회하며 알파벳과 대응되는 가중치를 참조형 배열에 저장
		for (Map.Entry<Character, Integer> m : map.entrySet()) {
			cw[cnt] = new charWeight(m.getKey(), weight[m.getValue()]);
			cnt++;
		}

		// 정렬 실행
		Arrays.sort(cw);

		// 정렬 후 가장 가중치가 큰 값부터 9~1에 해당하는 값 입력
		for (int i = 0; i < map.size(); i++) {
			cw[i].num = 9 - i;
		}

		// <Character, String>형태의 map에 알파벳과 상응하는 문자열타입 숫자 입력
		for (int i = 0; i < map.size(); i++) {
			fnl.put(cw[i].C, Integer.toString(cw[i].num));
		}

		// 각 단어를 숫자로 치환한 뒤 sum에 합
		for (int i = 0; i < wordCnt; i++) {
			String bfN = "";
			for (int j = 0; j < words[i].length(); j++) {
				bfN += fnl.get(words[i].charAt(j));
			}
			sum += Integer.parseInt(bfN);
		}
		sb.append(sum);
		bw.write(sb.toString());
		bw.flush();
	}
}