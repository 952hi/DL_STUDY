class Solution {
    
    public String solution(String p) {
        if(p.equals("")){
            return "";
        }
        int n = p.length();
        int pair=0, i=0;
        boolean isRight = true;
        do{
            if(p.charAt(i)=='('){pair++;}
            else{pair--;}
            //닫는 문자열이 먼저 나올 경우
            if(pair<0){
                isRight = false;
            }
            i++;
        }while(pair!=0);
        
        //올바른 문자열이면
        if(isRight){
            StringBuilder sb = new StringBuilder();
            sb.append(p.substring(0, i)).append(solution(p.substring(i, n)));
            return sb.toString();
        }
        else{
            String u = "";
            int size = i-1;
            //뒤집기
            for(int a=1; a<size; a++){
                switch(p.charAt(a)){
                    case '(':
                        u+=")";
                        break;
                    case ')':
                        u+="(";
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("(").append(solution(p.substring(i, n))).append(")").append(u);
            return sb.toString();
        }
    }
}