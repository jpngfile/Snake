import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;
public class MainFrame extends JFrame implements ActionListener,Observer
{
  JPanel mainPanel;
  GamePanel gamePanel;
  public MainFrame ()
  {
    setSize (493,515);
    //setDecorated (false);
    gamePanel = new GamePanel ();
    mainPanel = new JPanel (new CardLayout ());
    mainPanel.setPreferredSize (new Dimension (493,515));
    mainPanel.add ("menu",new MainMenu(this)); 
    mainPanel.add ("game",gamePanel);
    mainPanel.setFocusable (true);
    mainPanel.addKeyListener (new KListen());
    add (mainPanel);
    setVisible (true);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    String a = ae.getActionCommand ();
    if (a.equals ("play")){
      show ("game");
        gamePanel.updateThis ();
        System.out.println ("WHY?");
    }
    else if (a.equals ("quit")){
      System.out.println ("Exit");
      System.exit (0);
    }
  }
  
  public void show (String panel)
  {
    CardLayout cl = ((CardLayout)mainPanel.getLayout());
    cl.show (mainPanel, panel);
    revalidate ();
  }
   public void update (Observable o,Object obj)
   {
     if (((Boolean)obj).booleanValue() == true)
     {
       show ("menu");
     }
   }
  class KListen extends KeyAdapter
  {
    public void keyPressed (KeyEvent e)
    {
      int k = e.getKeyCode ();
      gamePanel.keyPressed (e);
      //I need some kind of listener for variables
      //Observable?
      //reorganize to remove the thread?
      if (k == KeyEvent.VK_M)
        show("menu");
      //System.out.println ("Fds");
    }    
  }
}