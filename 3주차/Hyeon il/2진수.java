import java.util.Arrays;
import java.util.Scanner;

public class BinaryQ {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		String line;
		int T = sc.nextInt();
		
		for(int i=0;i<=T;i++) {
			int a;
			int b;
			String bina;
			
			
			a= sc.nextInt();
			b= sc.nextInt();
			
			
			a=(int)Math.pow(2, (double)a)-1;
			bina=Integer.toBinaryString(a&b);
			
			
			if(bina.contains("0")) {
				System.out.printf("#%d OFF\n",i+1);
			}else {
				System.out.printf("#%d ON\n",i+1);
			}
			
			
		}
	}

}
