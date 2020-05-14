import java.util.ArrayList;

import javax.swing.JLabel;

/**
 * Hangman Game Logic.
 * @Author Afaq Anwar
 * @ID 1263265
 * @Version 05/13/2020
 */





public class Hangman {
    private String gameWord;
    private String[] guessWord;
    private String guess;
    private int guessAmount;
    private final int guessLimit = 9;
    private boolean hasWon;

    
    
 
    
    
    public Hangman(String gameWord) {
        this.gameWord = gameWord.toLowerCase();
        guessWord = new String[gameWord.length()];
        for (int i = 0; i < this.gameWord.length(); i++) {
            guessWord[i] = "_";
        }
        this.guess = "";
        this.guessAmount = 0;
        hasWon = false;
    }

    public Hangman() {
        this.gameWord = "";
        this.guess = "";
        this.guessAmount = 0;
        hasWon = false;
    }

    public void setGameWord(String gameWord) { this.gameWord = gameWord.toLowerCase(); }

    public String[] getGuessWord() {
        String[] word = new String[this.guessWord.length];
        for (int i = 0; i < this.guessWord.length; i++) {
            word[i] = this.guessWord[i];
        }
        return word;
    }
    public void setGuessWord() {
        guessWord = new String[gameWord.length()];
        for (int i = 0; i < this.gameWord.length(); i++) {
            guessWord[i] = "_";
        }
    }

    public boolean getWinState() { return hasWon; }
    public boolean hasReachedLimit() { return guessAmount == guessLimit; }

    public boolean guess(String currentGuess) {
        this.guess = currentGuess.toLowerCase();
        guessAmount++;
        if (gameWord.contains(guess)) {
            ArrayList<Integer> indexes = new ArrayList<>();
            int index = 0;
            while (index != -1) {
                index = gameWord.indexOf(guess, index);
                if (index != -1) {
                    indexes.add(index);
                    index++;
              
               if (guessAmount == 1) { 
            	   
            	   		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |            ");
                		System.out.println("        |            ");
                		System.out.println("       	|            ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 2) { 
                		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |      |     ");
                		System.out.println("        |            ");
                		System.out.println("       	|            ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 3) { 
                		System.out.println("         _______");
                		System.out.println("        |      |    ");
                		System.out.println("        |      O    ");
                		System.out.println("        |      |    ");
                		System.out.println("        |      |    ");
                		System.out.println("       	|           ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 4) { 
                		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |      |-    ");
                		System.out.println("        |      |     ");
                		System.out.println("       	|            ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 5) { 
                		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |     -|-    ");
                		System.out.println("        |      |     ");
                		System.out.println("       	|            ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 6) { 
                		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |     -|-    ");
                		System.out.println("        |      |     ");
                		System.out.println("       	|     /      ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 7) { 
                		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |     -|-    ");
                		System.out.println("        |     /|    ");
                		System.out.println("       	|            ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 8) { 
                		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |     -|-    ");
                		System.out.println("        |      |     ");
                		System.out.println("       	|     /|     ");
                		System.out.println("       	|          ");
                		System.out.println("       	|  Help !!        ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	} else if (guessAmount == 9) { 
                		System.out.println("         _______");
                		System.out.println("        |      |     ");
                		System.out.println("        |      O     ");
                		System.out.println("        |     -|-    ");
                		System.out.println("        |      |     ");
                		System.out.println("       	|     /|     ");
                		System.out.println("       	|          ");
                		System.out.println("       	|  Help Now!!        ");
                		System.out.println("         ____________");
                		System.out.print("\n");
                	}
                }
            }
            for (Integer i : indexes) {
                guessWord[i] = guess;
            }
            this.checkWin();
            return true;
        }
        return false;
    }

    private void checkWin() {
        hasWon = true;
        for (int i = 0; i < gameWord.length(); i++) {
            if (!guessWord[i].equals(gameWord.substring(i, i+1))) {
                hasWon = false;
                
                
            }
        }
    }


    public void reset(String gameWord) {
        this.gameWord = gameWord.toLowerCase();
        this.setGuessWord();
        this.guess = "";
        this.guessAmount = 0;
    }
}
