import java.util.ArrayList;
public class Calculator {

   public float add(ArrayList<Number> variables) {
      float result = 0;
      for(Number variable : variables) {
         result += variable.floatValue();
      }
      return result;
   }
   public float substract(ArrayList<Number> variables) {
      float result = 0;
      for(Number variable : variables) {
         result -= variable.floatValue();
      }
      return result;
   }
   public float multiply(ArrayList<Number> variables) {
      float result = 1;
      for(Number variable : variables) {
         result *= variable.floatValue();
      }
      return result;
   }
   public float divide(ArrayList<Number> variables) {
      float result = 1;
      for(Number variable : variables) {
         result /= variable.floatValue();
      }
      return result;
   }
}
