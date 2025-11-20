import java.util.Scanner;

public class Aeroplane {
    private int cost = 0;
    private String classType = "";
    private String menu = "";
    private String newspaper = "";

    void SetCostAndType(Scanner keyboard, int costAdder, String classType) {
        if (classType == "Alcohol") {
            System.out.println("Would You Like Vegetarian or other ? ");
            menu = (keyboard.nextLine() == "Veg") ? "Vegetarian" : "Without Restriction";
        }

        System.out.println("Would You Like " + classType + "?");

        if (keyboard.nextLine().equals("Yes")) {
            this.cost += costAdder;
            if (classType == "Class A") {
                System.out.println("What newspaper would you like ? ");
                this.newspaper = (keyboard.nextLine() == "ABC") ? "ABC" : ((keyboard.nextLine() == "DEF") ? "DEF" : "GHI");
                SetCostAndType(keyboard, 50, "Class B");
            } else if (classType == "Class B") {
                SetCostAndType(keyboard, 60, "Class C");
            } else if (classType == "Class C") {
                SetCostAndType(keyboard, 12, "Alcohol");
            }
            this.classType = (classType != "Alcohol" ? classType : "Class C");
        } else {
            keyboard.close();
            return;
        }
    }

    public int getCost() {
        return this.cost;
    }

    public String GetType() {
        return this.classType;
    }

    public String GetMenu() {
        return this.menu;
    }

    public Aeroplane() {
        Scanner keyboard = new Scanner(System.in);
        SetCostAndType(keyboard, 200, "Class A");

        System.out.println("Class :" + this.classType);
        System.out.println("Cost :" + this.cost);
        System.out.println("Menu :" + this.menu);
        System.out.println("Newspaper :" + this.newspaper);
    }
}
