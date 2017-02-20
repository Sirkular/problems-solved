import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

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

	static class Edge {
		int a, b;
		double r;
		Edge (int aa, int bb, double rr) {
			a = aa;
			b = bb;
			r = rr;
		}
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int M = s.nextInt();
		Map<String, Integer> ind = new HashMap<String, Integer>();
		List<Edge> ex = new ArrayList<Edge>(M);
		for (int i = 0; i < N; i++) {
			String in = s.nextLine();
			ind.put(in, i);
		}
		for (int i = 0; i < M; i++) {
			String[] in = s.nextLine().split(" ");
			ex.add(new Edge(ind.get(in[0]), ind.get(in[1]), Double.parseDouble(in[2])));
		}
		double[] val = new double[N];
		val[ind.get("APPLES")] = 1;
		for (int i = 0; i < N - 1; i++) for (Edge e : ex) if (val[e.a]*e.r > val[e.b]) val[e.b] = val[e.a]*e.r;
		
		double apples = val[ind.get("APPLES")];
		for (Edge e : ex) if (val[e.a]*e.r > val[e.b]) val[e.b] = val[e.a]*e.r;
		if (val[ind.get("APPLES")] - apples >= 0.01) System.out.println("YA");
		else System.out.println("NAW");
		
	}
}