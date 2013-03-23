package core.dados.exceptions;

public class DownloadNaoIniciadoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DownloadNaoIniciadoException ()
	{
		super ("Download ainda nao iniciado.");
	}

}
