import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1865 {

    static int t, n, m, w;
    static List<Node> list;
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int from;
        int to;
        int cost;
        public Node (int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for(int test = 1; test <= t; test++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            list = new ArrayList<>();


            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                list.add(new Node(a,b,c));
                list.add(new Node(b,a,c));
            }

            for(int i = 0; i < w; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                list.add(new Node(a, b, -c));
            }

            if(bellmanford()){
                sb.append("YES").append("\n");
            }else {
                sb.append("NO").append("\n");
            }
        }

        System.out.println(sb);
    }

    static boolean bellmanford(){
        int[] dist = new int[n+1];
        int cur,next,cost;
        for(int i=1;i<=n;i++){
            for(Node node:list){
                cur = node.from;
                next = node.to;
                cost = node.cost;
                if(dist[next]>dist[cur]+cost){
                    dist[next] = dist[cur]+cost;
                    if(i==n) return true;
                }
            }
        }
        return false;
    }
}
