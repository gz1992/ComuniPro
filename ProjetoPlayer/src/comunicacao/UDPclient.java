package comunicacao;

import java.net.*;
import java.util.Scanner;

class UDPclient {

	public static void main(String args[]) throws Exception {
		String inFromUser = new Scanner(System.in).next() + '\n'; //entrada do usuário
		DatagramSocket clientSocket = new DatagramSocket(); //socket UDP
		InetAddress IPServer = InetAddress.getByName("localhost"); //IP do servidor
		
		byte[] sendData = new byte[1024]; //dados a serem enviados
		sendData = inFromUser.getBytes();
		//criando o pacote de envio
	DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPServer, 5000);
		clientSocket.send(sendPacket);
		
		byte[] receiveData = new byte[1024]; //dados recebidos
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		
		clientSocket.receive(receivePacket);	 //recebendo o pacote 	
		String inFromServer = new String(receivePacket.getData());
		System.out.println("FROM SERVER: " + inFromServer);
		clientSocket.close();
	} 
}
