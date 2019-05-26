import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class Main {

    public static List<String> readAllLines(File file) {
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
    public static int[] getInitialPlayerPosition(List<String> board){
        int[] index = new int[2];

        for (int i=0; i<board.size();i++) {
            if (board.get(i).contains("P")){
                index[0] = board.get(i).indexOf("P");
                index[1] = i;
            }
        }
            return index;
    }

    public static int[] getInitialBoxesPosition(List<String> board){
        int[] index = new int[10];

        for (int i=0; i<board.size();i++) {
            if (board.get(i).contains("B")){
                i = board.get(i).indexOf('x');
                while(i >= 0) {
                    System.out.println(i);
                    i = board.get(i).indexOf('x', i+1);
                }
            }
        }
        return index;
    }
    public static void main(String[] args) {
        List<String> result = new LinkedList<>();
        List<String> board = new LinkedList<>();
        int[] position;

        File file = new File("holita.txt");
        result  = readAllLines(file);

        System.out.println(result.get(0).equals(""));

        int counter = 0;

        for(int i = 0; i< result.size(); i++){
            if (!result.get(i).equals("")) {
                board.add(result.get(i));
                counter++;
            }
        }
        position = getInitialPlayerPosition(board);

        System.out.println(position[0]);
        System.out.println(position[1]);
        int i = board.get(2).indexOf("B");
        while(i >= 0) {
            System.out.println(i);
            i =  board.get(2).indexOf('x', i+1);
        }

    }

}
