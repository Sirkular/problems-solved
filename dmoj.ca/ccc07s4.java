import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ccc07s4 {
	public static class Reader {
		BufferedReader br;
		StringTokenizer st;

		public Reader(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		public Reader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String nextToken() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		String nextLine(){
			try {
				return br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		int nextInt() {
			return Integer.parseInt(nextToken());
		}

		long nextLong() {
			return Long.parseLong(nextToken());
		}

		double nextDouble() {
			return Double.parseDouble(nextToken());
		}
	}

	public static void main(String[] args){
		Map<Integer, ArrayList<Integer>> m = new HashMap<Integer, ArrayList<Integer>>();
		Reader s = new Reader();
		int p = s.nextInt();
		int dp[] = new int[p + 1];
		while (true) {
			int x = s.nextInt();
			int y = s.nextInt();
			if (y == 0) break;
			if (!m.containsKey(x)) m.put(x, new ArrayList<Integer>());
			if (!m.containsKey(y)) m.put(y, new ArrayList<Integer>());
			m.get(y).add(x);
		}
		dp[p] = 1;
		for (int i = p; i >= 0; i--) {
			if (m.containsKey(i)) {
				for (int n = 0; n < m.get(i).size(); n++) {
					dp[m.get(i).get(n)] += dp[i];
				}
			}
		}
		
		System.out.println(dp[1]);
	}

}
