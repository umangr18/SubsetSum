import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Main {
  public static void main(String[] args) {
    ArrayList<Pair> baseCases = Parse.parse("BaseCases.txt");
    ArrayList<Pair> randomlyGenerated = Parse.parse("RandomlyGenerated.txt");
    ArrayList<Pair> onlineResources = Parse.parse("OnlineResources.txt");
    ArrayList<Pair> curatedHard = Parse.parse("CuratedHard.txt");

    // one minute results
    System.out.println("ONE MINUTE RESULTS");
    System.out.println("Base Cases:\n" + runInstances(baseCases, 1) + "\n");
    System.out.println("Randomly Generated:\n" + runInstances(randomlyGenerated, 1) + "\n");
    System.out.println("Online Resources:\n" + runInstances(onlineResources, 1) + "\n");
    System.out.println("Curated Hard:\n" + runInstances(curatedHard, 1) + "\n");

    // ten minute results
    System.out.println("TEN MINUTE RESULTS");
    System.out.println("Base Cases:\n" + runInstances(baseCases, 10) + "\n");
    System.out.println("Randomly Generated:\n" + runInstances(randomlyGenerated, 10) + "\n");
    System.out.println("Online Resources:\n" + runInstances(onlineResources, 10) + "\n");
    System.out.println("Curated Hard:\n" + runInstances(curatedHard, 10) + "\n");
  }

  private static ArrayList<String> runInstances(ArrayList<Pair> instances, int timeout) {
    ArrayList<String> results = new ArrayList<>();
    for (Pair instance : instances) {
      Exhaustive e = new Exhaustive(instance.getList(), instance.getTarget());
      ExecutorService service = Executors.newSingleThreadExecutor();
      try {
        Future<Boolean> f = service.submit(e::subsetSumExhaustive);
        boolean result = f.get(timeout, TimeUnit.MINUTES);
        results.add(Boolean.toString(result));
      } catch (TimeoutException ex) {
        results.add("FAIL");
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    return results;
  }
}
