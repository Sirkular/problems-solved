import java.text.DecimalFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.StringTokenizer;

public class wc154j4 {
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



	static boolean debug = false;



	public static int lower_bound(int[] arr, int key) {
        int len = arr.length;
        int lo = 0;
        int hi = len-1;
        int mid = (lo + hi)/2;
        while (true) {
            int cmp = arr[mid] - key;
            if (cmp == 0 || cmp > 0) {
                hi = mid-1;
                if (hi < lo)
                    return mid;
            } else {
                lo = mid + 1;
                if (hi < lo)
                    return mid<len-1?mid+1:-1;
            }
            mid = (lo + hi)/2;
        }
    }

	static int[] hits;


	public static void main(String[] args) { 
		Reader s = new Reader();
		int N = s.nextInt(); //rings
		int M = s.nextInt(); //shots
		int[] R = new int[N];
		int[] P = new int[N];
		hits = new int[N];
		int max = -1;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			R[i] = s.nextInt();
			if (R[i] > max) max = R[i];
			if (R[i] < min) min = R[i];
		}
		for (int i = 0; i < N; i++) {
			P[i] = s.nextInt();
		}
		Arrays.sort(P);
		for (int i = 0; i < M; i++) {
			long x = s.nextInt();
			long y = s.nextInt();
			BigDecimal val = BigDecimal.valueOf(Math.sqrt(x*x + y*y));
			val = val.setScale(0, RoundingMode.CEILING);
			if (val.compareTo(BigDecimal.valueOf(max)) > 0) continue;
			if (val.compareTo(BigDecimal.valueOf(min)) <= 0) hits[0]++;
			
			else hits[lower_bound(R, val.intValueExact())]++;
		}
		//System.out.println(rings.toString());

		Arrays.sort(hits);
		int total = 0;
		for (int i = 0; i < N; i++) {	
			total += P[i]*hits[N - 1 - i];
		}
		System.out.println(total);
		total = 0;
		for (int i = 0; i < N; i++) {
			total += P[N - 1 - i]*hits[N - 1 - i];
		}
		System.out.println(total);
		//System.out.println(rings.toString());

	}

}

