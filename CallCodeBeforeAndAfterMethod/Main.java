class Main {
  public static void main(String[] args) {
      ITruc test = Beans.get(Truc.class, true);
      test.test();
      Truc truc = Beans.get(Truc.class);
      truc.test();
  }
}
