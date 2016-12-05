import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
		int r, c;
		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int addUp(int num) {
		if (num == 1) return 1;
		return num*num + addUp(num - 1);
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt() + 1;
		int troy = 0;
		boolean[][] h = new boolean[N][N];
		int[][] left = new int[N + 1][N + 1];
		int[][] right = new int[N + 1][N + 1];
		for (int i = 1; i < N; i++) {
			String in = s.nextLine();
			for (int j = 1; j < N; j++) 
				if (in.charAt(j - 1) == '#') h[i][j] = true;
		}
		for (int i = 1; i < N; i++) 
			for (int j = 1; j < N; j++) 
				if (h[i][j]) left[i][j] = Math.min(left[i - 1][j], left[i][j - 1]) + 1;
		for (int i = 1; i < N; i++) 
			for (int j = N - 1; j > 0; j--) 
				if (h[i][j]) {
					right[i][j] = Math.min(right[i - 1][j], right[i][j + 1]) + 1;
					troy += Math.min(left[i][j], right[i][j]);
				}
		System.out.println(troy);
	}
}