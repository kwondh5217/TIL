import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class BOJ2138 {
    static int N, cntA, cntB;
    static char[] input;
    static char[] target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        input = br.readLine().toCharArray();
        target = br.readLine().toCharArray();
        char[] inputCopyA = new char[N];
        char[] inputCopyB = new char[N];
        for(int i = 0; i < N; i++){
            inputCopyA[i] = input[i];
            inputCopyB[i] = input[i];
        }

        while(true) {
            String before = new String(inputCopyA);
            inputCopyA[0] = (inputCopyA[0] == '0') ? '1' : '0';
            inputCopyA[1] = (inputCopyA[1] == '0') ? '1' : '0';
            cntA++;
            simulate(inputCopyA, true);
            simulateReverse(inputCopyA, true);

            if(isSame(new String(inputCopyA), new String(target))){
                break;
            }
            if(isSame(before, new String(inputCopyA))){
                cntA = -1;
                break;
            }
        }

        while(true) {
            String before = new String(inputCopyB);
            simulate(inputCopyB, false);
            simulateReverse(inputCopyB, false);

            if(isSame(new String(inputCopyB), new String(target))){
                break;
            }
            if(isSame(before, new String(inputCopyB))){
                cntB = -1;
                break;
            }
        }

        if(cntA >= 0 && cntB >= 0){
            System.out.println(cntA > cntB ? cntB : cntA);
        }else if(cntA < 0 && cntB >= 0){
            System.out.println(cntB);
        }else if(cntB < 0 && cntA >= 0){
            System.out.println(cntA);
        }else {
            System.out.println(-1);
        }
    }

    private static void simulate(char[] chars, boolean flag) {
        for(int i = 1; i < N; i++) {
            if(isSameCharacter(chars[i-1], target[i-1])) {
                continue;
            }
            swap(i, chars, flag);
        }
    }

    private static void simulateReverse(char[] chars, boolean flag) {
        for(int i = N-2; i >= 0; i--) {
            if(isSameCharacter(chars[i+1], target[i+1])) {
                continue;
            }
            swap(i, chars, flag);
        }
    }

    private static void swap(int index, char[] chars, boolean flag) {
        if (index >= 1 && index < N - 1) {
            for(int i = index-1; i <= index+1; i++) {
                chars[i] = (chars[i] == '0') ? '1' : '0';
            }
        } else if (index == 0) {
            for(int i = index; i <= index+1; i++) {
                chars[i] = (chars[i] == '0') ? '1' : '0';
            }
        } else if (index == N - 1) {
            for(int i = index-1; i <= index; i++) {
                chars[i] = (chars[i] == '0') ? '1' : '0';
            }
        }
        if(flag){
            cntA++;
        }else {
            cntB++;
        }
    }

    private static boolean isSame(String a, String b) {
        return a.equals(b);
    }

    private static boolean isSameCharacter(char a, char b) {
        return a == b;
    }
}
