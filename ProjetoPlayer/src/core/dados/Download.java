package core.dados;

public class Download {
	int tamanho;
	String musica, IP;
	double tempoEstimado;
	boolean ativo;
	
	public Download() {
		this.tamanho = 0;
		this.musica = "";
		this.IP = "";
		this.tempoEstimado = 0.0;
		this.ativo = false;
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
