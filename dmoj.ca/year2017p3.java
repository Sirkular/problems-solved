import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int K = s.nextInt();
		int N = s.nextInt();
		if (N < K - 1) {
			System.out.println("-1");
			System.exit(0);
		}
		long[] val = new long[N + 1];
		for (int i = 1; i < N + 1; i++) {
			val[i] = s.nextInt();
		}
		if (K == 1) {
			System.out.println(Math.max(val[1], val[2]));
			System.exit(0);
		}
		long maxCool = (N >= K) ? val[K] : 0;
		long curCool = 0;
		for (int i = K - 1; i >= 1; i -= 2) {
			curCool += val[i];
			maxCool = Math.max(maxCool, curCool + val[i - 1]);
		}
		System.out.println(maxCool);
	}
}