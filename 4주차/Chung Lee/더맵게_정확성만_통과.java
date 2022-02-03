package programmers.problem.더맵게;

import java.util.Arrays;
import java.util.Scanner;

public class 더맵게_정확성만_통과 {
	// 힙 알고리즘은 최상위 노드를 가장 마지막 노드에 넣고 고정시킬 때마다 돌려줘야 한다.
	public static int[] newRange = null;

	public static int[] heapify(int[] scoville, int nextNode) {
		// 계산 기준이 됨
		int last = scoville.length - 1 - nextNode;
		int firstCalcNode = last;

		// 첫번째 검사할 노드가 오른쪽 노드라면 last를 하나 빼줌
		if (last % 2 == 1) {
			last--;
		}

		while (last > 1) {
			// 첫번째 노드이고 형제가 없을 때
			if (firstCalcNode == last && last % 2 == 0) {
				// 자식이 더 작을 때
				if (scoville[last] < scoville[last / 2]) {
					int tmp = scoville[last / 2];
					scoville[last / 2] = scoville[last];
					scoville[last] = tmp;
				}
				last -= 2;
				continue;
			}

			else if (scoville[last / 2] > Math.min(scoville[last], scoville[last + 1])) {
				int tmp = scoville[last / 2];
				if (scoville[last] >= scoville[last + 1]) {
					scoville[last / 2] = scoville[last + 1];
					scoville[last + 1] = tmp;
				} else {
					scoville[last / 2] = scoville[last];
					scoville[last] = tmp;
				}
			}
			last -= 2;
		}
		
		return scoville;
	}

	public static void heapSort() {
		if(newRange.length - 1 < 2)
			return;
		for (int i = 0; i < 2; i++) {
			newRange = heapify(newRange, i);
			int tmp = newRange[newRange.length - 1 - i];
			newRange[newRange.length - 1 - i] = newRange[1];
			newRange[1] = tmp;
		}
		// System.out.println(Arrays.toString(newRange));
	}

	public static int solution(int[] scoville, int K) {
		newRange = new int[scoville.length + 1];
		int nRLength = scoville.length;
		
		System.arraycopy(scoville, 0, newRange, 1, scoville.length);
		newRange[0] = -1;
		heapSort();
		int cnt = 0;
		while (newRange[newRange.length - 1] < K && newRange.length - 1 > 1) {
			cnt++;
			newRange[newRange.length - 2] = newRange[newRange.length - 2] * 2 + newRange[newRange.length - 1];
			newRange = Arrays.copyOf(newRange, newRange.length - 1);
			heapSort();
		}
		if (newRange[newRange.length - 1] >= K) {
			System.out.println(cnt);
			return cnt;
		} else {
			System.out.println(-1);
			return -1;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		int[] inputArr = new int[input];
		for (int i = 0; i < input; i++) {
			inputArr[i] = sc.nextInt();
		}
		int K = sc.nextInt();
		solution(inputArr, K);
	}
}
