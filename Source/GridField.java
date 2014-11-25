import java.util.ArrayList;

/**
 * This is an implementation of the Grid interface to put Props on.
 * It provides many static methods to compare locations and keeps track of the locations of the game Props
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class GridField<E> implements Grid<E>
{
  public ArrayList [][] occupants;
  
  /**
   * Constructor in which the size is decided.
   * 
   * @param rows the width of the grid.
   * @param cols the height of the grid.
   */
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
  
  /**
   * Implementation of Grid method.
   * 
   * @return The amount of rows in the grid.
   */
  public int getNumRows ()
  {
    return occupants.length;
  }
  /**
   * Implementation of grid method.
   * 
   * @return The number of columns in the grid.
   */
  public int getNumCols ()
  {
    return occupants[0].length;
  }
  
  /**
   * Implementation of grid method.
   * 
   * @param The location being examined.
   * @return If the location is in the grid.
   */
  public boolean isValid (Location loc)
  {
    if (loc.getX() < getNumRows() && loc.getX() >= 0 && loc.getY() < getNumCols() && loc.getY() >= 0)
      return true;
    return false;
  }
  
  /**
   * Implementation of grid method.
   * 
   * @param loc The location being modified
   * @param obj The Object being added to the location.
   * @return The Object being added to the location.
   */
  public E put (Location loc, E obj)
  {
    if (!isValid (loc))
      throw new IllegalStateException ("Invalid location");
    int x = loc.getX();
    int y = loc.getY();
    occupants [x][y].add (obj);
    return obj;
  }
  
  /**
   * Implementation of grid method.
   * 
   * @param loc The location being modified
   * @param obj The Object being removed from the location.
   * @return The Object removed from the location.
   */
  public E remove (Location loc, E obj)
  {
    if (!isValid (loc))
      throw new IllegalStateException ("Invalid location");
    int x = loc.getX();
    int y = loc.getY();
    occupants [x][y].remove (obj);
    return obj;
  }
  
  /**
   * Implementation of grid method.
   * 
   * @param loc The location to get the occupants of.
   * @return An ArrayList of the Objects in the location.
   */
  public ArrayList<E> get (Location loc)
  {
    if (!isValid (loc))
      throw new IllegalStateException ("Invalid location");
    int x = loc.getX();
    int y = loc.getY();
    ArrayList<E> stuff = (ArrayList<E>)occupants[x][y];
    return stuff;
  }
  
  /**
   * Implementation of grid method.
   * 
   * @return An ArrayList of all locations with at least one Object on it.
   */
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
  
    /**
   * Implementation of grid method.
   * 
   * @return An ArrayList of all locations with no Objects on it.
   */
  public ArrayList<Location> getEmptyLocations ()
  {
    ArrayList<Location> locations = new ArrayList<Location>();
    for (int x = 0; x < getNumRows ();x++)
    {
      for (int y = 0; y < getNumCols();y++)
      {
        if (occupants [x][y].isEmpty()){
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
    /**
   * Implementation of grid method.
   * 
   * @param loc the Location to find adjacent locations around.
   * @return An ArrayList of all valid locations around the location.
   */
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
  
   /**
   * Implementation of grid method.
   * 
   * @param loc the Location to find adjacent locations around.
   * @return An ArrayList of all valid locations that have no occupants around the location.
   */
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
  
   /**
   * Implementation of grid method.
   * 
   * @param loc the Location to find adjacent locations around.
   * @return An ArrayList of all valid locations  that have at least on occupant on it around the location.
   */
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
  
   /**
   * Implementation of grid method.
   * 
   * @param loc the Location to find adjacent neighbours of.
   * @return An ArrayList of ArrayLists of Objects of adjacent locations.
   */
  public ArrayList<ArrayList<E>> getNeighbours (Location loc)
  {
    ArrayList<ArrayList<E>> neighbours = new ArrayList<ArrayList<E>>();
    for (Location l : getOccupiedAdjacentLocations (loc))
    {
      neighbours.add (get(l));
    }
    return neighbours;
  }
  
  /**
   * Removes all occupants from all locations in the grid.
   */
  public void removeAll ()
  {
    ArrayList<Location>  loc= getOccupiedLocations ();
    for (Location l : loc)
    {
      ArrayList<E> list = (ArrayList<E>)occupants[l.getX()][l.getY()];
      for ( int z = 0; z < list.size();z++){
        E obj = list.get(z);
        remove (l,obj);
      }
    }
  }
  
  /**
   * Prints out the location and Object of every occupant in the grid.
   */
  public void printOccupants ()
  {
    int counter = 0;
    ArrayList<Location> loc = getOccupiedLocations();
    for (Location l : loc)
    {
      ArrayList<E> list = (ArrayList<E>)occupants[l.getX()][l.getY()];
      System.out.println ("(" + l.getX() + ", " + l.getY() + ")");
      for ( int z = 0; z < list.size();z++){
        E obj = list.get(z);
        System.out.println (obj.toString());
        //band-aid solution
        counter++;
        //remove (new Location (x,y),obj);
      }
    }
  }
  public static int NORTH = 0;
  public static int NORTHEAST = 45;
  public static int EAST = 90;
  public static int SOUTHEAST = 135;
  public static int SOUTH = 180;
  public static int SOUTHWEST = 225;
  public static int WEST = 275;
  public static int NORTHWEST = 315;
  
  /**
   * Formats compass directions to be rounded to the nearest 45 and less than 360.
   * 
   * @return The formatted direction
   */
  public static int formatDirection (float direction)
  {
    return (Math.round ((direction % 360f)/45f)) * 45;
  }
  
  /**
   * Finds the adjacent location in the direction given.
   * 
   * @param loc The original location to find the adjacent location of.
   * @param direction The direction of the new location.
   * @return The adjacent location in the given direction from the given location.
   */
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
  
  /**
   * Finds the direction between two locations
   * 
   * @param loc1 The first location.
   * @param loc2 The second location to find the direction towards from the first location.
   * @return A formmatted direction from the first location to the seconds location.
   */
  public static int getDirectionTowards (Location loc1, Location loc2)
  {
    int deltaX = loc2.getX() - loc1.getX();
    int deltaY = loc2.getY() - loc1.getY();
    //atan2 converts coordinates to a polar grid; it returns the angle
    double angle = Math.toDegrees (Math.atan2 (deltaY,deltaX));
    angle = 90 - angle;
    if (angle < 0)
      angle+= 360;
    return formatDirection ((float)angle);
  }
}