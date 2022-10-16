import java.util.*;

public class fuser {
	static int count=0;
	static HashSet<String[]> hs = new HashSet<>();
	public static void main(String[] args) {
		String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String[] banned_id = {"fr*d*", "abc1**"};
		int result = check(user_id,banned_id);
		System.out.println(result);
	}

	private static int check(String[] user_id, String[] banned_id) {
		boolean[] visiteduser = new boolean[user_id.length];
		boolean[] visitedban = new boolean[banned_id.length];
		calc(user_id,banned_id,visiteduser,visitedban,0,0,banned_id.length);
		
		return hs.size();
	}

	private static void calc(String[] user_id, String[] banned_id, boolean[] visiteduser, boolean[] visitedban, int idx,int cnt,int n) {
		if(cnt==n) { // 밴 아이디 수만큼 뽑혔다면
			String[] comp = new String[n];
			int idxchk = 0;
			int x = 0;
			while(idxchk<n) {
				if(visiteduser[x]) {
					comp[idxchk++]=user_id[x++];
				}else x++;
			}
			hs.add(comp);
			return;
		}
		
		for(int i=idx,size=user_id.length;i<size;i++) { // 밴 아이디 수만큼 반복 인줄 알았으니 사람이 더많아서 모든 경우 확인을 위해 유저 아이디만큼 반복
			for(int j=0;j<n;j++) {// 하나의 유저마다 모든
				if(visitedban[j]) continue;
				if(visiteduser[i]) continue;
				if(calcuser(user_id[i],banned_id[j])) {
					visitedban[j] = true;
					visiteduser[i] = true;
					calc(user_id, banned_id, visiteduser, visitedban, idx+1, cnt+1, n);
					visitedban[j] = false;
					visiteduser[i] = false;
				}
			}
		}
	}

	private static boolean calcuser(String user, String ban) {
		boolean chk = false;
		int usersize=user.length();
		int bansize =ban.length();
		if(usersize!=bansize) return chk; // 길이다르면 리턴
		else {
			for(int i=0;i<usersize;i++) {
				if(ban.charAt(i)=='*') continue;
				if(user.charAt(i)!=ban.charAt(i)) return chk;
			}
		}
		return true;
	}


	

	



}
