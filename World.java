import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * This class controls the movement of all Props in the game
 * This will determine the status of Props and relay that information to the game panel
 * Will have to move out the global Props eventually
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
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
    score = s.getScore ();
    if (s.isCrashed()){
      terminateWorld();
    }
  }
  
  public void terminateWorld ()
  {
    toast = new Boolean (true);        
        s.removeSelfFromGrid ();
        s.truncate();
        f.removeSelfFromGrid ();
        setChanged ();
        notifyObservers (toast);
  }
  
  public void initWorld (JPanel p)
  {
    //map.printOccupants();
    things.clear();
    score = 0;
    toast = new Boolean (false);
    s.setCrashed (false);
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