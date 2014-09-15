import java.util.ArrayList;

//This contains multiple snake segments.
//It acts for the snake segments which otherwise do nothing at all.
public class FullSnake extends Prop
{
  ArrayList<SnakeSegment> s = new ArrayList<SnakeSegment>();
  public FullSnake (int x, int y)
  {
    super (x, y);
  }
}