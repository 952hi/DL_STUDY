import java.io.BufferedReader;
import java.io.InputStreamReader;

// 그냥 kmp를 익혔다기 보다
// 옮겨적은 느낌입니다
// 관련된 문제를 더풀어봐야 할것같습니다.
public class Main {
    static String pattern;
    static int[] pi;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String comp = br.readLine();
        String temp = br.readLine();
        pattern = br.readLine();
        getPi();

        if(kmp(comp) && kmp(temp))
            System.out.println("YES");

        else
            System.out.println("NO");
    }

    public static boolean kmp(String str) {
        char[] s = str.toCharArray();
        char[] p = pattern.toCharArray();

        int j = 0;
        for(int i=0; i<s.length; i++) {
            while(j>0 && s[i]!=p[j]) {
                j = pi[j-1];
            }

            if(s[i]==p[j]) {
                if(j==p.length-1) {
                    return true;
                }

                else
                    j++;
            }
        }

        return false;
    }

    public static int[] getPi() {
        char[] p = pattern.toCharArray();
        pi = new int[p.length];
        int j = 0;

        for(int i=1; i<p.length; i++) {
            while(j>0 && p[i]!=p[j]) {
                j = pi[j-1];
            }

            if(p[i]==p[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }
}