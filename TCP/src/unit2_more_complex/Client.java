package unit2_more_complex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public Client() throws IOException {
		// Step 1: Create regular socket
		Socket regularSocket = new Socket("127.0.0.1", 2020);
		System.out.println("Successful connection to the server!");
		
		// Step 2: Create I/O Buffers
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(regularSocket.getInputStream()));
		PrintWriter toServer = new PrintWriter(new OutputStreamWriter(regularSocket.getOutputStream()), true);

		// Step 3: Create communication
		Scanner s = new Scanner(System.in);
		
		System.out.println(fromServer.readLine());
		char request = s.nextLine().charAt(0);
		toServer.println(request);
		
		if (request == 'y' || request == 'Y') {
			int answer = -1;
			boolean checked = false;
			
			System.out.println(fromServer.readLine());
			
			do {
				answer = Integer.parseInt(s.nextLine());
				toServer.println(answer);
				checked = Boolean.parseBoolean(fromServer.readLine());
				System.out.println(checked);
			} while (!checked);
			
			System.out.println(fromServer.readLine());
			System.out.println(fromServer.readLine());
			System.out.println(fromServer.readLine());
		}
		System.out.println(fromServer.readLine());
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
			new Client();
		} catch (Exception e) {
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
