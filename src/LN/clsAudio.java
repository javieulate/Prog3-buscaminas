package LN;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import LD.clsBaseDeDatos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class clsAudio 
{
	private static Logger logger = Logger.getLogger(clsAudio.class.getName() );
	public static Clip clip;
	
	public static void play()  {

		File file = new File("song.wav");
		try {
			clip = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {

			logger.log( Level.SEVERE, "Audio no disponible debido a restricciones de las fuentes.", e1);
		} 
		try {
			clip.open(AudioSystem.getAudioInputStream(file));
		} catch (LineUnavailableException | IOException
				| UnsupportedAudioFileException e) {

			logger.log( Level.SEVERE, "Problemas para reproducir el audio.", e);
		}
		clip.loop(10000); 

		}
	
	public static void pararAudio()
	{
			clip.stop();
			clip.close();
	}
}