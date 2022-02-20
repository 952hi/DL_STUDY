import java.io.*;
import java.util.*;
//boj8983 x점 2회차
// 문제의 힌트의 이분탐색을 보고 생각한 방법
// 아이디어 1 -> 사대기준 이분탐색 
//이분탐색을 통해 각동물의 left right mid를 통해 미드에 걸리는 동물의 거리가
// L에 포함된다면 left ~ mid 까지 모두 가능한 경우로 판단
// 문제점 mid의 경우만 가능하고 left ~ mid가 완벽하다는 보장이 없다고 생각함
// 아이디어 2 -> 동물기준으로 가까운 사대를 찾는 방법
// 동물의 좌표기준으로 사대를 찾아 가능하면 1추가 

//메모리60932kb	732ms
public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer stz = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(stz.nextToken());
		int N = Integer.parseInt(stz.nextToken());
		int L = Integer.parseInt(stz.nextToken());
		int res =0;
		stz = new StringTokenizer(br.readLine()," ");
		Data []gun = new Data[M];
		Data []animal = new Data[N];
		
		for(int i=0;i<M;i++) gun[i] = new Data(Integer.parseInt(stz.nextToken()), 0);
		for(int i=0;i<N;i++) {
			stz = new StringTokenizer(br.readLine()," ");
			animal[i] = new Data(Integer.parseInt(stz.nextToken()), Integer.parseInt(stz.nextToken()));
		}
		Arrays.sort(gun);
		for(int i =0;i<N;i++) { //동물
			int left =0,right=M-1,mid;
			while(left<=right) {
				mid = (left+right)/2; // 사수자리
				int temp = Math.abs(animal[i].x-gun[mid].x)+animal[i].y;
				if(L<temp && animal[i].x < gun[mid].x) right = mid -1; // 거리체크 후 우측 범위일경우
				else if(L<temp && animal[i].x >= gun[mid].x) left =mid+1; // 거리체크 후 좌측 범위일경우
				else if(L>=temp) {
//					System.out.println(gun[mid].x+" "+animal[i].x+" "+animal[i].y);
					res += 1;
					break;
				}
			}
		}
		sb.append(res);
		bw.write(sb.toString());
		bw.flush();
	}
}
class Data implements Comparable<Data>{
	int x;
	int y;
	public Data(int x,int y) {
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Data o) {
		return this.x==o.x?this.y-o.y:this.x-o.x;
	}
	@Override
	public String toString() {
		return "["+x + " " + y + "]";
	}
	
}