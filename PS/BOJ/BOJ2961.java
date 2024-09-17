import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2961 {

	public static void main(String[] args) throws Exception, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = 1 << n;
		
		long result = Integer.MAX_VALUE;
		StringTokenizer st;
		
		Food[] foods = new Food[n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			foods[i] = new Food(x, y);
		}
		
		for(int i = 1; i < cnt; i++) {
			long sumx = 0;
			long sumy = 0;
			for(int j = 0; j < n; j++) {
				if((i & 1 << j) != 0) {
					if(sumx == 0) {
						sumx = foods[j].x;
					}else {
						sumx *= foods[j].x;
					}
					sumy += foods[j].y;
				}
			}
			result = Math.min(result, Math.abs(sumx - sumy));
		}
		
		System.out.println(result);
	}
	
	static class Food{
		int x;
		int y;
		
		public Food(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
