package comunicacao;

import java.net.*;

//conexão entre cliente e servidor
public class TSocket {
	private InetAddress IP;
	private int porta;
	private Connection connec;
	
	//construtor para o cliente
	public TSocket(String s, int p) throws Exception {
		IP = InetAddress.getByName(s);
		porta = p;
		connec = new Connection();
		DatagramPacket dp = new DatagramPacket(new byte[16], 16);
		int x;
		for (x = 0; x < 10; x++) {
			send('1');
			try {
				connec.receive(dp);
				if (dp.getData()[0] == '2') break;
			} catch (SocketTimeoutException e) {}
		}
		if (x == 10) throw new Exception();
		porta = dp.getPort();
		send('3');
	}
	//construtor para o servidor
	public TSocket(InetAddress ia, int p) throws Exception {
		IP = ia;
		porta = p;
		connec = new Connection();
		int x;
		for (x = 0; x < 10; x++) {
			send('2');
			try {
				if (nextComm() == '3') break;
			} catch (SocketTimeoutException e) {}
		}
		if (x == 10) throw new Exception();
	}
	public String getIP() {return IP.getHostName(); }
	//checksum
	public static short check(byte[] buf, int len) {
		int checksum1 = buf[0], checksum2 = buf[1];
		for (int x = 4; x < len; x += 2) checksum1 += buf[x];
		for (int x = 5; x < len; x += 2) checksum2 += buf[x];
		return (short) ((checksum1<<8) + checksum2);
	}
	//criar pacote
	public DatagramPacket makePkt(byte[] buf, int len, int offset, char tipo) {
		byte[] ret = new byte[len+4];
		ret[0] = (byte) tipo; ret[1] = 0;
		for (int x = 0; x < len; x++) ret[x+4] = buf[x+offset];
		short checksum = TSocket.check(ret, len+4);
		ret[2] = (byte) (checksum>>8); ret[3] = (byte) checksum;
		return new DatagramPacket(ret, len+4, IP, porta);
	}
	//usado pelos Respondedores
	public void send(byte[] buf, char tipo) throws Exception {
		DatagramPacket dp = makePkt(buf, buf.length, 0, tipo);
		int x;
		for (x = 0; x < 10; x++) {
			connec.send(dp);
			try {
				if (nextComm() == 'A') break;
			} catch (SocketTimeoutException e) {}
		}
		if (x == 10) throw new Exception();
	}
	//simplesmente envia um A/1/2/3/4/5, sem se importar se chegou ou não
	public void send(char tipo) throws Exception {
		connec.send(makePkt(new byte[0], 0, 0, tipo));
	}
	public int receive(byte[] buf, int offset) throws Exception {
		DatagramPacket dp = new DatagramPacket(new byte[16384], 16384);
		int len;
		do {
			connec.receiveForever(dp);
			if (dp.getData()[0] == '2') send('3');
		} while (dp.getData()[0] != 'M' && dp.getData()[0] != '4');
		byte[] array = dp.getData();
		if (array[0] == '4') {
			close2();
			throw new CloseException();
		}
		send('A');
		len = dp.getLength();
		for (int x = 0; x < len-4; x++) buf[x+offset] = array[x+4];
		return len-4;
	}
	//recebe o próximo caractere de comando
	public char nextComm() throws Exception {
		DatagramPacket dp = new DatagramPacket(new byte[16], 16);
		connec.receive(dp);
		return (char) dp.getData()[0];
	}
	//o primeiro a fechar
	public void close() throws Exception {
		for (int x = 0; x < 10; x++) {
			send('4');
			try {
				if (nextComm() == '5') break;
			} catch (SocketTimeoutException e) {}
		}
		connec.close();
	}
	//o segundo a fechar
	public void close2() throws Exception {
		send('5');
		for (int x = 0; x < 5; x++) {
			try {
				if (nextComm() == '4') send('5');
			} catch (SocketTimeoutException e) {}
		}
		connec.close();
	}
 }
