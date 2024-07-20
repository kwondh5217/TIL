import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ22233 {

    static int n, m;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        Set<String> memo = new HashSet<>();
        Set<String> post = new HashSet<>();

        for(int i = 0; i < n; i++){
            memo.add(br.readLine());
        }

        int cnt = 0;
        for(int i = 0 ; i < m; i++){
            st = new StringTokenizer(br.readLine(), ",");

            while(st.hasMoreTokens()){
                String str = st.nextToken();
                if(memo.contains(str)){
                    if(!post.contains(str)){
                        post.add(str);
                        cnt++;
                    }
                }
            }
            sb.append(memo.size() - cnt).append("\n");
        }

        System.out.println(sb);
    }
}
