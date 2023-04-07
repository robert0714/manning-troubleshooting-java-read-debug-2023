package main;

import java.util.*;

public class Main {

  public static void main(String[] args) {
    Decoder d = new Decoder();
    Integer result = d.decode(Arrays.asList("ab1c", "a112c", "abcd", "1234"));
    System.out.println(result);
  }
}
