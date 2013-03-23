package core.dados;
/*
import java.io.FileInputStream;
import java.io.InputStream;
*/

import java.io.File;
import java.util.Map;

import java.util.Scanner;

import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;

import core.fachada.InterfacePlayer;

/** @seeAlso paralelismo - Falta ainda tratar possíveis tentativas de colocar duas músicas para rodar ao mesmo
 * 		tempo.
 * @seeAlso funções - Tenho que fazer o pausar, voltar e a música continuar tocando se não ocorrer stop
 * @takeCare_Attention AudioDevice - Ele tem que estar no package 'sun.audio' determinado por mim. */
public class Player extends Thread implements InterfacePlayer, BasicPlayerListener {
	String name;
	BasicPlayer player;
	BasicController control;
	static final int PLAYING = 1;
	static final int PAUSED = 2;
	static final int STOPPED = 0;
	static int statusMusic = -1;

	Player (String n)
	{
		this.name = n;
		this.player = null;
		this.control = null;
	}
	
	public void run ()
	{
		try {/*
			this.arquivo = new File (this.name);		// definindo o caminho do arquivo
			this.arqStream = new FileInputStream(arquivo);
			this.som = new AudioStream (arqStream);
			this.audio = new AudioPlayer();
			*/
			
			this.player = new BasicPlayer();
			this.control = (BasicController) player;
			
			this.player.addBasicPlayerListener(this);
			
			//this.audio.run();
			
		} catch (Exception e){}
	}
	
	public void tocar () throws Exception
	{
		control.open(new File (this.name));
		control.play();
		control.setGain(0.75);
		control.setPan(0.0);
	}
	
	public void parar () throws Exception
	{
		control.stop();
	}
	
	public void pausar () throws Exception
	{
		control.pause();
	}
	
	public void continuar () throws Exception
	{
		control.resume();
	}
	
	public void opened(Object stream, Map properties)
	{
		// Pay attention to properties. It's useful to get duration, 
		// bitrate, channels, even tag such as ID3v2.
		//	display("opened : "+properties.toString());		
	}
		
	/**
	 * Progress callback while playing.
	 * 
	 * This method is called severals time per seconds while playing.
	 * properties map includes audio format features such as
	 * instant bitrate, microseconds position, current frame number, ... 
	 * 
	 * @param bytesread from encoded stream.
	 * @param microseconds elapsed (<b>reseted after a seek !</b>).
	 * @param pcmdata PCM samples.
	 * @param properties audio stream parameters.
	 */
	public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties)
	{
		// Pay attention to properties. It depends on underlying JavaSound SPI
		// MP3SPI provides mp3.equalizer.
		//	display("progress : "+properties.toString());
	}
	
	/**
	 * Notification callback for basicplayer events such as opened, eom ...
	 *  
	 * @param event
	 */
	public void stateUpdated(BasicPlayerEvent event)
	{
		// Notification of BasicPlayer states (opened, playing, end of media, ...)
		display("stateUpdated : "+event.toString());
		if (event.getCode()==BasicPlayerEvent.STOPPED)
		{
			System.exit(0);
		}
	}

	/**
	 * A handle to the BasicPlayer, plugins may control the player through
	 * the controller (play, stop, ...)
	 * @param controller : a handle to the player
	 */	
	public void setController(BasicController controller)
	{
		display("setController : "+controller);
	}
	
	private void display(String msg)
	{}
		
	public static void main(String[] args) throws Exception {
		Player t = new Player("c:/a.mp3");
		t.run();
		t.tocar();
		
		while (true)
		{
			String doUser = new Scanner (System.in).next();

			/** Tenho que fazer ele parar e os subsequentes métodos assim que receber um comando da GUI */
			if (doUser.equalsIgnoreCase("stop"))
			{
				t.parar();
			} else if (doUser.equalsIgnoreCase("pausar"))
				t.pausar();
			else if (doUser.equalsIgnoreCase("voltar"))
				t.continuar();
			else if (doUser.equalsIgnoreCase("tocar")) {
				t.tocar();
			}
		}

	}

}
