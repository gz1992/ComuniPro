package comunicacao;

//Thread do servidor que gera novas threads de conexão
public class GeradorServidor extends Thread {
	private TServer ts;
	public GeradorServidor(TServer ts) {
		this.ts = ts;
	}
	public void run() {
		while (true) {
			try {
				new ThreadServidor(ts.accept()).start();
				System.out.print("Novo\n\n");
			} catch (Exception e) {}
		}
	}
}
