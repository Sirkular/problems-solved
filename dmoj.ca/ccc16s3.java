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

	static int N, M, nodeCount;
	static int start = -1, startDist = 0;
	static List<List<Integer>> path = new ArrayList<List<Integer>>();
	static boolean[] pho;

	static boolean trim(int cur, int parent) {
		if (path.get(cur).size() == 1 && !pho[cur]) return true;
		for (int i = 0; i < path.get(cur).size(); i++) {
			if (path.get(cur).get(i) == parent) continue;
			if (trim(path.get(cur).get(i), cur)) {
				path.get(cur).remove(i);
				nodeCount--;
				i--;
			}
		}
		if (path.get(cur).size() == 1 && !pho[cur]) return true;
		return false;
	}

	static void farthest(int cur, int parent, int dist) {
		if (path.get(cur).size() == 1) if (startDist < dist) {
			start = cur;
			startDist = dist;
		}
		for (int i = 0; i < path.get(cur).size(); i++) {
			if (path.get(cur).get(i) == parent) continue;
			farthest(path.get(cur).get(i), cur, dist + 1);
		}
	}

	static int length(int cur, int parent) {
		int total = 0;
		for (int i = 0; i < path.get(cur).size(); i++) {
			if (path.get(cur).get(i) == parent) continue;
			total = Math.max(total, length(path.get(cur).get(i), cur));
		}
		return total + 1;
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		N = s.nextInt();
		M = s.nextInt();
		nodeCount = N;
		pho = new boolean[N];
		List<Integer> phoN = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			int x = s.nextInt();
			phoN.add(x);
			pho[x] = true;
		}
		for (int i = 0; i < N; i++) {
			path.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N - 1; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			path.get(a).add(b);
			path.get(b).add(a);
		}
		trim(phoN.get(0), -1);
		farthest(phoN.get(0), -1, 0);
		int maxLength = 0;
		maxLength = length(start, -1) - 1;
		System.out.println(2 * (nodeCount - 1) - maxLength);
	}
}