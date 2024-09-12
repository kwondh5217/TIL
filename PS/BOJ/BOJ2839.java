import java.util.Scanner;

public class BOJ2839 {
    static int n, count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        count = 0;

        while(true){
            if(n < 0){
                count = -1;
                break;
            }
            if(n % 5 == 0){
                count += n/5;
                break;
            }
            n -= 3;
            count++;
        }

        System.out.println(count);
    }
}
