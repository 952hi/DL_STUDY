package _220221.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983_G4_사냥꾼_another {
	static class aniPos implements Comparable<aniPos> {
		int x;
		int y;

		public aniPos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(aniPos o) {
			if (this.x != o.x)
				return this.x - o.x;
			else
				return o.y - this.y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int gs = Integer.parseInt(st.nextToken());
		int[] gsPos = new int[gs];

		int animal = Integer.parseInt(st.nextToken());

		aniPos[] aniPos = new aniPos[animal];

		int range = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < gs; i++) {
			gsPos[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(gsPos);

		int anicnt = 0;
		for (int i = 0; i < animal; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (y > range)
				continue;
			aniPos[anicnt] = new aniPos(x, y);
			anicnt++;
		}
		aniPos[] nAniPos = Arrays.copyOf(aniPos, anicnt);
		Arrays.sort(nAniPos);
		int answer = 0;
		for (int i = 0; i < anicnt; i++) {
			int canShootMin = nAniPos[i].x - (range - nAniPos[i].y);
			int canShootMax = nAniPos[i].x + range - nAniPos[i].y;

			int low = 0, high = gsPos.length - 1;
			while (low <= high) {
				int mid = (low + high) / 2;
				if (canShootMin <= gsPos[mid] && gsPos[mid] <= canShootMax) {
					answer++;
					break;
				} else if (gsPos[mid] < canShootMax) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
		}
		System.out.println(answer);
	}
}