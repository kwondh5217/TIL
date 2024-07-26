import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ19941 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        int result = 0;

        char[] chars = str.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == 'P') {
                int index = Math.max(i - k, 0);
                while (index < n && index <= i + k) {
                    if (chars[index] == 'H') {
                        chars[index] = 'X';
                        result++;
                        break;
                    }
                    index++;
                }
            }
        }
        System.out.println(result);
    }
}