//********************************************************************
//  Zombie.java, PHASE FOUR       Template by CS230 Staff S17
//
//  Represents one player of the Zombie Dice Game, either a user
//  or the computer. Keeps track of the statistics of Zombie-ness.
//
// Exam 2 - Ana Fernandez
//********************************************************************
public class Zombie {
  
  //instance vars
  private String zombieName;
  private int brainsAte, brainsHarvested, shots; //keeps track of the numbers
  
  public Zombie(String name){
    zombieName = name;
  }
  
  //getters
  public String getZombieName() {
    return zombieName;
  }
  
  public int getBrainsAte() {
    return brainsAte;
  }
  
  public int getBrainHarvested() {
    return brainsHarvested;
  }
  
  public int getShots() {
    return shots;
  }
  
  public void harvestBrain(){
    brainsHarvested++; //old plus new brains
  }
  
  public void takeShot(){
    shots++; //old plus, overrides old int of shots
  }
  
  public void feastOnBrains(){
    //increase brainsAte and wipe out this turn's harvest and shots taken
    brainsAte+=brainsHarvested;
    brainsHarvested = 0;
    shots = 0;
  }
  
  public void resetStats(){
    //these values are reset to zero after their turn
    brainsHarvested=0;
    shots=0;
  }
  
  public String toString(){
    String s = zombieName + ": " + brainsHarvested + " brains harvested, " + shots + " shots, " + brainsAte + " brains eaten.";
    return s;
  }
  
  //tester code
//  public static void main (String args[]) {
//    Zombie zombieAna = new Zombie("Ana");
//    System.out.println(zombieAna);
//  } 
}