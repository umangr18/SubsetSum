import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Main {
  public static void main(String[] args) {

    ArrayList<Pair> baseCases = Parse.parse("instances/BaseCases.txt");
    ArrayList<Pair> randomlyGenerated = Parse.parse("instances/RandomlyGenerated.txt");
    ArrayList<Pair> onlineResources = Parse.parse("instances/OnlineResources.txt");
    ArrayList<Pair> curatedHard = Parse.parse("instances/CuratedHard.txt");

    //createDatFiles("BaseCases", baseCases);
    //createDatFiles("RandomlyGenerated", randomlyGenerated);
    //createDatFiles("OnlineResources", onlineResources);
    //createDatFiles("CuratedHard", curatedHard);

    /*
    try {
      PrintWriter writer = new PrintWriter("GreedyResults.txt", StandardCharsets.UTF_8);
      writer.println(runInstances(baseCases, 1) + "\n");
      writer.println(runInstances(randomlyGenerated, 1) + "\n");
      writer.println(runInstances(onlineResources, 1) + "\n");
      writer.println(runInstances(curatedHard, 1) + "\n");

      //writer.println("TEN MINUTE RESULTS");
      //writer.println(runInstances(baseCases, 10) + "\n");
      //writer.println(runInstances(randomlyGenerated, 10) + "\n");
      //writer.println(runInstances(onlineResources, 10) + "\n");
      //writer.println(runInstances(curatedHard, 10) + "\n");

      writer.close();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    */

    System.exit(1);
  }

  private static void createDatFiles(String category, ArrayList<Pair> instances) {
    for (int i = 1; i <= instances.size(); i++) {
      ILPDataParser parser = new ILPDataParser(instances.get(i - 1));
      String filename = "DatFiles/" + category + "/instance" + i + ".dat";
      try {
        PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8);
        writer.println(parser.convert());
        writer.close();
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  private static ArrayList<String> runInstances(ArrayList<Pair> instances, int timeout) {
    ArrayList<String> results = new ArrayList<>();
    for (Pair instance : instances) {
      long startTime = System.nanoTime();
      Greedy e = new Greedy(instance.getList(), instance.getTarget());
      ExecutorService service = Executors.newSingleThreadExecutor();
      try {
        Future<Boolean> f = service.submit(e::subsetSumGreedy);
        boolean result = f.get(timeout, TimeUnit.MINUTES);
        long endTime = System.nanoTime();
        results.add(result + "-" + instance.getList().size() + "-" + (endTime - startTime)/1000000);
      } catch (TimeoutException ex) {
        results.add(instance.getList().size() + "-" + "FAIL");
      } catch (Exception ex) {
        System.out.println(ex.getMessage());
      }
    }
    return results;
  }
}
