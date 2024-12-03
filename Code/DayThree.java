import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayThree {
    public static void main(String[] args) {
        try {
            String file = Files.readString(Paths.get("./DayThree.txt"));
            Pattern p = Pattern.compile("mul[(][0-9]{1,3},[0-9]{1,3}[)]|don't()|do()");
            Matcher m = p.matcher(file);

            ArrayList<String> strings = new ArrayList<>();
            boolean add = true;
            while (m.find()) {
                if (m.group() == "don't") add = false;
                if (add) strings.add(m.group());
                if (m.group() == "do") add = true;
            }



            int sum = 0;
            for (String s : strings) {
                int p1 = s.indexOf("(");
                int c = s.indexOf(",");
                int p2 = s.indexOf(")");
                int firstNum = Integer.parseInt(s.substring(p1 + 1, c));
                int secondNum = Integer.parseInt(s.substring(c + 1, p2));
                sum += firstNum * secondNum;
            }

            System.out.println("Q1: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
