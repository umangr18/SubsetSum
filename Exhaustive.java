import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Exhaustive implements Callable<Boolean> {
    private final ArrayList<Integer> list;
    private final int target;

    Exhaustive(ArrayList<Integer> list, int target) {
      this.list = list;
      this.target = target;
    }

    @Override
    public Boolean call() {
      return subsetSumExhaustive();
    }

    private boolean helper(ArrayList<Integer> list, int index, int target) {
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

    public boolean subsetSumExhaustive() {
      if (this.list.size() == 0) {
        return false;
      } else if (this.list.size() == 1) {
        return this.list.get(0) == this.target;
      } else {
        return helper(this.list, 0, this.target);
      }
    }
}
