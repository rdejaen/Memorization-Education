import java.util.*;
import java.io.*;

/**
 * Matching.java
 * Assignment: APCS Final Project
 * Reason for class: Plays the Matching game by getting an array of terms and an
 * array of definitions and printing and checking 
 * the user's response.
 *
 *
 * @version 06/20/2016
 * @author Rachel De Jaen
 */

public class Matching {
   private String inputFile;
   
   public Matching (String inputFile) { //creates a new matching object
      this.inputFile = inputFile;
   }
   
   public String getInputFile() { //returns the name of the input file as a String
      return inputFile;
   }
   
   public ArrayList<String> getTerms () throws FileNotFoundException { //this method and the getDefinitions method are exactly the same besides what they return
                                                                       //goes through the input file and stores all the terms in an arrayList
      String inputFile = getInputFile();
      Scanner scanner = new Scanner (new File (inputFile));
      ArrayList<String> terms = new ArrayList<String>();
      ArrayList<String> definitions = new ArrayList<String>();
      while (scanner.hasNextLine()) {
         terms.add(scanner.nextLine().toLowerCase());
         definitions.add(scanner.nextLine());
      }
      return terms;
   }
   
   public ArrayList<String> getDefinitions () throws FileNotFoundException { //this method and the getTerms method are exactly the same besides what they return
                                                                             //goes through the input file and stores all the terms in an arrayList
      String inputFile = getInputFile();
      Scanner scanner = new Scanner (new File (inputFile));
      ArrayList<String> terms = new ArrayList<String>();
      ArrayList<String> definitions = new ArrayList<String>();
      while (scanner.hasNextLine()) {
         terms.add(scanner.nextLine().toLowerCase());
         definitions.add(scanner.nextLine());
      }
      return definitions;
   } 
      
   public void printAndCheck (ArrayList<String> terms, ArrayList<String> definitions, Scanner console, Random rand, Student user) { 
                                                                     //randomly pairs a term and a definition and asks the user if the pairing is "true" or "false."
                                                                     //checks to see if the user's answer is correct by comparing it to its index in the arrayList
      int arrayLength = terms.size();

      int numTerms = rand.nextInt(arrayLength);
      int numDefs = rand.nextInt(arrayLength);  
      int num = rand.nextInt(terms.size());
      System.out.println("Choose \"true\" or \"false\"");
      System.out.println(terms.get(numTerms) + " = " + definitions.get(numDefs) + "?"); //random pairing
      System.out.println();
      boolean correctAnswer = (terms.indexOf(terms.get(numTerms)) == definitions.indexOf(definitions.get(numDefs))); //the correct answer is a boolean, if the index of the term is the same as the index of the definition
      String response = console.next();
      if (response.equalsIgnoreCase("true") || response.equalsIgnoreCase("false")) {
         boolean userAnswer = (response.equalsIgnoreCase("true") || response.equalsIgnoreCase("t"));
         
         if (userAnswer == correctAnswer) {
            System.out.println("You got it right! :)");
            user.incrNumCorrect(); //for the stats method
            user.incrTotal(1); //for the stats method
         }else {
            System.out.println("You got it wrong :( ");
            user.incrTotal(1);
         }
      }else{
         System.out.println("Always remember to only choose \"true\" or \"false\"!"); //the user's response will be considered false if they do not type "true" or "false"
         user.incrTotal(1);
      }

      System.out.println();
   }
}