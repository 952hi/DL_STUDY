import java.util.Arrays;
public class Main2 {
	
	static int max=-1,minidx=99;
	static int[] dap = new int[11];
	public static void main(String[] args) {
		int n = 9;
		int[] info = {0,0,1,2,0,1,1,1,1,1,1};
		check(info,new int[11],0,0,n);
		System.out.println(Arrays.toString(dap));
	}
	private static void check(int[] info, int[] temp, int idx, int cnt,int n) {
		// n 보다 많이 쏨 => 계산 필요
		if(cnt==n) {
			// 어피치와 라이언 각각 양궁점수 확인
			int hap = sum(temp,info);
			if(hap!=-1) { // -1 ==> 라이언이 짐 => 계산 불필요
				if(hap>max) { // 더 큰 차이가 났다면 갱신
					max = hap;
					change(temp);
				}else if(hap==max){ // 같은 경우 더 작은 점수 인덱스 확인
					int before = 0, after =0;
					for(int i=10;i>=0;i--) {
						before = dap[i];
						after = temp[i];
						
						if(before == after) continue;// 같은경우
						else if(before>after) break;
						else {
							change(temp);
							break;
						}
					}
				}
			}
			return;
		}
		
		// 0 ~ 10점 반복
		for(int i=idx;i<11;i++) {
			temp[i]++;
			check(info,temp, i, cnt+1,n);
			temp[i]--;
		}
	}
	private static void change(int[] temp) {
		for(int i=0;i<11;i++) 
			dap[i] = temp[i];
	}

	private static int sum(int[] temp,int[] info) {
		// 값 계산은 10 - 인덱스 
		int peach=0,bear=0;
		for(int i=0;i<11;i++) {
			if(info[i]<temp[i]) {
				bear += 10-i;
			}else {
				if(info[i]>0) {
					peach+= 10-i;
				}
			}
		}
		if(bear-peach > 0) return bear-peach;
		else return -1;
	}
}
