public class Truc implements ITruc{

  @Override
  @Transactional
  public void test() {
      System.out.println("in test");
  }

}
