import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("sounds/paddle.wav"));
	public static final AudioClip BrickHit = Applet.newAudioClip(Sound.class.getResource("sounds/Brick_Hit.wav"));
	public static final AudioClip Powerup = Applet.newAudioClip(Sound.class.getResource("sounds/Powerup.wav"));
	public static final AudioClip missile = Applet.newAudioClip(Sound.class.getResource("sounds/missile.wav"));
	public static final AudioClip machineGun = Applet.newAudioClip(Sound.class.getResource("sounds/machineGun.wav"));
	static boolean isMuted = false;
	
	//public Sound(){
		
	//}
	
	/**
	 * plays the sound
	 * @param sound
	 */
	public static void Play(AudioClip sound) {
		if (isMuted==false){
			sound.play();
		}
	}

	/**
	 * returns the status of isMuted (boolean)
	 * @return
	 */
	public static boolean isMuted() {
		return isMuted;
	}

	/**
	 * sets the muted status of the sound
	 * @param isMuted
	 */
	public static void setMuted(boolean isMuted) {
		isMuted = isMuted;
	}
}
