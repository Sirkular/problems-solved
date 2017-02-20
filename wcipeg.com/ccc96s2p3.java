import java.text.DecimalFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ccc96s2p3 {
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

	static class Node {
		Boolean f;
		Node[] more = null;
		int pix;
	}

	static int cur = 0;
	static String out = "";
	static int pixels = 0;
	
	static void fill(Node list, String in) {
		if (in.charAt(cur) == 'p') {
			cur++;
			list.more = new Node[4];
			for (int i = 0; i < 4; i++) {
				list.more[i] = new Node();
				list.more[i].pix = list.pix + 1;
				fill(list.more[i], in);
			}
		}
		else if (in.charAt(cur) == 'e') {
			list.f = false;
			cur++;
		}
		else if (in.charAt(cur) == 'f') {
			list.f = true;
			cur++;
		}
	}

	static void add(Node one, Node two) {
		if (one.more == null && two.more == null) if (!one.f && !two.f) {

			return;
		}
		if (one.more == null) if (one.f) {
			pixels += Math.pow(4, 5 - one.pix);
			return;
		}
		if (two.more == null) if (two.f) {
			pixels += Math.pow(4, 5 - two.pix);
			return;
		}
		if (one.more != null && two.more != null) { //if both are comparable
			for (int i = 0; i < 4; i++) {
				add(one.more[i], two.more[i]);
			}
			return;
		}
		if (one.more == null && two.more != null) { //if one ends / is empty

				copy(two);
			
			return;
		}
		if (one.more != null && two.more == null) { //if two ends / is empty

				copy(one);
			
		}
	}

	static void copy(Node list) {
		if (list.more == null) {
			if (list.f) {
				pixels += Math.pow(4, 5 - list.pix);
			}
		}
		else {
			for (int i = 0; i < 4; i++) {
				copy(list.more[i]);
			}
		}
	}

	static void pr(Node list) {
		if (list.more == null) {
			if (list.f) System.out.print("f");
			else System.out.print("e");
		}
		else {
			System.out.print("p");
			for (int i = 0; i < 4; i++) {
				pr(list.more[i]);
			}
		}
	}

	public static void main(String[] args) { 
		Reader s = new Reader();
		int N = s.nextInt();

		for (int CASE = 0; CASE < N; CASE++) {
			String one = s.nextLine();
			String two = s.nextLine();
			Node list = new Node();
			list.pix = 0;
			fill(list, one);
			cur = 0;
			Node list2 = new Node();
			list2.pix = 0;
			fill(list2, two);
			
			/*
			pr(list);
			System.out.println();
			pr(list2);
			System.out.println();
			*/
			add(list, list2);
			System.out.println("There are " + pixels + " black pixels.");
			cur = 0;
			pixels = 0;
		}
	}

}