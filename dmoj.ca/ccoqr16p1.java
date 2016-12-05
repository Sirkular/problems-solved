import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		Map<Integer, List<Point>> Ys = new HashMap<Integer, List<Point>>();
		Map<Integer, List<Point>> Xs = new HashMap<Integer, List<Point>>();
		Map<Integer, Boolean> sorted = new HashMap<Integer, Boolean>();
		int N = s.nextInt();
		for (int i = 0; i < N; i++) {
			int x = s.nextInt();
			int y = s.nextInt();
			Point tmp = new Point(x, y);
			if (Xs.get(x) == null) Xs.put(x, new ArrayList<Point>());
			if (Ys.get(y) == null) Ys.put(y, new ArrayList<Point>());
			Xs.get(x).add(tmp);
			Ys.get(y).add(tmp);
		}
		long bowties = 0;
		for (List<Point> l : Xs.values()) {
			if (l.size() < 3) continue;
			Collections.sort(Xs.get(l.get(0).x), new Comparator<Point>() {
				@Override
				public int compare(Point p1, Point p2) {
					return p1.y - p2.y;
				}
			});
			for (int i = 1; i < l.size() - 1; i++) {
				Point p = l.get(i);
				List<Point> n = Ys.get(p.y);
				if (n.size() < 3) continue;
				if (sorted.get(p.y) == null) {
					Collections.sort(Ys.get(p.y), new Comparator<Point>() {
						@Override
						public int compare(Point p1, Point p2) {
							return p1.x - p2.x;
						}
					});
					sorted.put(p.y, true);
				}
				if (p.x == n.get(n.size() - 1).x || p.x == n.get(0).x) continue;
				long U = l.size() - 1 - i;
				long D = i;
				int index = Collections.binarySearch(n, p, new Comparator<Point>() {
					@Override
					public int compare(Point p1, Point p2) {
						return p1.x - p2.x;
					}
				});
				long L = n.size() - 1 - index;
				long R = index;
				bowties += U*D*L*R*2;
			}
		}
		System.out.println(bowties);
	}
}