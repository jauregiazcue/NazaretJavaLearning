import java.util.Scanner;
import java.util.ArrayList;

public class App {

  public static void UseCalculator(String action, ArrayList<Number> variables) {
    Calculator cal = new Calculator();
    switch (action) {
      case "add":
        System.out.println("Add : " + cal.add(variables));
        break;
      case "sub":
        System.out.println("Substract : " + cal.substract(variables));
        break;
      case "mul":
        System.out.println("Multiply : " + cal.multiply(variables));
        break;
      case "div":
        System.out.println("Divide : " + cal.divide(variables));
        break;
      default:
        System.out.println("Something went wrong");
        break;
    }
  }

  public static void main(String[] args) throws Exception {
    ArrayList<Number> variables = new ArrayList<Number>();
    Scanner keyboard = new Scanner(System.in);

    // Set how many variables there will be
    System.out.println("How many numbers are you going to use :");
    int quantity = keyboard.nextInt();

    // Set does variables
    for (int i = 0; i < quantity; i++) {
      System.out.println("Set value for number " + i);
      variables.add(keyboard.nextInt());
    }
    // Set action
    String action = new String("");
    // Will not let you continue running until you select a correct action
    do {
      System.out.println("What action are you doing: ");
      System.out.println("add | sub | mul | div");
      action = keyboard.nextLine();
      System.out.println("Check : " + action);
    } while (action.equals("add") ||
        action.equals("sub") ||
        action.equals("mul") ||
        action.equals("div"));

    // Close keyboard to remove memory leaks
    keyboard.close();
    // Create the calculator
    UseCalculator(action, variables);

  }
}