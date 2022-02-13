import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 1759번 : 암호 만들기
 * - 앞문제에 비해 쉽게 풀었음(일차원 조합)
 */
public class BOJ1759 {
	static int L, C;
	static String[] alpha, result;
	static StringBuilder sb = new StringBuilder();
	//조합
	//모음의 개수 1개 이상 L-2개 이하(자음이 2개 이상이기 때문)
	public static void combination(int start, int cnt, int v) {
		if(cnt == L) {
			if(1 <= v && v <= L-2) {
				for(String s : result)
					sb.append(s);
				sb.append("\n");
			}
			return;
		}
		//재귀
		for(int i=start; i<C; i++) {
			result[cnt] = alpha[i];
			if("aeiou".contains(alpha[i]))//모음이면 v+1
				combination(i+1, cnt+1, v+1);
			else//모음이 아니면 v
				combination(i+1, cnt+1, v);
		}
	}

	//모음 1개이상(a, e, i, o, u), 자음 2개이상
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		C = sc.nextInt();
		sc.nextLine();//Enter
		alpha = sc.nextLine().split(" ");
		result = new String[L];
		
		//오름차순 정렬(증가해야 되니까)
		Arrays.sort(alpha);
		
		combination(0, 0, 0);//시작idx, 선택한 개수, 모음 개수
		System.out.println(sb.toString());
	}
}
