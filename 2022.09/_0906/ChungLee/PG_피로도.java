package _0906.ChungLee;

public class PG_피로도 {
	static boolean[] isV;
    static int[][] staticD;
    static int Max = 0, dCnt = 0;
    
    //
    static public void runD(int crntPiro, int cntV){
        
        for(int i = 0; i < dCnt;i++){
            //방문했다면 pass
            if(isV[i])
                continue;
            if(crntPiro >= staticD[i][0]){
                //방문 처리
                isV[i] = true;
                Max = Math.max(Max, cntV+1);
                runD(crntPiro - staticD[i][1], cntV+1);
                //방문 처리 해제
                isV[i] = false;
            }
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        dCnt = dungeons.length;
        // System.out.println(dCnt);
        
        //던전 갯수만큼 체크
        isV = new boolean[dCnt];
        //static변수에 파라미터 매핑
        staticD = dungeons;
        
        //탐색 시작
        runD(k, 0);
        
        return Max;
    }
}
