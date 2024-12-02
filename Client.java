import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		
		while(true) {
			System.out.println("Enter your command:");
			String line = input.nextLine();
			int port = Integer.parseInt(line.split(" ")[0]);
			String command = line.split(" ")[1];
			System.out.println("port: " + port + "  command: " + command);
			oneTimeCommunicate(port,command);
		}
		
	}

	public static void oneTimeCommunicate(int port, String message) {
		Socket socket = null;

		try {
			socket = new Socket("localhost",port);
	
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			OutputStream output = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(output,true);
		
			//send message
			writer.println(message);

			System.out.println("Just send out: " + message + " to port: " + port);
			
			//get response
			String line = reader.readLine();

			System.out.println("Got response: " + line + "\n");
			
			//close the socket
			socket.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
