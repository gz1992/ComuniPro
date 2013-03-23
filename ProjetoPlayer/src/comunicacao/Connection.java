package comunicacao;

import java.net.*;
import java.util.Random;

//O básico da conexão: checa pacotes corrompidos, perde eles de propósito, corrompe eles de propósito, lida com o timeout, etc
public class Connection {
	private Random rand;
	private DatagramSocket ds;
	public static int timeout = 500;
	
	//construtor para o cliente
	public Connection() throws Exception {
		rand = new Random();
		ds = new DatagramSocket();
		setTimeout();
	}
	//construtor para o servidor
	public Connection(int porta) throws Exception {
		rand = new Random();
		ds = new DatagramSocket(porta);
	}
	//tira o timeout
	void clearTimeout() throws Exception {ds.setSoTimeout(0); }
	//seta o timeout
	void setTimeout() throws Exception {ds.setSoTimeout(timeout); }
	//para o teste do checksum
	public DatagramPacket corrupt(DatagramPacket dp) {
		byte[] buf = dp.getData(), buf2 = new byte[buf.length];
		int i = rand.nextInt(buf2.length-3)+1, x;
		for (x = 0; x < buf.length; x++) buf2[x] = buf[x];
		while (i-- > 0) {
			x = rand.nextInt(buf2.length);
			buf2[x] ^= (byte)rand.nextInt(256);
		}
		return new DatagramPacket(buf2, buf2.length, dp.getAddress(), dp.getPort());
	}
	//checa se o pacote está corrompido
	public static boolean corrupted(DatagramPacket dp) {
		byte[] buf = dp.getData();
		short checksum = TSocket.check(buf, buf.length);
		return (checksum>>8 != buf[2]) && (((byte)checksum) != buf[3]);
	}
	
	public void send(DatagramPacket dp) throws Exception {
		ds.send(dp);
		System.out.print("Enviado "+((char)dp.getData()[0])+"\n");
		/*if (rand.nextInt(100) < 50) {
			ds.send(corrupt(dp));
			System.out.print("Enviado "+((char)dp.getData()[0])+" corrompido\n");
		} else {
			ds.send(dp);
			System.out.print("Enviado "+((char)dp.getData()[0])+"\n");
		}*/
	}
	//recebe com timeout
	public void receive(DatagramPacket dp) throws Exception {
		setTimeout();
		ds.receive(dp);
		if (rand.nextInt(100) >= TD.get()) {
			if (!corrupted(dp)) {
				System.out.print("Recebido "+((char)dp.getData()[0])+"\n");
				return;
			}// else System.out.print("Recebido "+((char)dp.getData()[0])+" corrompido\n");
		}
		throw new SocketTimeoutException();
	}
	/*	public DatagramPacket receive() throws Exception {
		DatagramPacket dp = new DatagramPacket(new byte[16384], 16384);
		while (true) {
			ds.receive(dp);
			if (rand.nextInt(100) >= TD.get()) {
				if (!corrupted(dp)) {
					System.out.print("Recebido "+((char)dp.getData()[0])+"\n");
					return dp;
				}
			}
		}
	}*/
	//recebe sem timeout
	public void receiveForever(DatagramPacket dp) throws Exception {
		clearTimeout();
		while (true) {
			try {
				this.receive(dp);
				setTimeout();
				return;
			} catch (SocketTimeoutException e) {}
		}
	}
/*	public DatagramPacket receiveForever() throws Exception {
		clearTimeout();
		DatagramPacket dp = new DatagramPacket(new byte[16384], 16384);
		while (true) {
			ds.receive(dp);
			if (rand.nextInt(100) >= TD.get()) {
				if (!corrupted(dp)) {
					setTimeout();
					System.out.print("Recebido "+((char)dp.getData()[0])+"\n");
					return dp;
				}
			}
		}
	}*/
	public void close() {
		ds.close();
	}
}
