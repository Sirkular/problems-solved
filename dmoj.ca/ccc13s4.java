import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class testing {
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

	static Map<Integer, ArrayList<Integer>> tallerThan = new HashMap<Integer, ArrayList<Integer>>();
	static Map<Integer, Boolean> checked;
	
	static boolean isTaller (int cur, int q) {
		if (cur == q) return true;
		checked.put(cur, true);
		if (!tallerThan.containsKey(cur)) return false;
		ArrayList<Integer> list = tallerThan.get(cur);
		for (int i = 0; i < list.size(); i++) {
			if (!checked.containsKey(list.get(i))) if (isTaller(list.get(i), q)) return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int M = s.nextInt();
		for (int i = 0; i < M; i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			if (!tallerThan.containsKey(x)) tallerThan.put(x, new ArrayList<Integer>());
			tallerThan.get(x).add(y);
		}
		int p = s.nextInt();
		int q = s.nextInt();
		checked = new HashMap<Integer, Boolean>();
		boolean done = false;
		if (isTaller(p, q)) {
			System.out.println("yes");
			done = true;
		}
		checked = new HashMap<Integer, Boolean>();
		if (!done) if (isTaller(q, p)) {
			System.out.println("no");
			done = true;
		}
		if (!done) System.out.println("unknown");
	}
}