import java.util.ArrayList;

public class World
{
  GridField<Prop> map = new GridField<Prop>(18,18);
  ArrayList<Prop> things = new ArrayList<Prop>();
  FullSnake s = new FullSnake (5);
  public boolean toast;
  public World ()
  {
    initWorld ();
    things.add (s);
  }
  
  /**
   * each thing performs its act method
   * it will move through the grid
   */
  public void update ()
  {
    for (Prop p : things)
    {
      p.act();
    }
  }
  
  public void initWorld ()
  {
    s.putSelfInGrid (map, new Location (0,0));
  }
  
  public Grid<Prop> getMap ()
  {
    return map;
  }
  
}