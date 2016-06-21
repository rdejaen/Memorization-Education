import java.util.*;
import java.io.*;

/**
 * InOrder.java
 * Assignment: APCS Final Project
 * Reason for class: Plays the In Order game by getting a char array
 * and printing and checking the user's response.
 *
 * @version 06/20/2016
 
 * @author Rachel De Jaen
 */

public class InOrder {
   private String inputFile;
   private String decision;
   
   public InOrder (String inputFile, String decision) { //constructor for a new InOrder object
      this.inputFile = inputFile;
      this.decision = decision;
   }
   
   public String getInputFile () { //returns a String name of the user's input file
      return inputFile;
   }  
   
   public String getCharString(Scanner scanner) { //I need this method because there is no hasNextChar() method
      String charString = "";      
      while (scanner.hasNext()) {
         charString += scanner.next();
      } 
      return charString;
   }
   
   public char[] getCharArray(Scanner scanner) { //creates a char array of the information in the user's input file
      String charString = getCharString(scanner);
      char [] chars = charString.toCharArray();
      return chars;
   }
   

//*****************************************************************************************************************************//   
//    public ArrayList<String> getWordArray(Scanner scanner) {
//       ArrayList<String> words = new ArrayList<String>();        
//    //       for (int k = 0; scanner.hasNext(); k++) { //causes the same exception if I do the while loop
//    //          String single = scanner.next();
//    //          words.set(k, single);
//    //       }
//       int k = 1;
//       while (scanner.hasNext()) {
//          String w = scanner.next();
//          words.set(k, w); //causing the index out of bounds exception
//          k++;
//       }
//       return words;
//    }
//*****************************************************************************************************************************//

   public void printAndCheck (Scanner console, Scanner scanner, String decision, int count, char[] chars, Student user) throws FileNotFoundException { //prints the prompt and gets the users response, checks response with the correct answer
      String inputFile = getInputFile();
            
      if (decision.equals("character")) {  //if the user wants to memorize a file of characters
         String response = console.nextLine();
         char resp = response.charAt(0);
         char check = chars[count];
         if (resp == (check) && count != chars.length - 1) { //if the user's reponse was correct and it's not at the end of the file
            System.out.println("That was correct! What is the next character?"); 
            user.incrNumCorrect(); //for printing the stats
            user.incrTotal(1); //for printing the stats
         }
         else if (resp == (check) && count == chars.length - 1) { //if the user's reponse was correct but it's at the end of the input file 
            System.out.println("That was correct! ");
            System.out.println("Nice job! You finished the game! ");
            user.incrNumCorrect();
            user.incrTotal(1);
            user.stats(); //prints continuous statistics (rolling after every game)
         }
         else{ //if the user's response was not correct
            user.incrTotal(1);
            System.out.println("That is incorrect. The correct answer was " + "'" + check + "'");
            System.out.println("Your answer was " +  "'" + response + "'");
            System.out.println("What is the next character?");
         }                                    
      }
   }
//*****************************************************************************************************************************//
//       else if (decision.equals("word")) { //sequence of words    
//          ArrayList<String> words = getWordArray(scanner);     
//          System.out.print(words.get(0));
//          for (int m = 1; m <= words.size() - 2; m++) {
//             boolean isCorrectB = true;
//             while (isCorrectB) {
//                String responseB = console.nextLine();
//                if (responseB.equalsIgnoreCase(words.get(count + 1))) {
//                   isCorrectB = true;
//                }
//                else {
//                  isCorrectB = false;
//                   System.out.println("That is incorrect. The correct answer was " + words.get(count + 1).toString());
//                   System.out.println("Your answer was " + responseB);
//                }
//            }
//         }
//       }
//*****************************************************************************************************************************//
   
   
}
