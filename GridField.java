import java.util.ArrayList;

public class GridField<E> implements Grid<E>
{
  public ArrayList [][] occupants;
  
  public GridField (int rows, int cols)
  {
    if (rows <= 0)
      throw new IllegalArgumentException ("Rows must be above 0");
    else if (cols <= 0)
      throw new IllegalArgumentException ("Columns must be above 0");
    else
    {
      occupants = new ArrayList[rows][cols];
      for (int x = 0;x < rows;x++){
        for (int y = 0; y < cols;y++){
          occupants [x][y] = new ArrayList ();
        }
        
      }
    }
  }
  
  public int getNumRows ()
  {
    return occupants.length;
  }
  
  public int getNumCols ()
  {
    return occupants[0].length;
  }
  
  public boolean isValid (Location loc)
  {
    if (loc.getX() < getNumRows() && loc.getX() >= 0 && loc.getY() < getNumCols() && loc.getY() >= 0)
    return true;
    return false;
  }
  
  public E put (Location loc, E obj)
  {
    if (!isValid (loc))
      throw new IllegalStateException ("Invalid location");
    int x = loc.getX();
    int y = loc.getY();
    occupants [x][y].add (obj);
    return obj;
  }
  
  public E remove (Location loc, E obj)
  {
    if (!isValid (loc))
      throw new IllegalStateException ("Invalid location");
    int x = loc.getX();
    int y = loc.getY();
    occupants [x][y].remove (obj);
    return obj;
  }
  
  public ArrayList<E> get (Location loc)
  {
    if (!isValid (loc))
      throw new IllegalStateException ("Invalid location");
    int x = loc.getX();
    int y = loc.getY();
    ArrayList<E> stuff = (ArrayList<E>)occupants[x][y];
    return stuff;
  }
  
  public ArrayList<Location> getOccupiedLocations ()
  {
    ArrayList<Location> locations = new ArrayList<Location>();
    for (int x = 0; x < getNumRows ();x++)
    {
     for (int y = 0; y < getNumCols();y++)
     {
       if (!occupants [x][y].isEmpty()){
         locations.add (new Location (x,y));
       }
     }
    }
    return locations;
  }
  
  public static int FULL_CIRCLE = 360;
  public static int HALF_CIRCLE = 180;
  public static int QUARTER_CIRCLE = 90;
  public static int EIGHTH_CIRCLE = 45;
  public ArrayList<Location> getValidAdjacentLocations (Location loc)
  {
    ArrayList<Location> locations = new ArrayList<Location>();
    for (int x = NORTH;x <= FULL_CIRCLE; x+= EIGHTH_CIRCLE)
    {
      Location newLoc = getAdjacentLocation (loc,x);
      if (isValid (newLoc)){
        locations.add (newLoc);
      }
    }
    return locations;
  }
  
  public ArrayList<Location> getEmptyAdjacentLocations (Location loc)
  {
    ArrayList<Location> locations = new ArrayList<Location>();
    for (Location l : getValidAdjacentLocations (loc))
    {
      if (get(l).isEmpty()){
        locations.add (l);
      }
    }
    return locations;
  }
  
  public ArrayList<Location> getOccupiedAdjacentLocations (Location loc)
  {
    ArrayList<Location> locations = new ArrayList<Location>();
    for (Location l : getValidAdjacentLocations (loc))
    {
      if (!get(l).isEmpty()){
        locations.add (l);
      }
    }
    return locations;
  }
  
  public ArrayList<ArrayList<E>> getNeighbours (Location loc)
  {
    ArrayList<ArrayList<E>> neighbours = new ArrayList<ArrayList<E>>();
    for (Location l : getOccupiedAdjacentLocations (loc))
    {
      neighbours.add (get(l));
    }
    return neighbours;
  }
  
  public static int NORTH = 0;
  public static int NORTHEAST = 45;
  public static int EAST = 90;
  public static int SOUTHEAST = 135;
  public static int SOUTH = 180;
  public static int SOUTHWEST = 225;
  public static int WEST = 275;
  public static int NORTHWEST = 315;
  
  public static int formatDirection (float direction)
  {
    return (Math.round ((direction % 360f)/45f)) * 45;
  }
  public static Location getAdjacentLocation (Location loc, float direction)
  {
    //format the direction
    int newDirection = formatDirection (direction);
    int xFactor = 0;
    int yFactor = 0;
    //change to variable names
    if (newDirection >= 315 || newDirection <= 45){
      yFactor = -1;}
    if (newDirection >= 45 && newDirection <= 135){
      xFactor = 1;}
    if (newDirection >= 135 && newDirection <= 225){
      yFactor = 1;}
    if (newDirection >= 225 && newDirection <= 315){
      xFactor = -1;}
    return new Location (loc.getX() + xFactor, loc.getY() + yFactor);  
  }
  
 // @assert x and y are above 0
  //Revise and make sure it works
  //check corner cases
  public static int getDirectionTowards (Location loc1, Location loc2)
  {
    int deltaX = loc2.getX() - loc1.getX();
    int deltaY = loc2.getY() - loc1.getY();
    double angle;
    //if the angle will be negative
    if (deltaX * deltaY < 0){
      angle = Math.abs (Math.toDegrees (Math.atan (deltaY/deltaX)));
      if (loc2.getX() < loc1.getX()){
        angle += 270;
      }
      else{
        angle += 90;
      }
    }
    else{
      if (deltaY == 0)
        angle = 90;
      else
      angle = Math.toDegrees (Math.atan (deltaX/deltaY));
      if (loc2.getX() < loc1.getX()){
        angle += 180;
      }
    }
    return formatDirection ((float)angle);
  }
}