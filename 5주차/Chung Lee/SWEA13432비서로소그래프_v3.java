import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SWEA13432비서로고그래프final {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(br.readLine());

		for (int i = 0; i < TC; i++) {
			String[] ol = br.readLine().split(" ");
			int size = Integer.parseInt(ol[0]);
			int strt = Integer.parseInt(ol[1]);
			int end = Integer.parseInt(ol[2]);

			// 두 수가 같을 때
			if (strt == end) {
				System.out.println("#" + (i + 1) + " 0");
				continue;
			}
			// 둘 중 하나가 1일 경우
			else if (strt == 1 || end == 1) {
				System.out.println("#" + (i + 1) + " -1");
				continue;
			}

			Map<Integer, Boolean> strtSmall = new HashMap<>();

			int k = 2;
			int smallestStrt = 0;
			int state = 0;
			int tmpStrt = strt;
			while (tmpStrt != 1) {

				if (tmpStrt % k == 0) {
					if (state == 0) {
						smallestStrt = k;
						state = 1;
					}

					strtSmall.put(k, true);
					tmpStrt /= k;
					continue;
				}
				k++;
			}

			Map<Integer, Boolean> endSmall = new HashMap<>();

			k = 2;
			state = 0;
			int smallestEnd = 0;
			int tmpEnd = end;
			while (tmpEnd != 1) {
				if (tmpEnd % k == 0) {
					if (state == 0) {
						smallestEnd = k;
						state = 1;
					}
					endSmall.put(k, true);
					tmpEnd /= k;
					continue;
				}
				k++;
			}
			boolean isFirst = true;
			boolean isNext = false;
			
			//둘 다 소수일 때
			
			if(smallestEnd * smallestStrt > size) {
				System.out.println("#" + (i + 1) + " -1");
				continue;
			}
			if(isFirst  == true && smallestEnd == end && smallestStrt == strt) {
				//둘의 곱이 사이즈보다 작으면 2칸만에 도착 가능
				if(size >= smallestEnd * smallestStrt ) {
					System.out.println("#" + (i + 1) + " 2");
					continue;
				}
				else if(Math.max(smallestEnd, smallestStrt)*2 <=size){
					System.out.println("#" + (i + 1) + " 3");
					continue;
				}
			}
			
			for (int Key : strtSmall.keySet()) {
				// 소인수 값 중 겹치는 수가 있을 경우
				if (endSmall.keySet().contains(Key)) {
					System.out.println("#" + (i + 1) + " 1");
					isNext = true;
					break;
				}
			}
			if(isNext == true)
				continue;
				System.out.println("#" + (i + 1) + " 2");
		}
	}
}
