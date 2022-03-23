import java.io.*;
public class boj1300 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int k = Integer.parseInt(in.readLine());
		int left=1,right=k;
		//10 의 9승 이라 고민 근데 2의 32이 맥스값이라 10의 9승까지는 커버가능함
		int mid;
		int check;
		int result = 0;
		while(left<=right) {
			mid = (left+right)/2;
			check =0;
			for(int i=1;i<=n;i++) check += Math.min(n, mid/i);
			if(check >= k) {
				right = mid -1;
				result = mid;
			}else{
				left = mid +1;
			}
		}
		System.out.println(result);
	}
}