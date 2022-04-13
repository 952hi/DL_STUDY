package _0414;

import java.io.*;
import java.util.*;

public class Boj1461 {
	static HashMap<String, Integer> network;
	static HashMap<String, String> parent;
	
	public static String find(String p) {
		if(parent.get(p)==p) {
			return p;
		}
		else { 
			String np = find(parent.get(p));
			parent.put(p, np);
			return np;
		}
	}
	
	public static int union(String p1, String p2) {
		p1 = find(p1);
		p2 = find(p2);
		if(p1==p2) {
			return network.get(p1);
		}
		
		network.put(p1, network.get(p1)+network.get(p2));
		parent.put(p2, p1);//갱신
		
		return network.get(p1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int t=0; t<T; t++) {
			int F = Integer.parseInt(br.readLine());
			network = new HashMap<>();
			parent = new HashMap<>();
			for(int i=0; i<F; i++) {
				st = new StringTokenizer(br.readLine());
				String p1 = st.nextToken();
				String p2 = st.nextToken();
				if(!network.containsKey(p1)) {
					parent.put(p1, p1);
					network.put(p1, 1);
				}
				if(!network.containsKey(p2)) {
					parent.put(p2, p2);
					network.put(p2, 1);
				}
				sb.append(union(p1, p2)).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
