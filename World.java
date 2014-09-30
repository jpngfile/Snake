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
    for (Prop p : getProp (new FullSnake(0).getClass())){
      score = Math.max (((FullSnake)p).getScore(),score);
      if (((FullSnake)p).isCrashed()){
      terminateWorld();
      break;
    }
    }    
  }
  
  /**
   * Runs the world termination sequences which includes: <br>
   * Removes the global objects from the Grid, shortens the snake, and notifies the main frame that the game has ended.
   */
  public void terminateWorld ()
  {
    toast = new Boolean (true);        
    map.removeAll();
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
    //s.setCrashed (false);
    FullSnake s = new FullSnake (5);
    Food f = new Food();
    s.putSelfInGrid (map, new Location (0,0));
    f.putSelfInGrid (map, new Location ((int)(Math.random () *18) + 1,(int)( Math.random() * 18)));
    things.add (s);
    things.add (f);                    
    //JPanel, mainPanel, JLayeredPane, JRootPane, JFrame
    addObserver ((Observer)(p.getParent().getParent().getParent().getParent().getParent()));
  }
  
  public ArrayList<Prop> getProp (Class type)
  {
    ArrayList<Prop> list = new ArrayList<Prop>();
    for (Prop p : things)
    {     
      if (p.getClass().equals (type))
      {
        list.add (p);
      }
    }
    return list;
  }
  
  /**
   * Crashes every snake in the game to end it prematurely
   */
  public void crashGame ()
  {
    for (Prop p : getProp (new FullSnake(0).getClass())){
      ((FullSnake)p).setCrashed (true);
    }
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