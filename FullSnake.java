import java.util.ArrayList;
import java.awt.event.*;

/**
 * This is a container Prop to hold several SnakeSegments together
 * It is not on the grid but acts for the segments that do nothing alone.
 * It has it's own score based on it's length.
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class FullSnake extends Prop
{
  ArrayList<SnakeSegment> s = new ArrayList<SnakeSegment>();
  int length;
  int score;
  boolean crashed;
  
  /**
   * Main constructor of <code>class</code> which sets the length. <br>
   * The attributes are also set.
   * 
   * @param length The amount of <code>SnakeSegments</code> to be part of the initial snake.
   */
  public FullSnake (int length)
  {
    super ();
    this.length = length;
    crashed = false;
    score = 0;
    s.add (new SnakeHead());
    for (int x = 1; x < length; x++)
    {
      s.add (new SnakeSegment ());
    }
  }
  
  /**
   * Overridden method from <code>Prop</code> to put each segment into the grid
   * rather than the snake as a whole.
   * 
   * @param gr The <code>Grid</code> that the snake segments will be inside.
   * @param loc The <code>Location</code> in the <code>Grid</code> to put the snake.
   */
  @Override
  public void putSelfInGrid (Grid<Prop> gr, Location loc)
  {
    grid = gr;
    for (SnakeSegment sn : s){
      sn.putSelfInGrid (gr, loc);
    }
    get (0).setDirection (Prop.RIGHT);
  }
  
  /**
   * Overridden method from <code>Prop</code> to remove each segment rather than the
   * snake as a whole.
   */
  @Override
  public void removeSelfFromGrid ()
  {
    int counter = 0;
    if (grid != null)
    {
      int y = s.size();
      for (int x = 0;x < y;x++){
        s.get(x).removeSelfFromGrid ();
        counter++;        
      }
    }
  }
  
  /**
   * Accessor method to retrieve the length of the snake.
   * 
   * @return the amount of <code>SnakeSegment</code> that are part of the snake.
   */
  public int getLength ()
  {
    return length;
  }
  
  /**
   * Adds an amount of <code>SnakeSegments</code> to the end of the snake. <br>
   * This is called whenever the <code>FullSnake</code> encounters a <code>Food</code>.
   * 
   * @param num The amount of segments to be added to the snake.
   */
  public void addSegment (int num)
  {
    for (int x = 0; x < num;x++)
    {
      SnakeSegment sn = new SnakeSegment ();
      if (length > 2)
        sn.putSelfInGrid (grid,s.get (s.size() - 2).getLocation());
      else
        sn.putSelfInGrid (grid,GridField.getAdjacentLocation (s.get (0).getLocation(),s.get(0).getDirection() + GridField.HALF_CIRCLE));
      s.add (sn);
      length++;
    }
  }
  
  /**
   * Shortens the length of the snake to 5.
   * May be modified to allow any size.
   */
  public void truncate ()
  {
    int y = s.size() - 5;
    int counter = 0;
    if (s.size () > 5){
      for (int x = 0; x < y;x++){
        s.remove (5);
        counter++;
      }
      length = 5;
    }    
  }
  
  /**
   * Allows access to individual SnakeSegments within the snake by index.
   * 
   * @param index The index of the SnakeSegment to access.
   * @return the <code>SnakeSegment</code> at the index.
   */
  public SnakeSegment get (int index)
  {
    return s.get (index);
  }
  
  /**
   * Accesses the direction of the head of the snake.
   * 
   * @return the direction of the first <code>SnakeSegment</code>.
   */
  public int getDirection ()
  {
    return get(0).getDirection();
  }
  
  /**
   * Access to know if the snake has crashed into itself
   * 
   * @return Whether or not the snake has crashed.
   */
  public boolean isCrashed ()
  {
    return crashed; 
  }
  
  /**
   * Sets if the snake has crashed into itself
   * 
   * @param crsh the state of the snake being crashed
   */
  public void setCrashed (boolean crsh)
  {
    crashed = crsh; 
  }
  
  /**
   * Access the current score of the snake
   * 
   * @return The score of the snake, which is equal to the amount of food pellets eaten
   */
  public int getScore ()
  {
    return score; 
  }
  
  /**
   * Sets the score of the snake. <br>
   * It is incremented each time the snake eats <code>Food</code> and grows in length.
   * 
   * @param newScore The new score for the snake.
   */
  public void setScore (int newScore)
  {
    score = newScore; 
  }
  
  /**
   * The overriden method of acting that the snake performs every update.
   * This includes the movement of the segment, and determining when the snake has eaten food and has crashed.
   */
  @Override
  public void act ()
  {
    for (int x = s.size() - 1;x > 0;x--)
    {
      s.get(x).setLoc (s.get(x - 1).getLocation());
      s.get(x).setDirection (s.get(x - 1).getDirection());
    }
    int d = s.get(0).getDirection ();
    Location l = s.get(0).getLocation ();
    switch (d){
      case Prop.UP :
        if (l.getY() <= getGrid().getNumCols()){
        s.get(0).setLoc (new Location (l.getX(),l.getY() + 1));
      }
        break;
      case Prop.DOWN :
        if (l.getY() > 0 ){
        s.get(0).setLoc (new Location (l.getX(),l.getY() - 1));
      }
        break;
      case Prop.RIGHT :
        if (l.getX() <= getGrid().getNumRows()){
        s.get(0).setLoc (new Location (l.getX() + 1,l.getY()));
      }
        break;
      case Prop.LEFT :
        if (l.getX() > 0){
        s.get(0).setLoc (new Location (l.getX() - 1,l.getY()));
      }
        break;  
    }
    ArrayList<Prop> list = grid.get (s.get(0).getLocation());
    for (Prop p :list)
    {
      if (p instanceof Food){
        addSegment (1);
        ((Food)p).moveToRandomLocation();
        score++;
        break;
      }
    }
    for (int x = 1; x < length; x++){
      SnakeSegment sn = s.get (x);
      if (s.get(0).getLocation ().equals (sn.getLocation())){
        setCrashed (true);
        setScore (0);
        break;
      }
    }    
  }
  
  /**
   * Controls the key input for changing the direction of the snake.
   * 
   * @param k The keyboard input to determine which key was pressed.
   */
  public void keyPressed (KeyEvent k)
  {
    int key = k.getKeyCode ();
    int direction;
    if (length > 1){
      direction = GridField.getDirectionTowards (s.get(0).getLocation(),s.get(1).getLocation());
      if (s.get(0).getLocation().equals (s.get(1).getLocation()))
        direction = Integer.MAX_VALUE;
    }
    else{
      direction = Integer.MAX_VALUE;
    }
    switch (key){
      case KeyEvent.VK_UP :
        //move up
        if (direction != SnakeSegment.UP){
        //System.out.println (direction);
        s.get(0).setDirection (SnakeSegment.UP);}
        break;
      case KeyEvent.VK_DOWN :
        //move down
        if (direction != SnakeSegment.DOWN){
        //System.out.println (direction);
        s.get(0).setDirection (SnakeSegment.DOWN);}
        break;
      case KeyEvent.VK_RIGHT :
        //move right
        if (direction != SnakeSegment.RIGHT){
        //System.out.println (direction);
        s.get(0).setDirection (SnakeSegment.RIGHT);}
        break;
      case KeyEvent.VK_LEFT :
        //move left
        if (direction != SnakeSegment.LEFT){
        //System.out.println (direction);
        s.get(0).setDirection (SnakeSegment.LEFT);}
        break;
      case KeyEvent.VK_SPACE :
        System.out.println (s.get(0).getLocation().toString());
    }
  }
  
  /**
   * Provides a String represenation of the snake giving location, direction, length, and identifies it as a FullSnake.
   *
   * @return A String representation of the Object.
   */
  @Override
  public String toString ()
  {
    return get(0).toString() + " Length: " + length + " Full Snake";
  }
}