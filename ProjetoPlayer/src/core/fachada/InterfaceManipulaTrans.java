package core.fachada;

import core.dados.Download;

public interface InterfaceManipulaTrans {

	Download atualiza (Object bytes);
	void inserirDownload (Download down);
	void removerDownload (Download down);
	void removerDownloadPorIndice (int ind);
	void inserirDownloadEmIndice (int indice, Download download);
	
}
