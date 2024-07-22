import java.io.BufferedReader;
import java.io.InputStreamReader;

class BOJ1515 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        int index = 0;
        int num = 1;

        while(index < n.length()){
            String numString = String.valueOf(num);

            for(int i = 0; i < numString.length(); i++){
                if(n.charAt(index) == numString.charAt(i)){
                    index++;
                }
                if(index == n.length()){
                    System.out.println(numString);
                    return;
                }
            }
            num++;
        }
    }
}