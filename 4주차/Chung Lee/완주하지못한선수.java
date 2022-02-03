package programmers.problem.완주하지못한선수;

import java.util.HashMap;
import java.util.Map;

public class 완주하지못한선수 {

	public String solution(String[] participant, String[] completion) {
		//자바 Map은 키의 중복을 허용하지 않습니다.
		Map<String,Integer>part = new HashMap<>();
		for(int i = 0 ; i< completion.length;i++) {
			//map에 동일한 키가 존재한다면 그 키값의 value에 1을 추가
			if(part.get(completion[i]) != null) {
				part.put(completion[i],part.get(completion[i])+1);
			}
			//map에 동일한 키가 존재하지 않는다면 그 <key, 1>을 추가 
			else {
				part.put(completion[i],1);
			}
		}
		System.out.println(part.keySet());
		for(int i = 0 ; i< participant.length;i++) {
			//가장 좋은 경우. 완주자 명단에 없는 참가자를 발견했을 때 
			if(part.get(participant[i]) == null)
				return participant[i];
			//완주자 명단에 동일한 이름이 2명이 있는 경우 1을 뺌
			else if(part.get(participant[i]) >1) {
				part.put(participant[i], part.get(participant[i])-1);
			}
			//완주자 명단에 한명만 남아있다면 그 이름을 삭제
			else if(part.get(participant[i])  == 1)
				part.remove(participant[i]);
		}
		//최악의 경우. 참가자 명단 가장 마지막에 탈락자가 있는 경우
		return part.toString().substring(1, part.toString().length()-3);
	}

	public static void main(String[] args) {
		
	}

}
