package LN;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class clsAudio 
{

	public static Clip clip;
	
	public static void play() {
		try { File file = new File("song.wav");
		clip = AudioSystem.getClip(); 
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.start(); 
		Thread.sleep(clip.getMicrosecondLength()); }
		catch (Exception e) { 
			System.err.println(e.getMessage());
			}
		}
	
	public static void pararAudio()
	{
		if(clip.isRunning())
		{
			clip.stop();
		}
	}
}
