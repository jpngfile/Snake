import java.util.ArrayList;
/**
 * This is the prop for all items that the snake will eat.
 * It moves to a random location whenever it is eaten.
 * Perhaps in the future variations will be added.
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class Food extends Prop
{
  public Food ()
  {
    super ();
  }
  public void moveToRandomLocation ()
  {
    if (grid != null)
    {
      ArrayList <Location> l = grid.getEmptyLocations ();
      int x = (int)(Math.random () * l.size());
      setLoc (l.get(x));
    }
  }  
  public String toString ()
  {
   return super.toString() + " Food"; 
  }
}