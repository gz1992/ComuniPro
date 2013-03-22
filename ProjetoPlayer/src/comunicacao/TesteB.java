package comunicacao;

import java.util.Scanner;

public class TesteB {

	public static void main(String[] args) throws Exception {
		TD.set(25);
		Scanner in = new Scanner(System.in);
		TServer s = new TServer(2000);
		TSocket ts = s.accept();
		byte[] buf = new byte[68], buf2;
		int len;
		System.out.print("Conectou!\n");
		while (true) {
			len = ts.receive(buf, 0);
			System.out.print(new String(buf, 0, len)+'\n');
			buf2 = in.nextLine().getBytes();
			ts.send(buf2, 'M');
		}
	}
}
