/*
1 = player
2 = box
3 = objective
4 = wall

We're going to use / as representation of next line in the array
*/
import java.io.*;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;

public class Board {
    public static List<char[]> board = new LinkedList<>();
    public static int[] playerPos = {-1, -1};
    public static List<int[]> boxesPos = new LinkedList<>();
    public static List<int[]> objsPos = new LinkedList<>();

    public static List<char[]> readAllLines(File file) {
        List<char[]> result = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            for (String current = reader.readLine(); current != null; current = reader.readLine()) {
                result.add(current.toCharArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void getPositions() {
        for(int i = 0; i < board.size(); i++) {
            char[] line = board.get(i);
            for(int j = 0; j < line.length; j++) {
                switch(line[j]) {
                    case 'P':
                        playerPos[0] = i;
                        playerPos[1] = j;
                        break;
                    case 'B':
                        int[] boxPos = {i, j};
                        boxesPos.add(boxPos);
                        break;
                    case '@':
                        int[] objPos = { i, j };
                        objsPos.add(objPos);
                        break;
                }
            }
        }
    }

    public static void movement(int moveQuant) {
    
    }

    public static void transitionModel(String action) {
        switch(action) {
            case "up":
                break;
            case "down":
                break;
            case "left":
                break;
            case "right":
                break;
        }
    }

    public static void main(String[] args) {
        List<char[]> lines = new LinkedList<>();

        File file = new File("holita.txt");
        lines = readAllLines(file);

        for (int i = 0; i < lines.size(); i++) {
            char[] line = lines.get(i);
            if (line.length > 0) {
                board.add(line);
                System.out.println(line);
            }
        }
        getPositions();
        
        // prints for check the positions function
        System.out.println("player position: (" + playerPos[0] + ", " + playerPos[1] + ")");
        
        System.out.print("boxes positions: { ");
        for (int j = 0; j < boxesPos.size(); j++) {
            int[] boxPos = boxesPos.get(j);
            System.out.print("(" + boxPos[0] + ", " + boxPos[1] + ") ");
        }
        System.out.println("}");

        System.out.print("objectives positions: { ");
        for (int j = 0; j < objsPos.size(); j++) {
            int[] objPos = objsPos.get(j);
            System.out.print("(" + objPos[0] + ", " + objPos[1] + ") ");
        }
        System.out.println("}");
        //transitionModel(board1, "left");
        //transitionModel(board1, "right");
    }
}