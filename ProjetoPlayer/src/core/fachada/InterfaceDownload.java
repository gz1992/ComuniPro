package core.fachada;

import core.dados.Download;
import core.dados.exceptions.DownloadNaoIniciadoException;

public interface InterfaceDownload {

	int getConfirmado();
	void setConfirmado(int confirmado);
	int getTamanho();
	void setTamanho(int tamanho);
	String getMusica();
	void setMusica(String musica);
	String getIP();
	void setIP(String iP);
	double getTempoEstimado();
	void setTempoEstimado(double tempoEstimado);
	boolean isAtivo();
	void setAtivo(boolean ativo);
	Download statusDownload () throws DownloadNaoIniciadoException;
	void inicializaDownload(int tamanho, int confirmado, String musica, String IP, double tempoEstimado,
			boolean ativo);
	
}
