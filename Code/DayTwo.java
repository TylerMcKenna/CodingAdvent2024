import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DayTwo {
    public static void main(String[] args) {
        try {
            ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("./DayTwo.txt")));

            ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
            for (String l : lines) {
                String[] vals = l.split("\\s+");
                ArrayList<Integer> temp = new ArrayList<>();
                for (String s : vals) {
                    temp.add(Integer.parseInt(s));
                }
                nums.add(temp);
            }

            // Question 1
            boolean[] safe = new boolean[nums.size()];
            
            int count = 0;
            for (int i = 0; i < nums.size(); i++) {
                if (isStrictlyMonotonic(nums.get(i)) && changesSmallEnough(nums.get(i))) count++;
            }

            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Boolean changesSmallEnough(ArrayList<Integer> list) {
        int last = 0;

        for (int i = 0; i < list.size(); i++) {
            if (!(Math.abs(list.get(i) - list.get(last)) <= 3)) return false;
            last = i; 
        }
        return true;
    }

    // Code kinda sucks
    private static Boolean isStrictlyMonotonic(ArrayList<Integer> list) {
        boolean decreasing = true;
        boolean increasing = true;
        
        int last = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (i <= last) {
                increasing = false;
            }
            last = i;
        }
        if (increasing) return true;

        last = Integer.MAX_VALUE;
        for (Integer i : list) {
            if (i >= last) {
                decreasing = false;
            }
            last = i;
        }
        if (decreasing) return true;

        return false;
    }
}
