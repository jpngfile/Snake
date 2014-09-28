
/**
 * This is a data class for any given location in a grid.
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class Location
{
 int x,y;
 
 /**
  * The main constructor of any location.
  * @param x The x-component of the location coordinate.
  * @param y The y-component of the location coordinate.
  */
 public Location (int x, int y)
 {
   this.x = x;
   this.y = y;
 }
 
 /**
  * Accessor method to find the x component.
  * 
  * @return The x component of the location.
  */
 public int getX ()
 {
   return x;
 }
 
 /**
  * Accessor method to find the y component.
  * 
  * @return The y component of the location.
  */
 public int getY()
 {
   return y;
 }
 
 /**
  * Determines if another location has the same x and y components as this location.
  * @param loc The location to compare to.
  * @return Whether or not the locations have the same x and y components.
  */
 public boolean equals (Location loc)
 {
   if (loc.getX() == x && loc.getY() == y)
     return true;
   return false;
 }
 
 /**
  * Provides a String representation of the location.
  * @return The x component followed by the y component in brackets.
  */
 public String toString ()
 {
   return "(" + x + ", " + y + ")";
 }
}