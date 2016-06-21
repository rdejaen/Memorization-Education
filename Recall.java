import java.util.*;
import java.io.*;

/**
 * Recall.java
 * Assignment: APCS Final Project
 * Reason for class: Plays the Recall game by getting an array of terms and an
 * array of definitions and printing and checking 
 * the user's response.
 *
 * @version 06/20/2016
 * @author Rachel De Jaen
 */
 
public class Recall extends Matching {
   private String inputFile;   

   public Recall (String inputFile) { //contructor extends from the matching class to limit redundancy
      super(inputFile);
      this.inputFile = inputFile;
   }

   public void printAndCheck (ArrayList<String> terms, ArrayList<String> definitions, Scanner console, Random rand, Student user) { //randomly picks a definition and asks for the definition
                                                                                                                                    //checks to see if the user had the correct answer
      int arrayLength = definitions.size();
      System.out.println("The term for this definition is... ");
      user.incrPlays();
      int num = rand.nextInt(definitions.size()); //gets a random definition
      System.out.println(definitions.get(num));
      System.out.println();
      
      String response = console.nextLine().toLowerCase();    
      if (terms.contains(response)) {
         String check = terms.get(terms.indexOf(response)); 
         if (check.equalsIgnoreCase(terms.get(num))) { 
            System.out.println("You got it right! :) ");
            user.incrNumCorrect(); //increases for the stats method
         }
      }
      else { 
         System.out.println("You got it wrong :( ");
      }
      terms.remove(terms.indexOf(terms.get(num))); //remove the term so that it doesn't call it again
      definitions.remove(definitions.indexOf(definitions.get(num)));
   
      System.out.println();
   }
   
}