import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BOJ9017 {
    static int t, n;
    static int[] input;
    static int[] cnt;
    static int[][] scores;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        while(t --> 0){
            n = Integer.parseInt(br.readLine());
            scores = new int[201][3];
            cnt = new int[201];
            input = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 입력값에서 6명이 안되는 팀 찾기
            for (int i = 0; i < n; i++) {
                input[i] = Integer.parseInt(st.nextToken());
                cnt[input[i]]++;
            }

            int num = 1;
            /*
                0 : 점수를 세면서 몇명까지 세었는지 카운트
                1 : 점수의 합
                2 : 5번째 주자의 점수
             */
            for(int i = 0; i < n; i++){
                if(cnt[input[i]] < 6) continue;
                scores[input[i]][0]++;
                if(scores[input[i]][0] <= 4){
                    scores[input[i]][1] += num;
                }
                if(scores[input[i]][0] == 5){
                    scores[input[i]][2] = num;
                }
                num++;
            }

            int index = 0;
            int minScore = Integer.MAX_VALUE;
            int five = Integer.MAX_VALUE;

            for (int i = 1; i <= 200; i++) {
                if (scores[i][0] < 6) continue;
                if (scores[i][1] < minScore) {
                    minScore = scores[i][1];
                    five = scores[i][2];
                    index = i;
                } else if (scores[i][1] == minScore) {
                    if (scores[i][2] < five) {
                        minScore = scores[i][1];
                        five = scores[i][2];
                        index = i;
                    }
                }
            }

            if (t == 0) {
                sb.append(index);
            } else {
                sb.append(index).append("\n");
            }
        }

        System.out.println(sb);
    }


}