import java.util.Arrays;

public class Hash {
	// 느낀점
	// ==이 프로그래머스에서 작동하지않아서 
	// equals를 더 사용해야겠다고 생각했습니다.
	
	public String solution(String[] participant, String[] completion) {
		Arrays.sort(participant);
	    Arrays.sort(completion);
        
        for(int i=0;i<completion.length;i++) {
            if(!participant[i].equals(completion[i])){
                return participant[i];
            }
        }
	    return participant[completion.length];
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] participant = {"marina", "josipa", "nikola", "vinko", "filipa"};
		String[] completion = {"josipa", "filipa", "marina", "nikola"};
		Hash hs = new Hash();
		System.out.println(hs.solution(participant, completion));
	    
	}
}
