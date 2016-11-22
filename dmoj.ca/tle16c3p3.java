import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

	static class Point {
		int x, y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		Map<Integer, Integer> period = new HashMap<Integer, Integer>();
		Map<Integer, List<Integer>> students = new HashMap<Integer, List<Integer>>();
		List<List<Integer>> classes = new ArrayList<List<Integer>>();
		Reader s = new Reader();
		int N = s.nextInt();
		boolean[] checked = new boolean[N];
		int nerd = s.nextInt();
		int girl = s.nextInt();
		for (int i = 0; i < N; i++) {
			int p = s.nextInt();
			period.put(i, p);
			classes.add(new ArrayList<Integer>());
			int S = s.nextInt();
			for (int j = 0; j < S; j++) {
				int SID = s.nextInt();
				if (students.get(SID) == null) students.put(SID, new ArrayList<Integer>());
				students.get(SID).add(i);
				classes.get(i).add(SID);
			}
		}
		Queue<Integer> curStu = new LinkedList<Integer>();
		Queue<Integer> passes = new LinkedList<Integer>();
		Queue<Integer> cTime = new LinkedList<Integer>();
		curStu.add(nerd);
		passes.add(0);
		cTime.add(0);
		int min = Integer.MAX_VALUE;
		int time = Integer.MAX_VALUE;
		while (!curStu.isEmpty()) {
			int SID = curStu.poll();
			int pass = passes.poll();
			int curP = cTime.poll();
			if (pass > min) break;
			List<Integer> inClasses = students.get(SID);
			for (int i = 0; i < inClasses.size(); i++) {
				int CID = inClasses.get(i);
				List<Integer> curClass = classes.get(CID);
				int cT = period.get(CID);
				if (cT < curP || checked[CID]) continue;
				checked[CID] = true;
				for (int j = 0; j < curClass.size(); j++) {
					int curSID = curClass.get(j);
					if (curSID == girl) {
						if (pass + 1 <= min) {
							min = pass + 1;
							time = Math.min(time, cT);
						}
						break;
					}
					curStu.add(curSID);
					passes.add(pass + 1);
					cTime.add(cT);
				}
			}
		}
		if (min == Integer.MAX_VALUE) System.out.println("delivery failure");
		else {
			System.out.println(min);
			System.out.println(time);
		}

	}
}