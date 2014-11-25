import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.event.*;
/**
 * The class for any object within the game that has a location and can be within a grid.
 * Images to be used unique to each prop may be used in the future.
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class Prop
{
  final static int UP = 0;
  final static int RIGHT = 90;
  final static int DOWN = 180;
  final static int LEFT = 270;
  private Location location;
  private int direction;
  protected Grid<Prop> grid;
  protected Image img;
  
  /**
   * The main constructor of a Prop
   */
  public Prop ()
  {    
   direction = RIGHT;
   grid = null;
  }
  
  /**
   * Puts this Prop into a grid at a certain location.
   * 
   * @param gr The grid to be put in.
   * @param loc The location in the grid to be on.
   */
  public void putSelfInGrid (Grid<Prop> gr, Location loc)
  {    
    if (!gr.isValid(loc)){
      throw new IllegalStateException ("Co-ordinate not on grid");}
    grid = gr;
    location = loc;
    gr.put (loc, this);
  }
  
  /**
   * Removes the Prop from its current grid and location
   */
  public void removeSelfFromGrid ()
  {
    if (grid == null)
    {
      throw new IllegalStateException ("Not in grid");
    }
    grid.remove (location,this);
    //location = null;
    grid = null;
  }
  
  /**
   * Sets the direction of the Prop. May be used to rotate the image of the Prop.
   *
   * @param dir The direction to be set to.
   */
  public void setDirection (int dir)
  {
    this.direction = dir;
  }
  
  /**
   * Gets the current direction of the prop.
   * 
   * @return The direction of the Prop.
   */
  public int getDirection ()
  {
    return direction;
  }
  
  /**
   * Sets the location of the Prop
   * 
   * @param loc The location for the Prop to be moved to.
   */
  public void setLoc (Location loc)
  {
   if (grid == null)
     throw new IllegalStateException ("Not in a grid");
   if (grid.isValid (loc)){
     grid.remove (location, this);
     grid.put (loc, this);
       location = loc;
   }
  }
  
  /**
   * Gets the current location of the prop
   * 
   * @return A Location of the prop.
   */
  public Location getLocation ()
  {
   return location; 
  }
  
  /**
   * Gets the grid that the prop is on.
   * 
   * @return The grid the Prop is in.
   */
  public Grid<Prop> getGrid ()
  {
    return grid;
  }
  
  /**
   * Gets the Image that represents the Prop
   * 
   * @return The Image of the Prop. Currently, the base image of Prop is null.
   */
  public Image getImage ()
  {
   return img; 
  }
  
  /**
   * Sets the image of the Prop
   * 
   * @param imageName The name of the file of the Image.
   */
  public void setImage (String imageName)
  {
    //ClassLoader classloader = Thread.currentThread().getContextClassLoader ();
    //ImageIcon ii = new ImageIcon (classloader.getResource (imageName));
    //ImageIcon ii = new ImageIcon (imageName);
    ImageIcon ii = new ImageIcon (MainMenu.loadImage (imageName));
    img = ii.getImage();
  }
  /**
   * To be overrided to make props do stuff.
   */
  public void act ()
  {
    
  }
  
  /**
   * Gets a String representation of the Prop. it returns the location and the direction of the Prop.
   * 
   * @return Location and direction of the prop.
   */
  @Override
  public String toString ()
  {
    if (location != null)
    return location.toString() + " " + direction;
    else
      return "null " + direction;
  }
  
  /**
   * To be overriden by Props that use keyboard input
   */
  public void keyPressed (KeyEvent k)
  {
    
  }
}