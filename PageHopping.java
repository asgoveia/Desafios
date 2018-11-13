import java.util.Scanner;

public class Main {

    static final int MAX = 101;
    static final int INF = 10000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int matrix[][] = new int [MAX][MAX];
        int testCases = 0;

        while(scanner.hasNext()){

            testCases++;
            int pg1 = scanner.nextInt();
            int pg2 = scanner.nextInt();

            if(pg1 == 0 && pg2 == 0)
                break;

            for(int i = 0; i < MAX; i++){

                for(int j = 0; j < MAX; j++){

                    matrix[i][j] = INF;
                }
            }

            while(pg1 != 0 || pg2 != 0){

                //System.out.println(pg1 + " " + pg2);

                matrix[pg1][pg2] = 1;
                matrix[pg1][pg1] = 0;
                matrix[pg2][pg2] = 0;

                pg1 = scanner.nextInt();
                pg2 = scanner.nextInt();
            }

            for(int k = 0; k < MAX; k++){

                for(int i = 0; i < MAX; i++){

                    for(int j = 0; j < MAX; j++){

                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                    }
                }
            }

            int nodes = 0;
            int sumPath = 0;

            for(int i = 0; i < MAX; i++){
                for(int j = 0; j < MAX; j++){

                    if(matrix[i][j] != INF){
                        sumPath += matrix[i][j];
                    }

                }

                if(matrix[i][i] == 0){
                    nodes++;
                }
            }

            float pairs = nodes * (nodes - 1);
            float clicks = sumPath/pairs;

            System.out.println(pairs + " " + sumPath);

            System.out.format("Case %d: average length between pages = %.3f clicks%n",testCases, clicks);

        }
    }
}
