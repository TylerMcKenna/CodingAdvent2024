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
                String[] vals = l.split("^Mod([0-9][0-9][0-9])");
                ArrayList<Integer> temp = new ArrayList<>();
                for (String s : vals) {
                    temp.add(Integer.parseInt(s));
                }
                nums.add(temp);
            }

            // Question 1   
            int count = 0;
            for (int i = 0; i < nums.size(); i++) {
                if (isStrictlyMonotonic(nums.get(i)) && changesSmallEnough(nums.get(i))) count++;
            }
            System.out.println(count);

            // Question 2   
            count = 0;
            for (int i = 0; i < nums.size(); i++) {
                if (isStrictlyMonotonicFixable(nums.get(i)) && changesSmallEnough(nums.get(i))) { 
                    System.out.println("Safe " + lines.get(i));    
                    count++;
                }
                else if (!isStrictlyMonotonicFixable(nums.get(i))) {
                    System.out.println("Unsafe monotonic " + lines.get(i));
                }
                else if (!changesSmallEnough(nums.get(i))) {
                    System.out.println("Unsafe gap " + lines.get(i));
                }
                else {
                    System.out.println("Unsafe " + lines.get(i));    
                }
                //if (isStrictlyMonotonicFixable(nums.get(i)) && changesSmallEnough(nums.get(i))) count++;
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
        
        boolean failedOnce = false;
        int last = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (i <= last) {
                increasing = false;
            }
            last = i;
        }
        if (increasing) return true;

        failedOnce = false;
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


    // for part 2
    private static Boolean isStrictlyMonotonicFixable(ArrayList<Integer> list) {
        boolean decreasing = true;
        boolean increasing = true;
        
        boolean failedOnce = false;
        int last = Integer.MIN_VALUE;
        for (Integer i : list) {
            if (i <= last) {
                if (failedOnce)
                    increasing = false;
                else
                    failedOnce = true;
            }
            last = i;
        }
        if (increasing) return true;

        failedOnce = false;
        last = Integer.MAX_VALUE;
        for (Integer i : list) {
            if (i >= last) {
                if (failedOnce)
                    decreasing = false;
                else
                    failedOnce = true;
            }
            last = i;
        }
        if (decreasing) return true;

        return false;
    }
}
