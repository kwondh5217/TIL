import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2239 {
    static int[][] map = new int[9][9];
    static List<Set<Integer>> xSet = new ArrayList<>();
    static List<Set<Integer>> ySet = new ArrayList<>();
    static List<Set<Integer>> groupSet = new ArrayList<>();
    static List<int[]> zeros = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 9; i++){
            xSet.add(new HashSet<>());
            ySet.add(new HashSet<>());
            groupSet.add(new HashSet<>());
        }

        for(int i = 0; i < 9; i++){
            String str = br.readLine();
            for(int j = 0; j < 9; j++){
                int num = Integer.parseInt(String.valueOf(str.charAt(j)));
                map[i][j] = num;

                if(num != 0){
                    xSet.get(i).add(num);
                    ySet.get(j).add(num);
                    groupSet.get(getGroupIndex(i,j)).add(num);
                }else {
                    zeros.add(new int[]{i,j});
                }
            }
        }

        boolean[] check = new boolean[zeros.size()];
        if(recur(0, check)){
            System.out.println(sb);
        }
    }

    static boolean recur(int depth, boolean[] check){
        if(depth == zeros.size()){
            int cnt = 0;
            for(int i = 0; i < 9; i++){
                cnt += xSet.get(i).size();
                cnt += ySet.get(i).size();
                cnt += groupSet.get(i).size();

                if(cnt == 243){
                    for(int j = 0; j < 9; j++){
                        for(int k = 0; k < 9; k++){
                            sb.append(map[j][k]);
                        }
                        sb.append("\n");
                    }
                    return true;
                }
            }
            return false;
        }

        for(int i = 1; i <= 9; i++){
            if(!check[depth]){
                check[depth] = true;
                int[] ints = zeros.get(depth);
                int x = ints[0];
                int y = ints[1];
                int groupIdx = getGroupIndex(x,y);
                if(!xSet.get(x).contains(i) && !ySet.get(y).contains(i) && !groupSet.get(groupIdx).contains(i)){
                    map[x][y] = i;
                    xSet.get(x).add(i);
                    ySet.get(y).add(i);
                    groupSet.get(groupIdx).add(i);
                    if(recur(depth+1, check)){
                        return true;
                    }
                    map[x][y] = 0;
                    xSet.get(x).remove(i);
                    ySet.get(y).remove(i);
                    groupSet.get(groupIdx).remove(i);
                }
                check[depth] = false;
            }
        }
        return false;
    }
    static int getGroupIndex(int x, int y){
        int tmp = 0;
        if(x < 3){
            tmp = 0;
        }else if(x >= 3 && x < 6){
            tmp = 3;
        }else {
            tmp = 6;
        }
        return tmp + y/3;
    }
}
