import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.*;
import java.util.function.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

class Parse {

  private static ArrayList<Integer> helper(ArrayList<String> stringList, Function<String, Integer> function) {
    return new ArrayList<Integer>(stringList.stream().map(function).collect(Collectors.toList()));
  }

  public static HashMap<ArrayList<Integer>, Integer> parse(String filename) {
    HashMap<ArrayList<Integer>, Integer> map = new HashMap<>();
    String line = "";
    try {
      BufferedReader br = new BufferedReader(new FileReader("Instances.txt"));
      while ((line = br.readLine()) != null) {
        System.out.println(line);
        // ignore all lines that start with '#' - they're comments
        if (line.contains("#")) {
          continue;
        }
        String[] tokens = line.split(":");
        int target = Integer.parseInt(tokens[1]);
        String list = tokens[0].substring(1, tokens[0].length() - 1);
        String[] nums = list.split(", ");
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList(nums));
        ArrayList<Integer> numList = helper(stringList, Integer::parseInt);
        map.put(numList, target);
      }
      br.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return map;
  }
  
}
