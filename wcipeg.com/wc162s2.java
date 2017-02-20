import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.StringTokenizer;

public class wc161s2 {
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
	
	static class Edge {
		int a, b, w;
		Edge (int aa, int bb, int ww) {
			a = aa;
			b = bb;
			w = ww;
		}
	}
	
	static boolean trim(int cur, int parent) {
		List<Edge> temp = paths.get(cur);
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).b != parent) {
				if (trim(temp.get(i).b, cur)) {
					temp.remove(i);
					i--;
				}
			}
		}
		if (paths.get(cur).size() == 1 && useful.get(cur) == null) return true;
		return false;
	}

	static int add(int cur, int parent) {
		int total = 0;
		List<Edge> temp = paths.get(cur);
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).b != parent) {
				total += temp.get(i).w + add(temp.get(i).b, cur);
			}
		}
		return total;
	}
	
	static List<List<Edge>> paths;
	static Map<Integer, Boolean> useful;
	
	public static void main(String[] args) {
		Reader s = new Reader();
		useful = new HashMap<Integer, Boolean>();
		int N = s.nextInt();
		int K = s.nextInt();
		paths = new ArrayList<List<Edge>>(N);
		for (int i = 0; i < N; i++) paths.add(new ArrayList<Edge>());
		for (int i = 0; i < N - 1; i++) {
			int A = s.nextInt() - 1;
			int B = s.nextInt() - 1;
			int M = s.nextInt();
			paths.get(A).add(new Edge(A, B, M));
			paths.get(B).add(new Edge(B, A, M));
		}
		for (int i = 0; i < K; i++) useful.put(s.nextInt() - 1, true);
		trim(0, -1);
		System.out.println(add(0, -1));
	}
}