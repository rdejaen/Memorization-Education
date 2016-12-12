/**
 * Student.java
 * Assignment: APCS Final Project
 * Reason for class: This creates the Student object which stores the info about 
 * the user and records their status in the game
 *
 * @version 12/06/2016
 * @author Rachel De Jaen
 */

public class Student {
   private String name;
   private String inputFile;
   private String method;
   private int score;
   private int times;
   private int numCorrect;
   private int total;
   private int plays = 1;
   private String decision;
   
   public Student () {
      
   }
   
   public void setDecision(String decision) {
      this.decision = decision;
   }
   
   public String getDecision() {
      return decision;
   }
   
   public int getTimes() { //returns the number of times the user wants to run through their input file
      return times;
   }
   
   public void setTimes(int times) { //sets the number of times the user wants to run through their input file
      this.times = times;
   }
   
   public String getInputFile() { //returns the name of the users input file as a String
      return inputFile;
   }
   
//    public String getMethod() { //returns the name of the game the user wants to play
//       return method;
//    }
   
   public void setName(String name) { //sets the user's name
      this.name = name;
   }
   
   public String getName() { //returns the user's name
      return name;
   }
   
   public void incrPlays() { //increases the number of plays (number of total completed games)
      this.plays += 1;
   }
   
   public int getPlays() { //returns the number of plays
      return plays;
   }
   
   public void setInputFile(String inputFile) { //sets the name of the input file the user wants to use
      this.inputFile = inputFile;
   }
   
   public void setMethod(String method) { //sets the name of the game the user wants to play
      this.method = method;
   }
   
   //ALL BELOW FOR STATS
   
   public void stats () { //prints out the rolling statistics after every game
      System.out.println();
      System.out.println("STATISTICS (continuous) ");
      System.out.println("Number Correct: " + getNumCorrect() + " out of " + getTotal());
      String numF = String.format("Percent Correct: %.1f" , ((double) getNumCorrect() / getTotal()) * 100);
      System.out.println(numF + "%");   
   }
   
   public void setNumCorrect(int setNum) { //sets the number of correct answers the user got
      this.numCorrect = setNum;
   }
   
   public int getNumCorrect () { //returns the number of correct answers
      return numCorrect;
   }
   
   public void incrNumCorrect () { //increases the number of correct answers by one
      this.numCorrect += 1;
   } 
   
   public int getTotal () { //returns the total number of questions
      return total;
   } 
   
   public void setTotal (int total) { //sets the total to a specific amount
      this.total = total;
   }
   
   public void incrTotal (int incr) { //increases the total by a specific amount
      this.total += incr;
   }

}
