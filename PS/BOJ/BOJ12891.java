import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12891 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int result = 0;
		
		String str = br.readLine();
		
		st = new StringTokenizer(br.readLine());
		
		int[] cnts = new int[4];
		int[] currentCnts = new int[4];
		
		for(int i = 0; i < 4; i++) {
			cnts[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < m; i++) {
			char ch = str.charAt(i);
			
			switch(ch) {
			case 'A':
				currentCnts[0]++;
				break;
				
			case 'C':
				currentCnts[1]++;
				break;
			case 'G':
				currentCnts[2]++;
				break;
			case 'T':
				currentCnts[3]++;
				break;
			default:break;
			}
			
		}
		
		for(int i = m; i < n; i++) {
			
			if(isOk(currentCnts, cnts)) {
				result++;
			}
			
			
			char ch = str.charAt(i);
			
			switch(ch) {
			case 'A':
				currentCnts[0]++;
				break;
				
			case 'C':
				currentCnts[1]++;
				break;
			case 'G':
				currentCnts[2]++;
				break;
			case 'T':
				currentCnts[3]++;
				break;
			default:break;
			}
			
			char ch2 = str.charAt(i-m);
			switch(ch2) {
			case 'A':
				currentCnts[0]--;
				break;
				
			case 'C':
				currentCnts[1]--;
				break;
			case 'G':
				currentCnts[2]--;
				break;
			case 'T':
				currentCnts[3]--;
				break;
			default:break;
			}
			
		}
		if(isOk(currentCnts, cnts)) {
			result++;
		}
		
		System.out.println(result);
	}
	
	
	static boolean isOk(int[] currentCnts, int[] cnts) {
		for(int j = 0; j < 4; j++) {
			if(!(currentCnts[j] >= cnts[j])) {
				return false;
			}
		}
		
		return true;
	}
}
