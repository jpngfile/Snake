import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;

/**
 * This is the panel which contains the main menu buttons.
 * Most of the code is just modifying the buttons.
 * This class is prone to change for different looks and feels
 * 
 * @author Jason P'ng
 * @version 2.3 September 26th, 2014
 */
public class MainMenu extends JPanel implements ActionListener
{
  JFrame parent;
  BufferedImage img;
  static ClassLoader classloader;
  
  /**
   * The main constructor of the main menu panel.
   * Edit: Code could be reduced by putting the setup in a new method.
   * 
   * @param parent The JFrame this JPanel is within. This is used to make use of the JFrame actionPerformed method.
   */
  public MainMenu (JFrame parent)
  {
    this.parent = parent;
    SpringLayout layout = new SpringLayout ();
    setSize (parent.getSize());
    setLayout (layout);
    classloader = Thread.currentThread().getContextClassLoader ();
    ImageIcon playIcon = new ImageIcon (classloader.getResource("Images/playIcon.png"));
    ImageIcon quitIcon = new ImageIcon (classloader.getResource("Images/quitIcon.png"));
    Image img1 = playIcon.getImage();
    img1 = img1.getScaledInstance (75,75,Image.SCALE_SMOOTH);
    Image img2 = quitIcon.getImage();
    img2 = img2.getScaledInstance (75,75,Image.SCALE_SMOOTH);
    playIcon = new ImageIcon (img1);
    quitIcon = new ImageIcon (img2);
    JButton playButton = new JButton (playIcon);
    JButton quitButton = new JButton (quitIcon);
    playButton.setMargin (new Insets (0,0,0,0));
    quitButton.setMargin (new Insets (0,0,0,0));
    playButton.setBorderPainted (false);
    quitButton.setBorderPainted (false);
    playButton.setBackground (Color.BLACK);
    quitButton.setBackground (Color.BLACK);
    playButton.setSize (new Dimension (75,75));//(playButton.getPreferredSize());
    quitButton.setSize (new Dimension (75,75));//(quitButton.getPreferredSize());
    playButton.setActionCommand ("play");
    quitButton.setActionCommand ("quit");
    playButton.addActionListener (this);
    quitButton.addActionListener (this);
    
    ImageIcon playRolloverIcon = new ImageIcon (classloader.getResource ("Images/playIcon2.png"));
    ImageIcon quitRolloverIcon = new ImageIcon (classloader.getResource ("Images/quitIcon2.png"));
    Image img3 = playRolloverIcon.getImage();
    img3 = img3.getScaledInstance (80,80,Image.SCALE_SMOOTH);
    Image img4 = quitRolloverIcon.getImage();
    img4 = img4.getScaledInstance (75,75,Image.SCALE_SMOOTH);
    playRolloverIcon = new ImageIcon (img3);
    quitRolloverIcon = new ImageIcon (img4);
    
    playButton.setRolloverIcon (playRolloverIcon);
    quitButton.setRolloverIcon (quitRolloverIcon);
//    playButton.setRolloverSelectedIcon (playRolloverIcon);
//    quitButton.setRolloverSelectedIcon (quitRolloverIcon);
    playButton.setRolloverEnabled (true);
    quitButton.setRolloverEnabled (true);
    
    playButton.setPressedIcon (playRolloverIcon);
    quitButton.setPressedIcon (quitRolloverIcon);
    playButton.setFocusPainted (false);
    quitButton.setFocusPainted (false);
    //The buttons actually look nicer with a border
    //This gets rid of the annoying one though when pressing the button
    playButton.setBorder (null);
    quitButton.setBorder (null);
    //Put the buttons on top of each other
    layout.putConstraint  (SpringLayout.NORTH,playButton,170,SpringLayout.NORTH,this);
    layout.putConstraint  (SpringLayout.NORTH,quitButton,280,SpringLayout.NORTH,this);
    
    //center the buttons
    layout.putConstraint (SpringLayout.WEST,playButton,(int)((this.getSize().getWidth()/2)-(playButton.getSize().getWidth())/2),SpringLayout.WEST,this);
    layout.putConstraint (SpringLayout.WEST,quitButton,(int)((this.getSize().getWidth()/2)-(quitButton.getSize().getWidth())/2),SpringLayout.WEST,this);
    add (playButton);
    add (quitButton);
    try
    {
    img = ImageIO.read (getClass().getResourceAsStream("Images/menuBackground.png"));
    }
    catch (IOException e)
    {
    }
  }
  
  /**
   * Paint method for custom painting.
   * 
   * @param g The graphics used for this panel
   */
  @Override
  protected void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    g.drawImage (img,0,0,null);
  }
  
  public static URL loadImage (String name)
  {
    return classloader.getResource (name);
  }
  /**
   * Button action event response method which uses the parent method to get access to other panels.
   * @param ae The event triggering this method.
   */
  public void actionPerformed (ActionEvent ae)
  {
    ((ActionListener)parent).actionPerformed (ae);
  }
}