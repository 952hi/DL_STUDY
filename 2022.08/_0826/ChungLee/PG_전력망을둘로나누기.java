package _0826.ChungLee;
import java.util.*;
import java.io.*;
public class PG_전력망을둘로나누기 {
	static class Solution {
	    static int[]set;
	    public static int FindSet(int a){
	        //음수일 경우 루트
	        if(set[a] < 0){
	            return a;
	        }
	        return set[a] = FindSet(set[a]);
	    }
	    public static void Union(int a, int b){
	        //루트까지 올라갔을 때 
	        a = FindSet(a);
	        b = FindSet(b);
	        // System.out.println(a+" " + b);
	        // System.out.println(set[0]);
	        
	        //루트가 같으면
	        if(a != b){
	            if(set[a] < set[b]){
	                set[a] += set[b];
	                set[b] = a; 
	            }
	            else{
	                set[b] += set[a];
	                set[a] = b;
	            }
	        }
	    }
	    
	    public int solution(int n, int[][] wires) {
	        int num1 = 0, num2 = 0;
	        int answer = 10000;
	        
	        //하나씩 와이어를 지우면서 
	        for(int i = 0; i < n-1;i++){
	            set = new int[n+1];
	            
	            for(int j = 1; j < n+1; j++)
	                set[j] = -1;
	            
	            num1 = 0;
	            num2 = 0;
	            
	            //매번 와이어 연결
	            for(int j = 0; j < n-1;j++){
	                //와이어 하나씩 연결하지 않음
	                if(i == j)
	                    continue;
	                Union(wires[j][0],wires[j][1]);
	            }
	            
	            for(int j = 1; j < n+1;j++){
	                if(set[j] < 0){
	                    if(num1 == 0)
	                        num1 = set[j];
	                    else{
	                        num2 = set[j];
	                        break;
	                    }
	                }
	            }
	            answer = Math.min(answer, Math.abs(num1 - num2));
	        }
	        return answer;
	    }
	}
	public static void main(String[] args) {
		Solution s = new Solution();
	}
}
