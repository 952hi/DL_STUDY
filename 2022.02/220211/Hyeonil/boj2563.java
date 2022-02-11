import java.util.Scanner;
/*
3
3 7
15 7
5 2

*/
public class boj2563 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[][] map = new int[100][100];
		int cnt =0;
		for(int tc=0;tc<T;tc++) {
			int a=sc.nextInt()-1;
			int b=sc.nextInt()-1;
			for(int x=100-b-10;x<100-b;x++) {
				for(int y=100-a-10;y<100-a;y++) {
					if(map[x][y]==1) continue;
					map[x][y] = 1;
					cnt ++;
				}
				
			}
			
		}
		System.out.println(cnt);
	}

}
