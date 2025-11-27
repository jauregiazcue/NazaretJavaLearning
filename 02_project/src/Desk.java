public class Desk {
  private int consuntion;
  private String waitress;

  Desk(String waitress) {
    this.waitress = waitress;
    this.consuntion = 0;
  }

  public String getWaitress() {
    return this.waitress;
  }

  public int getConsuntion() {
    return this.consuntion;
  }

  public void addConsuntion(int addition) {
    this.consuntion += addition;
  }

  public void clearConsuntions() {
    this.consuntion = 0;
  }

  public void showConsuntion() {
    System.out.println("Desk Consuntion: " + this.consuntion);
  }

  public void showData(int DeskNumber) {
    System.out.println("Mesa n" + DeskNumber + "\n"
        + "Consumiciones: " + this.consuntion + "\n");
  }
}
