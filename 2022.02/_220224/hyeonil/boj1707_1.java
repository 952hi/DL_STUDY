import java.io.*;
import java.util.*;

public class boj1707_2 {
	static int map[][],n,cnt[];
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
			map=new int[n+1][n+1];
			v = new boolean[n+1];
			cnt = new int[n+1];
			Arrays.fill(cnt, 3);
			int from,to;
			for(int i=0;i<m;i++) {
				stz = new StringTokenizer(br.readLine()," ");
				from = Integer.parseInt(stz.nextToken());
				to = Integer.parseInt(stz.nextToken());
				map[from][to] = 1;
				map[to][from] = 1;
			}
			bfs(1);
			for(int i=1;i<n+1;i++) {
				for(int j=1;j<n+1;j++) {
					if(map[i][j] != 0 && cnt[i] == cnt[j]) {
						sb.append("No").append("\n");
						continue out;
					}
				}
			}
			System.out.println(Arrays.toString(cnt));
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
			for(int k=1;k<n+1;k++) {
				if(!v[k] && map[temp][k]!=0) {
					v[k]=true;
					cnt[k]=(cnt[temp]+1)%2;
					q.offer(k);
				}
			}
		}
	}
}