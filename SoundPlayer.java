import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * Class for playing sounds during the game
 * @author Jason P'ng
 * @version 2.5 September 30th, 2014
 */
public class SoundPlayer
{
  static AudioClip clip;
  
  /**
   * Constructor to set up the AudioClips
   */
  public SoundPlayer ()
  {
    URL url = SoundPlayer.class.getResource ("nom.wav");
    clip = Applet.newAudioClip (url);
  }
  
  /**
   * Plays the sound of a snake eating
   */
   public static void playNomClip()
   {
   clip.play ();
  }
}