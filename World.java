import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
public class World extends Observable
{
  GridField<Prop> map = new GridField<Prop>(19,19);
  ArrayList<Prop> things = new ArrayList<Prop>();
  FullSnake s = new FullSnake (5);
  Food f = new Food ();
  public Boolean toast;
  public int score;
  public World ()
  {
    //add error things    
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
    if (s.get(0).getLocation().equals (f.getLocation()))
    {
      s.addSegment (1);
      f.moveToRandomLocation();
      score++;
    }
    int y = s.s.size();
    for (int x = 1; x < y; x++){
      SnakeSegment sn = s.s.get (x);
      if (s.get(0).getLocation ().equals (sn.getLocation())){
        toast = new Boolean (true);        
        s.removeSelfFromGrid ();
        s.truncate();
        f.removeSelfFromGrid ();
        setChanged ();
        notifyObservers (toast);
        break;
      }      
    }
    
  }
  
  public void initWorld (JPanel p)
  {
    //map.printOccupants();
    things.clear();
    score = 0;
    toast = new Boolean (false);
    s.putSelfInGrid (map, new Location (0,0));
    f.putSelfInGrid (map, new Location ((int)(Math.random () *18) + 1,(int)( Math.random() * 18)));
    things.add (s);
    things.add (f);                    
    //JPanel, mainPanel, JLayeredPane, JRootPane, JFrame
    addObserver ((Observer)(p.getParent().getParent().getParent().getParent().getParent()));
  }
  
  public Grid<Prop> getMap ()
  {
    return map;
  }
  
}