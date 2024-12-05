import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DayFour {
    public static void main(String[] args) {
        try {
            
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("./DayFour.txt")));
            char[][] tempName = new char[lines.size()][lines.get(0).length()];;
            for (int i = 0; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length(); j++) {
                    tempName[i][j] = lines.get(i).charAt(j);
                }
            }

            int count = 0;

            for (int i = 0; i < tempName.length; i++) {
                for (int j = 0; j < tempName[i].length; j++) {
                    // Brute force checking all of the values instead of just the x ones
                    for (int k = -1; k <= 1; k++) {
                        for (int x = -1; x <= 1; x++) {
                            if (isXMAS(tempName, i, j, k, x)) count++;
                        }
                    }
                }
            }

            System.out.println(count);

            // -1,-1 -1,0 -1,1
            //  0,-1  0,0  0,1 
            //  1,-1  1,0  1,1

            // y,x
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static boolean isXMAS(char[][] array, int y, int x, int yAdd, int xAdd) {
        int yBound = array.length;
        int xBound = array[0].length;

        if (array[y][x] != 'X') return false;
        if (!isInBounds(y += yAdd, yBound) || !isInBounds(x += xAdd, xBound)) return false;
        if (array[y][x] != 'M') return false;
        if (!isInBounds(y += yAdd, yBound) || !isInBounds(x += xAdd, xBound)) return false;
        if (array[y][x] != 'A') return false;
        if (!isInBounds(y += yAdd, yBound) || !isInBounds(x += xAdd, xBound)) return false;
        if (array[y][x] != 'S') return false;
        return true;
    }

    private static boolean isInBounds(int i, int bound) {
        return i >= 0 && i < bound;
     }
}
