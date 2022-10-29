package _1030;
import java.util.*;
import java.io.*;

public class Boj1043 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());//사람수
		int M = Integer.parseInt(st.nextToken());//파티수
		st = new StringTokenizer(br.readLine());
		int truth = Integer.parseInt(st.nextToken());
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<truth; i++) {
			set.add(Integer.parseInt(st.nextToken()));
		}
		//파티 정보
		Queue<Set<Integer>> parties = new LinkedList<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			Set<Integer> participants = new HashSet<>();
			int cnt = Integer.parseInt(st.nextToken());
			for(int j=0; j<cnt; j++) {
				participants.add(Integer.parseInt(st.nextToken()));
			}
			parties.add(participants);
		}
		
		//연산
		int n = 0;
		while(n!=set.size() && parties.size()>0) {
			n = set.size();
			
			int size = parties.size();
			for(int i=0; i<size; i++) {
				Set<Integer> party = parties.poll();
				boolean ban = false;
				for(int su : party) {
					if(set.contains(su)) {
						ban = true;
						break;
					}
				}
				if(ban) {
					set.addAll(party);
				}
				else {
					parties.add(party);
				}
			}
				
		}
		System.out.println(parties.size());
	}

}

/*
4 5
1 1
1 1
1 2
1 3
1 4
2 4 1

2
*/