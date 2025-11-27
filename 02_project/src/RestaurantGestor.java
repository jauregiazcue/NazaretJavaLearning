import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class RestaurantGestor {
  Scanner keyboard = new Scanner(System.in);
  final int NUM_MESAS = 3;

  Desk[] desks = new Desk[NUM_MESAS];
  int totalWins = 0;
  int totalDesksClosed = 0;
  public static final String NOMBRE_FICHERO_TICKETS = "tickets.txt";
  String[] waitresses = { "Laura", "Kai", "Adam", "Haizea", "Eric", "Mark",
      "Derek", "Aitana", "Elena", "Isabel", "Ainhoa", "Eneko", "Markel"
  };

  RestaurantGestor() {
    for (int i = 0; i < desks.length; i++) {
      desks[i] = new Desk(waitresses[(new Random()).nextInt(waitresses.length - 1, 0)]);
    }
  }

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
        case 5 -> {
          System.out.println("Closing Restaurant");
        }
      }
    } while (option != 5);
    this.keyboard.close();
  }

  int ScannerSelection(int option, int minCheck, int maxCheck, String text) {
    System.out.println(text);
    do {
      option = this.keyboard.nextInt();
    } while ((!(option > minCheck && option <= maxCheck)));
    return option;
  }

  int DeskSelection() {
    int option = 0;
    option = ScannerSelection(
        option, 0, 3,
        "Select Desk 1-2-3");

    return option - 1;
  }

  void AddConsuntionToDesk() {
    int desk = DeskSelection();
    System.out.println("How many Consuntion should i add");
    int cons = this.keyboard.nextInt();
    if (cons > 0) {
      desks[desk].addConsuntion(cons);
    }
  }

  void ShowDeskConsuntion() {
    desks[DeskSelection()].showConsuntion();
  }

  void CloseDeskTab() {
    int desk = DeskSelection();

    this.totalDesksClosed++;
    this.totalWins += desks[desk].getConsuntion();
    writeRecieve(
        LocalDateTime.now().format(
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
            + "_tickets.txt",
        desk, "Laura");
    desks[desk].clearConsuntions();
  }

  void ShowRestaurantData() {
    System.out.println("Total Wins: " + this.totalWins);
    System.out.println("Total Desks Closed: " + this.totalDesksClosed);
    for (int i = 0; i < desks.length; i++) {
      desks[i].showData(i);
    }
  }

  void writeRecieve(String file,
      int desk,
      String Waitress) {
    try (FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw)) {

      out.println("-------- Recieve --------");
      out.println("Desk: " + desk);
      out.println("Waitress: " + Waitress);
      out.printf("Payment Needed: %d \n", desks[desk].getConsuntion());
      out.println("------------------------");

    } catch (IOException e) {
      System.out.println("Something went wrong" + e.getMessage());
    }
  }
}
