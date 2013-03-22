package comunicacao;

import java.util.Scanner;

public class TesteC {

	public class Printer extends Thread {
		TSocket in;
		public Printer(TSocket ts) {this.in = ts;}
		public void run() {
			byte[] buf = new byte[16384];
			int len = 0;
			while (true) {
				try {
					len = in.receive(buf, 0);
					System.out.print(new String(buf, 0, len)+"\n");
				} catch (CloseException e) {
					System.out.print("flw\n");
					break;
				} catch (Exception e) {
					System.out.print("Erro\n");
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		TD.set(25);
		Scanner sc = new Scanner(System.in);
		TServer serv = new TServer(2521);
		new TesteC().new Printer(new TSocket("localhost", 2520)).start();
		TSocket out = serv.accept();
		serv.close();
		String comando;
		while (true) {
			comando = sc.nextLine();
			if (comando.length() > 0 && comando.charAt(0) == 'F') {
				out.close();
				break;
			} else if (comando.charAt(0) == 'L') {
				System.out.print("Artista: ");
				comando += "\n"+sc.nextLine();
				System.out.print("Álbum: ");
				comando += "\n"+sc.nextLine();
				System.out.print("Gênero: ");
				comando += "\n"+sc.nextLine();
				System.out.print("Título: ");
				comando += "\n"+sc.nextLine();
				System.out.print("Ano: ");
				comando += "\n"+sc.nextLine()+"\n";
			}
			//System.out.print(comando);
			out.send(comando.getBytes(), 'M');
		}
		sc.close();
	}

}
