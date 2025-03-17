import java.util.*;
import java.io.*;
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String a = br.readLine();
        
        PriorityQueue<String> q = new PriorityQueue<>();
        
        for(int i = 0; i < a.length(); i++) {
            q.offer(a.substring(i, a.length()));
        }
        
        while(!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }
}
