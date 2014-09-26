import java.awt.*;

/**
 * The basic prop for a segment of a snake.
 * Alone these segments do nothing at all.
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class SnakeSegment extends Prop
{
  public SnakeSegment ()
  {
    super ();
    int x = (int)(Math.random()*10) + 1;
    setImage ("whiteScratches" + x + ".png");
  }
  public String toString ()
  {
   return super.toString() + " Snake Segment"; 
  }
}