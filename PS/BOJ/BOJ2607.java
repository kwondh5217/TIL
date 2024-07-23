import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

class BOJ2607 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int result = 0;
        int[] alphabet = new int[26];

        for(int i = 1; i < t; i++){
            String tmp = br.readLine();
            int cnt = 0;

            for(int j = 0; j < str.length(); j++){
                alphabet[str.charAt(j) - 'A']++;
            }

            for(int j = 0; j < tmp.length(); j++){
                if(alphabet[tmp.charAt(j) - 'A'] > 0) {
                    cnt++;
                    alphabet[tmp.charAt(j) - 'A']--;
                }
            }

            if(str.length() == tmp.length() && (str.length() == cnt || str.length()-1 == cnt)){
                result++;
            }else if(str.length()-1 == tmp.length() && str.length()-1 == cnt){
                result++;
            }else if(str.length()+1 == tmp.length() && str.length() == cnt){
                result++;
            }
        }

        System.out.println(result);
    }
}