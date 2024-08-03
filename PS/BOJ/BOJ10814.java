import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ10814 {

    static int n;
    static int order = 0;
    static PriorityQueue<Node> q = new PriorityQueue<>(((o1, o2) -> o1.age == o2.age ? o1.order - o2.order : o1.age - o2.age));
    static StringBuilder sb = new StringBuilder();

    static class Node {
        int age;
        String name;
        int order;

        public Node (int age, String name, int order){
            this.age = age;
            this.name = name;
            this.order = order;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            order++;
            q.offer(new Node(age, name, order));
        }

        while(!q.isEmpty()){
            Node poll = q.poll();

            sb.append(poll.age).append(" ").append(poll.name).append("\n");
        }

        System.out.println(sb);
    }
}
