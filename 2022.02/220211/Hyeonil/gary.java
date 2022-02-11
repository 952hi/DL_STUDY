// 이 코드는 dpsel57, loadine1224 가 pair programming 한 코드입니다.

import java.util.*;
import java.util.stream.Stream;
import java.io.*;

// 17471 게리맨더링
public class gary{
    public static boolean adj[][] = new boolean[10][10];
    public static int N;
    public static int pop[];
    public static int min = Integer.MAX_VALUE;
    public static int hap =0;
    
    public static int dfs(int start, ArrayList<Integer> G, int visit){
        visit |= (1<<start); // visit = visit | (1<<start) 0000010
        for(int i = 0; i < G.size(); i++)
            if(adj[start][G.get(i)] && (visit & (1<<G.get(i))) == 0)
                visit |= dfs(G.get(i), G, visit);
        return visit;
    }

    public static boolean isCon(ArrayList<Integer> G){
        if(G.size()==1) return true;
        
        int visit = (1<<N+1)-1;
        for(int i = 0; i < G.size(); i++)
            visit &= ~(1<<G.get(i));
        return dfs(G.get(0), G, visit)==((1<<N+1)-1);
    }

    public static void comb(int city, ArrayList<Integer> A, ArrayList<Integer> B){
        if(city==N){
            if(A.size()==0 || B.size()==0) return;
            // A, B가 다 연결되어 있는 칭구들이라면
            if(isCon(A) && isCon(B)){
                int sum =0;                
                for(int k=0; k<A.size();k++)
                    sum = sum + pop[A.get(k)];
                min = Math.min(min, Math.abs(hap - sum - sum));
            }
            return;
        }

        A.add(city);
        comb(city+1, A, B);
        A.remove(A.size()-1);

        B.add(city);
        comb(city+1, A, B);
        B.remove(B.size()-1);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // input
        N = Integer.parseInt(br.readLine());
        pop = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i = 0; i < N; i++){
            int temp[] = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j = 0; j < temp[0]; j++){
                adj[i][temp[j+1]-1] = true;
                adj[temp[j+1]-1][i] = true;
            }
        }
        for(int a : pop)
            hap += a;
        
        // algorithm
        comb(0, new ArrayList<Integer>(), new ArrayList<Integer>());
        if(min==Integer.MAX_VALUE)System.out.println(-1);
        else System.out.println(min);
        
    }
};