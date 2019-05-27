import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

public class Main {

    private static List<String> readBoard(File file) {
        List<String> result = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String current = reader.readLine(); current != null; current = reader
                    .readLine()) {
                result.add(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public class State{
        int[] boxPosition;
        int playersPosition;
    }

    private static int[] getInitialPlayerPosition(List<String> board){
        int[] index = new int[2];

        for (int i=0; i<board.size();i++) {
            if (board.get(i).contains("P")){
                index[0] = i;
                index[1] = board.get(i).indexOf("P");

            }
        }
            return index;
    }

    private static int[] getInitialBoxesPosition(List<String> board){
        int[] index = new int[20];
        int pivot;
        int counter = 0;

        for (int i=0; i<board.size();i++) {
            if (board.get(i).contains("B")){
                pivot = board.get(i).indexOf("B");

                while(pivot >= 0) {
                    index[counter] = i;
                    counter++;
                    index[counter] = pivot;
                    counter ++;
                    pivot = board.get(i).indexOf("B", pivot+1);
                }
            }
        }
        return index;
    }

    private static int[] getGoalsPositions(List<String> board){
        int[] index = new int[20];
        int pivot;
        int counter = 0;

        for (int i=0; i<board.size();i++) {
            if (board.get(i).contains("G")){
                pivot = board.get(i).indexOf("G");

                while(pivot >= 0) {
                    index[counter] = i;
                    counter++;
                    index[counter] = pivot;
                    counter ++;
                    pivot = board.get(i).indexOf("G", pivot+1);
                }
            }
        }
        return index;
    }

    public static boolean isWall(int x, int y, char[][] board){
        char c =board[x][y];
        return (c == 'x');
    }

    public static boolean isBoxOnGoal(int x, int y, char[][] board){
        char c = board[x][y];

        return (c == '*');

    }

    public static boolean isEmpty(int x, int y, char[][] board){
        char c = board[x][y];

        return (c == ' ' | c == '@' | c == '*');
    }


    public static void main(String[] args) {
        File file = new File("holita.txt");

        List<String> result = readBoard(file);
        List<String> nonProcessedBoard  = new ArrayList<>();
        List<String> startMap;
        int height, width = 0;

        char[][] boardMatrix;
        int[] position;
        int[] boxesPositions;
        int[] goalsPositions;
        int counter = 0;

        //Ignores all empty spaces in file, and add them in a new List
        for(int i = 0; i< result.size(); i++){
            if (!result.get(i).equals("")) {
                nonProcessedBoard.add(result.get(i));
                counter++;
            }
        }
        //Temp list
        startMap = nonProcessedBoard;
        //Calculates the height of the board
        height = nonProcessedBoard.size();

        //Calculates de width of the board
        for (int i = 0; i < height; i++){
            if (nonProcessedBoard.get(i).length()> width){
                width = nonProcessedBoard.get(i).length();
            }
        }
        //Initializes the board with the calculated height and width before hand
        boardMatrix = new char[height][width];

        //Initializes the board with empty spaces ' '
        for (char[] row: boardMatrix)
            Arrays.fill(row, ' ');

        //Copies the list to a char[][], so the final board is boardMatrix
        for (int i = 0; i < nonProcessedBoard.size(); i++){
            for (int j = 0; j < nonProcessedBoard.get(i).length(); j++){
                boardMatrix[i][j] = startMap.get(i).charAt(j);
            }
        }

        //Player's initial position, stored in an array with length = 2
        position = getInitialPlayerPosition(nonProcessedBoard);

        System.out.println(position[0]);
        System.out.println(position[1]);

        //All boxes's initial position, stored in an array that supports max 10 boxes (modify if needed)
        boxesPositions = getInitialBoxesPosition(nonProcessedBoard);

        //Gets all goals' positions, here we define the goal state.
        goalsPositions = getGoalsPositions(nonProcessedBoard);

        System.out.println("Boxes positions");
        for (int i = 0; i<boxesPositions.length-1; i++ ){
            if (!(boxesPositions[i] == 0 & boxesPositions[i+1]==0))
                System.out.println("x: " + boxesPositions[i] + ", y: "+ boxesPositions[++i]);
        }

        System.out.println("Goals positions: ");
        for (int i = 0; i<goalsPositions.length-1; i++ ){
            if (!(goalsPositions[i] == 0 & goalsPositions[i+1]==0))
                System.out.println("x: " + goalsPositions[i] + ", y: "+ goalsPositions[++i]);
        }

    }

}
