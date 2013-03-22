package comunicacao;

import java.net.*;

//socket de recepção do servidor
public class TServer {
	private Connection connec;
	
	public TServer(int p) throws Exception {
		connec = new Connection(p);
	}
	public TSocket accept() throws Exception {
		DatagramPacket dp = new DatagramPacket(new byte[16], 16);
		while (true) {
			connec.receiveForever(dp);
			if (dp.getData()[0] == (byte)'1') 
				return new TSocket(dp.getAddress(), dp.getPort());
		}
	}
	public void close() {
		connec.close();
	}
}
