import java.util.*;

class Solution {
    PriorityQueue<Integer> pq;

    public int getms(String[] time){
        int result = Integer.parseInt(time[0])*60;//시간 => 분
        result = (result + Integer.parseInt(time[1])) * 60;//분 => 초
        result = (result + Integer.parseInt(time[2])) * 1000;//s => ms
        result += Integer.parseInt(time[3]);
        return result;
    }

    public int solution(String[] lines) {
        pq = new PriorityQueue<>((o1, o2)->o2-o1);
        int answer = 0;
        int n = lines.length;
        StringTokenizer st = null;
        for(int i=n-1; i>=0; i--){
            st = new StringTokenizer(lines[i]);
            st.nextToken();//날짜
            String[] endTime = st.nextToken().split("[.:]");
            String[] duration = st.nextToken().split("[.s]");
            int end = getms(endTime);
            int start = end - Integer.parseInt(duration[0])*1000+1;
            if(duration.length>1){
                start -= Integer.parseInt(duration[1]);
            }
            //알고리즘
            for(int j=0, size=pq.size(); j<size; j++){
                if(end+1000>pq.peek()){
                    break;
                }
                pq.poll();
            }
            pq.add(start);
            answer = Math.max(answer, pq.size());
        }
        return answer;
    }
}