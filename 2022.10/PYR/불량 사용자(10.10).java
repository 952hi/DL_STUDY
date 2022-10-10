import java.util.*;
class Solution {
    Set<Integer> bannedSet[], results;
    int setSize;
    
    public boolean correct(String user, String ban){
        int len = user.length();
        if(len != ban.length()){
            return false;
        }
        
        return user.matches(ban.replace("*", "[\\w\\d]"));
    }
    
    public void dfs(int index, int visit){
        if(index >= setSize){
            results.add(visit);
            return;
        }
        for(int num : bannedSet[index]){
            if((visit & (1<<num)) != 0){ continue;}
            dfs(index+1, visit | (1<<num));
        }
    }
    
    public int solution(String[] user_id, String[] banned_id) {
        setSize = banned_id.length;
        bannedSet = new HashSet[setSize];
        results = new HashSet<>();
        for(int i=0; i<setSize; i++){
            bannedSet[i] = new HashSet<>();//초기화
            for(int j=0; j<user_id.length; j++){
                if(correct(user_id[j], banned_id[i])){
                    bannedSet[i].add(j);
                }
            }
        }
        
        dfs(0, 0);
        return results.size();
    }
}