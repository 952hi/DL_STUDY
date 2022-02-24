package _220224.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리: 124932KB, 시간: 720ms
public class BOJ_20040_G4_사이클게임 {
	static int nodeCnt;
	static int N[];

	static boolean Union(int start, int end) {
		start = FindSet(start);
		end = FindSet(end);
		if (start == end)
			return false;
		if (N[end] < N[start]) {
			N[end] += N[start];
			N[start] = end;
		} else {
			N[start] += N[end];
			N[end] = start;
		}
		return true;
	}

	static int FindSet(int a) {
		if (N[a] < 0)
			return a;
		return N[a] = FindSet(N[a]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		nodeCnt = Integer.parseInt(st.nextToken());
		int turnLimit = Integer.parseInt(st.nextToken());
		N = new int[nodeCnt];
		for (int i = 0; i < N.length; i++)
			N[i] = -1;
		for (int i = 1; i <= turnLimit; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (!Union(start, end)) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}
}
