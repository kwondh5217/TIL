import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ20920 {

    static int n,m;
    static Map<String, Word> map = new HashMap<>();
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Word> q = new PriorityQueue<>((o1, o2) -> {
       if(o1.cnt == o2.cnt){
           if(o1.name.length() == o2.name.length()){
               return o1.name.compareTo(o2.name);
           }else {
               return o2.name.length() - o1.name.length();
           }
       }else {
           return o2.cnt - o1.cnt;
       }
    });

    static class Word {
        int cnt;
        String name;
        public Word (int cnt, String name){
            this.cnt = cnt;
            this.name = name;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++){
            String str = br.readLine();
            if(str.length() < m) continue;
            Word word = null;
            if(map.containsKey(str)){
                word = map.get(str);
            }else {
                word = new Word(0, str);
            }
            word.cnt++;
            map.put(str, word);
        }

        for(Word word : map.values()){
            q.offer(word);
        }

        while(!q.isEmpty()){
            Word poll = q.poll();
            sb.append(poll.name).append("\n");
        }
        System.out.println(sb);
    }
}
