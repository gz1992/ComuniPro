package core.negocio;

import core.dados.Clientes;
import core.fachada.InterfaceManipulaTrans;

import java.util.Vector;

/**@author Angelo
 * TODO vai tratar de cuidar os casos do clientes, ou seja, para o possível caso de vários clientes */
public class ManipulaTransferencia implements InterfaceManipulaTrans {
	Vector <Clientes> cliente;

	public ManipulaTransferencia() {
		this.cliente = new Vector <Clientes>();
	}
	
	public Vector<Clientes> getCliente() {
		return cliente;
	}

	public void setCliente(Vector<Clientes> cliente) {
		this.cliente = cliente;
	}
	
	public void inserirCliente ()
	{
		
	}
	
	public void removerCliente ()
	{
		
	}
	
	public void removerClientePorIndice ()
	{
		
	}
	
	public void inserirClientePorIndice ()
	{
		
	}
	
	public Object identificaCliente(Object obj)
	{
		return null;
	}
	
	/** TODO ver como vou mandar a lista de downloads para a GUI */
	public void atualizaListaDeDownloads()
	{
		
	}
	
}
