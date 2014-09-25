import java.util.ArrayList;
import java.awt.event.*;

//This contains multiple snake segments.
//It acts for the snake segments which otherwise do nothing at all.
public class FullSnake extends Prop
{
  ArrayList<SnakeSegment> s = new ArrayList<SnakeSegment>();
  int length;
  public FullSnake (int length)
  {
    super ();
    this.length = length;
    for (int x = 0; x < length; x++)
    {
      s.add (new SnakeSegment ());
    }
  }
  public void putSelfInGrid (Grid<Prop> gr, Location loc)
  {
    //super.putSelfInGrid (gr, loc);
    grid = gr;
    for (SnakeSegment sn : s){
      sn.putSelfInGrid (gr, loc);
    }
    get (0).setDirection (Prop.RIGHT);
  }
  
  public void removeSelfFromGrid ()
  {
    int counter = 0;
    if (grid != null)
    {
      int y = s.size();
      for (int x = 0;x < y;x++){
        s.get(x).removeSelfFromGrid ();
        //s.remove (0);
        counter++;
        
      }
    }
  }
  
  public int getLength ()
  {
    return length;
  }
  
  //This thing is messing up everything
  //it leaves traces of snake behind
  //It leaves the trace of the first 4 segments
  public void addSegment (int num)
  {
    for (int x = 0; x < num;x++)
    {
      SnakeSegment sn = new SnakeSegment ();

      sn.putSelfInGrid (grid,s.get (s.size() - 2).getLocation());
      s.add (sn);
      length++;
    }
  }
  
  public void truncate ()
  {
    int y = s.size() - 5;
    int counter = 0;
    if (s.size () > 5)    {
      for (int x = 0; x < y;x++){
      s.remove (5);
      counter++;
      }
      length = 5;
    }
    
  }
  public SnakeSegment get (int index)
  {
    return s.get (index);
  }
  
  public int getDirection ()
  {
    return get(0).getDirection();
  }
  
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
  }
  
  public void keyPressed (KeyEvent k)
  {
    int key = k.getKeyCode ();
    int direction = GridField.getDirectionTowards (s.get(0).getLocation(),s.get(1).getLocation());
    if (s.get(0).getLocation().equals (s.get(1).getLocation()))
      direction = Integer.MAX_VALUE;
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
}