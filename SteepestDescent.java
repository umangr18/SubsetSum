import java.util.ArrayList;
import java.util.concurrent.Callable;

public class SteepestDescent implements Callable<Boolean> {
    private final ArrayList<Integer> list;
    private final int target;
    private final ArrayList<Integer> initialSolution;
    private int k;

    SteepestDescent(ArrayList<Integer> list, int target, ArrayList<Integer> initialSolution, int k) {
      this.list = list;
      this.target = target;
      this.initialSolution = initialSolution;
      this.k = k;
    }

    private ArrayList<ArrayList<Integer>> getPotentialNeighbors() {
      int n = this.list.size();
      ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();
      // how to find neighbors without taking O(2^n) time ???
      return neighbors;
    }

    private ArrayList<ArrayList<Integer>> getActualNeighbors() {
      int n = this.list.size();
      ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();

      // iterate through all possible options
      for (ArrayList<Integer> potential : this.getPotentialNeighbors()) {

        // check that potential neighbor is <= target
        int sum = 0;
        for (int i = 0; i < n; i++) {
          if (potential.get(i) == '1') {
            sum += this.list.get(i);
          }
          if (sum > this.target) {
            break;
          }
        }

        // check that potential solution has <= 2 switches
        int switches = 0;
        for (int i = 0; i < n; i++) {
          switches += Math.abs(potential.get(i) - this.initialSolution.get(i));
          if (switches > this.k) {
            break;
          }
        }

        // add to list
        neighbors.add(potential);
      }
      return neighbors;
    }

    @Override
    public Boolean call() {
      return subsetSumSteepestDescent();
    }

    public boolean subsetSumSteepestDescent() {

    }
}
