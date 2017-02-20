import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Asdf {
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

	public static void main(String[] args) {
		Reader s = new Reader();
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		Deque<Integer> stack = new ArrayDeque<Integer>();
		int N = s.nextInt();
		int M = s.nextInt();
		for (int i = 1; i <= N; i++) {
			map.put(i, new ArrayList<Integer>());
		}
		for (int i = 0; i < M; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			if (map.get(a).indexOf(b) == -1) 
				map.get(a).add(b);
		}
		
		for (int j = 1; j <= N; j++) {
			stack.push(j);
			boolean[] v = new boolean[N + 1];
			while (!stack.isEmpty()) {
				int cur = stack.pop();
				v[cur] = true;
				for (int i = 0; i < map.get(cur).size(); i++) {
					int consider = map.get(cur).get(i);
					if (v[consider]) {
						System.out.println("N");
						System.exit(0);
					}
					stack.push(consider);
				}
			}
		}
		System.out.println("Y");
	}
}