import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class MainFrame extends JFrame implements ActionListener
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
      System.out.println ("press");   
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
  
  class KListen extends KeyAdapter
  {
    public void keyPressed (KeyEvent e)
    {
      gamePanel.keyPressed (e);
      System.out.println ("Fds");
    }    
  }
}