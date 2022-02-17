import java.util.Arrays;
import java.util.Scanner;

/*
 * 	296ms 27308kb
1월에 SWEA 신도시연결문제와 매우 유사했습니다.
이번에는 집중국수가 센서보다 클때를 종료해줬습니다.
*/
public class boj2212 {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		if(k>=n) System.out.println(0);
		else {
			int temp[] = new int[n];
			int comp[] = new int[n-1];
			for(int i =0;i<n;i++) {
				temp[i] = sc.nextInt();
			}
			
			Arrays.sort(temp);
			for(int j=1;j<n;j++) {
				comp[j-1]=temp[j]-temp[j-1];
			}
			Arrays.sort(comp);
			int sum =0;
			for(int l=0;l<n-k;l++) {
				sum += comp[l];
			}
			System.out.println(sum);
			sc.close();
		}
		
	}

}
