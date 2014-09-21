import java.util.ArrayList;
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
}