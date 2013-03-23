package core.dados;

import java.util.Vector;

import core.fachada.InterfaceCliente;

public class Clientes implements InterfaceCliente {
	String IP, nome;
	int porta;
	Vector <Download> lista;

	public Clientes() {
		this.IP = "";
		this.nome = "";
		this.porta = 0;
		this.lista = null;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPorta() {
		return porta;
	}

	public void setPorta(int porta) {
		this.porta = porta;
	}
	
	public Vector<Download> getLista() {
		return lista;
	}

	public void setLista(Vector<Download> lista) {
		this.lista = lista;
	}
	
	/** Exibir as informações para a GUI */
	
	/**
	 * @param download O download que está sendo criado no momento.
	 */
	public void inserirDownload (Download download)
	{
		this.lista.add(download);
	}
	
	/**
	 * @param download O objeto a ser removido da lista.
	 */
	public void removerDownload (Download download)
	{
		this.lista.remove(lista);
	}
	
	/**
	 * @param indice O indice identificador do objeto.
	 */
	public void removerDownloadPorIndice (int indice)
	{
		this.lista.remove(indice);
	}
	
	/**
	 * @param indice	A posição em que o elemento vai ser inserido.
	 * @param download	O objeto a ser inserido.
	 */
	public void inserirDownloadEmIndice (int indice, Download download)
	{
		this.lista.add(indice, download);
	}
	
}
