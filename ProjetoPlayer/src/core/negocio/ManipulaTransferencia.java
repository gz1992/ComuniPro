package core.negocio;

import core.dados.Download;
import core.fachada.InterfaceManipulaTrans;

import java.util.ArrayList;

public class ManipulaTransferencia implements InterfaceManipulaTrans {
	ArrayList<Download> lista;

	public ManipulaTransferencia() {
		this.lista = new ArrayList<Download>();
	}
	
	public ArrayList<Download> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Download> lista) {
		this.lista = lista;
	}
	
	private Download enviaDownload ()
	{
		return null;
	}
	
	/**
	 * TODO ver como vai ser feita essa atualização.
	 * @return Os bytes que foram pegos no anterior segundo da transferência.
	 */
	public Download atualiza (Object bytes)
	{
		double start = System.currentTimeMillis(), delay;
		
		delay = System.currentTimeMillis() - start;
		while (delay < 1000)			// ou seja, menor que um segundo
		{
			delay = System.currentTimeMillis() - start;
			
			if (delay>=1000)
			{
				// manda os dados para a GUI
				
				this.enviaDownload();
			}
		}
		
		return null;
	}
	
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
