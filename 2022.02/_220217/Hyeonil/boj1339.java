import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj1339 {
	public static int temp[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int alpha[] = new int[26];
		int tc=Integer.parseInt(br.readLine());
		String[] comp= new String[tc];
		for(int i=0;i<tc;i++) {
			comp[i] = br.readLine();
			for(int j=0,size=comp[i].length();j<size;j++) {
				alpha[comp[i].charAt(j)-'A'] +=1;
			}
		}
		int cnt=0,sum=0,max=0;
		for(int a=0;a<26;a++) {
			if(alpha[a]==0) continue;
			cnt++;
		}
		char value[] = new char[cnt];
		for(int w=0;w<26;w++) {
			if(alpha[w]==0)continue;
			value[w] = (char)(w+65);
		}
		if(cnt ==1 ) {
			for(int e=0,size=comp.length;e<size;e++) {
				comp[e] = comp[e].replace(value[0], '9');
				sum += Integer.parseInt(comp[e]);
			}
			max = sum;
		}else {
			temp= new int[cnt];
			int digit =9-cnt+1;
			for(int k=0;k<cnt;k++) {
				temp[k]=digit++;
			}
			do {
				int big = comp.length;
				String replace[] = new String[big];
				System.arraycopy(comp, 0, replace, 0, big);
				for(int r=0;r<cnt;r++) {
					for(int e=0;e<big;e++) {
						replace[e] = replace[e].replace(value[r], (char)(temp[r]+48));
					}
				}
				for(int t=0;t<big;t++) {
					sum += Integer.parseInt(replace[t]);
				}
				max =Math.max(sum, max);
				sum =0;
			} while (np());
		}
		System.out.println(max);
	}
	private static boolean np() {
		int N = temp.length;
		int i = N-1;
		while(i>0 && temp[i-1]>=temp[i]) --i;
		if(i==0) return false;
		int j=N-1;
		while(temp[i-1]>=temp[j]) --j;
		swap(i-1,j);
		int k=N-1;
		while(i<k) {
			swap(i++,k--);
		}
		return true;
	}
	public static void swap(int a,int b) {
		int comp = temp[a];
		temp[a] = temp[b];
		temp[b] = comp;
	}
}