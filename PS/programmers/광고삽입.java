class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String[] plays = play_time.split(":");
        int totalSecond = (3600 * Integer.parseInt(plays[0])) + (60 * Integer.parseInt(plays[1])) + Integer.parseInt(plays[2]);
        int[] arr = new int[totalSecond + 1];
        
        for(int i = 0; i < logs.length; i++) {
            String[] strs = logs[i].split("-");
            String[] froms = strs[0].split(":");
            String[] tos = strs[1].split(":");
            
            int fromSecond = 0;
            fromSecond += Integer.parseInt(froms[0]) * 3600;
            fromSecond += Integer.parseInt(froms[1]) * 60;
            fromSecond += Integer.parseInt(froms[2]);
            
            int toSecond = 0;
            toSecond += Integer.parseInt(tos[0]) * 3600;
            toSecond += Integer.parseInt(tos[1]) * 60;
            toSecond += Integer.parseInt(tos[2]);
            
            arr[fromSecond]++;
            if (toSecond < totalSecond) arr[toSecond]--;
        }
        
        for(int i = 1; i <= totalSecond; i++) {
            arr[i] += arr[i - 1];
        }
        
        String[] advs = adv_time.split(":");
        int window = (3600 * Integer.parseInt(advs[0])) + (60 * Integer.parseInt(advs[1])) + Integer.parseInt(advs[2]);
        long max = 0;
        long result = 0;
        int ans = 0;
        
        for(int i = 0; i < window; i++) {
            max += arr[i];
        }
        result = max;
        
        for(int i = window; i <= totalSecond; i++) {
            max -= arr[i - window];
            max += arr[i];
            
            if(max > result) {
                result = max;
                ans = i - window + 1;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        int hour = ans / 3600;
        sb.append(hour < 10 ? "0" + hour : hour).append(":");
        int minute = (ans % 3600) / 60;
        sb.append(minute < 10 ? "0" + minute : minute).append(":");
        int second = ans % 60;
        sb.append(second < 10 ? "0" + second : second);
        
        return sb.toString();
    }
}
