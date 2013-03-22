package comunicacao;

import java.util.Scanner;

public class TesteA {

	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(System.in);
		byte[] buf = new byte[68], buf2;
		int len;
		TSocket ts = new TSocket("localhost", 2000);
		System.out.print("Conectou!\n");
		while (true) {
			buf2 = in.nextLine().getBytes();
			ts.send(buf2, 'M');
			len = ts.receive(buf, 0);
			System.out.print(new String(buf, 0, len)+'\n');
		}
	}

}
