package unit2_more_complex;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Sender {
	public Sender() throws Exception {
		//// Step 1: Create a regular socket, DatagramPacket, buffer
		DatagramSocket regularSocket = new DatagramSocket();
		// > Mo cong de trao doi data
		// > Tao regular socket khong can truyen doi so Port vao
		DatagramPacket packet = null;
		// > Dung de chuyen data cho Receiver thong qua regular socket
		byte[] buffer = null;
		// > Dong goi data lai de packet truyen di
		
		
		
		
		
		//// Step 2: Create a communication
		Scanner s = new Scanner(System.in);
		String request = "";
		String response = "";
		do {
			// Send message
			System.out.println("Enter \"exit\" or 'e' to exit communication");
			System.out.print("Enter your message: ");
			request = s.nextLine();
			
			buffer = request.getBytes();
			packet = new DatagramPacket(buffer, buffer.length);
			packet.setAddress(InetAddress.getByName("127.0.0.1"));
			packet.setPort(2020);
			
			regularSocket.send(packet);
			
			// To exit while loop when we send "exit" or "e"
			if(request.equalsIgnoreCase("exit") || request.equalsIgnoreCase("e")) {
				System.out.println("The communication is end");
				break;
			}
					
			
			// Receive message
			buffer = new byte[1500];
			packet = new DatagramPacket(buffer, buffer.length);
			
			regularSocket.receive(packet);
			
			response = new String(buffer).trim();
			System.out.println("You received from receiver: " + response + "\n");
			
			// To exit while loop when we get "exit" or "e"
			if(response.equalsIgnoreCase("exit") || response.equalsIgnoreCase("e")) {
				System.out.println("The communication is end");
				break;
			}
		} while (true);

		
		
		
		
		//// Vs UDP thi khong phai tao server socket nen cung khong can phai close() regular socket
		////	khi nhan tin xong
	}
	
	public static void main(String[] args) {
		try {
			new Sender();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// > Muon xem cuoc hoi thoai cua sender va receiver thi:
		//		+ Bat dau chay class Sender truoc
		//		+ Sau do chay class Receiver
		//		+ Sau do moi nhap message cho 2 class
		// 		+ Cuoi cung bam icon "Display selected console" (hinh cai man hinh TV ben phai man hinh console)
		//			de tiep tuc cuoc hoi thoain neu khong bam thi se chi thay loi thoai dau tien cua Sender va Receiver thoi
	}
}
