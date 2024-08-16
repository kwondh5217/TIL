import java.util.*;

class Solution {

    static boolean[][][] check;
    static char[][] map;
    static List<Integer> answers = new ArrayList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] grid) {
        int length = grid.length;
        int width = grid[0].length();
        map = new char[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                map[i][j] = grid[i].charAt(j);
            }
        }

        check = new boolean[length][width][4];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < 4; k++) {
                    if (!check[i][j][k]) {
                        answers.add(simulate(i, j, k));
                    }
                }
            }
        }

        Collections.sort(answers);
        return answers.stream().mapToInt(i -> i).toArray();
    }

    static int simulate(int x, int y, int d) {
        int cnt = 0;
        int startX = x, startY = y, startD = d;

        while (true) {
            if (check[x][y][d]) break;

            check[x][y][d] = true;
            cnt++;

            x = calculate(x, dx[d], map.length);
            y = calculate(y, dy[d], map[0].length);
            d = nextDirection(d, map[x][y]);

            if (x == startX && y == startY && d == startD) break;
        }

        return cnt;
    }

    static int calculate(int pos, int delta, int maxLength) {
        int newPos = (pos + delta) % maxLength;
        if (newPos < 0) {
            newPos += maxLength;
        }
        return newPos;
    }

    static int nextDirection(int d, char ch) {
        switch (ch) {
            case 'L':
                return (d + 3) % 4;
            case 'R':
                return (d + 1) % 4;
            default:
                return d;
        }
    }
}
