import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class SWEA13432비서로소그래프resolve {
	static int[] checkNum;
	static int strt = 0;
	static int end = 0;
	static int size = 0;
	static Queue<Integer> q;
	static StringBuilder sb = new StringBuilder();
	static int answer[] = new int[6];
	static Set<Integer> soinsu(int num) {
		Set<Integer> set = new HashSet<>();
		int k = 2;

		while (num != 1) {
			if (num % k == 0) {
				set.add(k);
				num /= k;
				continue;
			}
			k++;
		}
		return set;
	}

	static int bfs() {
		// 값에 대한 소인수분해 결과가 저장

		while (!q.isEmpty()) {
			int crnt = q.poll();
			Set<Integer> soinsus = soinsu(crnt);
			Iterator<Integer> iter = soinsus.iterator();
			while (iter.hasNext()) {
				int num = iter.next();
				if(checkNum[num] != 0) {
					continue;
				}
				int cnt = 1;
				int mul = num * cnt;
				while (mul <= size) {

					if (checkNum[mul] == 0) {
						checkNum[mul] = checkNum[crnt] + 1;
						q.add(mul);
						if (mul == end) {
							System.out.println(checkNum[mul]-1+", "+size+", "+strt+", "+end);
							if(checkNum[mul]-1 == - 1) {
								answer[0]++;
							}
							else if(checkNum[mul]-1 == 0) {
								answer[1]++;
							}
							else if(checkNum[mul]-1 == 1) {
								answer[2]++;
							}
							else if(checkNum[mul]-1 == 2) {
								
								answer[3]++;
							}
							else if(checkNum[mul]-1 == 3) {
								answer[4]++;
								System.out.println();
							}
							else  {
								answer[5]++;
							}
							return checkNum[mul]-1;
						}
					}
					cnt++;
					mul = num * cnt;
				}
			}
		}
		System.out.println("-1");
		return 0;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Random rand = new Random();
		
		//int TC = Integer.parseInt(br.readLine());
		
		// 이 문제는 1과 나머지 각각의 양의 정수를 서로소로 보고 있음. 1과 나머지 숫자 사이에 간선 존재하지 않음
		for (int i = 0; i < 1000; i++) {
			size = rand.nextInt(1000)+1;
			strt = rand.nextInt(size)+1;
			end = rand.nextInt(size)+1;
			if(i % 100 == 0)
				System.out.println(i);
			System.out.print("#"+(i+1)+" ");
			//String[] ol = br.readLine().split(" ");
			//size = Integer.parseInt(ol[0]);
			//strt = Integer.parseInt(ol[1]);
			//end = Integer.parseInt(ol[2]);
			checkNum = new int[size + 1];
			q = new LinkedList<>();
			if (strt == end) {
				System.out.println("0");
				continue;
			} else {
				q.add(strt);
				int returned = bfs();
			}
		}
		// System.out.println("프로그램 종료");
		for(int i = 0; i<7;i++) {
			if(i == 0)
			System.out.println("-1 :"+answer[i]);
			
			if(i == 1)
				System.out.println("0 :"+answer[i]);
			
			if(i == 2)
				System.out.println("1 :"+answer[i]);
			
			if(i == 3)
				System.out.println("2 :"+answer[i]);
			
			if(i == 4)
				System.out.println("3 :"+answer[i]);
			
			if(i == 5)
				System.out.println("else :"+answer[i]);
			
		}
	}
}
