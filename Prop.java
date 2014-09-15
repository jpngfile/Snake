public class Prop
{
    final static int UP = 0;
  final static int RIGHT = 1;
  final static int DOWN = 2;
  final static int LEFT = 3;
  private int x,y;
  private int direction;
  private Grid<Prop> grid;
  //change x and y to a location
  //implement the grid
  // add the act method and the putting in grid
  public Prop (int x, int y)
  {
   this.y =y;
   this.x =y;
   direction = RIGHT;
  }
  public void setX (int x)
  {
    this.x = x;
  }
  public void setY (int y)
  {
   this.y = y; 
  }
  public void setDirection (int dir)
  {
    this.direction = dir;
  }
  public int getDirection ()
  {
    return direction;
  }
  public int getX ()
  {
    return x;
  }
  public int getY ()
  {
    return y;
  }
  public void setLoc (int x, int y)
  {
   this.x = x;
   this.y = y;
  }
}