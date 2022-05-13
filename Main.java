import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Main {
  
  public static void main(String[] args) {
    ArrayList<Pair> instances = Parse.parse("BaseCases.txt");
    ArrayList<String> oneMinuteResults = new ArrayList<>();
    ArrayList<String> tenMinuteResults = new ArrayList<>();

    // one minute
    for (Pair instance : instances) {
      Exhaustive e = new Exhaustive(instance.getList(), instance.getTarget());

      long start = System.currentTimeMillis();
      long end = start + 5 * 1000;
      boolean result = false;
      while (System.currentTimeMillis() < end) {
        result = e.subsetSumExhaustive();
      }
      System.out.println(result);
      oneMinuteResults.add(Boolean.toString(result));

      //FutureTask<Boolean> task = new FutureTask<Boolean>(e);
      /*try {
        //boolean result = task.get(1, TimeUnit.MINUTES);
        boolean result = task.get(20, TimeUnit.SECONDS);
        oneMinuteResults.add(Boolean.toString(result));
      } catch (Exception ex) {
        task.cancel(true);
        System.out.println(ex.getMessage());
        oneMinuteResults.add("FAIL");
      }*/
    }

    System.out.println("1 Minute Results: " + oneMinuteResults);
    
    // ten minutes
    /*for (Map.Entry<ArrayList<Integer>, Integer> instance : instances.entrySet()) {
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
    }*/

    //System.out.println("10 Minute Results: " + tenMinuteResults);
  }
  
}
