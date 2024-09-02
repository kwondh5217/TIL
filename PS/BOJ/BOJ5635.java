import java.util.*;
import java.io.*;

public class BOJ5635 {
    static class Student {
        String name;
        int year;
        int month;
        int day;
        
        public Student(String name, int day, int month, int year) {
            this.name = name;
            this.year = year;
            this.month = month;
            this.day = day;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());

        // 우선순위 큐: 나이가 많은 순서로 정렬
        PriorityQueue<Student> pq = new PriorityQueue<>((a, b) -> {
            if (a.year != b.year) {
                return a.year - b.year; // 연도로 비교
            } else if (a.month != b.month) {
                return a.month - b.month; // 월로 비교
            } else {
                return a.day - b.day; // 일로 비교
            }
        });
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int day = Integer.parseInt(st.nextToken());
            int month = Integer.parseInt(st.nextToken());
            int year = Integer.parseInt(st.nextToken());
            pq.offer(new Student(name, day, month, year));
        }

        // 가장 나이가 많은 사람은 우선순위 큐의 첫 번째 요소
        Student oldest = pq.peek();
        
        // 우선순위 큐를 순회해서 가장 나이가 적은 사람을 찾음
        Student youngest = oldest;
        while (!pq.isEmpty()) {
            youngest = pq.poll();
        }

        System.out.println(youngest.name);
        System.out.println(oldest.name);
    }
}
