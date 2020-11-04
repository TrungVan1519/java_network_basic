package unit2_more_complex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

	public Server() throws IOException {
		// Step 1: Create ser socket and regualr socket
		ServerSocket serverSocket = new ServerSocket(2020);
		System.out.println("Port 2020 is opened");
		
		Socket regularSocket = serverSocket.accept();
		System.out.println("Client " + regularSocket.getInetAddress() + " has connected");
	
		// Step 2: Create I/O Buffers
		BufferedReader fromClient = new BufferedReader(new InputStreamReader(regularSocket.getInputStream()));
		PrintWriter toClient = new PrintWriter(new OutputStreamWriter(regularSocket.getOutputStream()), true);

		// Step 3: Create communication
		toClient.println("Do you want to play the game? (Press y or Y to play)");
		String response = fromClient.readLine();
		System.out.println("Client " + " says: " + response);
		
		if(response.charAt(0) == 'y' || response.charAt(0) == 'Y') {
			int result = new Random().nextInt(10) + 1;
			int answer = -1;
			boolean checked = false;
			
			System.out.println("==> The random number is: " + result);
			toClient.println("Guess a number [1-10]: ");
			
			while (checked == false) {
				response = fromClient.readLine();
				answer = Integer.parseInt(response);
				
				if(answer == result) {
					checked = true;
				}
				
				System.out.println("Client " + " says: " + response + " ==> " + checked);
				toClient.println(checked);
			}
			toClient.println("Ping pong! The random number is " + result);
			toClient.append("Goodgame\n");
			toClient.write("See you later!");
		}
		toClient.println("Bye");
		// > CHU Y: 
		//		+ Ben class Server toClient.println() truyen bao nhieu lan thi ben class Client phai
		//			fromServer.readLine() nhan bay nhieu lan
		//		+ Tuong tu ben class Client toServer.println() bao nhieu lan thi ben class Server phai
		//			fromClient.readLine() nhan bay nhieu lan
		
		// Step 4: Close regular socket
		regularSocket.close();
		System.out.println("Socket is closed");
	}
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// > Muon xem cuoc hoi thoai cua Server va Client thi:
		//		+ Bat dau chay class Server truoc
		//		+ Sau do chay class Client
		// 		+ Cuoi cung bam icon "Display selected console" (hinh cai man hinh TV ben phai man hinh console)
		//			de tiep tuc cuoc hoi thoain neu khong bam thi se chi thay loi thoai dau tien cua Server va Client thoi
		// > Su dung TcpView de huy port neu nhu muon chay lai lan tiep theo cho chinh port day vi nhu da noi o tren 
		//		Socket socket = serverSocket.accept(); se lam Java luon dung lai de doi den khi nao co 1 connection hop le duoc
		//		accept thi moi chay dong lenh tiep theo, neu khong thi no se mai dung o dong do va port do se khong bao h duoc dong
		//		ke ca ta tat chay console nen neu chay lan tiep theo vao chinh port do se bi loi vi port do dang doi connection hop le
		//		de accept chu khong can phai mo lai port nua
	}
}
