import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TabuSearch implements Callable<Boolean> {
    private final ArrayList<Integer> list;
    private final int target;
    private final ArrayList<Integer> initialSolution;
    private final int k;
    private final int kTabu;
    private final ArrayList<ArrayList<Integer>> tabuList;

    TabuSearch(ArrayList<Integer> list, int target, ArrayList<Integer> initialSolution, int k, int kTabu) {
      this.list = list;
      this.target = target;
      this.initialSolution = initialSolution;
      this.k = k;
      this.kTabu = kTabu;
      this.tabuList = new ArrayList<>();
    }

    private ArrayList<ArrayList<Integer>> getPotentialNeighbors(ArrayList<Integer> solution) {
      int n = this.list.size();
      ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();
      // TODO: how to find neighbors without taking O(2^n) time ???
      return neighbors;
    }

    private ArrayList<ArrayList<Integer>> getActualNeighbors(ArrayList<Integer> solution) {
      int n = this.list.size();
      ArrayList<ArrayList<Integer>> neighbors = new ArrayList<>();

      // iterate through all possible options
      for (ArrayList<Integer> potential : this.getPotentialNeighbors(solution)) {

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
          switches += Math.abs(potential.get(i) - solution.get(i));
          if (switches > this.k) {
            break;
          }
        }

        // add to list
        neighbors.add(potential);
      }
      return neighbors;
    }

    private ArrayList<Integer> getBestNeighbor(ArrayList<ArrayList<Integer>> neighbors) {
      ArrayList<Integer> best = new ArrayList<>();
      int bestValue = 0;
      for (ArrayList<Integer> neighbor : neighbors) {
        if (getValue(neighbor) > bestValue && !this.tabuList.contains(neighbor)) {
          best = neighbor;
        }
      }
      return best;
    }

    // returns the total sum of all selected elements in the provided set
    private int getValue(ArrayList<Integer> solution) {
      int sum = 0;
      for (int i : solution) {
        if (i == 1) {
          sum += this.list.get(i);
        }
      }
      return sum;
    }

    @Override
    public Boolean call() {
      return subsetSumTabuSearch();
    }

    public boolean subsetSumTabuSearch() {
      return this.getValue(this.helper(this.initialSolution)) == this.target;
    }

    private ArrayList<Integer> helper(ArrayList<Integer> current) {
      if (this.tabuList.size() < this.kTabu) {
        this.tabuList.add(current);
      } else if (this.tabuList.size() == this.kTabu) {
        this.tabuList.remove(0);
        this.tabuList.add(current);
      }
      if (this.getValue(current) == this.target /* TODO: OR IF TIME LIMIT EXCEEDED */) {
        return current;
      } else {
        return helper(this.getBestNeighbor(this.getActualNeighbors(current)));
      }
    }
}
