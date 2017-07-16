//********************************************************************
//  Die.java       Java Foundations
//
//  Represents one die (singular of dice) with faces showing values
//  between 1 and 6.
//
//  Modified for use in Exam 2. Instance variables now protected.
//
//  You DO NOT need to MODIFY this code in any way.
//********************************************************************

public class Die{
   protected final int MAX = 6;  // maximum face value
   protected int faceValue;  // current value showing on the die

   //-----------------------------------------------------------------
   //  Constructor: Sets the initial face value of this die.
   //-----------------------------------------------------------------
   public Die()
   {
      faceValue = 1;
   }

   //-----------------------------------------------------------------
   //  Computes a new face value for this die and returns the result.
   //-----------------------------------------------------------------
   public int roll()
   {
      faceValue = (int)(Math.random() * MAX) + 1;

      return faceValue;
   }

   //-----------------------------------------------------------------
   //  Face value mutator. The face value is not modified if the
   //  specified value is not valid.
   //-----------------------------------------------------------------
   public void setFaceValue (int value)
   {
      if (value > 0 && value <= MAX)
         faceValue = value;
   }

   //-----------------------------------------------------------------
   //  Face value accessor.
   //-----------------------------------------------------------------
   public int getFaceValue()
   {
      return faceValue;
   }

   //-----------------------------------------------------------------
   //  Returns a string representation of this die.
   //-----------------------------------------------------------------
   public String toString()
   {
      String result = Integer.toString(faceValue);

      return result;
   }
}

