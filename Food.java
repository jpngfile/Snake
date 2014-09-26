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
  /**
   * The constructor of Food which creates a standard Prop
   */
  public Food ()
  {
    super ();
    setImage ("whitePellet.png");
  }
  
  /**
   * The primary method of food for moving to different locations around the Grid
   * It picks a random empty location in the Grid to move to.
   */
  public void moveToRandomLocation ()
  {
    if (grid != null)
    {
      ArrayList <Location> l = grid.getEmptyLocations ();
      int x = (int)(Math.random () * l.size());
      setLoc (l.get(x));
    }
  }
 
  /**
   * Overriden method to express Food as a String
   * It outputs location, direction, and Class
   */
  public String toString ()
  {
   return super.toString() + " Food"; 
  }
}