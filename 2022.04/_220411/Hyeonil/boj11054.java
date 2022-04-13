import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class boj11054 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer stz = new StringTokenizer(br.readLine());
		int[] temp = new int[n];
		int[] dpFront = new int[n];
		int[] dpBack = new int[n];
		for(int i=0;i<n;i++) {
			temp[i] = Integer.parseInt(stz.nextToken());
		}
		for(int i=0;i<n;i++) {
			dpFront[i] = 1;
			dpBack[n-i-1] = 1;
			for(int j=0;j<i;j++) {
				if(temp[j]<temp[i] && dpFront[i]<dpFront[j]+1) {
					dpFront[i] = dpFront[j] +1;
				}
			}
			for(int j=n-1;j>n-1-i;j--) {
				if(temp[j]<temp[n-1-i] && dpBack[n-1-i]<dpBack[j]+1) {
					dpBack[n-1-i] = dpBack[j]+1;
				}
			}
		}
		int max=0;
		for(int i=0;i<n;i++) {
			max = Math.max(max, dpBack[i]+dpFront[i]);
		}
		System.out.println(max-1);
	}
}