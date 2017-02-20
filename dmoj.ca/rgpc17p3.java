import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
public class prac {
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
		int Q = s.nextInt();
		for (int q = 0; q < Q; q++) {
			int N = s.nextInt();
			long[] coeff = new long[N + 1];
			int[] roots = new int[N];
			for (int i = 0; i < N; i++) {
				int root = s.nextInt();
				roots[i] = root;
			}
			long x = s.nextLong();
			long y = s.nextLong();
			long divisor = x - roots[0];
			for (int i = 1; i < N; i++) {
				divisor *= x - roots[i];
			}
			long a = y/divisor;
			coeff[1] = 1;
			coeff[0] = -roots[0];
			long[] add = new long[N + 1];
			for (int i = 1; i < N; i++) {
				for (int j = 0; j <= i; j++) {
					add[j + 1] = coeff[j];
				}
				
				for (int j = 0; j <= i + 1; j++) {
					coeff[j] *= -roots[i];
					coeff[j] += add[j];
				}
				//System.out.print("Test: ");
				//for (int j = N; j >= 0; j--) System.out.print(coeff[j] + " ");
				//System.out.println();
			}
			for (int i = 0; i < N; i++) {
				coeff[i] *= a;
			}
			coeff[N] = a;
			for (int i = N; i >= 0; i--) System.out.print(coeff[i] + " ");
			System.out.println();
		}

	}
}