import java.util.*;
import java.io.*;

public class BOJ2343 {

	static int n, m;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		long left = 0l;
		long right = 0l;
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right += arr[i];
			left = Math.max(left, arr[i]);
		}

		long result = Long.MAX_VALUE;

		while(left <= right) {
			long mid = (left + right) / 2;

			int cnt = simulate(mid);

			if(cnt <= m) {
				result = Math.min(result, mid);
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(result);
	}

	private static int simulate(long mid) {
		int idx = 0;
		int cnt = 1;
		long tmp = 0l;
		while(idx < n) {
			if(tmp + arr[idx] <= mid) {
				tmp += arr[idx];
			} else {
				tmp = arr[idx];
				cnt++;
			}
			idx++;
		}

		return cnt;
	}
}
