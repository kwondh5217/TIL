import java.util.*;

class Solution {
    static int[] rions = new int[11];
    static int[] result = new int[11];
    static int maxScore = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        dfs(0, n, info, 0);  // DFS 탐색 시작
        if (maxScore == Integer.MIN_VALUE) {
            return new int[]{-1};  // 점수 차이가 없을 경우 -1 반환
        }
        return result;
    }

    private void dfs(int depth, int remainingArrows, int[] info, int idx) {
        // 모든 화살을 다 쏜 경우
        if (depth == remainingArrows || idx == 11) {
            // 남은 화살이 있을 경우 마지막 0점에 몰아줌
            if (depth < remainingArrows) {
                rions[10] += remainingArrows - depth;
            }
            compareScore(info);  // 현재 rions 상태로 점수 비교
            if (depth < remainingArrows) {
                rions[10] -= remainingArrows - depth;  // 복구
            }
            return;
        }

        // 남은 화살이 없을 경우
        if (remainingArrows - depth < info[idx] + 1) {
            // 남은 화살이 현재 점수를 이길 수 없는 경우, 패스
            dfs(depth, remainingArrows, info, idx + 1);
        } else {
            // 1. 현재 점수를 이기기 위해 화살을 쏘는 경우
            rions[idx] = info[idx] + 1;  // 라이언이 이기기 위해 필요한 화살 수
            dfs(depth + rions[idx], remainingArrows, info, idx + 1);
            rions[idx] = 0;  // 복구

            // 2. 화살을 쏘지 않고 다음 점수로 넘어가는 경우
            dfs(depth, remainingArrows, info, idx + 1);
        }
    }

    private void compareScore(int[] info) {
        int apeach = 0;
        int rion = 0;

        // 각 점수에 대해 라이언과 에이피치의 점수 계산
        for (int i = 0; i < 11; i++) {
            if (info[i] == 0 && rions[i] == 0) {
                continue;  // 둘 다 화살을 쏘지 않은 경우 건너뜀
            }
            if (info[i] >= rions[i]) {
                apeach += 10 - i;  // 에이피치가 점수를 가져감
            } else {
                rion += 10 - i;  // 라이언이 점수를 가져감
            }
        }

        // 라이언의 점수가 에이피치보다 클 경우에만 비교
        if (rion > apeach) {
            int diff = rion - apeach;
            if (diff > maxScore) {
                maxScore = diff;
                result = rions.clone();
            } else if (diff == maxScore) {
                // 점수 차이가 같으면 더 낮은 점수를 맞힌 경우를 선택
                for (int i = 10; i >= 0; i--) {
                    if (result[i] < rions[i]) {
                        result = rions.clone();
                        break;
                    } else if (result[i] > rions[i]) {
                        break;
                    }
                }
            }
        }
    }
}
