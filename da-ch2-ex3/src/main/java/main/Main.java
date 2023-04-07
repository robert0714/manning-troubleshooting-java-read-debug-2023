package main;

import java.util.*;

public class Main {

  public static void main(String[] args) {
    Decoder d = new Decoder();
    List list = new ArrayList<String>();
    list.add(null);  // adding null to make the code throw an exception
    Integer result = d.decode(list);
    System.out.println(result);
  }
}
