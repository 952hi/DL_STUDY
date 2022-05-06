package _220506.ChungLee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9935_G4_문자열폭발_best {

	public static void main(String[] args) throws IOException {

		char[] source, bomb;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		source = br.readLine().toCharArray();
		bomb = br.readLine().toCharArray();

		// pos = 지금까지 발견한 폭탄의 총 길이
		int pos = 0;
		int s_len = source.length, b_len = bomb.length;
		boolean check = false;

		// 각 입력값을 저장하는 배열
		char[] ans = new char[s_len];

		// 비교 당하는 문자열 길이만큼 반복
		for (int i = 0; i < s_len; i++) {

			// pos값을 뺀 위치에 문자열의 i번째 값을 저장
			ans[i - pos] = source[i];

			// 소스의 i번째와 폭탄의 마지막 글자가 같고 i번째에서 pos값을 뺀 게 폭탄 길이 보다 길 때
			if (source[i] == bomb[b_len - 1] && i - pos >= b_len - 1) {
				check = true;

				// 1, 2, 3, 4 ... 폭탄 길이 -1번만큼 반복
				for (int j = 1; j < b_len; j++) {
					// 뒤에서부터 비교 시작
					// ex)12ab라면 a부터 2, 1인지 비교

					// 다른 값이라면 바로 취소
					if (ans[i - pos - j] != bomb[b_len - j - 1]) {
						check = false;
						break;
					}

				}
				// 일치하는 문자열을 발견했을 때 폭탄 길이만큼 pos에 더해줌
				if (check) {
					pos += b_len;
				}
			}
		}
		// 전체 폭탄 길이가 곧 전체 문자열의 길이인 경우 == 전부 없앨 수 있는 경우
		if (s_len == pos) {
			bw.write("FRULA");
		} else {
			String result = new String(ans, 0, s_len - pos);
			bw.write(result);
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
