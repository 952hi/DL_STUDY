import java.io.*;
import java.util.*;
public class boj17404 {
	static int res=Integer.MAX_VALUE,N,comp[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		N = Integer.parseInt(br.readLine());
		comp = new int[N][3];
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<3;j++) comp[i][j] = Integer.parseInt(stz.nextToken());
		}
		Exceptcolor(0);
		Exceptcolor(1);
		Exceptcolor(2);
		System.out.println(res);
	}
	private static void Exceptcolor(int idx) {
		int temp[][] = new int[N][3];
		for(int i=0;i<N;i++) temp[i] = Arrays.copyOf(comp[i], 3);
		temp[1][0] = temp[1][0] +temp[0][idx];
		temp[1][1] = temp[1][1] +temp[0][idx];
		temp[1][2] = temp[1][2] +temp[0][idx];
		temp[1][idx] = Integer.MAX_VALUE;
		
		for(int i=2;i<N;i++) {
			temp[i][0] = temp[i][0] + Math.min(temp[i-1][1], temp[i-1][2]);
			temp[i][1] = temp[i][1] + Math.min(temp[i-1][0], temp[i-1][2]);
			temp[i][2] = temp[i][2] + Math.min(temp[i-1][0], temp[i-1][1]);
		}
		temp[N-1][idx] = Integer.MAX_VALUE;
		
		int cnt=Integer.MAX_VALUE;
		for(int i=0;i<3;i++) {
			if(cnt>temp[N-1][i])cnt = temp[N-1][i];		
		}
		res = Math.min(cnt, res);
	}
}