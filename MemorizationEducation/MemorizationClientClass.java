import java.util.*;
import java.io.*;

/**
 * MemorizationClientClass.java
 * Assignment: APCS Final Project
 * Reason for class: This is the class that the user will run to play any of the games. It 
 * handles all the console output, choosing which game to play, deciding if they want to play again, 
 * and calls all the respective game classes.
 *
 * @version 12/06/2016
 * @author Rachel De Jaen
 */
///////////////test to check integration//////////////////////////////
public class MemorizationClientClass {
   public static void main (String [] args) throws FileNotFoundException { //prints the beginning introduction and calls the other intro methods (intro and intro2)
                                                                           //most methods return something, which is stored in the Student object (named user)
      Scanner console = new Scanner(System.in);
      System.out.println("Welcome to Memorization Education");
      System.out.println("Play fun, interactive memorization games!");
      System.out.println("You can choose an input file containing the information you want to memorize");
      System.out.println("What is your first name? ");
      Student user = new Student (); //creates new Student object 
      user.setName(console.nextLine());
      System.out.println();
      user.setInputFile(intro(console, user));
      user.setMethod(intro2(console, user)); //this is the only occurance of setMethod()     
   }
   
   public static String intro (Scanner console, Student user)  { //prints instructions for the user on how to fromat their input file
      String fName = user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1).toLowerCase();
      System.out.println(fName + ", please type the name of your input file: ");
      System.out.println("Need to know how to format your input file? ");
      System.out.println("If so, type \"yes,\" otherwise, please type the name of your input file. ");
      String answer = console.nextLine().toLowerCase();
      System.out.println();
      String newAnswer = "";
      if (answer.equalsIgnoreCase("yes")) { //if they don't know how to format their input file
         help(); //this method prints instructions for the user on how to format their file
         return intro(console, user); //gives the user another option to give the name of their input file
      }
      else if (!answer.endsWith(".txt")) { //makes sure the user's input file is a .txt file
         System.out.println("You're doing it wrong");
         return intro(console, user);
      }
      else {      
         newAnswer = answer;
         return answer;
      }      
   }
   
   public static String intro2 (Scanner console, Student user) throws FileNotFoundException { //lets the user pick what type of game to play
      System.out.println("How do you want to memorize your information? ");
      System.out.println("Choose the method that best matches your input file. ");
      System.out.println("Please note that the game will quit after 3 incorrect answers are typed. ");
      System.out.println("Please type A, B, or C");
      System.out.println("A. Matching: Type \"true\" or \"false\" if the term is paired with the correct definition");
      System.out.println("B. Recall: Type the term when prompted with the definition");
      System.out.println("C. In-Order: Memorize a data set in a specific order");
      String method = "";
      String response = console.nextLine();
      String inputFile = user.getInputFile();
      if (response.equalsIgnoreCase("A") || response.equalsIgnoreCase("matching")) {
         method = "Matching";
         times(console, user); //lets the user pick how many times they want to run through their file
         matching(inputFile, console, user);
      }
      else if (response.equalsIgnoreCase("B") || response.equalsIgnoreCase("recall")) {
         method = "Recall";
         recall(inputFile, console, user);
      }
      else if (response.equalsIgnoreCase("C")) {
         method = "In-Order";
         user.setDecision(chooseInOrderType(console, user));
         String decision = user.getDecision();
         inOrder(inputFile, console, user, decision);
      }
      else { //if the user doesn't pick a, b, or c
         System.out.println("Please pick a valid option");
         System.out.println();
         intro2(console, user);
      }
      String responseStop = playAgain(user, console, inputFile, method);
      if (responseStop.equals("no")) {
         return "stop";
      }else{
         return method;
      }      
   }
   
   public static void times (Scanner console, Student user) { //for the matching game, the user can pick how many times they want to run through their input file
      System.out.println("How many times do you want to run through your file? Please type a number");
      int times = console.nextInt();
      console.nextLine();
      user.setTimes(times);
   }
     
   public static String chooseInOrderType (Scanner console, Student user) {
      System.out.println("Please Choose A or B: ");
         System.out.println("A. Sequence of characters ");
         System.out.println("B. Sequence of words ");
         String response = console.nextLine();
         if (response.equalsIgnoreCase("A")) {
            user.setDecision("character");
         }
         else{
            user.setDecision("word");
         }
      return user.getDecision();
   }
   
   public static void help () { //instuctions for how to format the user's input file
      System.out.println("The Matching and Recall games require an input file with a set of\nterms with their respective definitions on separate lines. ");
      System.out.println("Sample Matching game and Recall game input file: ");
      System.out.println("Bering Strait"); //create random sample input (Key terms)
      System.out.println("a strait between Alaska and the Russian Federation in Asia. ");
      System.out.println();
      System.out.println("The In-Order game can take in two types of input files and tests for the sequence. ");
      System.out.println("The first game can have a sequence of characters or letters, like the following: ");
      System.out.println("3.1415926"); //create random sample input (3.14)
      System.out.println();
      System.out.println("The second can have a sequence of words, like the following: ");
      System.out.println("There is a place where the sidewalk ends"); //create random sample input (poem)
      System.out.println();   
   }
   
   public static String playAgain (Student user, Scanner console, String inputFile, String method) throws FileNotFoundException {
                                                               //gives the user the option to play again, either the same game or a different one
      System.out.println("Do you want to play again? Please type \"yes\" or \"no\"");
      if (method.equals("Matching")) { //I need this because the console structure is different for the matching game than for the other games
         console.nextLine();
      }
      String response = console.nextLine();
      if (response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("y")) { //if the user wants to play again
         user.incrPlays();
         String newMethod = "";
         System.out.println("Do you want to play the same game or a new one? Please type \"same\" or \"new\"");
         
         String newSame = console.nextLine();
         if (newSame.equalsIgnoreCase("new")) {
            user.setInputFile(intro(console, user));
            newMethod = intro2(console, user);       
         }else if (newSame.equalsIgnoreCase("same")){
            newMethod = method;
            if (newMethod.equals("In-Order")) {
               user.setDecision(chooseInOrderType(console, user));
            }
            
            user.setInputFile(intro(console, user));
         }

         if (newMethod.equals("Matching")) {
            times(console, user);
            matching(inputFile, console, user); 
            playAgain(user, console, inputFile, method);           
         }
         else if (newMethod.equals("Recall")) {
            recall(inputFile, console, user);            
            playAgain(user, console, inputFile, method);
         }else if (newMethod.equals("In-Order")){
            String decision = user.getDecision();
            inOrder(user.getInputFile(), console, user, user.getDecision()); 
            playAgain(user, console, inputFile, method);
         }
         
      }
      else if (response.equalsIgnoreCase("no") || response.equalsIgnoreCase("n")) { //if they do not want to play another game
         System.out.println("Thanks for playing!");
      }
      else{                                                                                                                                                                                                                                                                                                                                                                              
         System.out.println("Please type a valid answer ");
         playAgain(user, console, inputFile, method);
      }
      return response;
   }
   
   public static void matching (String inputFile, Scanner console, Student user) throws FileNotFoundException { //plays the matching game, calls the matching class
      Matching match = new Matching(inputFile);  //new matching game                                                 
      Random rand = new Random();                                                                 
      ArrayList<String> terms = match.getTerms(); //creates arrayList of the terms 
      ArrayList<String> definitions = match.getDefinitions(); //arayList of the definitions
      int sizeOfArray = terms.size();

      int count = 0; 
      while (count <= terms.size() * user.getTimes() - 1 && user.getNumIncorrect() < 3) {           
         match.printAndCheck(terms, definitions, console, rand, user); //goes to the matching class, the printAndCheck method
         count++;         
      }
      if (user.getNumIncorrect() == 3) {
         System.out.println("You got more than 3 incorrect answers ");
      }
      System.out.println("Nice job! You finished the game!"); 
      user.stats();
   }
   
   public static void recall (String inputFile, Scanner console, Student user) throws FileNotFoundException { //plays the recall game 
      Recall rec = new Recall(inputFile); //creates new recall objects
      Random rand = new Random();
      ArrayList<String> terms = rec.getTerms();
      ArrayList<String> definitions = rec.getDefinitions();
      int sizeOfArray = terms.size();
      int count = 0; 
      user.incrTotal(terms.size()); 
       
      while (terms.size() >= 1 && user.getNumIncorrect() < 3) { //goes through the entire input file
         rec.printAndCheck(terms, definitions, console, rand, user);
         count++; 
      } 
      if (user.getNumIncorrect() == 3) {
         System.out.println("You got more than 3 incorrect answers ");
      }
      System.out.println("Nice job! You finished the game!"); 
      user.stats();
   }
   
   public static void inOrder (String inputFile, Scanner console, Student user, String decision) throws FileNotFoundException { //plays the in order game
      decision = user.getDecision();
      InOrder in = new InOrder(inputFile, decision);    
      int count = 1;
      int sizeOfArray = 0; 
      String sum = "";
      System.out.println("Please type the next " + decision + ". Here's the first one! ");  
      Scanner s = new Scanner(new File(inputFile));
      char[] chars = in.getCharArray(s);
      ArrayList<String> words = in.getWordArray();
      if (decision.equals("character")) {
         if (user.getPlays() < 1) { 
            user.setTotal(chars.length);
            user.incrTotal(2);
         }
         System.out.println(chars[0]); 
         sizeOfArray = chars.length;
         while (count <= sizeOfArray - 1 && user.getNumIncorrect() < 3) {
            in.printAndCheck(console, decision, count, user, sum, chars, words); //goes to the print and check method in the in order class 
            count++; 
         }
         if (user.getNumIncorrect() == 3) {
            System.out.println("You got more than 3 incorrect answers ");
         }
         System.out.println("Nice job! You finished the game! ");        
         user.stats();
      }    
      else if (decision.equals("word")) {
          
         if (user.getPlays() < 1) {
            user.setTotal(words.size());
            user.incrTotal(1); //used to be 2
         }
         System.out.println(words.get(0)); //added
         sizeOfArray = words.size();                
         while (count <= sizeOfArray - 1 && user.getNumIncorrect() < 3) { 
            in.printAndCheck(console, decision, count, user, sum, chars, words);
            count++; 
         }        
         if (user.getNumIncorrect() == 3) {
            System.out.println("You got more than 3 incorrect answers ");
         }
         System.out.println("Nice job! You finished the game! ");
         user.stats();
      }
   }

}