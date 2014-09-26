
/**
 * This is a data class for any given location in a grid.
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class Location
{
 int x,y;
 public Location (int x, int y)
 {
   this.x = x;
   this.y = y;
 }
 public int getX ()
 {
   return x;
 }
 
 public int getY()
 {
   return y;
 }
 public boolean equals (Location loc)
 {
   if (loc.getX() == x && loc.getY() == y)
     return true;
   return false;
 }
 
 public String toString ()
 {
   return "(" + x + ", " + y + ")";
 }
}