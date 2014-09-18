import java.util.ArrayList;

public class World
{
  GridField<Prop> map = new GridField<Prop>(19,19);
  ArrayList<Prop> things = new ArrayList<Prop>();
  FullSnake s = new FullSnake (5);
  public boolean toast;
  public World ()
  {
    initWorld ();
    things.add (s);
    toast = false;
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
    for (int x = 1; x < s.s.size (); x++){
      SnakeSegment sn = s.s.get (x);
      if (s.get(0).getLocation ().equals (sn.getLocation())){
        toast = true;
        System.out.println ("toast");
      }      
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