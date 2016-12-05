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

	static class Point {
		int r, c;
		Point(int nr, int nc) {
			r = nr;
			c = nc;
		}
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int[] cx = {0, 0, -1, 1};
		int[] cy = {-1, 1, 0, 0};
		int N = s.nextInt();
		int H = s.nextInt();
		int[][] elevation = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				elevation[i][j] = s.nextInt();
			}
		}
		Deque<Point> q = new ArrayDeque<Point>();
		q.add(new Point(0, 0));
		while (!q.isEmpty()) {
			Point p = q.pop();
			
			if (p.r == N - 1 && p.c == N - 1) {
				System.out.println("yes");
				System.exit(0);
			}
			//System.out.println(p.r + " " + p.c);
			for (int i = 0; i < 4; i++) {
				int r = p.r + cy[i];
				int c = p.c + cx[i];
				if (r < 0 || c < 0 || r >= N || c >= N) continue;
				if (visited[r][c]) continue;
				if (Math.abs(elevation[p.r][p.c] - elevation[r][c]) > H) continue;
				visited[r][c] = true;
				q.add(new Point(r, c));
			}
		}
		System.out.println("no");
		/*for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}*/
	}
}