import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class boj1707 {
	static int p[],n,m;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stz;
		int tc = Integer.parseInt(br.readLine());
		for(int t=0;t<tc;t++) {
			stz = new StringTokenizer(br.readLine()," ");
			n = Integer.parseInt(stz.nextToken());
			m = Integer.parseInt(stz.nextToken());
			p = new int[n+1];
			for(int i=1;i<n+1;i++) p[i] = i;
			for(int i=0;i<m;i++) {
				stz = new StringTokenizer(br.readLine()," ");
				union(Integer.parseInt(stz.nextToken()),Integer.parseInt(stz.nextToken()));
			}
			System.out.println(Arrays.toString(p));
		}
	}
	
	static int find(int x) {
		if(x==p[x]) return x;
		return p[x] = find(p[x]);
	}
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if(x==y) return;
		if(x<y) p[y] = x;
		else p[x] = y;
		return;
	}
}
