public class Solution {

    public boolean solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;
        // 자물쇠 크기를 3배로 확장하여 가운데에 자물쇠를 위치시킴
        int[][] extendedLock = new int[n * 3][n * 3];
        
        // 확장된 자물쇠의 중앙에 기존 자물쇠 복사
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                extendedLock[i + n][j + n] = lock[i][j];
            }
        }

        // 열쇠를 4번 회전시키며 모든 위치에서 자물쇠를 열 수 있는지 확인
        for (int rotation = 0; rotation < 4; rotation++) {
            key = rotate90(key); // 열쇠 회전
            // 자물쇠에 맞춰 이동
            for (int x = 0; x < n * 2; x++) {
                for (int y = 0; y < n * 2; y++) {
                    // 열쇠를 현재 위치에 맞춰봄
                    if (canUnlock(extendedLock, key, x, y, n)) {
                        return true; // 자물쇠가 열리면 true 반환
                    }
                }
            }
        }

        return false; // 모든 경우의 수에서 자물쇠를 열지 못하면 false 반환
    }

    // 열쇠를 90도 회전시키는 함수
    private int[][] rotate90(int[][] key) {
        int m = key.length;
        int[][] rotatedKey = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                rotatedKey[j][m - 1 - i] = key[i][j];
            }
        }
        return rotatedKey;
    }

    // 열쇠가 자물쇠에 맞는지 확인하는 함수
    private boolean canUnlock(int[][] extendedLock, int[][] key, int x, int y, int n) {
        int[][] tempLock = new int[extendedLock.length][extendedLock.length];

        // 확장 자물쇠 복사
        for (int i = 0; i < extendedLock.length; i++) {
            tempLock[i] = extendedLock[i].clone();
        }

        // 열쇠를 자물쇠에 맞춰봄
        int m = key.length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                tempLock[x + i][y + j] += key[i][j];
            }
        }

        // 자물쇠의 중앙 부분이 모두 1인지 확인 (자물쇠가 열렸는지 체크)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tempLock[i + n][j + n] != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
