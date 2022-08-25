package _0826.ChungLee;

public class PG_괄호변환 {
	static public String solution(String p) {
		StringBuilder sb = new StringBuilder();
		// return empty String
		String tmp = "";
		int cnt = 0,pos = 0;
		// u, v 분리
		while (true) {
			if (p.length() == 0)
				return "";
			cnt = 0;
			//첫 문자에 대한 처리
			if(p.charAt(0) == ')')
				cnt--;
			else
				cnt++;
			
			//u : 균형잡힌 괄호 문자열 만들기
			for (int i = 1; i < p.length(); i++) {
				if(p.charAt(i) ==')')
					cnt--;
				else
					cnt++;
				//균형잡힌 괄호 문자열인 경우
				if(cnt == 0) {
					pos = i;
					break;
				}
					
			}
			//u가 올바른 괄호 문자열인 경우
			if(p.charAt(0)=='(') {
				//u
				sb.append(p.substring(0, pos+1));
				//v에 대한 재귀적 수행
				tmp = solution(p.substring(pos+1,p.length()));
				sb.append(tmp);
			}
			//u가 올바른 괄호 문자열 아닌 경우
			else {
				//4-1
				sb.append('(');
				//4-2
				tmp =solution(p.substring(pos+1,p.length()));
				sb.append(tmp);
				//4-3
				sb.append(')');
				//4-4
				for(int j = 1; j < pos; j++) {
					if(p.charAt(j) == '(')
						sb.append(')');
					else
						sb.append('(');
				}
			}
			return new String(sb.toString());
		}
	}

	public static void main(String[] args) {
		System.out.println(solution("()))((()"));
	}
}
