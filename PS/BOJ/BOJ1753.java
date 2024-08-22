import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1753 {

    static int v, e;
    static List<List<Node>> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int index;
        int dist;
        public Node(int index, int dist){
            this.index = index;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(br.readLine());

        for(int i = 0; i <= v; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b,c));
        }

        int[] cost = new int[v+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        boolean[] visit = new boolean[v+1];

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.dist- o2.dist);
        q.offer(new Node(start, 0));

        while(!q.isEmpty()){
            Node poll = q.poll();

            int curIndex = poll.index;
            if(visit[curIndex]) continue;
            visit[curIndex] = true;

            for(int i = 0; i < list.get(curIndex).size(); i++){
                Node tmp = list.get(curIndex).get(i);
                cost[tmp.index] = Math.min(cost[tmp.index], cost[curIndex] + tmp.dist);
                q.offer(new Node(tmp.index, cost[tmp.index]));
            }
        }

        for(int i = 1; i <= v; i++){
            if(cost[i] == Integer.MAX_VALUE){
                sb.append("INF");
            }else {
                sb.append(cost[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
