import java.io.BufferedReader;
import java.io.InputStreamReader;

class BOJ20437 {

    private static int T, K;
    private static String W;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
           W = br.readLine();
           K = Integer.parseInt(br.readLine());

           if(K == 1){
               System.out.println("1 1");
               continue;
           }

           int[] alphabet = new int[26];

           for(int i = 0; i < W.length(); i++) {
               alphabet[W.charAt(i) - 'a']++;
           }

           int minLength = Integer.MAX_VALUE;
           int maxLength = Integer.MIN_VALUE;

           for(int i = 0; i < W.length(); i++) {
               if(alphabet[W.charAt(i) - 'a'] < K) continue;

               int count = 1;
               for(int j = i + 1; j < W.length(); j++){
                   if(W.charAt(j) == W.charAt(i)) count++;

                   if(count == K) {
                       minLength = Math.min(minLength, j - i + 1);
                       maxLength = Math.max(maxLength, j - i + 1);
                       break;
                   }
               }
           }

            System.out.println(minLength == Integer.MAX_VALUE || maxLength == Integer.MIN_VALUE ? -1 : minLength + " " + maxLength);
        }
    }
}