import java.util.Scanner;

public class RestaurantGestor {
  public class Desk {
    private int consuntion;
    private boolean open;

    Desk() {
      this.consuntion = 0;
      this.open = true;
    }

    void addConsuntion(int addition) {
      this.consuntion += addition;
    }

    void ShowConsuntion() {
      System.out.println(this.consuntion);
    }

    void CloseDesk() {
      this.open = false;
    }

    boolean IsDeskOpen() {
      return this.open;
    }

    void ShowData(int DeskNumber) {
      System.out.println("Mesa n" + DeskNumber + "\n"
          + "Consumiciones: " + this.consuntion + "\n"
          + "Cuenta de mesa: " + (this.open ? "Abierta" : "Cerrada"));
    }
  }

  Desk[] desks = { new Desk(), new Desk(), new Desk() };

  void main() {
    int option = 0;
    do {
      option = ScannerSelection(option,
          0, 5,
          """
              ------ GESTOR DE RESTAURANTE ------
              1. Añadir consumicion a una mesa
              2. Ver cuenta de una mesa
              3. Cerrar cuenta de una mesa
              4. Ver resumen del restaurante
              5. Salir
              -----------------------------------
              Elige una opcion:
              """);

      switch (option) {
        case 1 -> {
          AddConsuntionToDesk();
        }
        case 2 -> {
          ShowDeskConsuntion();
        }
        case 3 -> {
          CloseDeskTab();
        }
        case 4 -> {
          ShowRestaurantData();
        }
      }
    } while (option == 5);
  }

  int DeskSelection() {
    int option = 0;
    do {
      option = ScannerSelection(option, 0, 3, "Select Desk 1-2-3");
    } while (desks[option - 1].IsDeskOpen());

    return option;
  }

  int ScannerSelection(int option, int minCheck, int maxCheck, String text) {
    System.out.println(text);
    Scanner keyboard = new Scanner(System.in);
    do {
      option = keyboard.nextInt();
    } while ((!(option > minCheck && option <= maxCheck)));
    keyboard.close();
    return option;
  }

  void AddConsuntionToDesk() {
    int desk = DeskSelection();
    System.out.println("How many Consuntion should i ask");
    Scanner keyboard = new Scanner(System.in);
    int cons = keyboard.nextInt();
    if (cons > 0) {
      desks[desk].addConsuntion(cons);
    }
    keyboard.close();
  }

  void ShowDeskConsuntion() {
    desks[DeskSelection()].ShowConsuntion();
  }

  void CloseDeskTab() {
    desks[DeskSelection()].CloseDesk();
  }

  void ShowRestaurantData() {
    for (int i = 0; i < desks.length; i++) {
      desks[i].ShowData(i);
    }
  }
}
