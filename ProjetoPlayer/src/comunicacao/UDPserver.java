package comunicacao;

import java.net.*;
class UDPserver {

	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(5000);
		byte[] receiveData = new byte[1024] ; byte[] sendData = new byte[1024];
		String inFromClient, outToClient;
		InetAddress clientIP; int port;
		while(true) {
			//pacote a ser recebido
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);  
			serverSocket.receive(receivePacket);  //recebendo o pacotes
			inFromClient = new String(receivePacket.getData()); //colocando na string os dados recebidos
			clientIP = receivePacket.getAddress(); //pegando o IP e porta do pacote que chegou
			port = receivePacket.getPort();
		
			outToClient = inFromClient.toUpperCase();
			sendData = outToClient.getBytes();
			//enviando pacote de resposta
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIP, port);
			serverSocket.send(sendPacket);
		} 
	} 
}
