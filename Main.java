class Main {
  
  public static void main(String[] args) {
    HashMap<ArrayList<Integer>, Integer> instances = Parse.parse("Instances.txt");
    ArrayList<String> oneMinuteResults = new ArrayList<>();
    ArrayList<String> tenMinuteResults = new ArrayList<>();
    
    // one minute
    for (Map.Entry<ArrayList<Integer>, Integer> instance : instances.entrySet()) {
      Exhaustive e = new Exhaustive(instance.getKey(), instance.getValue());
      FutureTask task = new FutureTask(e);
      try {
        boolean result = task.get(1, TimeOut.MINUTES);
        oneMinuteResults.add(Boolean.toString(result);
      } catch (TimeOutException e) {
        task.cancel(true);
        System.out.println(e.getMessage());
        oneMinuteResults.add("FAIL");
      }
    }
    
    // ten minutes
    for (Map.Entry<ArrayList<Integer>, Integer> instance : instances.entrySet()) {
      Exhaustive e = new Exhaustive(instance.getKey(), instance.getValue());
      FutureTask task = new FutureTask(e);
      try {
        boolean result = task.get(10, TimeOut.MINUTES);
        tenMinuteResults.add(Boolean.toString(result);
      } catch (TimeOutException e) {
        task.cancel(true);
        System.out.println(e.getMessage());
        tenMinuteResults.add("FAIL");
      }
    }
  }
  
}
