import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

public class comp {
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

	static List<List<Integer>> paths;
	static Map<Integer, Integer> Ddist;
	static long convicts = 0;
	
	static void dfs(int cur, int dist) {
		if (paths.get(cur).size() == 0) {
			Ddist.put(cur, Math.min(Ddist.get(cur), dist));
			convicts++;
		}
		for (int i = 0; i < paths.get(cur).size(); i++) {
			dfs(paths.get(cur).get(i), dist + 1);
		}
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int M = s.nextInt();
		BitSet notEntr = new BitSet(N);
		BitSet notDest = new BitSet(N);
		paths = new ArrayList<List<Integer>>(N);
		Ddist = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++) paths.add(new ArrayList<Integer>());
		for (int i = 0; i < M; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			notDest.set(a);
			notEntr.set(b);
			paths.get(a).add(b);
		}
		int pointer = 0;
		List<Integer> destinations = new ArrayList<Integer>();
		while (true) {
			pointer = notDest.nextClearBit(pointer);
			if (pointer >= N) break;
			destinations.add(pointer);
			Ddist.put(pointer, Integer.MAX_VALUE);
			pointer++;
		}
		pointer = 0;
		while (true) {
			pointer = notEntr.nextClearBit(pointer);
			if (pointer >= N) break;
			dfs(pointer, 1);
			pointer++;
		}
		System.out.println(convicts%1000000007);
		for (Integer d : destinations) {
			System.out.print(Ddist.get(d) + " ");
		}
	}
}