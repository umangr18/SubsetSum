import java.util.ArrayList;
import java.util.Arrays;

class Exhaustive {
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

  // uses recursion to iterate through subsets to find a match
  // not currently working - idk why
  private static int getTotalSum(ArrayList<Integer> list) {
    int sum = 0;
    for (int i = 0; i < list.size(); i++) {
      sum += list.get(i);
    }
    return sum;
  }
  private static boolean recursiveExhaustive(ArrayList<Integer> list, int target, int index, int sum) {
    for (int i = index; i < list.size() - 1; i++) {
      int currentSum = sum + getTotalSum(list);
      if (currentSum == target || recursiveExhaustive(list, target, index + 1, currentSum)) {
        return true;
      }
    }
    return false;
  }

  // uses binary logic to map and iterate over all the subsets
  // not currently working - array index out of bounds exception (need to pad binary string?)
  private static ArrayList<Integer> intToBinary(int i) {
    ArrayList<Integer> list = new ArrayList<Integer>();
    String binaryString = Integer.toBinaryString(i);
    for (char c : binaryString.toCharArray()) {
      list.add(Character.getNumericValue(c));
    }
    return list;
  }
  private static boolean bitwiseExhaustive(ArrayList<Integer> list, int target) {
    for (int i = 0; i < Math.pow(2, list.size()); i++) {
      ArrayList<Integer> binaryString = intToBinary(i);
      int sum = 0;
      for (int j = 0; j < list.size(); j++) {
        if (binaryString.get(j) == 1) {
          sum += list.get(j);
        }
      }
      if (sum == target) {
        return true;
      }
    }
    return false;
  }

  // main method to call the algorithm
  public static void main(String[] args) {
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
    int target = 3;
    System.out.println("Subset Sum Found? " + subsetSumExhaustive(list, target));
    //System.out.println("Subset Sum Found? " + recursiveExhaustive(list, target, 0, 0));
    //System.out.println("Subset Sum Found? " + bitwiseExhaustive(list, target));
  }
}
