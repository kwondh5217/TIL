import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ13023 {
    static int n,m,result;
    static List<List<Integer>> list = new ArrayList<>();
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        check = new boolean[n];

        for(int i = 0; i < n; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }


        for(int i = 0; i < n; i++){
            if(result == 1){
                break;
            }
            dfs(i,1);
          
        }

        System.out.println(result);

    }

    static void dfs(int index, int cnt){
        if(result == 1) return;
        if(cnt == 5){
            result = 1;
            return;
        }

        check[index] = true;

        for(int num : list.get(index)){
            if(!check[num]) {
                check[index] = true;
                dfs(num, cnt + 1);
            }
        }
        check[index] = false;
    }
}
