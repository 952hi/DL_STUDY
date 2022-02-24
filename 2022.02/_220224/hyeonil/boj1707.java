import java.io.*;
import java.util.*;
public class boj1717 {
	static int[] p;
	public static void main(String[] args) throws IOException {
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer stz = new StringTokenizer(br.readLine()," ");
		int n = Integer.parseInt(stz.nextToken());
		int m = Integer.parseInt(stz.nextToken());
		p = new int[n+1];
		int a,b,c;
		for(int i=0;i<n+1;i++) p[i]=i;
		for(int i=0;i<m;i++) {
			stz = new StringTokenizer(br.readLine()," ");
			a=Integer.parseInt(stz.nextToken());
			b=Integer.parseInt(stz.nextToken());
			c=Integer.parseInt(stz.nextToken());
			if(a == 0) union(b, c);
			else if(a==1) {
				if(find(b)==find(c)) sb.append("YES").append("\n");
				else sb.append("NO").append("\n");
			}
		}
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
	}
	static int find(int x) {
		if(x==p[x]) return x;
		return p[x] = find(p[x]);
	}
	static void union(int x,int y) {
		x = find(x);
		y = find(y);
		if(x == y) return;
		if(x>y) p[x] =y;
		else p[y] = x;
		return;
	}
}