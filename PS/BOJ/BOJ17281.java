import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class BOJ17281 {
    static int n;
    static int[][] arr;
    static int[] order;
    static boolean[] check;
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1][10];
        order = new int[10];
        check = new boolean[10];

        order[4] = 1;
        check[4] = true;

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 9; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findOrder(2);

        System.out.println(result);


    }

    static void findOrder(int depth){
        if(depth == 10){
            simulate();
            return;
        }

        for(int i = 1; i <= 9; i++){
            if(!check[i]){
                check[i] = true;
                order[i] = depth;
                findOrder(depth+1);
                check[i] = false;
            }
        }

    }

    static void simulate() {
        int index = 1;
        int score = 0;
        for (int i = 1; i <= n; i++) {
            int outCount = 0;
            boolean[] check = new boolean[4];

            while(outCount < 3){
                int tmp = arr[i][order[index++]];

                if(index > 9){
                    index = 1;
                }

                switch (tmp) {
                    case 0 :
                        outCount++;
                        break;
                    case 1 :
                        for(int k = 3; k >= 1; k--) {
                            if (check[k]) {
                                if (k == 3) {
                                    check[k] = false;
                                    score++;
                                    continue;
                                }
                                check[k] = false;
                                check[k + 1] = true;
                            }
                        }
                        check[1] = true;
                        break;
                    case 2 :
                        for(int k = 3; k >= 1; k--) {
                            if (check[k]) {
                                if (k >= 2) {
                                    if (check[k]) {
                                        check[k] = false;
                                        score++;
                                        continue;
                                    }
                                }
                                check[k] = false;
                                check[k+2] = true;
                            }
                        }
                        check[2] = true;
                        break;
                    case 3 :
                        for(int k = 3; k >= 1; k--){
                            if(check[k]){
                                score++;
                                check[k] = false;
                            }
                        }
                        check[3] = true;
                        break;
                    case 4 :
                        for(int k = 1; k <= 3; k++){
                            if(check[k]){
                                score++;
                                check[k] = false;
                            }
                        }
                        score++;
                        break;
                    default :
                        break;
                }
            }
        }

        result = Math.max(score, result);
    }
}