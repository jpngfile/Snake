import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
public class GamePanel extends JPanel
{
  final int BOARD_LENGTH = 18;
  final long UPDATE_RATE = 10;
  int [][] board = new int [BOARD_LENGTH][BOARD_LENGTH];//replace with world
  ArrayList<SnakeSegment> s = new ArrayList<SnakeSegment>();
  boolean isRunning = false;
  boolean updated = false;
  
  //Create a grid class of locations.
  //This will be a map for all future endeavours
  //Also, create a standard object to put in the Grid
  public GamePanel ()
  {
    setPreferredSize (new Dimension (500,525));
    setBackground (Color.CYAN);
    initSnake();
    //addKeyListener (new KListen ());
    setVisible (true);
  }
  
  public void initSnake ()
  {
    s.add (new SnakeSegment (5,5));
    s.add (new SnakeSegment (4,5));
    s.add (new SnakeSegment (3,5));
     s.add (new SnakeSegment (2,5));
    s.add (new SnakeSegment (1,5)); 
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
      updateSnake();
      repaint ();
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
  public void updateSnake ()
  {
    for (int x = s.size() - 1;x > 0;x--)
    {
      s.get(x).setLoc (s.get(x - 1).getX(),s.get(x - 1).getY());
      s.get(x).setDirection (s.get(x - 1).getDirection());
    }
    int d = s.get(0).getDirection ();
    switch (d){
      case Prop.UP :
        if (s.get(0).getY() > 0){
        s.get(0).setY (s.get(0).getY() - 1);
      }
        break;
      case Prop.DOWN :
        if (s.get(0).getY() < BOARD_LENGTH){
        s.get(0).setY (s.get(0).getY() + 1);
      }
        break;
      case Prop.RIGHT :
        if (s.get(0).getX() < BOARD_LENGTH){
        s.get(0).setX (s.get(0).getX() + 1);
      }
        break;
      case Prop.LEFT :
        if (s.get(0).getX() >0){
        s.get(0).setX (s.get(0).getX() - 1);
      }
        break;
    }
    updated = false;
  }

  protected void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    System.out.println ("Painted");
    g.setColor (Color.BLACK);
    g.fillRect (0,0,500,500);
    g.setColor (Color.WHITE);
    for (int x = 0; x < s.size();x++){
    g.drawOval (25*s.get(x).getX(),25*s.get(x).getY(),25,25);
    }
  }
  
  //kind of sticky
  //Change so that it finds the direction of the previous segment and doesn't allow directions towards that.
  public void keyPressed (KeyEvent k)
  {
    if (!updated)
    {
    int key = k.getKeyCode ();
    switch (key){
      case KeyEvent.VK_UP :
        //move up
        if (s.get(0).getDirection() != SnakeSegment.DOWN)
        s.get(0).setDirection (SnakeSegment.UP);
        break;
      case KeyEvent.VK_DOWN :
        //move down
        if (s.get(0).getDirection() != SnakeSegment.UP)
        s.get(0).setDirection (SnakeSegment.DOWN);
        break;
      case KeyEvent.VK_RIGHT :
        //move right
        if (s.get(0).getDirection() != SnakeSegment.LEFT)
        s.get(0).setDirection (SnakeSegment.RIGHT);
        break;
      case KeyEvent.VK_LEFT :
        //move left
        if (s.get(0).getDirection() != SnakeSegment.RIGHT)
        s.get(0).setDirection (SnakeSegment.LEFT);
        break;
    }
    updated = true;
    }
    
  }
  
}