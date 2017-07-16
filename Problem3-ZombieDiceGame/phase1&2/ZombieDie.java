//********************************************************************
//  ZombieDie.java, PHASE ONE & TWO      Template by CS230 Staff S17
//
//  Represents one zombie die (singular of dice) with faces showing values
//  brain, runner, or shotgun.
//
// Exam 2 - Ana Fernandez
//********************************************************************
public class ZombieDie extends Die {
  //instance vars
  private String zombieValue;
  
  public ZombieDie(){
    zombieValue = "shot";
  }
  
  public String getZombieValue() {
    //depending on faceValue of Die, set zombieValue
    //total there are two brains, two runners, two shotguns
    if (faceValue == 1 || faceValue == 3) {
      zombieValue = "brain";
    }
    else if (faceValue == 2 || faceValue == 4) {
      zombieValue = "shotgun";
    }
    else {
      zombieValue = "runner";
    }
    return zombieValue;
  }
  
  public String toString(){
    String s = "The current zombie value of this ZombieDie is " + getZombieValue() + ".";    
    return s;
  }
  
  //tester
//  public static void main (String args[]) {
//    ZombieDie die = new ZombieDie();
//    System.out.println(die.getZombieValue());
//    System.out.println(die);
//    die.roll();
//    
//    System.out.println(die.getFaceValue());
//    System.out.println(die);
//    die.setFaceValue(3);
//    System.out.println(die);
//  }
}