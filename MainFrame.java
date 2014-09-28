import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

/**
 * The frame to hold the panels and contain the program.
 * It controls the movement between JPanels
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class MainFrame extends JFrame implements ActionListener,Observer
{
  JPanel mainPanel;
  GamePanel gamePanel;
  String container;
  
  /**
   * The main constructor of the frame.
   */
  public MainFrame ()
  {
    setSize (493,515);
    //setUndecorated (true);
    gamePanel = new GamePanel ();
    mainPanel = new JPanel (new CardLayout ());
    mainPanel.setPreferredSize (new Dimension (493,515));
    mainPanel.add ("menu",new MainMenu(this)); 
    mainPanel.add ("game",gamePanel);
    mainPanel.setFocusable (true);
    mainPanel.addKeyListener (new KListen());
    add (mainPanel);
    show ("menu");
    setLocation (400,100);
    setVisible (true);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }
  
  /**
   * Implementation of the ActionListener response method.
   * 
   * @param ae The ActionEvent triggering the method.
   */
  @Override
  public void actionPerformed (ActionEvent ae)
  {
    String a = ae.getActionCommand ();
    if (a.equals ("play")){
      show ("game");
        gamePanel.updateThis ();
    }
    else if (a.equals ("quit")){
      System.exit (0);
    }
  }
  
  /**
   * Shows a specific panel within the CardLayout of the main panel.
   * 
   * @param panel The name of the panel to be shown.
   */
  public void show (String panel)
  {
    CardLayout cl = ((CardLayout)mainPanel.getLayout());
    cl.show (mainPanel, panel);
    container = panel;
    revalidate ();
  }
  
  /**
   * Implementation of Observer interface to react to changes.
   * This is used to show the main menu once the <code>World</code> of the game panel triggers this method indicating the snake has crashed.
   * 
   * @param Observable The Object being observed, in this case being <code>World</code>.
   * @param obj The specific Object within the Observable being checked.
   */
   @Override
   public void update (Observable o,Object obj)
   {
     if (((Boolean)obj).booleanValue() == true)
     {
       show ("menu");
     }
   }
   
   /**
    * A private class to control all keyboard input of the game.
    */
  class KListen extends KeyAdapter
  {
    /**
     * Response method when a key is pressed. This triggers every other keyPressed method in other classes nested within this frame.
     *
     * @param e The key input triggering the method.
     */
    public void keyPressed (KeyEvent e)
    {
      int k = e.getKeyCode ();
      if (k == KeyEvent.VK_M){
        gamePanel.w.s.setCrashed (true);
      }
      else if (k == KeyEvent.VK_ENTER)
      {
        if (container.equals ("menu"))
          actionPerformed (new ActionEvent (this, 1, "play"));
      }
      else if (k == KeyEvent.VK_ESCAPE)
      {
        actionPerformed (new ActionEvent (this, 1, "quit"));
      }
      else
      {
       gamePanel.keyPressed (e); 
      }
    }    
  }
}