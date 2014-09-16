import java.util.ArrayList;

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
  }
  public int getLength ()
  {
    return length;
  }
  public void addSegment (int num)
  {
    for (int x = 0; x < num;x++)
    {
      SnakeSegment sn = new SnakeSegment ();
      s.add (sn);
      sn.putSelfInGrid (getGrid (),get(s.size() - 1).getLocation());
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
        if (l.getX() >0){
        s.get(0).setLoc (new Location (l.getX() - 1,l.getY()));
      }
        break;
    
  }
  }
}