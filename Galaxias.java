import java.util.*;

public class Main {

    private static int visitados[];
    private static Map<Integer, List<Integer>> adj;
    private static int cj1;
    private static int cj2;
    private static int numPontos;


    private static void dfs(int i, int conjunto){
        int j;
        visitados[i] = 1;

        if(conjunto == 1){
            cj1++;
        }else{
            cj2++;
        }

        while(!(adj.get(i).isEmpty())) {


            j = adj.get(i).get(0);
            adj.get(i).remove(0);

            if (visitados[j] == 0) {

                if (conjunto == 1) {
                    dfs(j, 2);
                } else {
                    dfs(j, 1);
                }
            }
        }
    }

    public static void main(String[] args) {

            Scanner s = new Scanner(System.in);

            while (s.hasNext()) {
                numPontos = s.nextInt();

                List<Integer> x = new ArrayList<Integer>();
                List<Integer> y = new ArrayList<Integer>();
                Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
                visitados = new int[numPontos];
                adj = new HashMap<Integer, List<Integer>>();

                for (int i = 0; i < numPontos; i++) {
                    int x1 = s.nextInt(), y1 = s.nextInt();

                    x.add(x1);
                    y.add(y1);

                    if (!map.containsKey(x1))
                        map.put(x1, new HashMap<Integer, Integer>());
                    map.get(x1).put(y1, i);
                    visitados[i] = 0;
                    if (!adj.containsKey(i))
                        adj.put(i, new ArrayList<Integer>());
                }

                for (int k = -5; k <= 5; k++) {
                    for (int j = -5; j <= 5; j++) {

                        int dist = k * k + j * j;
                        if (dist <= 25) {

                            for (int i = 0; i < numPontos; i++) {
                                int xi = x.get(i);
                                int yi = y.get(i);

                                if (map.containsKey(xi + k)) {
                                    int xj = xi + k;
                                    int yj = yi + j;
                                    Map<Integer, Integer> pxj = map.get(xj);
                                    if (pxj.containsKey(yj)) {

                                        int idx = pxj.get(yj);
                                        adj.get(i).add(idx);
                                        adj.get(idx).add(i);
                                    }
                                }
                            }
                        }
                    }
                }

                int tot = 0;

                for (int i = 0; i < numPontos; i++) {
                    cj1 = cj2 = 0;
                    if (visitados[i] == 0) {
                        dfs(i, 1);
                        tot += Math.min(cj1, cj2);
                    }
                }

                System.out.println(tot);
            }
    }

}
