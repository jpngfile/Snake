import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class GamePanel extends JPanel
{
  final int BOARD_LENGTH = 18;
  final long UPDATE_RATE = 10;
  World w = new World();
  
  boolean isRunning = false;
  boolean updated = false;
  
  //Create a grid class of locations.
  //This will be a map for all future endeavours
  //Also, create a standard object to put in the Grid
  public GamePanel ()
  {
    setPreferredSize (new Dimension (500,525));
    setBackground (Color.CYAN);
    //addKeyListener (new KListen ());
    setVisible (true);
  }
  
  
  public void updateThis ()
  {
    System.out.println ("Updatred");
    isRunning = true;
    int counter = 0;
    //Why a new Thread?
    //because java threads.
    //Thread.sleep() pauses the EDT halting painting
    //research to find out more about Threads
    new Thread () {
      public void run (){
while (isRunning)
{
      long timeBeforeMillis,timePassedMillis,timeLeftMillis;
      timeBeforeMillis = System.currentTimeMillis();
      w.update ();
      repaint ();
      updated = false;
      //System.out.println (s.getX() + ", " + s.getY());
      
      timePassedMillis = System.currentTimeMillis() - timeBeforeMillis;
      timeLeftMillis = 1000L / UPDATE_RATE - timePassedMillis;
      if (timeLeftMillis < 50)
        timeLeftMillis = 50;
      //counter += (int)timeLeftMillis;
      try
      {
        Thread.sleep (timeLeftMillis);
      }
      catch (InterruptedException e)
      {
      }
}
}
    }.start();
  }
  

  //invert the grid so that it is easier to use
  protected void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.setColor (Color.BLACK);
    g.fillRect (0,0,500,500);
    g.setColor (Color.WHITE);
//    for (int x = 0; x < s.size();x++){
//    g.drawOval (25*s.get(x).getX(),25*s.get(x).getY(),25,25);
//    }
    for (Location l : w.getMap().getOccupiedLocations())
    {
      Location loc = (new Location (l.getX(),w.map.getNumCols () -  l.getY()));
      
      g.drawOval (25*loc.getX(),25*loc.getY() - 25,25,25);
    }
  }
  
  //kind of sticky
  //Change so that it finds the direction of the previous segment and doesn't allow directions towards that.
  //I should really be moving this to snake. I'll do that next update
  public void keyPressed (KeyEvent k)
  {
    if (!updated)
    {
    int key = k.getKeyCode ();
    int direction = GridField.getDirectionTowards (w.s.get(0).getLocation(),w.s.get(1).getLocation());
    if (w.s.get(0).getLocation().equals (w.s.get(1).getLocation()))
      direction = Integer.MAX_VALUE;
    switch (key){
      case KeyEvent.VK_UP :
        //move up
        if (direction != SnakeSegment.UP){
        System.out.println (direction);
        w.s.get(0).setDirection (SnakeSegment.UP);}
        break;
      case KeyEvent.VK_DOWN :
        //move down
        if (direction != SnakeSegment.DOWN){
        System.out.println (direction);
        w.s.get(0).setDirection (SnakeSegment.DOWN);}
        break;
      case KeyEvent.VK_RIGHT :
        //move right
        if (direction != SnakeSegment.RIGHT){
        System.out.println (direction);
        w.s.get(0).setDirection (SnakeSegment.RIGHT);}
        break;
      case KeyEvent.VK_LEFT :
        //move left
        if (direction != SnakeSegment.LEFT){
        System.out.println (direction);
        w.s.get(0).setDirection (SnakeSegment.LEFT);}
        break;
      case KeyEvent.VK_SPACE :
        System.out.println (w.s.get(0).getLocation().toString());
    }
    updated = true;
    }
    
  }
  
}