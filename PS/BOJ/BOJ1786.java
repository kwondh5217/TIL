import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ1786 {

    static int cnt;
    static int[] pi;
    static String t, p;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = br.readLine();
        p = br.readLine();


        kmp();
        System.out.println(cnt);
        System.out.println(sb);
    }

    static void kmp(){
        getPI();

        int tLength = t.length();

        char[] tArray = t.toCharArray();
        char[] pArray = p.toCharArray();

        int j = 0;
        for(int i = 0; i < tLength; i++){
            if(j > 0 && tArray[i] != pArray[j]) j = pi[j-1];
            if(tArray[i] == pArray[j]) {
                if(j == p.length()-1){
                    cnt++;
                    sb.append(i-j+1).append("\n");
                    j = pi[j];
                }else {
                    j++;
                }
            }
        }
    }

    static void getPI(){
        pi = new int[p.length()];

        // PI 생성
        char[] pArray = p.toCharArray();

        int j = 0;
        for(int i = 1; i < pArray.length; i++){
            while( j > 0 && pArray[j] != pArray[i]) j = pi[j-1];
            if(pArray[i] == pArray[j]) pi[i] = ++j;
        }
    }
}
