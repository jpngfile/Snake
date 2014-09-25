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
        
    ImageIcon playRolloverIcon = new ImageIcon ("playRolloverIcon.png");
    ImageIcon quitRolloverIcon = new ImageIcon ("quitRolloverIcon.png");
    Image img3 = playRolloverIcon.getImage();
    img3 = img3.getScaledInstance (80,80,Image.SCALE_SMOOTH);
    Image img4 = quitRolloverIcon.getImage();
    img4 = img4.getScaledInstance (75,75,Image.SCALE_SMOOTH);
    playRolloverIcon = new ImageIcon (img3);
    quitRolloverIcon = new ImageIcon (img4);
    
    playButton.setRolloverIcon (playRolloverIcon);
    quitButton.setRolloverIcon (quitRolloverIcon);
    playButton.setRolloverSelectedIcon (playRolloverIcon);
    quitButton.setRolloverSelectedIcon (quitRolloverIcon);
    playButton.setRolloverEnabled (true);
    quitButton.setRolloverEnabled (true);

    playButton.setPressedIcon (playRolloverIcon);
    quitButton.setPressedIcon (quitRolloverIcon);
    
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