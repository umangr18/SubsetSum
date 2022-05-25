import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Callable;

public class Greedy implements Callable<Boolean> {
    private final ArrayList<Integer> list;
    private final int target;

    Greedy(ArrayList<Integer> list, int target) {
      this.list = list;
      this.target = target;
    }

    @Override
    public Boolean call() {
      return subsetSumGreedy();
    }

    private int totalSum(ArrayList<Integer> list) {
      int sum = 0;
      for (int i : list) {
        sum += i;
      }
      return sum;
    }

    public boolean subsetSumGreedy() {
      ArrayList<Integer> subset = new ArrayList<>(); // empty list
      this.list.sort(Collections.reverseOrder()); // sort in decreasing order
      for (int i : this.list) { // iterate through set
        if (i < this.target - totalSum(subset)) { // if smaller than target - current total sum ...
          subset.add(i); // ... add to the subset
        }
      }
      return totalSum(subset) == this.target; // return true if subset elements sum up to target value
    }
}
