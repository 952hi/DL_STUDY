import java.util.*;

class Solution {
    public int htom(String h){
        String[] time = h.split(":");
        return Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);
    }
    
    public int calculate(int[] fees, int[] status){
        int result = 0;
        if(status[0]>0){status[1]+=htom("23:59");}//출차
        //기본 요금
        status[1] = Math.max(0, status[1]-fees[0]);
        result+=fees[1];
        //단위 요금
        result += (status[1]+fees[2]-1)/fees[2]*fees[3];
        return result;
    }
    
    public int[] solution(int[] fees, String[] records) {
        Map<String, int[]> cars = new HashMap<>();//입차+1, 출차-1 | 시간 합
        
        StringTokenizer st = null;
        for(String record : records){
            st = new StringTokenizer(record);
            int minute = htom(st.nextToken());
            String carNum = st.nextToken();
            int[] status;
            switch(st.nextToken()){
                case "IN":
                    status = cars.getOrDefault(carNum, new int[]{0, 0});
                    status[0]++;
                    status[1]-=minute;
                    cars.put(carNum, status);
                    break;
                case "OUT":
                    status = cars.get(carNum);
                    status[0]--;
                    status[1]+=minute;
                    cars.put(carNum, status);
                    break;
            }
        }
        Set<String> set = cars.keySet();
        String[] numbers = set.toArray(new String[set.size()]);
        Arrays.sort(numbers);
        int[] answer = new int[set.size()];
        int idx = 0;
        for(String number : numbers){
            answer[idx++] = calculate(fees, cars.get(number));
        }
        return answer;
    }
}