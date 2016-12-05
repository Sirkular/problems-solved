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

	static class Beacon {
		int x, y, r;
		Beacon(int nx, int ny, int nr) {
			x = nx;
			y = ny;
			r = nr;
		}
	}

	static List<List<Integer>> connect;
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int B = s.nextInt();
		int Q = s.nextInt();
		connect = new ArrayList<List<Integer>>(B);
		if (true) {
			Beacon[] b = new Beacon[B];
			for (int i = 0; i < B; i++) {
				int x = s.nextInt();
				int y = s.nextInt();
				int r = s.nextInt();
				b[i] = new Beacon(x, y, r);
				connect.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < B; i++) {
				for (int j = 0; j < B; j++) {
					if (j == i) continue;
					if ((b[j].x - b[i].x)*(b[j].x - b[i].x) + (b[j].y - b[i].y)*(b[j].y - b[i].y) <= b[i].r*b[i].r) {
						connect.get(i).add(j);
					}
				}
			}
		}
		for (int i = 0; i < Q; i++) {
			int a = s.nextInt() - 1;
			int t = s.nextInt() - 1;
			if (propagate(a, new boolean[B], t)) System.out.println("YES");
			else System.out.println("NO");
		}

	}
	
	static boolean propagate(int cur, boolean[] visited, int target) {
		for (int j = 0; j < connect.get(cur).size(); j++) {
			int temp = connect.get(cur).get(j);
			if (visited[temp]) continue;
			if (target == temp) {
				return true;
			}
			visited[temp] = true;
			if (propagate(temp, visited, target)) return true;
		}
		return false;
	}
}