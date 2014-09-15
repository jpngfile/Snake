import java.util.ArrayList;

public class World
{
  GridField<Prop> map = new GridField<Prop>(18,18);
  ArrayList<Prop> things = new ArrayList<Prop>();
  public boolean toast;
  public World ()
  {
    
  }
  
  /**
   * each thing performs its act method
   * it will move through the grid
   */
  public void update ()
  {
  }
}