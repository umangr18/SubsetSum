import java.util.ArrayList;

/*
- given an instance in the form "[x ... x]:t"
- return the contents of a dat file in the form ...
     param target := t;
     set E := 1 ... n ;
     param weight :=
       1  x
       .  x
       .  x
       .  x
       n  x
     ;
 */
public class ILPDataParser {

  ArrayList<Integer> list;
  int target;

  ILPDataParser(Pair instance) {
    this.list = instance.getList();
    this.target = instance.getTarget();
  }

  private String set() {
    StringBuilder s = new StringBuilder();
    for (int i = 1; i <= this.list.size(); i++) {
      s.append(i).append(" ");
    }
    return s.toString();
  }

  private String weight() {
    StringBuilder s = new StringBuilder();
    for (int i = 1; i <= this.list.size(); i++) {
      s.append("  ").append(i).append("  ").append(this.list.get(i)).append("\n");
    }
    return s.toString();
  }

  public String convert() {
    return "param target := " + this.target + ";\nset E := " + this.set() + ";\nparam weight := " + this.weight() + ";";
  }

}
