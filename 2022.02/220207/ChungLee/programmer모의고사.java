
import java.util.ArrayList;
import java.util.List;

public class programmer모의고사 {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] answers = {1,2,3,4,5,1,2,3,4,5};
		s.solution(answers);
	}
}

class Solution {
	static int answerscnt[] = new int[3];
	static int num1[] = { 1, 2, 3, 4, 5 };
	static int num2[] = { 2, 1, 2, 3, 2, 4, 2, 5 };
	static int num3[] = {3,3,1,1,2,2,4,4,5,5};
	static List<Integer> l = new ArrayList<>(); 
	public Object[] solution(int[] answers) {
		for(int i = 0; i< answers.length;i++) {
			if(answers[i] == num1[i%num1.length]) {
				answerscnt[0]++;
			}
			if(answers[i] == num2[i%num2.length]) {
				answerscnt[1]++;
			}
			if(answers[i] == num3[i%num3.length]) {
				answerscnt[2]++;
			}
		}
		
		int max = Math.max(Math.max(answerscnt[0], answerscnt[1]),answerscnt[2]);
		for(int j = 0; j< 3; j++) {
			if(max ==answerscnt[j]) {
				l.add(j+1);
			}
		}
		Integer[] answer = l.toArray(new Integer[0]);
		
		
		return answer;
	}
}
