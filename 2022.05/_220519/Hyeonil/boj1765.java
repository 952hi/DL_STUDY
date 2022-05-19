import java.io.*;
import java.util.*;
public class boj1765 {
	static int parent[],n,map[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stz;
		
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		int res[] = new int[n+1];
		parent = new int[n+1];
		for(int i=1;i<n+1;i++) parent[i] = i; // 부모배열 초기화
		
		int s,e;
		String type;
		for(int i=0;i<m;i++) {
			stz = new StringTokenizer(br.readLine());
			type = stz.nextToken();
			s = Integer.parseInt(stz.nextToken());
			e = Integer.parseInt(stz.nextToken());
			if(type.equals("F")) {
				map[s][e] = 2;
				map[e][s] = 2;
				uparent(s, e);// 친구라면 그룹합치기
			}else {
				map[s][e] = -1;
				map[e][s] = -1;
			}
		}
		for(int i=0;i<n;i++) {
			int cnt =0;
			int idx =0;
			for(int j=0;j<n;j++) {
				if(map[i][j]==-1) {
					idx = j;
					cnt++;
				}
			}
			if(cnt >=2) { // 원수가 2명이상이면 원수의 원수는 친구이므로 친구추가 
				map[i][idx] = 0; // 0으로 바꿔주는이유는 이미 친구간 그룹형성했기에 다른 곳에서 또 다시 합쳐지지 않기위해
				enemy(i,idx);
			}
		}
		for(int i=1;i<n+1;i++) {
			fparent(i); // 부모 제대로 가르치게 하기
			res[parent[i]] ++; // 그룹갯수 확인을 위한 값증가
		}
		int cnt =0;
		for(int i=1;i<n+1;i++) {
			if(res[i]!=0) cnt++;
		}
		System.out.println(cnt);
	}

	private static void enemy(int x,int idx) {
		for(int j=0;j<n+1;j++) {
			if(map[x][j]==-1) {
				 if(uparent(idx, j)) map[x][j]=0;
			}
		}
	}
	private static int fparent(int i) {
		if(parent[i]==i) return i;
		return parent[i] = fparent(parent[i]);
	}
	private static boolean uparent(int x,int y) {
		x = fparent(x);
		y = fparent(y);
		if(x==y) return false;
		if(x>y) parent[x] = y; 
		else parent[y] = x;
		return true;
	}
}