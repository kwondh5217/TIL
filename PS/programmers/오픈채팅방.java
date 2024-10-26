import java.util.*;
class Solution {
    public String[] solution(String[] record) {
    
        Map<String, String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        for(String log : record){
            String[] strs = log.split(" ");
            if(!strs[0].equals("Leave")){
                map.put(strs[1], strs[2]);
            }
        }
        
        for(String log : record){
            String[] strs = log.split(" ");
            String message = "";
            if(strs[0].equals("Enter")){
                String nickname = map.get(strs[1]);
                message += nickname + "님이 들어왔습니다.";
            }else if(strs[0].equals("Leave")){
                String nickname = map.get(strs[1]);
                message += nickname + "님이 나갔습니다.";
            }
            if(!message.equals("")){
                list.add(message);
            }
        }
    
        String[] answer = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}