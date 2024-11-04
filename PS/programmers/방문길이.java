import java.util.HashSet;
import java.util.Set;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int solution(String dirs) {
        int x = 5, y = 5;
        int answer = 0;
        
        Set<String> visitedPaths = new HashSet<>();
        
        for (int i = 0; i < dirs.length(); i++) {
            int dir = convert(dirs.charAt(i));
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (nx < 0 || ny < 0 || nx >= 11 || ny >= 11) continue;
            
            String path1 = x + "," + y + "->" + nx + "," + ny;
            String path2 = nx + "," + ny + "->" + x + "," + y;
            
            if (!visitedPaths.contains(path1)) {
                visitedPaths.add(path1);
                visitedPaths.add(path2);
                answer++;
            }
            
            x = nx;
            y = ny;
        }
        return answer;
    }
    
    private int convert(char ch) {
        switch (ch) {
            case 'U': return 0;
            case 'D': return 1;
            case 'R': return 2;
            case 'L': return 3;
            default: return -1;
        }
    }
}
