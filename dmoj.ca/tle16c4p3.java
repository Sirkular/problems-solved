import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

	private static void swap( final int[] c, final int i, final int j ) {
		final int tmp = c[ i ];
		c[ i ] = c[ j ];
		c[ j ] = tmp;
	}

	private static int getFirst( final int[] c ) {
		for ( int i = c.length - 2; i >= 0; --i ) if (c[ i ] - c[ i + 1 ] < 0 ) return i;
		return -1;
	}


	private static int[] nextPermutation( final int[] c ) {
		// 1. finds the largest k, that c[k] < c[k+1]
		int first = getFirst( c );
		if ( first == -1 ) return null; // no greater permutation
		// 2. find last index toSwap, that c[k] < c[toSwap]
		int toSwap = c.length - 1;
		while (c[ first ] - c[ toSwap ] >= 0 ) --toSwap;
		// 3. swap elements with indexes first and last
		swap( c, first++, toSwap );
		// 4. reverse sequence from k+1 to n (inclusive) 
		toSwap = c.length - 1;
		while ( first < toSwap ) swap( c, first++, toSwap-- );
		return c;
	}


	static class Pair {
		int a, b;
		Pair (int aa, int bb) {
			a = aa;
			b = bb;
		}
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		boolean[][] beam = new boolean[9][9];
		int[] permutations = new int[8];
		List<Pair> beams = new ArrayList<Pair>();
		beams.add(new Pair(1, 2));
		beams.add(new Pair(1, 3));
		beams.add(new Pair(1, 5));
		beams.add(new Pair(2, 4));
		beams.add(new Pair(2, 6));
		beams.add(new Pair(3, 4));
		beams.add(new Pair(3, 7));
		beams.add(new Pair(4, 8));
		beams.add(new Pair(5, 6));
		beams.add(new Pair(5, 7));
		beams.add(new Pair(6, 8));
		beams.add(new Pair(7, 8));
		for (int i = 1; i <= 8; i++) permutations[i - 1] = i;
		for (int i = 0, a, b; i < N; i++) {
			a = s.nextInt();
			b = s.nextInt();
			beam[a][b] = true;
			beam[b][a] = true;
		}
		do {
			boolean valid = true;
			for (Pair i : beams) {
				int a = permutations[i.a - 1];
				int b = permutations[i.b - 1];
				if (!beam[a][b]) valid = false;
			}
			if (valid) {
				System.out.println("Ja");
				System.exit(0);
			}
		} while (nextPermutation(permutations) != null);
		System.out.println("Nej");
	}
}