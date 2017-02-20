import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

	static HashSet<Long> c = new HashSet<Long>();
	static Map<Long, List<Long>> path = new HashMap<Long, List<Long>>();
	static Map<String, Long> hashValues = new HashMap<String, Long>();
	static Map<Long, Integer> dist = new HashMap<Long, Integer>();
	
	static long hash(String in) {
		long h = 0;
		for (int i = 0; i < in.length(); i++) {
			h = (h*31 + in.charAt(i))%1000000007;
		}
		return h;
	}
	
	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int T = s.nextInt();
		hashValues.put("home", hash("home"));
		hashValues.put("Waterloo GO", hash("Waterloo GO"));
		path.put(hashValues.get("home"), new ArrayList<Long>());
		path.put(hashValues.get("Waterloo GO"), new ArrayList<Long>());
		dist.put(hashValues.get("home"), 0);
		dist.put(hashValues.get("Waterloo GO"), Integer.MAX_VALUE);
		for (int i = 0; i < N; i++) {
			String in = s.nextLine();
			long inHash = hash(in);
			hashValues.put(in, inHash);
			path.put(inHash, new ArrayList<Long>());
			dist.put(inHash, Integer.MAX_VALUE);
		}
		for (int i = 0; i < T; i++) {
			String[] in = s.nextLine().split("-");
			path.get(hashValues.get(in[0])).add(hashValues.get(in[1]));
			path.get(hashValues.get(in[1])).add(hashValues.get(in[0]));
		}
		Queue<Long> q = new LinkedList<Long>();
		q.add(hashValues.get("home"));
		while (!q.isEmpty()) {
			long cur = q.poll();
			if (cur == hashValues.get("Waterloo GO")) continue;
			for (int i = 0; i < path.get(cur).size(); i++) {
				if (dist.get(path.get(cur).get(i)) > dist.get(cur) + 1) {
					q.add(path.get(cur).get(i));
					dist.put(path.get(cur).get(i), dist.get(cur) + 1);
				}
			}
		}
		if (dist.get(hashValues.get("Waterloo GO")) == Integer.MAX_VALUE) System.out.println("RIP ACE");
		else System.out.println(dist.get(hashValues.get("Waterloo GO")));
			
		
	}
}