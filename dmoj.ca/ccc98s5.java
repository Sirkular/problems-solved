import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	static int[] cr = {0, 0, -1, 1};
	static int[] cc = {-1, 1, 0, 0} ;
	

	public static void main(String[] args) {
		Reader s = new Reader();
		int trips = s.nextInt();
		for (int k = 0; k < trips; k++) {
			int N = s.nextInt();
			int[][] grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					grid[i][j] = s.nextInt();
				}
			}
			int[][] oxy = new int[N][N];
			for (int[] row : oxy) Arrays.fill(row, Integer.MAX_VALUE);
			oxy[0][0] = 0;
			int elev = grid[0][0];
			Queue<Pt> q = new LinkedList<Pt>();
			q.add(new Pt(0, 0));
			int debug = 0;
			while (!q.isEmpty()) {
				Pt now = q.poll();
				int r = now.y;
				int c = now.x;
				int o = oxy[r][c];
				for (int i = 0; i < 4; i++) {
					int nr = r + cr[i];
					int nc = c + cc[i];

					if (nr < N && nr >= 0 && nc < N && nc >= 0) {
						if (grid[nr][nc] > grid[r][c]) {
							if (grid[r][c] + 2 < grid[nr][nc]) {
								continue;
							}
						}
						if (grid[nr][nc] < grid[r][c]) {
							if (grid[r][c] - 2 > grid[nr][nc]) {
								continue;
							}
						}
						
						int newOxy = (grid[nr][nc] > elev || grid[r][c] > elev) ? o + 1 : o;
						if (oxy[nr][nc] <= newOxy) continue;
						oxy[nr][nc] = newOxy;
						q.add(new Pt(nc, nr));
					}
				}
			}
			if (oxy[N - 1][N - 1] != Integer.MAX_VALUE) {
				System.out.println(oxy[N - 1][N - 1]);
			}
			else {
				System.out.println("CANNOT MAKE THE TRIP");
			}
			System.out.println();
		}
		
		

	}
}