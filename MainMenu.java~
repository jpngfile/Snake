import java.awt.*;
import javax.swing.*;
  import java.awt.event.*;
public class MainMenu extends JPanel implements ActionListener
{
  JFrame parent;
  public MainMenu (JFrame parent)
  {
    this.parent = parent;
    SpringLayout layout = new SpringLayout ();
    setSize (parent.getSize());
    setLayout (layout);
    ImageIcon playIcon = new ImageIcon ("playicon.png");
    ImageIcon quitIcon = new ImageIcon ("quiticon.png");
    Image img1 = playIcon.getImage();
    img1 = img1.getScaledInstance (75,75,Image.SCALE_SMOOTH);
    Image img2 = quitIcon.getImage();
    img2 = img2.getScaledInstance (75,75,Image.SCALE_SMOOTH);
    playIcon = new ImageIcon (img1);
    quitIcon = new ImageIcon (img2);
    JButton playButton = new JButton (playIcon);
    JButton quitButton = new JButton (quitIcon);
    playButton.setSize (playButton.getPreferredSize());
    quitButton.setSize (quitButton.getPreferredSize());
    playButton.setActionCommand ("play");
    quitButton.setActionCommand ("quit");
    playButton.addActionListener (this);
    quitButton.addActionListener (this);

    //Put the buttons on top of each other
    layout.putConstraint  (SpringLayout.NORTH,playButton,100,SpringLayout.NORTH,this);
    layout.putConstraint  (SpringLayout.NORTH,quitButton,200,SpringLayout.NORTH,this);
    
    //center the buttons
    layout.putConstraint (SpringLayout.WEST,playButton,(int)((this.getSize().getWidth()/2)-(playButton.getSize().getWidth())/2),SpringLayout.WEST,this);
    layout.putConstraint (SpringLayout.WEST,quitButton,(int)((this.getSize().getWidth()/2)-(quitButton.getSize().getWidth())/2),SpringLayout.WEST,this);
    add (playButton);
    add (quitButton);
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    ((ActionListener)parent).actionPerformed (ae);
  }
}