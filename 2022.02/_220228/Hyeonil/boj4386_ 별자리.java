import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
// 12432 KB 100ms
public class boj4386 {
	static int p[];
	static class Data implements Comparable<Data>{
		int from;
		int to;
		double wei;
		public Data(int from, int to, double wei) {
			super();
			this.from = from;
			this.to = to;
			this.wei = wei;
		}
		@Override
		public int compareTo(Data o) {
			return Double.compare(this.wei, o.wei);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer stz;
		double map[][] = new double[n][2];
		for(int i=0;i<n;i++) {
			stz = new StringTokenizer(br.readLine()," ");
			map[i] = new double[]{Double.parseDouble(stz.nextToken()),Double.parseDouble(stz.nextToken())};
		}
		int edge = (n*(n-1))/2;
		Data data[] = new Data[edge];
		int idx=0;
		for(int i=0;i<n-1;i++) {
			for(int j=i+1;j<n;j++) {
				data[idx++] = new Data(i, j, dist(map[i][0], map[i][1], map[j][0], map[j][1]));
			}
		}
		Arrays.sort(data);
		p = new int[n];
		for(int i=0;i<n;i++) p[i] = i;
		int check=0;
		double sum = 0;
		for(Data comp:data) {
			if(findp(comp.from)==findp(comp.to)) continue;
			unionp(comp.from, comp.to);
			check++;
			sum += comp.wei;
			if(check == n-1) break;
		}
		System.out.printf("%.2f",sum);
	}
	static double dist(double x,double y,double x2,double y2) {
		return Math.sqrt(Math.pow((x-x2), 2)+Math.pow((y-y2), 2));
	}
	static int findp(int x) {
		if(p[x]==x) return x;
		else return p[x] = findp(p[x]);
	}
	static void unionp(int x,int y) {
		x = findp(x);
		y = findp(y);
		if(x==y) return;
		if(x>y) p[x] =y;
		else p[y] = x;
		return;
	}
}
