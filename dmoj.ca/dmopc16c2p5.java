import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.BitSet;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.Iterator;

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

	static class Pair {
		int num, max;
		Pair (int num, int max) {
			this.num = num;
			this.max = max;
		}
	}

	static class MaxQueue {
		Deque<Pair> stack1 = new ArrayDeque<Pair>();
		Deque<Pair> stack2 = new ArrayDeque<Pair>();
		int maxSize, size = 0;

		MaxQueue (int maxSize) {
			this.maxSize = maxSize;
		}
		public int peek() {
			return Math.max((!stack1.isEmpty()) ? stack1.peek().max : 0, (!stack2.isEmpty()) ? stack2.peek().max : 0);
		}
		public void stringOf() {
			System.out.print("[");
			for(Iterator<Pair> itr = stack1.iterator(); itr.hasNext();)  {
				Pair p = itr.next();
				System.out.print("(" + p.num + "," + p.max + ")");
			}
			System.out.println("]");
			System.out.print("[");
			for(Iterator<Pair> itr = stack2.iterator(); itr.hasNext();)  {
				Pair p = itr.next();
				System.out.print("(" + p.num + "," + p.max + ")");
			}
			System.out.println("]");
		}
		public void push(int value) {
			if (size + 1 > maxSize) stack2.pop();
			else size++;
			int newMax = (!stack1.isEmpty()) ? stack1.peek().max : 0;
			stack1.push(new Pair(value, (newMax < value) ? value : newMax));
			if (stack2.isEmpty()) {
				Pair temp = stack1.pop();
				stack2.push(new Pair(temp.num, temp.num));
				while (!stack1.isEmpty()) {
					temp = stack1.pop();
					stack2.push(new Pair(temp.num, (stack2.peek().max < temp.num) ? temp.num : stack2.peek().max));
				}
			}
		}
	}

	public static void main(String[] args) {
		Reader s = new Reader();
		int N = s.nextInt();
		int M = s.nextInt();
		int K = s.nextInt();
		Queue<Character> commands = new LinkedList<Character>();
		Queue<Integer> nums = new LinkedList<Integer>();
		boolean[] reversion = new boolean[M + 1];
		for (int i = 0; i < M; i++) {
			String cur = s.nextLine();
			commands.add(cur.charAt(0));
			if (cur.charAt(0) != 'X') nums.add(Integer.valueOf(cur.split(" ")[1]));
			else nums.add(0);
			if (cur.charAt(0) == 'R') {
				reversion[Integer.valueOf(cur.split(" ")[1])] = true;
			}
		}
		BitSet sisters = new BitSet(N + 1);
		Map<Integer, BitSet> snapshots = new HashMap<Integer, BitSet>();
		Map<Integer, Integer> snapshotTotals = new HashMap<Integer, Integer>();
		snapshots.put(0, new BitSet(N + 1));
		snapshotTotals.put(0, 0);
		MaxQueue queue = new MaxQueue(K);
		int total = 0;
		for (int i = 0; i < M; i++) {
			char command = commands.poll();
			int num = nums.poll();
			if (command == 'X') {
				sisters.flip(1, N + 1);
				total = N - total;
			}
			else if (command == 'F') {
				if (!sisters.get(num)) total++;
				sisters.set(num);
			}
			else if (command == 'D') {
				if (sisters.get(num)) total--;
				sisters.clear(num);
			}
			else if (command == 'R') {
				sisters = (BitSet) snapshots.get(num).clone();
				total = snapshotTotals.get(num);
			}
			if (reversion[i + 1]) {
				snapshots.put(i + 1, (BitSet) sisters.clone());
				snapshotTotals.put(i + 1, total);

			}
			queue.push(total);
			System.out.println(total + " " + queue.peek());
		}
	}
}