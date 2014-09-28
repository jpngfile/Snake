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
  
  /**
   * A constructor for a world.
   */
  public World ()
  {
    //add error things    
  }
  
  /**
   * Runs each Props act method.
   * It updates the score and terminates the world if the snake has crashed.
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
  
  /**
   * Runs the world termination sequences which includes: <br>
   * Removes the global objects from the Grid, shortens the snake, and notifies the main frame that the game has ended.
   */
  public void terminateWorld ()
  {
    toast = new Boolean (true);        
        s.removeSelfFromGrid ();
        s.truncate();
        f.removeSelfFromGrid ();
        setChanged ();
        notifyObservers (toast);
  }
  
  /**
   * The code for setting up a world for a new game. This is called not when world is constructed but when a new game starts.
   * It needs JPanel so it can add the JFrame as an Observer.
   * 
   * @param JPanel The game panel this world is connected to.
   */
  public void initWorld (JPanel p)
  {
    //map.printOccupants();
    things.clear();
    map.removeAll();
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
  
  /**
   * Gets the Grid this world uses.
   * 
   * @return The Grid that the map implements.
   */
  public Grid<Prop> getMap ()
  {
    return map;
  }
  
}