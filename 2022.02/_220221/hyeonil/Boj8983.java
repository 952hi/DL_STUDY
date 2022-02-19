import java.io.*;
import java.util.*;
//boj8983 41점 1회차
public class Boj8983 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stz = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());
		int L = Integer.parseInt(stz.nextToken());
		int res =0;
		stz = new StringTokenizer(br.readLine()," ");
		Data []gun = new Data[M];
		List<Data> list = new LinkedList<Data>();
		
		for(int i=0;i<M;i++) gun[i] = new Data(Integer.parseInt(stz.nextToken()), 0);
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine()," ");
			list.add(new Data(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken())));
		}
		for(int i=0;i<M;i++) {
			for(int j=0,size=list.size();j<size;j++) {
				if((Math.abs(gun[i].x-list.get(j).x)+list.get(j).y)<=L) {
					list.remove(j);
					res++;
					j--;
					size-=1;
				}
			}
		}
		sb.append(res);
		bw.write(sb.toString());
		bw.flush();
	}
}
class Data{
	int x;
	int y;
	public Data(int x,int y) {
		this.x = x;
		this.y = y;
	}
}