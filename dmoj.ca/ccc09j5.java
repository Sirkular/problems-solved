import java.util.*;
public class ccc09s3 {
	static Scanner s = new Scanner(System.in);
	static Map<Integer, ArrayList<Integer>> friends = new HashMap<Integer, ArrayList<Integer>>();
	static void addLink(int a, int b) {
		if (!friends.get(a).contains(b)) {
			friends.get(a).add(b);
			friends.get(b).add(a);
		}
	}
	static void delLink(int a, int b) {
		friends.get(a).remove(friends.get(a).indexOf(b));
		friends.get(b).remove(friends.get(b).indexOf(a));
		if (friends.get(a).isEmpty()) friends.remove(a);
		if (friends.get(b).isEmpty()) friends.remove(b);
	}

	public static int bfs(int start, int goal){
		Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		Map<Integer, Integer> dist = new HashMap<Integer, Integer>();
		for (Integer key : friends.keySet()) {
			visited.put(key, false);
			dist.put(key, 0);
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited.put(start, true);
		while(!q.isEmpty()){
			int cur = q.remove();
			if(cur == goal)
				break;
			for(int i = 0; i < friends.size(); i++){
				if(friends.get(cur).contains(i) && !visited.get(i)){
					q.add(i);
					visited.put(i, true);
					dist.put(i, dist.get(cur) + 1);
				}
			}
		}
		if (dist.get(goal) == null) return 0;
		return dist.get(goal);
	}
	
	public static void main(String[] args){
		for (int i = 1; i <= 18; i++) friends.put(i, new ArrayList<Integer>());
		addLink(1, 6);
		addLink(2, 6);
		addLink(6, 7);
		addLink(6, 5);
		addLink(4, 6);
		addLink(3, 6);
		addLink(3, 4);
		addLink(4, 5);
		addLink(3, 5);
		addLink(7, 8);
		addLink(8, 9);
		addLink(9, 10);
		addLink(9, 12);
		addLink(10, 11);
		addLink(11, 12);
		addLink(3, 15);
		addLink(13, 15);
		addLink(12, 13);
		addLink(13, 14);
		addLink(16, 17);
		addLink(17, 18);
		addLink(16, 18);
		while (true) {
			char c = s.next().charAt(0);
			if (c == 'i') {
				int x = s.nextInt();
				int y = s.nextInt();
				if (!friends.containsKey(x)) {
					friends.put(x, new ArrayList<Integer>());
				}
				if (!friends.containsKey(y)) {
					friends.put(y, new ArrayList<Integer>());
				}
				addLink(x, y);
			}
			else if (c == 'd') {
				int x = s.nextInt();
				int y = s.nextInt();
				delLink(x, y);
			}
			else if (c == 'n') {
				int x = s.nextInt();
				if (friends.containsKey(x)) System.out.println(friends.get(x).size());
				else System.out.println(0);
			}
			else if (c == 'f') {
				Map<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
				for (Integer key : friends.keySet()) visited.put(key, false);
				int x = s.nextInt();
				int total = 0;
				for (int i = 0; i < friends.get(x).size(); i++) visited.put(friends.get(x).get(i), true);
				visited.put(x, true);
				for (int i = 0; i < friends.get(x).size(); i++) {
					for (int n = 0; n < friends.get(friends.get(x).get(i)).size(); n++) {
						if (!visited.get(friends.get(friends.get(x).get(i)).get(n))) total++;
						visited.put(friends.get(friends.get(x).get(i)).get(n), true);
					}
				}
				System.out.println(total);
			}
			else if (c == 's') {
				int x = s.nextInt();
				int y = s.nextInt();
				int ans = bfs(x, y);
				if (ans == 0) System.out.println("Not connected");
				else System.out.println(ans);
			}
			else if (c == 'q') break;
		}
	}
}