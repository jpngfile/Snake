import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * This is the graphics panel to display the main game
 * It also controls the main flow of the game and connects the world to the JFrame.
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class GamePanel extends JPanel
{
  final long UPDATE_RATE = 10;
  World w = new World();
  boolean pause = false;
  
  /**
   * Constructore for a basic panel.
   */
  public GamePanel ()
  {
    setPreferredSize (new Dimension (500,525));
    setBackground (Color.CYAN);
    //addKeyListener (new KListen ());
    setVisible (true);
  }
  
  /**
   * The primary method to run the game.
   * This controls the game flow and connects updating the snake position to drawing it.
   */
  public void updateThis ()
  {
    //Why a new Thread?
    //because java threads.
    //Thread.sleep() pauses the EDT halting painting
    //research to find out more about Threads
      w.initWorld (this);      
    new Thread () {
      boolean isRunning = true;
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
        //snake is dead here
      }
    }.start();
  }
  
  /**
   * Overriden painting method for custom graphics.
   * 
   * @param g The graphics being use by the JPanel.
   */
  //Is Toolkit.getDefaultTookit().sync() useful here?
  @Override
  protected void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.setColor (Color.BLACK);
    g.fillRect (0,0,500,500);    
    //why doesn't this work?
    Grid gr = w.getMap();
    //One time, this crashed because it requested an empty location in which the arraylist had a size of 0.
    for (Location l : w.getMap().getOccupiedLocations())
    {
      //This inverts the location so the grid is easier to use. Up and right is positive.
      Location loc = (new Location (l.getX(),w.map.getNumCols () -  l.getY()));
      Prop p = w.getMap().get(l).get(0);
      if (p.getImage() == null){
      g.drawOval (25*loc.getX(),25*loc.getY() - 25,25,25);
      }
      else{
       g.drawImage (p.getImage(),25*loc.getX(),25*loc.getY() - 25,null); 
      }
    }
    g.setColor (Color.WHITE);
    g.drawString (Integer.toString (w.score), 10,20);
  }
  
  /**
   * Accessor method to get the world used by the game panel.
   * 
   * @return the World instance in use.
   */
  public World getWorld ()
  {
   return w; 
  }
  
  /**
   * Provides a String identifying the class.
   * 
   * @return The name "Game Panel".
   */
  @Override
  public String toString ()
  {
    return "Game Panel";
  }

  /**
   * Controls key input for the game panel.
   * It also runs the keyPressed method for the snake.
   * 
   * @param k The key input
   */
  public void keyPressed (KeyEvent k)
  {
    for (Prop p : w.getProp (new FullSnake(0).getClass())){
      p.keyPressed (k);}
    if (k.getKeyCode () == KeyEvent.VK_P)
    {
      pause = !pause;
    }
  }
  
}