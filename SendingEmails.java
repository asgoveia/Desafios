import java.util.*;

class Edge implements Comparable<Edge>{

    public int dest;
    public int cost;

    public Edge(int dest, int cost){

        this.dest = dest;
        this.cost = cost;
    }

    public int compareTo (Edge e) {
        return this.cost - e.cost;
    }
}

public class Main {


    public static int dijkstra(Map<Integer, List<Edge>> adj, int n, int src, int dest){

        int costs[] = new int[n];
        PriorityQueue<Edge> queue = new PriorityQueue<>();

        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[src] = 0;
        queue.add(new Edge(src, 0));

        while (!queue.isEmpty()){

            Edge edge = queue.remove();

            if(edge.dest == dest)
                return costs[dest];

            if(adj.containsKey(edge.dest)) {

                List<Edge> current = adj.get(edge.dest);

                if(!current.isEmpty()) {

                    for (Edge e : current) {

                        int costCur = costs[edge.dest] + e.cost;

                        if (costs[e.dest] > costCur) {

                            costs[e.dest] = costCur;
                            queue.add(new Edge(e.dest, costs[e.dest]));

                        }
                    }
                }
            }
        }

        return -1;
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Map<Integer, List<Edge>> adj;

        int testCases = scanner.nextInt();

        for(int i = 1; i <= testCases; i++){

            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int S = scanner.nextInt();
            int T = scanner.nextInt();


            adj = new HashMap<Integer, List<Edge>>();

            for(int j = 0; j < m; j++){

                int server1 = scanner.nextInt();
                int server2 = scanner.nextInt();
                int latency = scanner.nextInt();


                if (!adj.containsKey(server1))
                    adj.put(server1, new ArrayList<Edge>());

                adj.get(server1).add(new Edge(server2, latency));

                if (!adj.containsKey(server2))
                    adj.put(server2, new ArrayList<Edge>());

                adj.get(server2).add(new Edge(server1, latency));

            }

            int minCost = dijkstra(adj,n,S,T);

            if(minCost == -1){
                System.out.println("Case #" + i + ": " + "unreachable");
            }

            else{
                System.out.println("Case #" + i + ": " + minCost);
            }

        }


    }
}
