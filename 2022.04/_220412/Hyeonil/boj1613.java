import java.io.*;
import java.util.*;
public class boj1613 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stz = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(stz.nextToken());
		int m = Integer.parseInt(stz.nextToken());
		int map[][] = new int[n+1][n+1];
		int a,b;
		for(int i=0;i<m;i++) {
			stz = new StringTokenizer(br.readLine());
			a = Integer.parseInt(stz.nextToken());
			b = Integer.parseInt(stz.nextToken());
			map[a][b] = 1;
		}
		
		for (int k = 1; k <= n; k++) { 
			for (int i = 1; i <= n; i++) { 
				if(i==k || map[i][k] == 0) continue;
				for (int j = 1; j <=n ; j++) { 
					if(map[i][j]==1) continue;
					if(map[k][j]==1) {
						map[i][j] = 1;
					}
				}
			}
		}
		int k = Integer.parseInt(br.readLine());
		for(int i=0;i<k;i++) {
			stz = new StringTokenizer(br.readLine());
			a = Integer.parseInt(stz.nextToken());
			b = Integer.parseInt(stz.nextToken());
			if(map[a][b]==1) sb.append(-1).append("\n");
			else if(map[b][a]==1) sb.append(1).append("\n");
			else if(map[a][b]==0 && map[b][a]==0)sb.append(0).append("\n");
		}
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
	}
}