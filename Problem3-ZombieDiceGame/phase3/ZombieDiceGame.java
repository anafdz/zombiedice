//********************************************************************
//  ZombieDiceGame.java, PHASE THREE       Template by CS230 Staff S17
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
  private Vector<ZombieDie> dice; //vector holding dice
  private Zombie computer;
  private Zombie player;
  
  public ZombieDiceGame(String playerName, String computerName){
    player = new Zombie(playerName);
    computer = new Zombie(computerName);
    
    dice = new Vector<ZombieDie>(NUM_DICE);
    
    //adds one die of each diff level to the dice Vector
    dice.add(new ZombieDie("easy"));
    dice.add(new ZombieDie("medium"));
    dice.add(new ZombieDie("hard"));
  }
  
  private void rollDice(){
    //rolls each one
    for (int i = 0; i < NUM_DICE; i++) {
      dice.get(i).roll();
    }
  }
  
  //a zombie's attack includes rolling the dice, incrementing counts, 
  //printing roll result, printing zombie current counts 
  private int attack(Zombie zombie){ 
    rollDice();
    for (int i = 0; i < NUM_DICE; i++) {
      String dieZombieValue = dice.get(i).getZombieValue();
      //harvest or take shot depending on dieValue. runner is ignored
      if (dieZombieValue == "brain") {
        zombie.harvestBrain();
      }
      else if (dieZombieValue == "shotgun") {
        zombie.takeShot();
      }
      System.out.println("[" + dieZombieValue + "]");
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
    
    //keeps looping until a zombie eats 13 brains
    if (zombie.getBrainsAte() >= 13) { 
      return true; //true is player wins
    }
    else {
      return false; //true if computer wins
    }
  }
  
  private boolean playerTurn(){
    boolean hasPlayerWonGame = false;
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("It's your turn, " + player.getZombieName() + ". Press[Enter] to continue.");
    if (scanner.nextLine().equals("")) { //if pressed [Enter]
      attack(player);
      while (player.getShots() < 3) { //while the game is not over
        System.out.println("[Enter] to Attack; [S][Enter] to Stop and eat brains");
        String userResponse = scanner.nextLine();
        // Enter =  attack
        if (userResponse.equals("")) { 
          attack(player);              
        }
        // S = end turn
        else if (userResponse.equals("S")) { 
          scanner.close();
          break;
        }
      }
      //change the boolean if the player won the game
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
    while (computer.getShots() < 2) { 
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
