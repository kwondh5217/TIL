import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, ArrayDeque<String>> map = new HashMap<>();
        Map<String, Integer> minutes = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();
        Set<String> set = new HashSet<>();
        PriorityQueue<String> carNumbers = new PriorityQueue<>();
        
        // 초기화
        for (String record : records) {
            String[] splits = record.split(" ");
            if (!set.contains(splits[1])) {
                map.put(splits[1], new ArrayDeque<>());
                result.put(splits[1], 0);
                minutes.put(splits[1], 0);
                carNumbers.offer(splits[1]);
                set.add(splits[1]);
            }
        }
        
        // 입/출차 기록 처리
        for (String record : records) {
            String[] splits = record.split(" ");
            String time = splits[0];
            String carNumber = splits[1];
            String action = splits[2];
            
            ArrayDeque<String> stack = map.get(carNumber);
            if (action.equals("IN")) {
                stack.push(time);
            } else {
                String inTime = stack.pop();
                int minute = getMinute(inTime, time);
                minutes.put(carNumber, minutes.get(carNumber) + minute);
            }
        }
        
        // 출차되지 않은 차량 처리 ("23:59"에 출차된 것으로 간주)
        for (String carNumber : set) {
            ArrayDeque<String> stack = map.get(carNumber);
            if (!stack.isEmpty()) {
                String inTime = stack.pop();
                int minute = getMinute(inTime, "23:59");
                minutes.put(carNumber, minutes.get(carNumber) + minute);
            }
        }
        
        // 주차 요금 계산
        for (String carNumber : set) {
            int charge = getCharge(minutes.get(carNumber), fees);
            result.put(carNumber, charge); // 요금 계산 후 바로 저장
        }
        
        // 결과값을 차량 번호 순으로 출력
        int[] answer = new int[carNumbers.size()];
        int idx = 0;
        while (!carNumbers.isEmpty()) {
            String carNumber = carNumbers.poll();
            answer[idx++] = result.get(carNumber);
        }
        return answer;
    }
    
    // 누적 시간 계산 함수
    private int getMinute(String in, String out) {
        String[] inSplit = in.split(":");
        String[] outSplit = out.split(":");
        
        int inHour = Integer.parseInt(inSplit[0]);
        int inMinute = Integer.parseInt(inSplit[1]);
        int outHour = Integer.parseInt(outSplit[0]);
        int outMinute = Integer.parseInt(outSplit[1]);
        
        // 시간을 분 단위로 변환
        int inTotalMinutes = inHour * 60 + inMinute;
        int outTotalMinutes = outHour * 60 + outMinute;
        
        // 주차 시간 계산
        return outTotalMinutes - inTotalMinutes;
    }
    
    // 요금 계산 함수
    private int getCharge(int parkedTime, int[] fees) {
        // 기본 시간 이하라면 기본 요금만 부과
        if (parkedTime <= fees[0]) {
            return fees[1];
        }
        
        // 초과한 시간에 대해 요금 부과
        int extraTime = parkedTime - fees[0];
        int extraCharges = (int) Math.ceil((double) extraTime / fees[2]) * fees[3];
        
        return fees[1] + extraCharges;
    }
}
