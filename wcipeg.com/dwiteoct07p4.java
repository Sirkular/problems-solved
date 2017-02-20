import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class dwiteoct07p4 {
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
				// TODO Auto-generated catch block
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

	public static void main(String[] args){
		Reader s = new Reader();
		int H = s.nextInt();
		int S = s.nextInt();
		int[] blocks = new int[S];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < S; i++) blocks[i] = s.nextInt();
		for (int i = 0; i < S; i++) {
			boolean[] used = new boolean[S];
			used[i] = true;
			int blocksUsed = tower(blocks[i], blocks, used, H, 1);
			if (blocksUsed != 0) min = Math.min(min, blocksUsed);
		}
		if (min == Integer.MAX_VALUE) System.out.println(0);
		else System.out.println(min);
	}

	public static int tower(int cur, int[] blocks, boolean[] used, int target, int blocksUsed) {
		boolean[] copy = new boolean[used.length];
		for (int i = 0; i < blocks.length; i++) copy[i] = used[i];
		if (cur > target) return 0;
		if (cur == target) return blocksUsed;
		int min = Integer.MAX_VALUE;
		boolean done = false;
		for (int i = 0; i < blocks.length; i++) {
			done = true;
			if (!used[i]) {
				copy[i] = true;
				int towerSize = tower(cur + blocks[i], blocks, copy, target, blocksUsed + 1);
				if (towerSize != 0) min = Math.min(min, towerSize);
			}
		}
		
		if (done) {
			if (min == Integer.MAX_VALUE) {
				return 0;
			}
			else {
				return min;
			}
		}
		else return blocksUsed;
	}

}
