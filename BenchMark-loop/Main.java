import java.util.*;

class Main {
  public static void main(String[] args) {
    long start = System.nanoTime();
    List<Partner> pList = new ArrayList<>();
    for(int i = 0; i < 100000; i++) {
      pList.add(new Partner("toto"));
    }


/*
    Int i = new Int();
    for(Partner p : pList) {
      i.addition(p.getName().equals("toto") ? 1 : 0);
    }
*/

    Int i = new Int();
    Int a = new Int();
    Int b = new Int();
    pList.forEach(p -> {
      i.addition(p.getName().equals("toto") ? 1 : 0);
      a.addition(p.getName().equals("truc") ? 1 : 0);
      b.addition(p.getName().equals("chose") ? 1 : 0);
    });


  System.out.println(i.intValue());
  System.out.println(a.intValue());
  System.out.println(b.intValue());
  /*  int j = 0;
    for(Partner p : pList) {
      j = p.getName().equals("toto") ? j + 1 : j;
    }

    
    System.out.println(j);
*/
    long end = System.nanoTime();
  

    System.out.println(end - start);
  }
}
