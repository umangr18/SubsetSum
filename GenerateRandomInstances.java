import java.util.ArrayList;

class Main {
  public static void main(String[] args) {
    for (int i = 5; i <= 50; i += 5) { // number of elements in list (5-50)
      System.out.println(randomSetGenerator(i) + "\n");
    }
  }

  private static ArrayList<Integer> randomSetGenerator(int numElements) {
    ArrayList<Integer> list = new ArrayList<>();
    int lower = numElements * -1;
    int upper = numElements;
    for (int i = 0; i < numElements; i++) {
      int num = (int) ((Math.random() * (upper - lower)) + lower);
      list.add(num);
    }
    return list;
  }
}
