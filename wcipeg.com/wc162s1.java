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

public class wc162s1 {
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
		int N = s.nextInt();
		List<String> in2 = new ArrayList<String>();
		if (true) {
			String[] in = s.nextLine().split(" ");
			for (int i = 0; i < N; i++) {
				in2.add(in[i]);
			}
		}
		while (in2.size() > 1) {
			int a = in2.indexOf("and");
			if (a < 0) 
				break;
			if ((in2.get(a - 1).equals("unknown") && in2.get(a + 1).equals("false")) || (in2.get(a + 1).equals("unknown") && in2.get(a - 1).equals("false"))) {
				in2.add(a + 2, "false");
			}
			else if ((in2.get(a - 1).equals("unknown") && Boolean.valueOf(in2.get(a + 1))) || (in2.get(a + 1).equals("unknown") && Boolean.valueOf(in2.get(a - 1)))) {
				in2.add(a + 2, "unknown");
			}
			else if (in2.get(a - 1).equals("unknown") && in2.get(a + 1).equals("unknown")) {
				in2.add(a + 2, "unknown");
			}
			else if (Boolean.valueOf(in2.get(a - 1)) && Boolean.valueOf(in2.get(a + 1))) {
				in2.add(a + 2,"true");
			}
			else {
				in2.add(a + 2, "false");
			}
			for (int i = 0; i < 3; i++) in2.remove(a - 1);
		}
		while (in2.size() > 1) {
			int a = in2.indexOf("or");
			if (a < 0) 
				break;
			if (Boolean.valueOf(in2.get(a - 1)) || Boolean.valueOf(in2.get(a + 1))) {
				in2.add(a + 2,"true");
			}
			else if (in2.get(a - 1).equals("unknown") || in2.get(a + 1).equals("unknown")) {
				in2.add(a + 2, "unknown");
			}
			else {
				in2.add(a + 2, "false");
			}
			for (int i = 0; i < 3; i++) in2.remove(a - 1);
		}
		System.out.println(in2.get(0));
	}
}

