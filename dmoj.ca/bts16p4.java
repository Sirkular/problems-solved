import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	public static void main(String[] args) {
		Reader s = new Reader();
		int C = s.nextInt();
		int R = s.nextInt();
		int T = s.nextInt();
		char[][] map = new char[R][C];
		boolean hor = true;
		int r = 0;
		int c = 0;
		for (int i = 0; i < R; i++) {
			char[] temp = s.nextLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (temp[j] == 'O') {
					r = i;
					c = j;
				}
				map[i][j] = temp[j];
			}
		}
		int tick = 0;
		int mod = 1;
		boolean stays = false;
		while (true) {
			if (hor) c += mod;
			else r += mod;
			tick++;
			try {
				if (map[r][c] == '\\') {
					hor = !hor;
					map[r][c] = '/';
				}
				else if (map[r][c] == '/') {
					hor = !hor;
					mod = -mod;
					map[r][c] = '\\';
				}
				else if (map[r][c] == '-') {
					if (!hor) {
						mod = -mod;
						map[r][c] = '|';
					}
				}
				else if (map[r][c] == '|') {
					if (hor) {
						mod = -mod;
						map[r][c] = '-';
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
			if (tick >= T) {
				System.out.println("The observer stays within the grid.");
				stays = true;
				break;
			}
		}
		if (!stays) System.out.println("The observer leaves the grid after " + tick + " tick(s).");

	}
}