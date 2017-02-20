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

	static class Point {
		int r, c;
		Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		int[] cx = {0, 0, -1, 1};
		int[] cy = {-1, 1, 0, 0};
		int R = s.nextInt();
		int C = s.nextInt();
		char[][] map = new char[R][C];
		int[][] dist = new int[R][C];
		int MR = s.nextInt();
		int MC = s.nextInt();
		int OR = s.nextInt();
		int OC = s.nextInt();
		for (int i = 0; i < R; i++) {
			String row = s.nextLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = row.charAt(j);
			}
		}
		int T = s.nextInt();
		Point[] telepads = new Point[T];
		for (int i = 0; i < T; i++) {
			int r = s.nextInt();
			int c = s.nextInt();
			telepads[i] = new Point(r, c);
			map[r][c] = 'T';
		}
		for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
		dist[MR][MC] = 0;
		Queue<Point> move = new LinkedList<Point>();
		move.add(new Point(MR, MC));
		while (!move.isEmpty()) {
			Point cur = move.poll();
			int r = cur.r;
			int c = cur.c;
			for (int i = 0; i < 4; i++) {
				int cr = r + cy[i];
				int cc = c + cx[i];
				if (cr < 0 || cr >= R || cc < 0 || cc >= C) continue;
				if (map[cr][cc] == 'X') continue;
				if (dist[r][c] + 1 > dist[cr][cc]) continue;
				dist[cr][cc] = dist[r][c] + 1;
				if (map[cr][cc] == 'T') continue;
				move.add(new Point(cr, cc));
			}
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < T; i++) min = Math.min(min, dist[telepads[i].r][telepads[i].c]);
		if (min >= dist[OR][OC]) System.out.println(0);
		else System.out.println(dist[OR][OC] - min);
		
	}
}