import java.util.ArrayList;

public class Pair {
  private final ArrayList<Integer> list;
  private final int target;

  Pair(ArrayList<Integer> list, int target) {
    this.list = list;
    this.target = target;
  }

  public ArrayList<Integer> getList() {
    return this.list;
  }

  public int getTarget() {
    return this.target;
  }
}
