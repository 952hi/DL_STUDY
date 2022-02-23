package _220221.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class BOJ_8983_G4_사냥꾼 {
	static class aniPos implements Comparable<aniPos> {
		long x;
		long y;

		public aniPos(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(aniPos o) {
			if (this.x != o.x)
				return (int) (this.x - o.x);
			else
				return (int) (o.y - this.y);
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
		int crntAniPos = 0;
		int gsPosCnt = 0;
		boolean isFisrtOver = true;
		int isFisrtOverAni = 0;
		for (int i = 0; i < nAniPos.length; i++) {
			// 동물이 어디있든 사대의 사정거리 안일 때
			if (Math.max(nAniPos[i].x, gsPos[gsPosCnt]) - Math.min(nAniPos[i].x, gsPos[gsPosCnt])
					+ nAniPos[i].y <= range) {
				answer++;
			}
			// 동물이 사대 오른쪽 위치
			else if (nAniPos[i].x - gsPos[gsPosCnt] > 0) {
				// 가장 처음으로 사대 오른쪽 위치한 동물위치 저장
				if (isFisrtOver == true) {
					isFisrtOver = false;
					isFisrtOverAni = i;
				}
				// 동물이 다음 사대의 범위안에는 들어갈 때 사대 증가, 동물 재확인
				if (gsPosCnt + 1 < gs && Math.abs(nAniPos[i].x - gsPos[gsPosCnt + 1]) + nAniPos[i].y <= range) {
					isFisrtOver = true;
					gsPosCnt++;
					i--;
				}
				// 만약 동물x와 사대 좌표 뺀게 사정거리 밖이거나 동물이 마지막 동물일 때
				else if (gsPosCnt + 1 < gs && (nAniPos[i].x - gsPos[gsPosCnt] >= range || i == nAniPos.length - 1)) {
					isFisrtOver = true;
					i = isFisrtOverAni - 1;
					gsPosCnt++;
				} else
					continue;
			}

		}
		System.out.println(answer);
	}
}