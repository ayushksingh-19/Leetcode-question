import java.util.*;

public class jumpgame {
    public int minJumps(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int n = arr.length;
        if (n == 1) {
            return 0;
        }

        Map<Integer, List<Integer>> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});

        boolean[] vis = new boolean[n];
        vis[0] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int dist = curr[1];

            if (node == n - 1) return dist;

            if (node - 1 >= 0 && !vis[node - 1]) {
                vis[node - 1] = true;
                q.offer(new int[]{node - 1, dist + 1});
            }

            if (node + 1 < n && !vis[node + 1]) {
                vis[node + 1] = true;
                q.offer(new int[]{node + 1, dist + 1});
            }

            for (int next : mp.get(arr[node])) {
                if (!vis[next]) {
                    vis[next] = true;
                    q.offer(new int[]{next, dist + 1});
                }
            }

            mp.get(arr[node]).clear();
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        jumpgame obj = new jumpgame();
        int result = obj.minJumps(arr);
        System.out.println("Minimum jumps: " + result);

        sc.close();
    }
}
