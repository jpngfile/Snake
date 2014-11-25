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
  
  /**
   * The constructor of a basic SnakeSegment.
   */
  public SnakeSegment ()
  {
    super ();
//    int x = (int)(Math.random()*10) + 1;
//    setImage ("whiteScratches" + x + ".png");
    setImage("Images/snakeBody.png");
  }
  
  /**
   * Gets a String representation of the snake segment.
   * 
   * @return The location, direction, and "Snake Segment".
   */
  @Override
  public String toString ()
  {
   return super.toString() + " Snake Segment"; 
  }
}