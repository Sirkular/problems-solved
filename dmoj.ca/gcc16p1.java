import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
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

	static class Pair {
		int a, b;
		Pair(int aa, int bb) {
			a = aa;
			b = bb;
		}
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int A = s.nextInt();
		int C = s.nextInt();
		Map<Integer, Integer> com = new HashMap<Integer, Integer>();
		List<Integer> events = new ArrayList<Integer>(A + C);
		List<Pair> allowed = new ArrayList<Pair>();
		List<Pair> denied = new ArrayList<Pair>();
		int[] difAnime = new int[500010];
		int[] difCommit = new int[500010];
		for (int i = 0; i < A; i++) { //allowed
			int a = s.nextInt();
			int b = s.nextInt();
			events.add(a);
			events.add(b + 1);
			allowed.add(new Pair(a, b));
		}
		for (int i = 0; i < C; i++) { //denied
			int a = s.nextInt();
			int b = s.nextInt();
			events.add(a);
			events.add(b + 1);
			denied.add(new Pair(a, b));
		}
		Collections.sort(events);
		for (int i = 0; i < events.size(); i++) com.put(events.get(i), i);
		for (int i = 0; i < A; i++) {
			allowed.get(i).a = com.get(allowed.get(i).a);
			allowed.get(i).b = com.get(allowed.get(i).b + 1);
		}
		for (int i = 0; i < C; i++) {
			denied.get(i).a = com.get(denied.get(i).a);
			denied.get(i).b = com.get(denied.get(i).b + 1);
		}
		for (int i = 0; i < allowed.size(); i++) {
			difAnime[allowed.get(i).a]++;
			difAnime[allowed.get(i).b]--;
		}
		for (int i = 0; i < events.size(); i++) {
			if (i > 0) difAnime[i] += difAnime[i - 1];
		}
		for (int i = 0; i < denied.size(); i++) {
			difCommit[denied.get(i).a]++;
			difCommit[denied.get(i).b]--;
		}
		for (int i = 0; i < events.size(); i++) {
			if (i > 0) difCommit[i] += difCommit[i - 1];
		}
		int total = 0;
		for (int i = 0; i < events.size(); i++) {
			if (difAnime[i] > 0 && difCommit[i] == 0) {
				total += events.get(i + 1) - events.get(i);
			}
		}
		System.out.println(total);
	}
}