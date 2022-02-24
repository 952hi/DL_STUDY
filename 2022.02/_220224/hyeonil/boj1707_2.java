import java.io.*;
import java.util.*;
public class boj1707_3 {
	static int n,cnt[];
	static List<Integer>[] list;
	static boolean v[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb =new StringBuilder();
		StringTokenizer stz;
		int tc = Integer.parseInt(br.readLine());
out:	for(int t=0;t<tc;t++) {
			stz = new StringTokenizer(br.readLine()," ");
			n=Integer.parseInt(stz.nextToken());
			int m=Integer.parseInt(stz.nextToken());
			list = new ArrayList[n+1];
			for(int i=1;i<n+1;i++) list[i] = new ArrayList<>();
			v = new boolean[n+1];
			cnt = new int[n+1];
			Arrays.fill(cnt, 3);
			int from,to;
			for(int i=0;i<m;i++) {
				stz = new StringTokenizer(br.readLine()," ");
				from = Integer.parseInt(stz.nextToken());
				to = Integer.parseInt(stz.nextToken());
				list[from].add(to);
				list[to].add(from);
			}
			bfs(1);
			for(int i=1;i<n+1;i++) {
				for(int j=1,size=list[i].size();j<size;j++) {
					if(cnt[i] == cnt[j]) {
						sb.append("No").append("\n");
						continue out;
					}
				}
			}
			sb.append("YES").append("\n");
		}
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
	}
	private static void bfs(int x) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(x);
		v[x] = true;
		cnt[x] = 0;
		int temp;
		while(!q.isEmpty()) {
			temp = q.poll();
			for(int i=0,size=list[temp].size();i<size;i++) {
				if(!v[list[temp].get(i)]) {
					v[list[temp].get(i)]=true;
					cnt[list[temp].get(i)]=(cnt[temp]+1)%2;
					q.offer(list[temp].get(i));
				}
			}
		}
	}
}