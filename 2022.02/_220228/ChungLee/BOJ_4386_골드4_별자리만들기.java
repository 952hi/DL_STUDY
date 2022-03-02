package _220228.ChungLee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4386_골드4_별자리만들기 {

	static toY pointer[] = null;
	static toY sorted[] = null;

	static void qs(int i, int j) {
		if (i >= j)
			return;
		int left = i;
		int right = j;
		float mid = (pointer[i].dist + pointer[j].dist) / 2;

		while (left <= right) {
			while (pointer[left].dist <= mid)
				left++;
			while (pointer[right].dist >= mid)
				right--;
			if (left <= right) {
				toY tmp = pointer[left];
				pointer[left] = pointer[right];
				pointer[right] = tmp;
				left++;
				right--;
			}
		}
		qs(i, right);
		qs(left, j);
	}

	static void Merge(int left, int mid, int right) {
		int LLeft, RLeft, sortedListLeft;
		LLeft = left;
		RLeft = mid + 1;
		sortedListLeft = left;

		// 분할 정렬된 리스트 다시 합병
		while (LLeft <= mid && RLeft <= right) {
			if (pointer[LLeft].dist <= pointer[RLeft].dist) {
				sorted[sortedListLeft++] = pointer[LLeft++];
			} else {
				sorted[sortedListLeft++] = pointer[RLeft++];
			}
		}
		// 남아있는 왼쪽 값을 전부 붙여두기
		if (LLeft > mid) {
			for (int l = RLeft; l <= right; l++) {
				sorted[sortedListLeft++] = pointer[l];
			}
		}
		// 남아있는 오른쪽 값을 전부 붙여두기
		else {
			for (int l = LLeft; l <= mid; l++) {
				sorted[sortedListLeft++] = pointer[l];
			}
		}
		// 복사
		for (int l = left; l <= right; l++) {
			pointer[l] = sorted[l];
		}

	}

	static void ms(int i, int j) {
		int mid;
		if (pointer[i].dist < pointer[j].dist) {
			mid = (i + j) / 2;
			ms(i, mid);
			ms(mid + 1, j);
			Merge(i, mid, j);
		}
	}

	static class toY {
		int from;
		int to;
		float dist;

		public toY(int from, int to, float dist) {
			this.from = from;
			this.to = to;
			this.dist = dist;
		}
	}

	static boolean Union(int a, int b) {
		a = FindSet(a);
		b = FindSet(b);
		if (a == b)
			return false;
		if (N[b] > N[a]) {
			N[a] += N[b];
			N[b] = a;
		} else {
			N[b] += N[a];
			N[a] = b;
		}
		return true;
	}

	static int FindSet(int a) {
		if (N[a] < 0)
			return a;
		return N[a] = FindSet(N[a]);
	}

	static int N[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int star = Integer.parseInt(br.readLine());
		pointer = new toY[star * (star - 1) / 2];
		sorted = new toY[star * (star - 1) / 2];
		float xy[][] = new float[star][2];
		N = new int[star];
		for (int i = 0; i < star; i++)
			N[i] = -1;

		for (int i = 0; i < star; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			float x = Float.parseFloat(st.nextToken());
			float y = Float.parseFloat(st.nextToken());
			xy[i][0] = x;
			xy[i][1] = y;
		}
		int next = 0;
		for (int i = 0; i < star; i++) {
			for (int j = i + 1; j < star; j++) {
				pointer[next] = new toY(i, j, (float) Math
						.sqrt(Math.pow(Math.abs(xy[i][0] - xy[j][0]), 2) + Math.pow(Math.abs(xy[i][1] - xy[j][1]), 2)));
				next++;
			}
		}
		// qs(0, pointer.length - 1);
		ms(0, pointer.length - 1);
		int cnt = 0;
		float weight = 0;
		for (int i = 0; i < pointer.length; i++) {
			if (cnt == star - 1)
				break;
			if (Union(pointer[i].from, pointer[i].to)) {
				cnt++;
				weight += pointer[i].dist;
			}
		}
		System.out.printf("%.2f", weight);
	}

}
