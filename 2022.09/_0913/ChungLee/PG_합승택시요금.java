package _0913.ChungLee;

import java.util.*;

//다익스트라로 무지, 어치피 두 경우 A=>B까지 최단 루트 검색
//시작점 => 함께 갈 수 있는 지점의 비용은 한번, 그 외는 각각 ADD

//해결법
//1. 시작 지점 => 도착 지점까지 다익스트라로 최단 비용 구하기
//2. 시작 지점들을 제외하고 그 외 지점에서 
class Solution {
    //n 지점 개수
    //s 출발 지점
    //a A의 도착 지점
    // b B의 도착 지점
    //fares 지점 사이 예상 택시 요금
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
    	//간선값 저장 배열 초기화
        int[][] dijk = new int[n+1][n+1];
        
        //20억으로 전체 초기화
        for(int i = 1; i < n+1;i++){
               Arrays.fill(dijk[i],1000000);
        }
        
        //간선별 가중치를 기록
        for(int i = 0; i < fares.length; i++){
            dijk[fares[i][0]][fares[i][1]] = fares[i][2];
            dijk[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        
        //자기 자신은 0으로 초기화
        for(int i = 1; i < n+1;i++){
               dijk[i][i] = 0;
        }
        
        for(int k = 1; k < n+1; k++){
            for(int i = 1; i < n+1; i++){
                for(int j = 1; j < n+1; j++){
                	//기존 값과 두 값을 이었을 때의 값 중 짧은 것을 저장
                    dijk[i][j] = Math.min(dijk[i][j], dijk[i][k] +dijk[k][j]);
                    
                }
            }
        }
        
        int answer = 2000000000;
        for(int i = 1; i < n+1;i++){
            
            answer = Math.min(answer, dijk[s][i] + dijk[i][a] + dijk[i][b]);
        }
        return answer;
    }
}