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
		long mod = (long) Math.pow(10, 9) + 7;
		long exp = 31;
		String[] input = s.nextLine().split(" ");
		char[] in0 = input[0].toCharArray();
		char[] in1 = input[1].toCharArray();
		int ceil = Math.min(in0.length, in1.length);
		long[] hash0 = new long[ceil];
		long[] hash1 = new long[ceil];
		hash0[0] = in0[in0.length - 1];
		hash1[0] = in1[0];
		for (int i = 1; i < ceil; i++) {
			hash0[i] = (hash0[i - 1] + exp*in0[in0.length - i - 1])%mod;
			exp = (31*exp)%mod;
			//System.out.print(hash0[i] + " ");
		}
		//System.out.println();
		for (int i = 1; i < ceil; i++) {
			hash1[i] = (hash1[i - 1]*31 + in1[i])%mod;
			//System.out.print(hash1[i] + " ");
		}
		//System.out.println();
		for (int i = ceil - 1; i >= 0; i--) {
			if (hash0[i] == hash1[i]) {
				System.out.println(input[0] + input[1].substring(i + 1));
				System.exit(0);
			}
		}
		System.out.println(input[0] + input[1]);

	}
}