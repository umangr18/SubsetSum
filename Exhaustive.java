import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Exhaustive implements Callable<Boolean> {
    private ArrayList<Integer> list;
    private int target;

    Exhaustive(ArrayList<Integer> list, int target) {
      this.list = list;
      this.target = target;
    }

    @Override
    public Boolean call() throws Exception {
      return subsetSumExhaustive(this.list, this.target);
    }

    // combination of recursive and bitwise algorithms
    private static boolean helper(ArrayList<Integer> list, int index, int target) {
      if (target == 0) {
        return true;
      }

      if (list.size() - index == 1) {
        return list.get(index) == target;
      }

      boolean a = helper(list, index + 1, target - list.get(index));
      boolean b = helper(list, index + 1, target);
      return a | b;
    }
    private static boolean subsetSumExhaustive(ArrayList<Integer> list, int target) {
      if (list.size() == 0) {
        return false;
      } else if (list.size() == 1) {
        return list.get(0) == target;
      } else {
        return helper(list, 0, target);
      }
    }
}
