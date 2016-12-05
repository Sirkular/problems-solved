import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Iterator;

public class placeholder {
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

	static class Snowflake {
		int[] arms = new int[6];
		List<Integer> load () {
			List<Integer> a = new ArrayList<Integer>(6);
			for (int i = 0; i < 6; i++) a.add(arms[i]);
			return a;
		}
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		Map<Integer, List<Snowflake>> list = new HashMap<Integer, List<Snowflake>>();
		int N = s.nextInt();
		for (int i = 0; i < N; i++) {
			Snowflake flake = new Snowflake();
			int total = 0;
			for (int j = 0; j < 6; j++) {
				flake.arms[j] = s.nextInt();
				total += flake.arms[j];
			}
			boolean isTwin = true;
			if (list.get(total) == null) list.put(total, new ArrayList<Snowflake>());
			else {
				List<Snowflake> temp = list.get(total);
				for (int j = 0; j < temp.size(); j++) {
					List<Integer> arms = temp.get(j).load();
					for (int n = 0; n < 6; n++) {
						int t = arms.indexOf(flake.arms[n]);
						if (t > -1) arms.remove(t); 
						else {
							isTwin = false;
							break;
						}
					}
					if (isTwin) {
						System.out.println("Twin snowflakes found.");
						System.exit(0);
					}
				}
			}
			list.get(total).add(flake);
		}
		System.out.println("No two snowflakes are alike.");
	}
}