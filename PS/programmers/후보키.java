import java.util.*;

class Solution {
    static String[][] strs;
    static List<Integer> candidateKeys; // 후보키의 인덱스를 비트마스크로 저장

    public int solution(String[][] relation) {
        strs = relation;
        candidateKeys = new ArrayList<>();
        int n = relation[0].length;

        // 모든 속성 조합(부분 집합)을 비트마스크로 구함
        for (int subset = 1; subset < (1 << n); subset++) {
            // 유일성 검사
            if (isUnique(subset)) {
                // 최소성 검사
                boolean isMinimal = true;
                for (int key : candidateKeys) {
                    if ((key & subset) == key) { // 기존 후보키가 새로운 키의 부분 집합이면 최소성 위반
                        isMinimal = false;
                        break;
                    }
                }
                if (isMinimal) {
                    candidateKeys.add(subset); // 최소성 만족 시 후보키로 추가
                }
            }
        }

        return candidateKeys.size(); // 후보키의 개수 반환
    }

    // 유일성 검사
    private boolean isUnique(int subset) {
        Set<String> set = new HashSet<>();

        for (String[] row : strs) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < row.length; i++) {
                if ((subset & (1 << i)) != 0) { // 해당 속성이 부분 집합에 포함된 경우만 선택
                    sb.append(row[i]).append(",");
                }
            }
            if (!set.add(sb.toString())) { // 중복되면 유일성 실패
                return false;
            }
        }

        return true; // 모든 튜플이 유일하다면 유일성 만족
    }
}
