package core.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

import sun.audio.*;

/** @seeAlso paralelismo - Falta ainda tratar possíveis tentativas de colocar duas músicas para rodar ao mesmo
 * 		tempo.
 * @seeAlso funções - Tenho que fazer o pausar, voltar e a música continuar tocando se não ocorrer stop
 * @takeCare_Attention AudioDevice - Ele tem que estar no package 'sun.audio' determinado por mim. */
public class Player {
	String name;
	AudioStream som;
	File arquivo;
	InputStream arqStream;
	AudioPlayer audio;

	Player (String n)
	{
		this.name = n;
		this.som = null;
		this.arquivo = null;
		this.arqStream = null;
		this.audio = null;
	}

	public void tocar () throws Exception
	{
		try {
			this.arquivo = new File (this.name);		// definindo o caminho do arquivo
			this.arqStream = new FileInputStream(arquivo);

			this.audio = new AudioPlayer();
			this.som = new AudioStream (arqStream);

			this.audio.player.start(this.som);

		} catch (Exception e) {
			System.err.printf(e.getMessage(), '\n');
		}
	}

	public void parar () throws Exception
	{
		try {
			this.audio.player.stop(this.som);
		} catch (Exception e) {
			System.err.printf(e.getMessage(), '\n');
		}
	}

	public void pausar () throws Exception
	{
		try {
			this.audio.player.wait();
		} catch (Exception e) {
			System.err.printf(e.getMessage(), '\n');
		}
	}

	public void voltar () throws Exception
	{
		try {
			this.audio.player.notify();
		} catch (Exception e) {
			System.err.printf(e.getMessage(), '\n');
		}
	}

	public boolean interrompida () throws Exception
	{
		return this.audio.interrupted();
	}

	public static void main(String[] args) throws Exception {
		Player t = new Player("c:/b.mp3");

		while (true)
		{
			t.tocar();

			String doUser = new Scanner (System.in).next();

			/** Tenho que fazer ele parar e os subsequentes métodos assim que receber um comando da GUI */
			if (doUser.equalsIgnoreCase("stop"))
			{
				t.parar();
				break;
			} else if (doUser.equalsIgnoreCase("pausar"))
				t.pausar();
			else if (doUser.equalsIgnoreCase("voltar"))
				t.voltar();

		}

	}

}
