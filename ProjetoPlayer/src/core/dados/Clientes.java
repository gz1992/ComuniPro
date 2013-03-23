package core.dados;

import core.fachada.InterfaceCliente;

public class Clientes implements InterfaceCliente {
	String IP, nome;
	int porta;
	Download down;

	public Clientes() {
		this.IP = "";
		this.nome = "";
		this.porta = 0;
		this.down = null;
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

	public Download getDown() {
		return down;
	}

	public void setDown(Download down) {
		this.down = down;
	}
	
}
