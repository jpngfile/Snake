import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class GamePanel extends JPanel
{
  //discontinued
  final int BOARD_LENGTH = 18;
  final long UPDATE_RATE = 10;
  World w = new World();
  
  boolean isRunning = false;
  boolean updated = false;
  boolean pause = false;
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
  
  int counter = 0;
  ///For some reasion this is getting faster and faster each iteration
  public void updateThis ()
  {
    isRunning = true;
    
    //Why a new Thread?
    //because java threads.
    //Thread.sleep() pauses the EDT halting painting
    //research to find out more about Threads
      w.initWorld (this);
    new Thread () {
      public void run (){
        while (isRunning)
        {
          long timeBeforeMillis,timePassedMillis,timeLeftMillis;
          timeBeforeMillis = System.currentTimeMillis();
          
          if (!pause)
          w.update ();
          if (w.toast.booleanValue ()){
            break;
          }
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
        
//This part runs once the snake is dead.
//reset the game here
//return to main menu
      }
    }.start();
    //end this.
    counter++;
    //must find a way to return to the main menu
  }
  
  
  //invert the grid so that it is easier to use
  //Will have to change to draw different kinds of shapes
  protected void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.setColor (Color.BLACK);
    g.fillRect (0,0,500,500);
    g.setColor (Color.WHITE);
    g.drawString (Integer.toString (w.score), 10,20);
//    for (int x = 0; x < s.size();x++){
//    g.drawOval (25*s.get(x).getX(),25*s.get(x).getY(),25,25);
//    }
    for (Location l : w.getMap().getOccupiedLocations())
    {
      Location loc = (new Location (l.getX(),w.map.getNumCols () -  l.getY()));
      
      g.drawOval (25*loc.getX(),25*loc.getY() - 25,25,25);
    }
  }
  
  public String toString ()
  {
    return "Game Panel";
  }
  //kind of sticky
  //Change so that it finds the direction of the previous segment and doesn't allow directions towards that.
  //I should really be moving this to snake. I'll do that next update
  public void keyPressed (KeyEvent k)
  {
    if (!updated)
    {
      w.s.keyPressed (k);
      updated = true; 
    }
    if (k.getKeyCode () == KeyEvent.VK_P)
    {
      pause = !pause;
    }
  }
  
}