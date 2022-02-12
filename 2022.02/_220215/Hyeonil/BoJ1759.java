import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int M;
	public static boolean checked[];
	public static String[] temp;
	public static int digit=0;
	public static int other=0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(stz.nextToken());
		M = Integer.parseInt(stz.nextToken());
		checked = new boolean[M];
		temp = br.readLine().split(" ");
		Arrays.sort(temp);
		combi(0,0);
		
	}
	public static void combi(int idx,int cnt) {
		if(idx==M) {
			if(cnt == N) {
				for(int i=0;i<M;i++) {
					if(checked[i]) {
						if(temp[i].equals("a")||temp[i].equals("e")||temp[i].equals("i")||temp[i].equals("o")||temp[i].equals("u")) {
							digit+=1;
						}else {
							other += 1;
						}
					}
				}
				if(digit>=1 && other >=2) {
					for(int j=0;j<M;j++) {
						if(checked[j])System.out.print(temp[j]);
					}
					System.out.println();
				}
				digit +=1;
			}
			other =0;
			digit =0;
			return;
		}
		
		checked[idx] = true;
		combi(idx+1,cnt+1);
		
		checked[idx] = false;
		combi(idx+1,cnt);
	}
}
