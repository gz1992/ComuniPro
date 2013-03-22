package comunicacao;

import java.util.Scanner;

//thread do servidor dedicada a cada cliente
public class ThreadServidor extends Thread {
	private TSocket in, out;
	public ThreadServidor(TSocket ts) throws Exception {
		out = ts;
		in = new TSocket(ts.getIP(), 2521);
	}
	public void run() {
		String comm, path = "H:\\IC\\músicas\\";
		//path = "C:\\Users\\Mario\\Music\\Outros\\";
		//path = "C:\\Users\\mhss\\Downloads\\músicas\\"; 
		//path = "C:\\Users\\Mario\\Desktop\\Faculdade\\IC\\Projeto\\músicas\\";
		Scanner sc;
		byte[] buf = new byte[1024];
		int len = 0;
		boolean go = true;
		while (go) {
			try {
				len = in.receive(buf, 0);
				comm = new String(buf, 0, len);
				if (comm.charAt(0) == 'L') {
					System.out.print("Lista\n");
					sc = new Scanner(comm);
					sc.nextLine();
					out.send(ListMus.buscaMus(path, sc.nextLine(), sc.nextLine(), sc.nextLine(), 
											sc.nextLine(), sc.nextLine()).getBytes(), 'M');
					//manda a lista de músicas
				} else {
					System.out.print("Download\n");
					out.send(buf, 'M');
					//começa um download
				}
			} catch (CloseException e) {
				try {
					out.close();
				} catch (Exception e2) {}
				break;
			} catch (Exception e) {}
			System.out.print("\n");
			for (int x = 0; x < len; x++) buf[x] = 0;
		}
		System.out.print("flw\n");
	}
}
