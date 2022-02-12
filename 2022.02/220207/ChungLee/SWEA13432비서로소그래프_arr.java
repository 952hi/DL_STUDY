

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;

public class SWEA13432비서로소그래프_arr {
	static long strt = 0;
	static long end = 0;
	static long size = 0;
	static StringBuilder sb = new StringBuilder();

	static long soinsu(long num) {
		if (num % 2 == 0)
			return 2;

		else {
			for (long j = 3; j <= Math.floor(Math.sqrt(num)); j += 2) {
				// 가장 작은 소인수 반환
				if (num % j == 0) {
					return j;
				}
			}
			// 소수 반환
			return num;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long TC = Integer.parseInt(br.readLine());
		
		// 이 문제는 1과 나머지 각각의 양의 정수를 서로소로 보고 있음. 1과 나머지 숫자 사이에 간선 존재하지 않음
		for (long i = 1; i <= TC; i++) {
			sb.append("#" + i + " ");
			String[] ol = br.readLine().split(" ");
			
			long size = Integer.parseInt(ol[0]);
			strt = Integer.parseInt(ol[1]);
			end = Integer.parseInt(ol[2]);
			
			long soinsuStrt = soinsu(strt);
			long soinsuEnd = soinsu(end);
			
			BigInteger b1 = BigInteger.valueOf(strt);
			BigInteger b2 = BigInteger.valueOf(end);

			if (strt == end) {
				sb.append(0 + "\n");
				continue;
			}

			else if (strt == 1 || 1 == end) {
				sb.append(-1 + "\n");
				continue;
			}

			else if (b1.gcd(b2).longValue() != 1) {
				sb.append(1 + "\n");
				continue;
			}
			//4 7     2   x 7 = 14   
			//10억  
			else if (soinsuStrt * soinsuEnd <= size) {
				sb.append(2 + "\n");
				continue;
			}
			else {
				//   34   3 => 6  17 => 34          3 => 6 => 34 => 17          33    18    11      
				if ((Math.max(soinsuStrt, soinsuEnd) * 2) <= size) {
					sb.append(3 + "\n");
					continue;
				}

				else {
					sb.append(-1 + "\n");
					continue;
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
