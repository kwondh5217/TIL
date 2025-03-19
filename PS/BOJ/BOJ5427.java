import java.util.*;
import java.io.*;

class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            char[][] map = new char[n][m];
            boolean[][] visit = new boolean[n][m];
            Queue<int[]> fires = new LinkedList<>();
            Queue<int[]> saves = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < s.length(); j++) {
                    map[i][j] = s.charAt(j);
                    if (map[i][j] == '@') {
                        saves.offer(new int[]{i, j, 0});
                        visit[i][j] = true;
                    } else if (map[i][j] == '*') {
                        fires.offer(new int[]{i, j});
                    }
                }
            }

            int answer = -1;

            while (!saves.isEmpty()) {
                int fireSize = fires.size();
                while (fireSize-- > 0) {
                    int[] poll = fires.poll();
                    for (int d = 0; d < 4; d++) {
                        int nx = poll[0] + dx[d];
                        int ny = poll[1] + dy[d];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                        if (map[nx][ny] == '#' || map[nx][ny] == '*') continue;

                        map[nx][ny] = '*';
                        fires.offer(new int[]{nx, ny});
                    }
                }

                int size = saves.size();
                while (size-- > 0) {
                    int[] poll = saves.poll();
                    int px = poll[0], py = poll[1], moves = poll[2];

                    for (int d = 0; d < 4; d++) {
                        int nx = px + dx[d];
                        int ny = py + dy[d];

                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                            answer = moves + 1;
                            break;
                        }
                        if (map[nx][ny] == '*' || map[nx][ny] == '#' || visit[nx][ny]) continue;

                        visit[nx][ny] = true;
                        saves.offer(new int[]{nx, ny, moves + 1});
                    }
                    if (answer != -1) break;
                }
                if (answer != -1) break;
            }

            sb.append(answer == -1 ? "IMPOSSIBLE" : answer).append("\n");
        }
        System.out.print(sb);
    }
}
