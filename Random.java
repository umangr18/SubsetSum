import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Random implements Callable<Boolean> {
    private final ArrayList<Integer> list;
    private final int target;

    Random(ArrayList<Integer> list, int target) {
      this.list = list;
      this.target = target;
    }

    private int totalSum(ArrayList<Integer> list) {
      int sum = 0;
      for (int i : list) {
        sum += i;
      }
      return sum;
    }

    @Override
    public Boolean call() {
      return subsetSumRandom();
    }

    public boolean subsetSumRandom() {
      ArrayList<Integer> subset = new ArrayList<>();
      int n = this.list.size();
      int upperBound = (1 << n) - 1;
      int randomNumber = (int) (Math.random() * upperBound);
      String binaryString = Integer.toBinaryString(randomNumber);
      String binary = String.format("%" + n + "s", binaryString).replace(" ", "0");
      char[] array = binary.toCharArray();
      for (int i = 0; i < n; i++) {
        if (array[i] == '1') {
          subset.add(this.list.get(i));
        }
      }
      return totalSum(subset) == this.target;
    }
}
