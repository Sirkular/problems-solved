import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

	static class Pair implements Comparable<Pair> {
		int num, index;
		Pair(int n, int i) {
			num = n;
			index = i;
		}
		@Override
		public int compareTo(Pair arg0) {
			return this.num - arg0.num;
		}
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int K = s.nextInt();
		int Q = s.nextInt();
		if (N <= 1000 && Q <= 1000) {
			Map<Integer, ArrayList<Integer>> occur = new HashMap<Integer, ArrayList<Integer>>();
			int[] seq = new int[N];
			int[] sum = new int[N + 1];
			for (int i = 0; i < N; i++) {
				seq[i] = s.nextInt();
				sum[i + 1] = seq[i] + sum[i];
				try {
					occur.get(seq[i]).add(i);
				} catch (NullPointerException e) {
					occur.put(seq[i], new ArrayList<Integer>());
					occur.get(seq[i]).add(i);
				}
			}
			for (int q = 0; q < Q; q++) {
				int a = s.nextInt();
				int b = s.nextInt();
				int x = s.nextInt() - 1;
				int y = s.nextInt() - 1;
				if (sum[y + 1] - sum[x] <= K) {
					System.out.println("No");
					continue;
				}
				boolean hasA = false;
				boolean hasB = false;
				ArrayList<Integer> list = new ArrayList<Integer>();
				list = occur.get(a);
				int ceil;
				try {
					ceil = list.size();
				} catch (NullPointerException e) {
					System.out.println("No");
					continue;
				}
				for (int i = 0; i < list.size(); i++) {
					int cur = list.get(i);
					if (cur >= x && cur <= y) {
						hasA = true;
						break;
					}
				}
				if (!hasA) {
					System.out.println("No");
					continue;
				}
				list = occur.get(b);
				try {
					ceil = list.size();
				} catch (NullPointerException e) {
					System.out.println("No");
					continue;
				}
				for (int i = 0; i < ceil; i++) {
					int cur = list.get(i);
					if (cur >= x && cur <= y) {
						hasB = true;
						break;
					}
				}
				if (!hasB) {
					System.out.println("No");
					continue;
				}
				System.out.println("Yes");
			}
		}
		else {
			N++;
			int[] seq = new int[N];
			Pair[] sortedSeq = new Pair[N - 1];
			int[] sum = new int[N];
			for (int i = 1; i < N; i++) {
				int a = s.nextInt();
				seq[i] = a;
				sortedSeq[i - 1] = new Pair(a, i);
				sum[i] += sum[i - 1] + seq[i];
			}
			Arrays.sort(sortedSeq);
			for (int i = 0; i < Q; i++) {
				int a = s.nextInt();
				int b = s.nextInt();
				int x = s.nextInt();
				int y = s.nextInt();
				if (sum[y] - sum[x - 1] <= K) {
					System.out.println("No");
					continue;
				}
				int aIndex = Arrays.binarySearch(sortedSeq, new Pair(a, 0));
				if (aIndex < 0) {
					System.out.println("No");
					continue;
				}
				int bIndex = Arrays.binarySearch(sortedSeq, new Pair(b, 0));
				if (bIndex < 0) {
					System.out.println("No");
					continue;
				}
				int tInd = aIndex;
				boolean isThere = false;
				while (sortedSeq[tInd].num == a) {
					if (sortedSeq[tInd].index >= x && sortedSeq[tInd].index <= y) {
						isThere = true;
						break;
					}
					if (tInd + 1 >= N - 1) break;
					tInd++;
				}
				tInd = aIndex;
				while (sortedSeq[tInd].num == a) {
					if (sortedSeq[tInd].index >= x && sortedSeq[tInd].index <= y) {
						isThere = true;
						break;
					}
					if (tInd - 1 < 0) break;
					tInd--;
				}
				if (!isThere) {
					System.out.println("No");
					continue;
				}
				tInd = bIndex;
				while (sortedSeq[tInd].num == b) {
					if (sortedSeq[tInd].index >= x && sortedSeq[tInd].index <= y) {
						isThere = true;
						break;
					}
					if (tInd + 1 >= N - 1) break;
					tInd++;
				}
				tInd = bIndex;
				while (sortedSeq[tInd].num == b) {
					if (sortedSeq[tInd].index >= x && sortedSeq[tInd].index <= y) {
						isThere = true;
						break;
					}
					if (tInd - 1 < 0) break;
					tInd--;
				}
				if (!isThere) {
					System.out.println("No");
					continue;
				}
				System.out.println("Yes");
			}
		}
	}
}