public class Prop
{
  final static int UP = 0;
  final static int RIGHT = 90;
  final static int DOWN = 180;
  final static int LEFT = 270;
  private Location location;
  private int direction;
  protected Grid<Prop> grid;
  //change x and y to a location
  //implement the grid
  // add the act method and the putting in grid
  public Prop ()
  {
    
   direction = RIGHT;
   grid = null;
  }
  public void putSelfInGrid (Grid<Prop> gr, Location loc)
  {    
    if (!gr.isValid(loc)){
      throw new IllegalStateException ("Co-ordinate not on grid");}
    grid = gr;
    location = loc;
    gr.put (loc, this);
  }
  
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
  public void setDirection (int dir)
  {
    this.direction = dir;
  }
  
  public int getDirection ()
  {
    return direction;
  }
  
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
  public Location getLocation ()
  {
   return location; 
  }
  
  public Grid<Prop> getGrid ()
  {
    return grid;
  }
  //To be overrided to make props do stuff
  public void act ()
  {
    
  }
  
  public String toString ()
  {
    return "prop";
  }
}