import java.util.*;

class Solution {
    static final int N = 50;
    static String[][] map;
    static Map<String, int[]> parentMap;

    public String[] solution(String[] commands) {
        // 초기화
        parentMap = new HashMap<>();
        map = new String[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                String key = getKey(i, j);
                parentMap.put(key, new int[]{i, j});  // 각 셀의 부모는 자기 자신으로 초기화
            }
        }

        // 결과값
        Queue<String> result = new LinkedList<>();
        
        // 명령어 실행
        for (String commandLine : commands) {
            String[] command = commandLine.split(" ");
            String instruction = command[0];

            if (instruction.equals("UPDATE") && command.length == 4) {
                int r = getInt(command[1]);
                int c = getInt(command[2]);
                String value = command[3];
                updateV1(r, c, value);
            } else if (instruction.equals("UPDATE")) {
                updateV2(command[1], command[2]);
            } else if (instruction.equals("MERGE")) {
                merge(getInt(command[1]), getInt(command[2]), 
                      getInt(command[3]), getInt(command[4]));
            } else if (instruction.equals("UNMERGE")) {
                unmerge(getInt(command[1]), getInt(command[2]));
            } else if (instruction.equals("PRINT")) {
                int[] rc = find(getInt(command[1]), getInt(command[2]));
                result.offer(map[rc[0]][rc[1]] == null ? "EMPTY" : map[rc[0]][rc[1]]);
            }
        }

        // 결과 처리
        String[] answer = new String[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.poll();
        }
        return answer;
    }

    // 셀 업데이트
    private void updateV1(int r, int c, String value) {
        int[] pointer = find(r, c);
        map[pointer[0]][pointer[1]] = value;
    }

    // 병합된 그룹의 부모 찾기
    private int[] find(int r, int c) {
        String key = getKey(r, c);
        int[] pointer = parentMap.get(key);
        if (pointer[0] == r && pointer[1] == c) {
            return pointer;  // 자기 자신이 부모일 경우
        }
        int[] root = find(pointer[0], pointer[1]);  // 부모를 찾아서 루트로 갱신
        parentMap.put(key, root);
        return root;
    }

    // 셀 병합 해제
    private void unmerge(int r, int c) {
        int[] root = find(r, c);
        String value = map[root[0]][root[1]];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] tmp = find(i, j);
                if (tmp[0] == root[0] && tmp[1] == root[1]) {
                    q.offer(new int[]{i,j});
                }
            }
        }
        
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            int i = poll[0];
            int j = poll[1];
            parentMap.put(getKey(i, j), new int[]{i, j});
            map[i][j] = null;
        }
        map[r][c] = value;  // 해제된 셀에만 값을 남김
    }

    // 두 셀 병합
    private void union(int r1, int c1, int r2, int c2) {
        int[] parent1 = find(r1, c1);
        int[] parent2 = find(r2, c2);
        
        map[parent2[0]][parent2[1]] = null;  // 두 번째 셀 값 초기화

        parent2[0] = parent1[0];
        parent2[1] = parent1[1];
        parentMap.put(getKey(r2, c2), parent2);  // 병합된 셀들의 부모를 갱신
    }

    // 같은 값 가진 셀 업데이트
    private void updateV2(String update, String value) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == null) continue;
                if (map[i][j].equals(update)) {
                    map[i][j] = value;
                }
            }
        }
    }

    // 두 셀을 병합
    private void merge(int r1, int c1, int r2, int c2) {
        int[] fp = find(r1, c1);
        int[] sp = find(r2, c2);

        if (fp[0] == sp[0] && fp[1] == sp[1]) {
            return;  // 이미 병합된 셀일 경우
        }

        String firstValue = map[fp[0]][fp[1]];
        String secondValue = map[sp[0]][sp[1]];

        // 병합 시 값 처리: 값이 있는 셀을 우선하여 병합
        if (firstValue == null && secondValue != null) {
            union(r2, c2, r1, c1);
        } else {
            union(r1, c1, r2, c2);
        }
    }

    // 셀 좌표를 문자열 키로 변환
    private String getKey(int r, int c) {
        return r + "," + c;
    }

    // 문자열을 정수로 변환
    private int getInt(String str) {
        return Integer.parseInt(str) - 1;
    }
}
