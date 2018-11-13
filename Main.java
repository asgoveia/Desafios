import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Integer> originalBoard = new ArrayList<>(8);
    public static ArrayList<Integer> newBoard = new ArrayList<>(8);


    public static boolean possible(int col, int row){

        for(int i = 0; i < col; i++){

            if(row == newBoard.get(i) || Math.abs(col - i) == Math.abs(row - newBoard.get(i))){
                return false;
            }
        }

        return true;
    }

    public static int queens(int c){

        if(c == 8){
            return 0;
        }

        int moves = 9;

        for (int row = 0; row < 8; row++) {

            if (possible(c, row)) {
                newBoard.set(c, row);

                if(row == originalBoard.get(c)){
                    moves = Math.min(moves, (queens(c+1)));
                }

                else{
                    moves = Math.min(moves, (1 + queens(c+1)));
                }
            }
        }

        return moves;

    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String input;
        int caso = 1;

        while (scanner.hasNext()) {

            input = scanner.nextLine();
            input = input.replace(" ", "");
            originalBoard.clear();
            newBoard.clear();

            for(int i = 0; i < 8; i++){

                originalBoard.add(Character.getNumericValue(input.charAt(i))-1);
            }

            newBoard = (ArrayList)originalBoard.clone();

            System.out.println("Case " + caso + ": " + queens(0));
            caso++;
        }

    }
}
