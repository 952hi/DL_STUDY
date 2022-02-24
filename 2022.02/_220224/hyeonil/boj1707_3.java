import java.io.*;
import java.util.*;
public class boj1707_3 {
	// 287224kb	1096ms
	static int n,cnt[];
	static List<Integer>[] list;
	static StringBuilder sb =new StringBuilder();
	static boolean v[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stz;
		int tc = Integer.parseInt(br.readLine());
		for(int t=0;t<tc;t++) {
			stz = new StringTokenizer(br.readLine()," ");
			n=Integer.parseInt(stz.nextToken());
			int m=Integer.parseInt(stz.nextToken());
			list = new ArrayList[n+1];
			for(int i=1;i<n+1;i++) list[i] = new ArrayList<>();
			v = new boolean[n+1];
			cnt = new int[n+1];
			int from,to;
			for(int i=0;i<m;i++) {
				stz = new StringTokenizer(br.readLine()," ");
				from = Integer.parseInt(stz.nextToken());
				to = Integer.parseInt(stz.nextToken());
				list[from].add(to);
				list[to].add(from);
			}
			bfs();
		}
		sb.setLength(sb.length()-1);
		bw.write(sb.toString());
		bw.flush();
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		int curr;
		// 나눠진 그래프 일경우 탐색하기위해 반복문 
		for(int i=1;i<n+1;i++) {
			if(!v[i]) {
				v[i] = true;
				q.offer(i);
				cnt[i] = 1;
			}else {
				continue;
			}
			while(!q.isEmpty()) {
				curr = q.poll();
				for(int j=0,size=list[curr].size();j<size;j++) {
					if(!v[list[curr].get(j)] && cnt[list[curr].get(j)]==0) {
						q.offer(list[curr].get(j));
					}
					// 같다면 이분 그래프 X
					if(cnt[curr] == cnt[list[curr].get(j)]) {
						sb.append("NO").append("\n");
						return;
					}
					// 각각 1,2 넣어줌
					if(cnt[curr] == 1 && cnt[list[curr].get(j)] == 0 && !v[list[curr].get(j)]) {
						v[list[curr].get(j)] = true;
						cnt[list[curr].get(j)] = 2;
					}else if(!v[list[curr].get(j)] && cnt[curr] == 2 && cnt[list[curr].get(j)] == 0 ) {
						cnt[list[curr].get(j)] = 1;
						v[list[curr].get(j)] = true;
					}
				}
			}
		}
		sb.append("YES").append("\n");
	}
}