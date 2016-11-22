import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		int b, w;
		Edge (int b, int w) {
			this.b = b;
			this.w = w;
		}
	}
	
	static class Pair {
		int c, w;
		Pair (int c, int w) {
			this.c = c;
			this.w = w;
		}
	}
	
	static int N, C, K;
	static int[] cherries;
	static List<List<Edge>> branches;
	static int unique = 0;
	
	static Pair analyze(int cur, int weight, int parent) {
		Pair cut = new Pair(cherries[cur], weight);
		List<Edge> thisBranches = branches.get(cur);
		for (int i = 0; i < thisBranches.size(); i++) {
			Edge pair = thisBranches.get(i);
			if (pair.b == parent) continue; 
			Pair curPair = analyze(pair.b, pair.w, cur);
			cut.c += curPair.c;
			cut.w += curPair.w;
		}
		if (cut.c >= C && cut.w <= K) unique++;
		return cut;
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		N = s.nextInt();
		C = s.nextInt();
		K = s.nextInt();
		cherries = new int[N];
		branches = new ArrayList<List<Edge>>(N);
		for (int i = 0; i < N; i++) {
			cherries[i] = s.nextInt();
			branches.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < N - 1; i++) {
			int a = s.nextInt() - 1;
			int b = s.nextInt() - 1;
			int w = s.nextInt();
			branches.get(a).add(new Edge(b, w));
			branches.get(b).add(new Edge(a, w));
		}
		for (int i = 0; i < branches.get(0).size(); i++) 
			analyze(branches.get(0).get(i).b, branches.get(0).get(i).w, 0);
		
		System.out.println(unique);

	}
}