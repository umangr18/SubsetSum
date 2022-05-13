import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class Main {
  
  public static void main(String[] args) {
    HashMap<ArrayList<Integer>, Integer> instances = Parse.parse("Instances.txt");
    ArrayList<String> oneMinuteResults = new ArrayList<>();
    ArrayList<String> tenMinuteResults = new ArrayList<>();
    
    // one minute
    for (Map.Entry<ArrayList<Integer>, Integer> instance : instances.entrySet()) {
      Exhaustive e = new Exhaustive(instance.getKey(), instance.getValue());
      FutureTask<Boolean> task = new FutureTask<Boolean>(e);
      try {
        boolean result = task.get(1, TimeUnit.MINUTES);
        oneMinuteResults.add(Boolean.toString(result));
      } catch (Exception ex) {
        task.cancel(true);
        System.out.println(ex.getMessage());
        oneMinuteResults.add("FAIL");
      }
    }
    
    // ten minutes
    for (Map.Entry<ArrayList<Integer>, Integer> instance : instances.entrySet()) {
      Exhaustive e = new Exhaustive(instance.getKey(), instance.getValue());
      FutureTask<Boolean> task = new FutureTask<Boolean>(e);
      try {
        boolean result = task.get(10, TimeUnit.MINUTES);
        tenMinuteResults.add(Boolean.toString(result));
      } catch (Exception ex) {
        task.cancel(true);
        System.out.println(ex.getMessage());
        tenMinuteResults.add("FAIL");
      }
    }

    System.out.println("1 Minute Results: " + oneMinuteResults);
    System.out.println("10 Minute Results: " + tenMinuteResults);
  }
  
}
