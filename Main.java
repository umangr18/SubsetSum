import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Main {
  
  public static void main(String[] args) {
    HashMap<ArrayList<Integer>, Integer> instances = Parse.parse("BaseCases.txt");
    ArrayList<String> oneMinuteResults = new ArrayList<>();
    ArrayList<String> tenMinuteResults = new ArrayList<>();
    
    // one minute
    for (Map.Entry<ArrayList<Integer>, Integer> instance : instances.entrySet()) {
      Exhaustive e = new Exhaustive(instance.getKey(), instance.getValue());
      //FutureTask<Boolean> task = new FutureTask<Boolean>(e);
      /*try {
        //boolean result = task.get(1, TimeUnit.MINUTES);
        boolean result = task.get(20, TimeUnit.SECONDS);
        oneMinuteResults.add(Boolean.toString(result));
      } catch (ExecutionException e1) {
        task.cancel(true);
        System.out.println("1");
        oneMinuteResults.add("FAIL");
      } catch (TimeoutException e2) {
        task.cancel(true);
        System.out.println("2");
        oneMinuteResults.add("FAIL");
      } catch (InterruptedException e3) {
        task.cancel(true);
        System.out.println("3");
        oneMinuteResults.add("FAIL");
      }*/
    }

    System.out.println("1 Minute Results: " + oneMinuteResults);
    
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

    System.out.println("10 Minute Results: " + tenMinuteResults);
  }
  
}
