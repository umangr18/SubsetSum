import java.util.ArrayList;

/*
- given an instance in the form "[x x x]:t"
- return the contents of a dat file in the form ...
     param n := 3;
     param weight := x x x;
     param target := t;
 */
public class ILPDataParser {

  ArrayList<Integer> list;
  int target;
  int n;

  ILPDataParser(Pair instance) {
    this.list = instance.getList();
    this.target = instance.getTarget();
    this.n = this.list.size();
  }

  private String printElements() {
    StringBuilder s = new StringBuilder();
    for (int i : this.list) {
      s.append(i).append(" ");
    }
    return s.toString();
  }

  public String convert() {
    return "param n := " + this.n + ";\nparam weight := " + this.printElements() + ";\nparam target := " + this.target + ";";
  }


}
