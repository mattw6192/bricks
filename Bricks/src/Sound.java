import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("sounds/Laser_Shoot20.wav"));
	public static final AudioClip BrickHit = Applet.newAudioClip(Sound.class.getResource("sounds/Brick_Hit.wav"));
	public static final AudioClip Powerup = Applet.newAudioClip(Sound.class.getResource("sounds/Powerup.wav"));
}