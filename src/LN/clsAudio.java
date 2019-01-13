package LN;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class clsAudio 
{

	public static Clip clip;
	
	public static void play()  {
//		try {
		File file = new File("song.wav");
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
			clip.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException
				| UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clip.start();; 
//		Thread.sleep(clip.getMicrosecondLength()); }
//		catch (Exception e) { 
//			System.err.println(e.getMessage());
//			}
		
		
		
		}
	
	public static void pararAudio()
	{
		if(clip.isOpen())
		{
			clip.stop();
			clip.close();
		}
	}
}
