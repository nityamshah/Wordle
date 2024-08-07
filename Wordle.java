import java.util.*;
/*
 * File: Wordle.java
 * -----------------
 * This module is the starter file for the Wordle assignment.
 * BE SURE TO UPDATE THIS COMMENT WHEN YOU COMPLETE THE CODE.
 */

public class Wordle {

   public void run() {
      gw = new WordleGWindow();
      System.out.println(THEWORD);
      
      /*
      String word = words[(int)(Math.random()*words.length)];
      for (int x = 0; x < word.length(); x++){
         String letter = word.substring(x, x+1);
         gw.setSquareLetter(0, x, letter);
      }
      */
        
      gw.addEnterListener((s) -> enterAction(s));
   }

/*
 * Called when the user hits the RETURN key or clicks the ENTER button,
 * passing in the string of characters on the current row.
 */

/* WORKING VERSION EXCEPT THAT ONE PART
   public void enterAction(String s) {
      boolean realWord = false;
      String lower = s.toLowerCase();
      String upper = s.toUpperCase();
      for (String x : WordleDictionary.FIVE_LETTER_WORDS){
         if (x.equals(lower)){
            realWord = true;
         }
      }
      if (realWord){
         if (lower.equals(THEWORD)){
            for (int x = 0; x < lower.length(); x++){
                gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.CORRECT_COLOR);
                gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.CORRECT_COLOR);
            }
            gw.showMessage("Correct Word.");
         }
         else {
            for (int x = 0; x < lower.length(); x++){
               //color sqaures and keys
               if (lower.substring(x,x+1).equals(THEWORD.substring(x, x+1))){
                  gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.CORRECT_COLOR);
                  gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.CORRECT_COLOR);
               }
               else if (THEWORD.indexOf(lower.substring(x, x+1)) != -1){
                  gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.PRESENT_COLOR);
                  gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.PRESENT_COLOR);
               }
               else if (THEWORD.indexOf(lower.substring(x, x+1)) == -1){
                  gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.MISSING_COLOR );
                  gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.MISSING_COLOR);
               }
            }
            gw.setCurrentRow(gw.getCurrentRow() + 1);
         }
      }
      else {
         gw.showMessage("Not in word list.");
      }
        
   }
*/
   
   //the way that takes niche scenarios into account
   //array of THEWORD, go through and make green letters green, then cross them off the list. then go through guess again and if its not green and in there then yellow, else gray
   public void enterAction(String s) {
      boolean realWord = false;
      String lower = s.toLowerCase();
      String upper = s.toUpperCase();
      ArrayList<String> theWordAsList = new ArrayList<>();
      for (int x = 0; x < THEWORD.length(); x++){
         //makes array list
         theWordAsList.add(THEWORD.substring(x,x+1));
      }
      //is it a real word
      for (String x : WordleDictionary.FIVE_LETTER_WORDS){
         if (x.equals(lower)){
            realWord = true;
         }
      }
      //if its a real word
      if (realWord){
         //if its the word
         if (lower.equals(THEWORD)){
            for (int x = 0; x < lower.length(); x++){
                gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.CORRECT_COLOR);
                gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.CORRECT_COLOR);
            }
            gw.showMessage("Correct Word.");
         }
         else {
            for (int x = 0; x < lower.length(); x++){
               //color green sqaures and keys
               if (lower.substring(x,x+1).equals(THEWORD.substring(x, x+1))){
                  gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.CORRECT_COLOR);
                  gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.CORRECT_COLOR);
                  theWordAsList.remove(lower.substring(x,x+1)); 
               }
            }   
            
            for (int x = 0; x < lower.length(); x++){
               //color sqaures and keys
               if (THEWORD.indexOf(lower.substring(x, x+1)) == -1){ 
                  gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.MISSING_COLOR );
                  gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.MISSING_COLOR);
               }
               else if ((THEWORD.indexOf(lower.substring(x, x+1)) != -1) && (gw.getSquareColor(gw.getCurrentRow(), x) != WordleGWindow.CORRECT_COLOR) && (theWordAsList.contains(lower.substring(x, x+1)))){
                  gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.PRESENT_COLOR);
                  gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.PRESENT_COLOR);
                  theWordAsList.remove(lower.substring(x,x+1)); 
               }
               else if ((THEWORD.indexOf(lower.substring(x, x+1)) != -1) && (gw.getSquareColor(gw.getCurrentRow(), x) != WordleGWindow.CORRECT_COLOR) && (gw.getSquareColor(gw.getCurrentRow(), x) != WordleGWindow.PRESENT_COLOR) && !(theWordAsList.contains(lower.substring(x, x+1)))){
                  gw.setSquareColor(gw.getCurrentRow(), x, WordleGWindow.MISSING_COLOR );
                  gw.setKeyColor(upper.substring(x,x+1), WordleGWindow.MISSING_COLOR); 
               }

            }
            gw.setCurrentRow(gw.getCurrentRow() + 1);
         }
      }
      else {
         gw.showMessage("Not in word list.");
      }
        
   }


/* Startup code */

   public static void main(String[] args) {
      new Wordle().run();
   }

/* Private instance variables */

   private WordleGWindow gw;
   private String THEWORD = WordleDictionary.WORDS_TO_PICK_FROM[(int)(Math.random()*WordleDictionary.WORDS_TO_PICK_FROM.length)];

}
