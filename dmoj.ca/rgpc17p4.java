import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

	static class Pt {
		int x, y;
		Pt(int xx, int yy) {
			x = xx;
			y = yy;
		}
	}
	
	static class Link {
		int a, b, d;
		Link (int aa, int bb, int dd) {
			a = aa;
			b = bb;
			d = dd;
		}
		Link (int bb, int dd) {
			b = bb;
			d = dd;
		}
	}

	static int[] cr = {0, 0, -1, 1};
	static int[] cc = {-1, 1, 0, 0};
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int M = s.nextInt();
		List<Link> path = new ArrayList<Link>();
		for (int i = 0; i < M; i++) {
			int a = s.nextInt() - 1;
			int b = s.nextInt() - 1;
			int d = -1*(s.nextInt());
			path.add(new Link(a, b, d));
		}
		long[] dist = new long[N];
		int[] pred = new int[N];
		for (int i = 0; i < N; i++) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
		}
		dist[0] = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < path.size(); j++) {
				Link cur = path.get(j);
				if (dist[cur.a] + cur.d < dist[cur.b]) {
					dist[cur.b] = dist[cur.a] + cur.d;
					pred[cur.b] = cur.a;
				}
			}
		}
		for (int j = 0; j < path.size(); j++) {
			Link cur = path.get(j);
			if (dist[cur.a] + cur.d < dist[cur.b]) {
				System.out.println(-1);
				System.exit(0);
			}
		}
		int scene = 1;
		int cur = N - 1;
		while (true) {
			cur = pred[cur];
			scene++;
			if (cur == 0) break;
		}
		System.out.println(-1*dist[N - 1] + " " + scene);
		
	}
}