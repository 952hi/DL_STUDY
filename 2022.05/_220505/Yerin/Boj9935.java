package _0505;

import java.io.*;

public class Boj9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char s1[] = br.readLine().toCharArray();//주어진 문자열
		String bomb = br.readLine();
		char s2[] = bomb.toCharArray();//폭발시킬 문자열
		int len = s2.length;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0, size=s1.length; i<size; i++) {
			sb.append(s1[i]);
			int sbLen = sb.length();
			if(sbLen>=len && s1[i]==s2[len-1]) {
				String s = sb.substring(sbLen-len, sbLen);
				if(s.equals(bomb)) {
					sb.setLength(sb.length()-len);//자르기
				}
			}	
		}
		
		if(sb.length()==0) { System.out.print("FRULA");}
		else {System.out.print(sb.toString());}
	}
}