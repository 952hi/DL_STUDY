package _220217.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ_1339_G4_단어수학_DFS {
	static Map<Character, Integer> map = new HashMap<Character, Integer>();
	static List<Character> L = new ArrayList<Character>();
	static int chckBit = 0;
	static String[] words = null;
	static int maxSum = 0;
	static int Lsize = 0;

	static void findMaxNum(int crntCh) {
		if (crntCh == Lsize) {
			int sum = 0;
			for (int i = 0; i < words.length; i++) {
				String trans = "";
				for (int j = 0; j < words[i].length(); j++) {
					trans += map.get(words[i].charAt(j));
				}
				sum += Integer.parseInt(trans);
			}
			maxSum = Math.max(sum, maxSum);
		}
		for (int i = 0; i < Lsize; i++) {
			if ((chckBit & 1 << i) == 0) {
				chckBit |= 1 << i;

				map.put(L.get(crntCh), (9 - i));
				findMaxNum(crntCh + 1);
				chckBit &= ~(1 << i);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int wordCnt = Integer.parseInt(br.readLine());
		words = new String[wordCnt];
		for (int i = 0; i < wordCnt; i++) {
			String word = br.readLine();
			words[i] = word;
			for (char c : word.toCharArray()) {
				if (!L.contains(c))
					L.add(c);
			}
		}
		Lsize = L.size();
		findMaxNum(0);
		sb.append(maxSum);
		bw.write(sb.toString());
		bw.flush();
	}

}
