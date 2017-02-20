import java.util.*;
public class ccc07s3 {
	static Scanner s = new Scanner(System.in);
	static Map<Integer, Integer> adjList = new HashMap<Integer, Integer>();	

	static int search(int start, int target) {
		boolean[] visited = new boolean[10000];
		Queue<Integer> q = new LinkedList<Integer>();
		visited[start] = true;
		int dist = 0;
		q.add(start);
		int updated = 0;
		while (!q.isEmpty()) {
			int cur = q.remove();
			updated = cur;
			if (cur == target) break;
			if (adjList.containsKey(cur)) {
				if (!visited[adjList.get(cur)]){
					visited[adjList.get(cur)] = true;
					dist++;
					q.add(adjList.get(cur));
				}
			}
		}
		if (updated == target) return dist;
		return 0;
	}

	public static void main(String[] args){
		ArrayList<Integer> query = new ArrayList<Integer>();

		int n = s.nextInt();
		for (int i = 0; i < n; i++) adjList.put(s.nextInt(),s.nextInt());
		while (true) {
			int a = s.nextInt();
			int b = s.nextInt();
			if (a == 0) break;
			query.add(a);
			query.add(b);
		}
		for (int i = 0; i < query.size(); i += 2) {
			int ans = search(query.get(i), query.get(i + 1));
			if (ans > 0) System.out.println("Yes " + (ans - 1));
			else System.out.println("No");
		}

	}
}