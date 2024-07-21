import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3758 {
    static int T, n, k, t, m;
    static Team[] teams;
    static StringBuilder sb = new StringBuilder();
    static class Team {
        int teamNo;
        int[] problems;
        int order;
        int count;
        int sum;

        public Team(int teamNo, int cnt, int order){
            this.teamNo = teamNo;
            this.problems = new int[cnt];
            this.order = order;
            this.count = 0;
            this.sum = 0;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while(T --> 0){
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken())-1;
            m = Integer.parseInt(st.nextToken());

            teams = new Team[n];
            for(int i = 0; i < n; i++){
                teams[i] = new Team(i, k, 0);
            }

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());

                int no = Integer.parseInt(st.nextToken())-1;
                int j = Integer.parseInt(st.nextToken())-1;
                int s = Integer.parseInt(st.nextToken());

                if(teams[no].problems[j] < s){
                    teams[no].problems[j] = s;
                }
                teams[no].order = i;
                teams[no].count++;
            }

            for(int i = 0; i < n; i++){
                int tmp = 0;
                for(int score : teams[i].problems){
                    tmp += score;
                }
                teams[i].sum = tmp;
            }

            Arrays.sort(teams, (o1, o2) -> {
                if(o1.sum == o2.sum && o1.count == o2.count){
                    return o1.order - o2.order;
                }else if(o1.sum == o2.sum){
                    return o1.count - o2.count;
                }else {
                    return o2.sum - o1.sum;
                }
            });

            for(int i = 0; i < n; i++){
                if(teams[i].teamNo == t){
                    sb.append(i+1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }

}
