import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10250 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        while(N --> 0){
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());


            if(count % height == 0){
                sb.append((height * 100) + (count/height));
            }else {
                sb.append((count%height) * 100 + (count/height)+1);
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}
