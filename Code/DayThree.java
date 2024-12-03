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
            Pattern p = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|don't|do");
            Matcher m = p.matcher(file);

            ArrayList<String> operations = new ArrayList<>();
            boolean isDisabled = false;
            // Leave only operations.add(match) for question one's answer
            while (m.find()) {
                String match = m.group();
                if ("don't".equals(match)) 
                    isDisabled = true;
                else if ("do".equals(match))
                    isDisabled = false;
                else if (!isDisabled) 
                    operations.add(match);
            }

            int sum = 0;
            for (String s : operations) {
                int p1 = s.indexOf("(");
                int c = s.indexOf(",");
                int p2 = s.indexOf(")");
                int firstNum = Integer.parseInt(s.substring(p1 + 1, c));
                int secondNum = Integer.parseInt(s.substring(c + 1, p2));
                sum += firstNum * secondNum;
            }

            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
