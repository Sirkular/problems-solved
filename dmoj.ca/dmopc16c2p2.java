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
	
	static ArrayList<ArrayList<Integer>> classes = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> students = new ArrayList<ArrayList<Integer>>();
	static boolean[] quarantined;
	static boolean[] classChecked;
	static int quarantinedCount = 0;

	static void find(int SID) {
		quarantined[SID] = true;
		quarantinedCount++;
		ArrayList<Integer> studentClasses = students.get(SID);
		for (int i = 0; i < studentClasses.size(); i++) {
			if (classChecked[studentClasses.get(i)]) continue;
			classChecked[studentClasses.get(i)] = true;
			ArrayList<Integer> curClass = classes.get(studentClasses.get(i));
			for (int j = 0; j < curClass.size(); j++) {
				if (quarantined[curClass.get(j)]) continue;
				find(curClass.get(j));
			}
		}
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		quarantined = new boolean[N + 1];
		for (int i = 0; i <= N; i++) students.add(new ArrayList<Integer>());
		int M = s.nextInt();
		classChecked = new boolean[M];
		for (int i = 0; i < M; i++) {
			classes.add(new ArrayList<Integer>());
			int K = s.nextInt();
			for (int j = 0; j < K; j++) {
				int student = s.nextInt();
				students.get(student).add(i);
				classes.get(i).add(student);
			}
		}
		find(1);
		
		System.out.println(quarantinedCount);
		for (int i = 1; i <= N; i++) {
			if (quarantined[i]) System.out.print(i + " ");
		}
	}
}