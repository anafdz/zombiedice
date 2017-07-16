//********************************************************************
//  ZombieDie.java, PHASE THREE       Template by CS230 Staff S17
//
//  Represents one zombie die (singular of dice) with faces showing values
//  brain, runner, or shotgun.
//
// Exam 2 - Ana Fernandez
//********************************************************************
public class ZombieDie extends Die {
  //instance vars
  private String zombieValue;
  private String difficulty;
  
  public ZombieDie(){
    //defaults
    zombieValue = "brain";
    difficulty = "medium"; 
  }
  public ZombieDie(String level) {
    zombieValue = "brain";
    difficulty = level; //set difficulty if given as constructor param
  }
  
  public String getZombieValue() {
    //depending on faceValue of Die, set zombieValue
    //total there are two brains, two runners, two shotguns
    
    //if easy, 3 brains 2 runners 1 shotgun 
    if (difficulty.equals("easy")) { 
      if (faceValue == 1 || faceValue == 2 || faceValue == 3) {
        zombieValue = "brain";
      }
      else if (faceValue == 4 || faceValue == 5) {
        zombieValue = "runner";
      }
      else {
        zombieValue = "shotgun";
      }
    }
    //if medium, 2 brains 2 runners 2 shotguns 
    else if (difficulty.equals("medium")) { 
      if (faceValue == 1 || faceValue == 3) {
        zombieValue = "brain";
      }
      else if (faceValue == 2 || faceValue == 4) {
        zombieValue = "runner";
      }
      else {
        zombieValue = "shotgun";
      }
    }  
    ////if hard, 1 brain 2 runners 3 shotguns 
    else if (difficulty.equals("hard")) { 
      if (faceValue == 2 || faceValue == 3 || faceValue == 4) {
        zombieValue = "shotgun";
      }
      else if (faceValue == 5 || faceValue == 6) {
        zombieValue = "runner";
      }
      else { 
        zombieValue = "brain";
      }  
    }
    return zombieValue;
  }
  public String getDifficulty() {
    return difficulty;
  }
  
  public String toString(){
    String s = "ZombieDie difficulty: " + difficulty + "\nZombieDie value: " + getZombieValue();    
    return s;
  }
  
  //tester
// public static void main (String args[]) {
//   ZombieDie zombie = new ZombieDie("hard");
//   System.out.println(zombie.getZombieValue());
//   System.out.println(zombie);
//   
//   zombie.roll();
//   
//   System.out.println(zombie.getFaceValue());
//   System.out.println(zombie);
//   
//   myZD.setFaceValue(5);
//   System.out.println(zombie);
//   } 
}