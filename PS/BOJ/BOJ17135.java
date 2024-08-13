import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17135 {

    static int N, M, D, max;
    static int[] archer = new int[3]; //  궁수 위치 3자리(x)
    static List<Enemy> enemy = new ArrayList<>();
    static List<Enemy> enemyCopy = new ArrayList<>();
    static PriorityQueue<Enemy> pqueue = new PriorityQueue<>(
            (e1, e2) -> e1.d == e2.d ? e1.x - e2.x : e1.d - e2.d );

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        // 적군 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if( n == 1 ) enemyCopy.add(new Enemy(i, j));
            }
        }
        // 풀이
        comb(0, 0); // 궁수의 자리
        System.out.println(max);
    }

    static void check() {
        // 초기화 및 적 복사
        enemy.clear();
        for (Enemy e : enemyCopy) {
            enemy.add(new Enemy(e.y, e.x));
        }
        // 시물레이션
        int dead = 0;
        while(true) {
            // 조건에 맞는 적을 계속 죽이는 과정 반복
            // 각 궁사가 한명씩 발사
            for (int i = 0; i < 3; i++) {
                pqueue.clear();
                int ac = archer[i];
                // 현재 남은 적들 중 현재 궁사와의 사정거리 안에 드는 적을 선별 삭제
                int size = enemy.size();
                for (int j = 0; j < size; j++) {
                    Enemy e = enemy.get(j);
                    // 현재 궁사와 현재 적의 거리를 계산, 저장
                    e.d = Math.abs(ac - e.x) + Math.abs(N - e.y);
                    if(e.d <= D) {
                        pqueue.offer(e);
                    }
                }
                if( !pqueue.isEmpty() ) {
                    pqueue.poll().dead = true; // enemy 엔 남아 있다.
                }
            }

            // 사망한 적 제거
            for (int i = enemy.size()-1; i >= 0; i--) {
                Enemy e = enemy.get(i);
                if( e.dead ) {
                    enemy.remove(i); // 죽은 적
                    dead++;
                }else if( e.y == N - 1) {
                    enemy.remove(i); // 영역 벗어난 적
                }else {
                    e.y++;
                }
            }

            if( enemy.size() == 0 ) break;
        }

        max = Math.max(max, dead);
    }

    static void comb(int srcIdx, int tgtIdx) {
        if( tgtIdx == 3 ) {
            check();
            return;
        }

        if( srcIdx == M ) return;

        archer[tgtIdx] = srcIdx;

        comb(srcIdx + 1, tgtIdx + 1);
        comb(srcIdx + 1, tgtIdx);
    }

    static class Enemy{
        int y, x, d;
        boolean dead; // 사망 여부
        Enemy(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
