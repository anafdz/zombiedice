//********************************************************************
//  ZombieDiceGame.java, PHASE FOUR       Template by CS230 Staff S17
//
//  Implements the Zombie Dice Game. Allows a user to play a computer
//  zombie oponent in a race to consume 13 brains.
//
// Exam 2 - Ana Fernandez   
//********************************************************************
import java.util.*;
import java.io.*;

public class ZombieDiceGame {
  //instance variables
  private final int NUM_DICE = 3;
  private Vector<ZombieDie> dice, allDice, brainShotCup;
  private Zombie computer;
  private Zombie player;
  
  public ZombieDiceGame(String playerName, String computerName){
    player = new Zombie(playerName);
    computer = new Zombie(computerName);
    dice = new Vector<ZombieDie> (NUM_DICE); 
    brainShotCup = new Vector<ZombieDie> (13);
    allDice = new Vector<ZombieDie> (13);
    
    
    //add 6 easy 4 medium 3 hard to allDice
    for (int i = 0; i < 6; i++) {
      allDice.add(new ZombieDie("easy"));
    }
    for (int i = 0; i < 4; i++) {
      allDice.add(new ZombieDie("medium"));
    }
    for (int i = 0; i < 3; i++) {
      allDice.add(new ZombieDie("hard"));
    }
  }
  
  private void rollDice(){
    //rolls each one
    //set shotguns and brains aside to brainShotCup
    //puts runners back to allDice depending if it's a brain or a shotgun or a runner
    
    //roll allDice
    for (int i = 0; i < allDice.size(); i++) {
      allDice.get(i).roll();
    }
    
    if (!dice.isEmpty()) {
      for (int i = dice.size()-1; i >= 0; i--) { 
        String value = dice.get(i).getZombieValue();
        if (value.equals("brain") || value.equals("shotgun")) {
          brainShotCup.add(dice.get(i));
          dice.remove(i);
        }
        else {
          allDice.add(dice.get(i));
          dice.remove(i);
        }
      }
    }
      
      //adds three random dice to the dice cup & removes them from allDice
      Random random = new Random();  
      int r1 = random.nextInt(allDice.size()); 
      //save this int so the corresponding die is added to dice and removed from allDice
      dice.add(allDice.get(r1));
      allDice.remove(r1);
      //one less die in allDice to randomly choose an index from!!!!!
      int r2 = random.nextInt(allDice.size()); 
      dice.add(allDice.get(r2));
      allDice.remove(r2);
      int r3 = random.nextInt(allDice.size());
      dice.add(allDice.get(r3));
      allDice.remove(r3);
    }
    
    //a zombie's attack includes rolling the dice, incrementing counts, 
    //printing roll result, printing zombie current counts 
    private int attack(Zombie zombie){ 
      rollDice();
      for (int i = 0; i < NUM_DICE; i++) {
        String dieZombieValue = dice.get(i).getZombieValue();
        String dieDifficulty = dice.get(i).getDifficulty();
        //harvest or take shot depending on dieValue. runner is ignored
        if (dieZombieValue == "brain") {
          zombie.harvestBrain();
        }
        else if (dieZombieValue == "shotgun") {
          zombie.takeShot();
        }
        System.out.println("[(" + dieDifficulty + ")" + dieZombieValue + "]");
      }
      System.out.println("\n" + zombie);
      return zombie.getShots();
    }
    
    private boolean endTurn(Zombie zombie){ 
      //if zombie decides to end before 3 shotguns, zombie gets to eat brains
      if (zombie.getShots() < 3) {
        zombie.feastOnBrains(); 
      }
      else {
        zombie.resetStats(); //otherwise wipes info
      }
      
      //System.out.println("\n" + currentScore());
      
      //preps so the next player can start with 13 dice in allDice
      for (int i = dice.size()-1; i >= 0; i--) {
        allDice.add(dice.get(i));
        dice.remove(i); 
      }    
      
      for (int i = brainShotCup.size()-1; i >= 0; i--) {
        allDice.add(brainShotCup.get(i));
        brainShotCup.remove(i);     
      }
      
      //System.out.println("\n" + currentScore());   
      
      if (zombie.getBrainsAte() >= 13) {
        return true;
      }
      else {
        return false;
      } 
    }
    
    private boolean playerTurn(){
      //roll all the frickin dice at the start of each turn
      for (int i = 0; i < 13; i++) {
        allDice.get(i).roll();
      }  
      boolean hasPlayerWonGame = false;
      Scanner scanner = new Scanner(System.in);
      System.out.println("It's your turn, " + player.getZombieName() + ". Press[Enter] to continue.");
      if (scanner.nextLine().equals("")) {
        attack(player);
        while (player.getShots() < 3 && allDice.size() >= 3) {
          //if there are less than 3 dice left in allDice, the player's turn is over --> endTurn()
          System.out.println("[Enter] to Attack; [S][Enter] to Stop and eat brains");
          String userResponse = scanner.nextLine();
          if (userResponse.equals("")) {
            attack(player);              
          }
          else if (userResponse.equals("S")) {
            scanner.close();
            break;
          }
        }
        hasPlayerWonGame = endTurn(player);
      }  
      return hasPlayerWonGame;
    }
    //returns true if computer won the game
    private boolean computerTurn(){
      System.out.println("Now for " + computer.getZombieName() + " to give it a go."); 
      boolean hasComputerWonGame = false;
      attack(computer);
      
      
      //computer ends turn once it gets two shots becuase it's smart
      while (computer.getShots() < 2 && allDice.size() >= 3) {
        System.out.println("[" + computer.getZombieName() + " chooses to roll again]\n");
        attack(computer);              
      }
      hasComputerWonGame = endTurn(computer);
      return hasComputerWonGame;
    }
    
    public String currentScore(){
      String s ="**************************************\n* "+ player.getZombieName(); 
      s+= " consumed " + player.getBrainsAte() + " of 13 brains\n";
      s+= "* " + computer.getZombieName() + " consumed " + computer.getBrainsAte();
      s+= " of 13 brains\n**************************************";
      return s;
    }
    
    //returns true if player won, false if player didn't win meaning computer won
    public boolean playZombieDiceGame(){
      System.out.println("Welcome to the Zombie Apocalypse. Are you ready to devour some brains?\n");
      System.out.println(currentScore());
      
      boolean hasPlayerWonGame = false;
      boolean hasComputerWonGame = false;
      
      //as long as no one has won
      while (!hasPlayerWonGame && !hasComputerWonGame) { 
        //player always goes first
        hasPlayerWonGame = playerTurn(); 
        //if player didn't win a turn
        if (!hasPlayerWonGame) { 
          hasComputerWonGame = computerTurn(); 
        }
      }
      //prints who won 
      //in this method instead of main because of the local var hasPlayerWonGame and I could not figure out how to do it in main because of that...should have been an instance var prob
      if (hasPlayerWonGame) {
        System.out.println("Congrats, " + player.getZombieName() + ", you win the Apocalypse!");
      }
      else {
        System.out.println("Too bad, " + computer.getZombieName() + " won the Apocalypse!");
      }
      return hasPlayerWonGame; 
    }
    
    
    public static void main(String[] args){
      ZombieDiceGame game;
      //if has two args, take as player names
      if (args.length > 1) { 
        game = new ZombieDiceGame(args[0], args[1]); 
      }
      else { 
        game = new ZombieDiceGame("Ana", "Denisse"); 
      }
      game.playZombieDiceGame();
    }
}

