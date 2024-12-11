import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0};
    static Map<String, int[]> map;
    static int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        N = n;
        map = new HashMap<>();
        
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];  // 0: 기둥, 1: 보
            int b = build_frame[i][3];  // 0: 삭제, 1: 설치

            String key = x + "," + y + "," + a;

            if (b == 1) {  // 설치
                map.put(key, new int[]{x, y, a});
                if (!isValid()) {
                    map.remove(key);  // 설치 후 구조물 조건을 만족하지 않으면 설치 취소
                }
            } else {  // 삭제
                map.remove(key);
                if (!isValid()) {
                    map.put(key, new int[]{x, y, a});  // 삭제 후 구조물 조건을 만족하지 않으면 삭제 취소
                }
            }
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a1, a2) -> {
            if (a1[0] == a2[0]) {
                if (a1[1] == a2[1]) {
                    return a1[2] - a2[2];
                }
                return a1[1] - a2[1];
            }
            return a1[0] - a2[0];
        });

        for (int[] value : map.values()) {
            q.offer(value);
        }

        int[][] answer = new int[q.size()][3];
        int index = 0;
        while (!q.isEmpty()) {
            answer[index++] = q.poll();
        }
        
        return answer;
    }

    // 모든 기둥과 보가 설치 조건을 만족하는지 검사하는 메서드
    private boolean isValid() {
        for (int[] value : map.values()) {
            int x = value[0];
            int y = value[1];
            int a = value[2];
            
            if (a == 0) {  // 기둥
                if (y == 0 || map.containsKey((x - 1) + "," + y + ",1") || map.containsKey(x + "," + (y - 1) + ",0") || map.containsKey(x + "," + y + ",1")) {
                    continue;
                }
                return false;
            } else {  // 보
                if (map.containsKey(x + "," + (y - 1) + ",0") || map.containsKey((x + 1) + "," + (y - 1) + ",0") || (map.containsKey((x - 1) + "," + y + ",1") && map.containsKey((x + 1) + "," + y + ",1"))) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}
