import java.io.*;

import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        
        //Integer : 차량번호, int[] : 입차시간, 출차시간, 주차시간
        StringTokenizer st;
        Map<Integer, int[]> io = new TreeMap<>();
        List<Integer> list = new LinkedList<>();
        String[] timeSplit;

        int carNum = 0, time = 0;
        
        String text;
        int[] tmp;
        for(int i = 0; i < records.length; i++){
            st = new StringTokenizer(records[i]);
            timeSplit = st.nextToken().split(":");
            time += Integer.parseInt(timeSplit[0]) * 60;
            time += Integer.parseInt(timeSplit[1]);
            carNum = Integer.parseInt(st.nextToken());
            String b;
            
            //입차
            if("IN" == st.nextToken()){
                //첫 차량 등록이라면
                if(io.get(carNum) == null){
                    list.add(carNum);
                    io.put(carNum,new int[]{time, 0, 0});
                    // io.put(carNum) = new int[]{time, 0, 0};
                }
                //이전 입차 기록이 있을 때
                else{
                    //기존 데이터 꺼내기
                    tmp = io.get(carNum);
                    io.put(carNum, new int[]{time, 0, tmp[2]});
                }
            }
            //출차
            else {
                //기록 꺼내기
                tmp = io.get(carNum);
                io.put(carNum,new int[]{tmp[0], time,tmp[2] + time});
            }
        }
        
        Collections.sort(list);
        int[] answer = new int[list.size()];
        int lastOrder = 23 * 60 + 59, value = 0;
        
        //가장 작은 주차번호 순회
        for(Integer i: list){
             value = 0;
            //작은 순서대로 주차 비용 저장
            //출차되지 않았을 때
            tmp = io.get(i);
            
            //출차시간이 0일 때
            if(tmp[1] == 0){
                //단위 시간으로 나눠서 ceil로 해당 값보다 큰 정수로 변경  
                //기본 시간을 오버했을 때
                if(lastOrder - tmp[0] + tmp[2] - fees[0] >= 1){
                    value = 
                    		(int) ((Math.ceil((double)(lastOrder - tmp[0] + tmp[2] - fees[0]) / fees[2]) / 1) * fees[3]);
                    value+= 5000;
                }
                
            }
            //정상출차되었을 때
            else {
                if(tmp[2] - lastOrder >= 1){
                    value+= 5000;
                    value = (int)Math.ceil((double)(tmp[2] - fees[0]) / fees[2]) * fees[3];      
                }
                
            }
            answer[i] = value;
        }
        
        return answer;
    }
}
public class Main {
	
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
//		s.solution(fees, records);
	}
}