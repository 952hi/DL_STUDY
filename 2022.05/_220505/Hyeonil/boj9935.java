import java.io.*;
import java.util.Stack;
public class boj9935 {
	// boj 9935
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		String[] temp = br.readLine().split("");
		String[] comp = br.readLine().split("");
		Stack<String> st = new Stack<>();
		
		int tempsize = temp.length;
		int compsize = comp.length;
		for(int i=0;i<tempsize;i++) {
			st.add(temp[i]);
			if(st.size()>=compsize) {
				boolean check = true;
				int size = st.size();
				for(int j=0;j<compsize;j++) {
					if(st.get(size-compsize+j).compareTo(comp[j])!=0) {
						check = false;
						break;
					}
				}
				if(check) {
					for(int k=0;k<compsize;k++) st.pop();
				}
			}
		}
		if(st.size()==0) sb.append("FRULA");
		else {
			for(int i=0;i<st.size();i++) {
				sb.append(st.get(i));
			}
		}
		System.out.println(sb.toString());
	}
}
