package _1023.LC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_G4_운동 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int[][] bd = new int[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
			Arrays.fill(bd[i], Integer.MAX_VALUE);
		}
		boolean[] visit = new boolean[V + 1];
		int s, e, w;
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			bd[s][e] = w;
		}
		for (int k = 1; k <= V; k++) {
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					if (bd[i][k] == Integer.MAX_VALUE || bd[k][j] == Integer.MAX_VALUE)
						continue;
					if (bd[i][k] + bd[k][j] < bd[i][j])
						bd[i][j] = bd[i][k] + bd[k][j];
				}
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			if (bd[i][i] != -1)
				answer = Math.min(answer, bd[i][i]);
		}
		if (answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
}
