package core.fachada;

import core.dados.Download;

public interface InterfaceCliente {

	String getIP();
	void setIP(String iP);
	String getNome();
	void setNome(String nome);
	int getPorta();
	void setPorta(int porta);
	Download getDown();
	void setDown(Download down);
}
