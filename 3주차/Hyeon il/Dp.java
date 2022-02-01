import java.util.Arrays;
// 자바로 디피 구현하기가 굉장히 어려웟다.

public class Dp {
	public int solution(int N, int number) {
		String Nstring = String.valueOf(N);
		int[][] digit = new int[8][];
		digit[0] = new int[] {N};
		if(N==number) return 1;
		
		Nstring = Nstring + Nstring;
		digit[1] = new int[] {N*N,N+N,N-N,N/N,Integer.parseInt(Nstring)};
		
		for(int test:digit[1]) {
			if(number==test) return 2;
		}

		
		for(int i=2;i<digit.length;i++) {
			StringBuffer one = new StringBuffer("");
				for(int k =0;k<digit[i-1].length;k++) {
					one.append(digit[i-1][k]+N);
					one.append(" ");
					one.append(digit[i-1][k]-N);
					one.append(" ");
					one.append(digit[i-1][k]*N);
					one.append(" ");
					one.append(digit[i-1][k]/N);
					one.append(" ");
			}
			Nstring = Nstring + String.valueOf(N);
			one.append(Nstring);
			digit[i] = Arrays.stream(one.toString().split(" ")).mapToInt(Integer::parseInt).toArray();
			for(int test=0;test<digit[i].length;test++) {
				if(digit[i][test]==number) {
					return i+1;
				}
			}
		}
		return -1;
   }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dp dp = new Dp();
		
		//입력예시 5 12 /// 2 11 /// 5 31168
		System.out.println(dp.solution(5, 31168));
	}

}
