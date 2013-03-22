package comunicacao;

public class TesteD {

	public static void main(String[] args) throws Exception {
		TD.set(0);
		new GeradorServidor(new TServer(2520)).start();
		while (true) {}
	}

}
