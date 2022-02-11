import java.util.Arrays;

public class PGanswer {

	public static void main(String[] args) {
		int[] one = { 1, 2, 3, 4, 5 };
		int[] two = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] three = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
		int[] score = new int[3];

		int[] answers = { 1,3,2,4,2 };
		for (int i = 0; i < answers.length; i++) {
			if (one[i % 5] == answers[i]) {
				score[0] += 1;
			}
			if (two[i % 8] == answers[i]) {
				score[1] += 1;
			}
			if (three[i % 10] == answers[i]) {
				score[2] += 1;
			}
		}

		int max = 0;
		int cnt = 0;
		for (int j = 0; j < score.length; j++) {
			if (score[j] > max) {
				cnt =1;
				max = score[j];
			}else if(score[j]==max) {
				cnt = cnt +1;
			}
		}
		int answer[] = new int[cnt];
		int t=0;
		for(int k=0;k<score.length;k++) {
			if(max==score[k]) {
				answer[t++] = k+1;
			}
		}
		System.out.println(Arrays.toString(answer));
	}

}
