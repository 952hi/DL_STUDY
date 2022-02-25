package _20220224;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
 * 백준 9253번 : 스페셜 저지
 * 시간 : 208ms
 */

public class Boj9253_specialJudge {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		String R = br.readLine();//사용자의 답
		
		//부분 일치 함수
		int size = R.length();
		int[] patt = new int[size];
		for(int i=1, j=0;i<size;i++) {//첫번째 문자는 건너뜀
			if(R.charAt(i)!=R.charAt(j)) {//다르면 같은 문자 개수 0으로!
				j=0;
				continue;
			}
			j++;
			patt[i] = j;//j개 일치중임
		}
		//
		
		if(compare(A, R, patt) && compare(B, R, patt))
			System.out.println("YES");
		else
			System.out.println("NO");
	}
	
	public static boolean compare(String s, String a, int[] patt) {
		boolean result = false;
		int size = s.length();
		for(int i=0, j=0; i<size; i++) {
			//다르면 j위치 이동
			if(s.charAt(i) != a.charAt(j)) {
				if(j!=0) i--;//i유지
				j = patt[Math.max(j-1, 0)];
				continue;
			}
			//같으면 다음 값 비교
			j++;
			//끝까지 비교 = 일치하는 문자열을 찾음 => 종료
			if(j==a.length()) {
				result = true;
				break;
			}
		}
		return result;
	}

}
