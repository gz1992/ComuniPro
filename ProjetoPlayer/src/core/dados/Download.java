package core.dados;

import core.dados.exceptions.DownloadNaoIniciadoException;
import core.fachada.InterfaceDownload;

public class Download implements InterfaceDownload {
	int tamanho, confirmado;
	String musica, IP;
	double tempoEstimado;
	boolean ativo;
	
	public Download() {
		this.confirmado = 0;
		this.tamanho = 0;
		this.musica = "";
		this.IP = "";
		this.tempoEstimado = 0.0;
		this.ativo = false;
	}
	
	public Download statusDownload () throws DownloadNaoIniciadoException
	{
		if (this == null)
			throw new DownloadNaoIniciadoException();
		else
			return (this);
	}
	
	public void inicializaDownload(int tamanho, int confirmado, String musica, String IP, double tempoEstimado,
			boolean ativo)
	{
		this.setTamanho(tamanho);
		this.setConfirmado(confirmado);
		this.setMusica(musica);
		this.setIP(IP);
		this.setTempoEstimado(tempoEstimado);
		this.setAtivo(ativo);
	}
	
	public int getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(int confirmado) {
		this.confirmado = confirmado;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String getMusica() {
		return musica;
	}

	public void setMusica(String musica) {
		this.musica = musica;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public double getTempoEstimado() {
		return tempoEstimado;
	}

	public void setTempoEstimado(double tempoEstimado) {
		this.tempoEstimado = tempoEstimado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
