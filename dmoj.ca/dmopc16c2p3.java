import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
		int T = s.nextInt();
		for (int cn = 0; cn < T; cn++) {
			int N = s.nextInt();
			int M = s.nextInt();
			int maxA = 0;
			int maxB = 0;
			boolean canDo = true;
			for (int i = 0; i < M; i++) {
				int A = s.nextInt();
				int B = s.nextInt();
				if (A > maxA) maxA = A;
				if (B > maxB) maxB = B;
				if (A + B > N) {
					canDo = false;
					break;
				}
			}
			if (!canDo) System.out.println(-1);
			else {
				for (int i = 0; i < maxA; i++) System.out.print("a");
				for (int i = 0; i < maxB; i++) System.out.print("b");
			}
			System.out.println();
		}

	}
}