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
	
	static boolean[][] players;
	static int M;
	
	static boolean dfs(int player, int[] loaded) {
		if (player == M) return false;
		for (int i = 0; i < 3; i++) {
			if (players[player][i]) {
				loaded[i]--;
				if (loaded[0] == 0 && loaded[1] == 0 && loaded[2] == 0) return true;
				if (dfs(player + 1, loaded)) return true;
				loaded[i]++;
			}
		}
		if (dfs(player + 1, loaded)) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int[][] forms = new int[N][3];
		for (int i = 0; i < N; i++) {
			String[] in = s.nextLine().split("-");
			forms[i][0] = Integer.parseInt(in[0]);
			forms[i][1] = Integer.parseInt(in[1]);
			forms[i][2] = Integer.parseInt(in[2]);
		}
		M = s.nextInt();
		players = new boolean[M][3];
		for (int i = 0; i < M; i++) {
			String in = s.nextLine();
			if (in.indexOf('O') > -1) players[i][0] = true;
			if (in.indexOf('V') > -1) players[i][1] = true;
			if (in.indexOf('N') > -1) players[i][2] = true;
		}
		for (int i = 0; i < N; i++) {
			if (dfs(0, forms[i])) System.out.println("DA");
			else System.out.println("NE");
		}
		
		
	}
}