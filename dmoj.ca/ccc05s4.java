import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

	//N - depth of tree * 2 is answer
	
	static int depth(String cur, Map<String, List<String>> map, int total) {
		int curTotal = total;
		for (int i = 0; i < map.get(cur).size(); i++) {
			curTotal = Math.max(curTotal, depth(map.get(cur).get(i), map, total + 1));
		}
		return curTotal;
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int L = s.nextInt();
		for (int cn = 0; cn < L; cn++) {
			Map<String, List<String>> map = new HashMap<String, List<String>>();
			Map<String, String> parent = new HashMap<String, String>();
			int N = s.nextInt();
			String[] list = new String[N];
			for (int i = 0; i < N; i++) 
				list[i] = s.nextLine();
			map.put(list[N - 1], new ArrayList<String>());
			parent.put(list[N - 1], "");
			parent.put(list[0], list[N - 1]);
			map.get(list[N - 1]).add(list[0]);
			map.put(list[0], new ArrayList<String>());
			String prev = list[0];
			for (int i = 1; i < N; i++) {
				String name = list[i];
				if (map.get(name) == null) map.put(name, new ArrayList<String>());
				if (parent.get(name) == null) {
					parent.put(name, prev);
					map.get(prev).add(name);
					prev = name;
				}
				else if (parent.get(prev).equals(name)) {
					prev = name;
				}
				
			}
			System.out.println(N*10 - depth(list[N - 1], map, 0)*2*10);
		}
	}
}