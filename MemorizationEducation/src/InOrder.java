import java.util.*;
import java.io.*;

/**
 * InOrder.java
 * Reason for class: Plays the In Order game by getting a char array
 * and printing and checking the user's response.
 *
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
   
   public ArrayList<String> getWordArray() throws FileNotFoundException {
      ArrayList<String> words = new ArrayList<String>();  
      Scanner scanWords = new Scanner(new File(inputFile));
      for (int k = 0; scanWords.hasNext(); k++) {    
         String w = scanWords.next();
         words.add(k, w);
      }
      return words;
   }

   public void printAndCheck (Scanner console, String decision, int count, Student user, String sum, char[] chars, ArrayList<String> words) throws FileNotFoundException { //prints the prompt and gets the users response, checks response with the correct answer
      String inputFile = getInputFile();
      Scanner scanner = new Scanner (new File (inputFile));
      decision = user.getDecision();  
      String cont = " ";
      // char[] chars = getCharArray(scanner);      
         
     
      if (decision.equals("character")) {  //if the user wants to memorize a file of characters
         //char[] chars = getCharArray(scanner);//this is getting re initialized everytime, and since sum
                                              //is dependent on this, it doesnt include everything in the string
//          if (count == 1) {
//             sum = chars[0] + "";
//          }
//          else{
//             sum += chars[count];
//          }
         String response = console.nextLine();
         char resp = response.charAt(0);
         char check = chars[count];
         sum += check;
         
         if (resp == (check)) { //if the user's response was correct and it's not at the end of the file
            System.out.print("That was correct! ");
            user.incrNumCorrect();
            user.incrTotal(1);
            
            if (count != chars.length - 1) {
               System.out.println("What is the next character?"); 
            }else{
               System.out.println(); 
               System.out.println("Nice job! You finished the game! ");
               user.stats(); //prints continuous statistics (rolling after every game)
            }           
         }
         else if (resp != check) {
            user.incrTotal(1);
            System.out.println("That is incorrect. The correct answer was " + "'" + check + "'");
            System.out.println("Your answer was " +  "'" + response + "'");
            System.out.println();
            user.incNumIncorrect();
            if (count == chars.length - 1) {
               System.out.println("Nice job! You finished the game!");
            }else{
               
               //System.out.println("What is the next character?");
               //still need to add this stuff to git hub copy
               //String b = new String (chars);
               //c = b.substring(0, b.indexOf(check) + 1);
               //System.out.println(getCharString(scanner));
               //System.out.println(chars[15] + "");
               //sum = getCharString(scanner).substring(0, );///////////added//////the index has issues
               //System.out.println("What is after '" + sum + "' ?");
               
               //cumulative sum would be better 
               System.out.println("What is after " + check + "?");
               
               //make it print the entire thing previous, and not just the last character
            }            
         }
                                          
      }
      else if (decision.equals("word")) { //sequence of words    
         //ArrayList<String> words = getWordArray();    
         String r = console.nextLine();
         String check = words.get(count);
         cont += words.get(words.lastIndexOf(check));         
         if (r.equals(check)) {            
            System.out.print("That was correct!"); 
            System.out.println();
            user.incrNumCorrect();
            user.incrTotal(1);
            if (count != words.size() - 1) {
               System.out.println("What is the next word?");
            }            
         }
         else if (!r.equals(check)) {
            System.out.println("That is incorrect. The correct answer was " + "\"" + check + "\"");
            System.out.println("Your answer was " +  "\"" + r + "\"");
            System.out.println();
            user.incNumIncorrect();
            user.incrTotal(1);
            if (count != words.size() - 1) {
               //String[] wordsC = words.toArray();
               //String p = new String (words);
               //h = p.substring(0, p.indexOf(words) + 1);
               System.out.println("What is after \"" + cont + "\" ?");
            }            
         }
      
      }   
   }  
   
}
