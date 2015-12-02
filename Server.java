import java.io.*;
import java.net.*;
import java.util.*;


public class Server implements Runnable{


  

	private Socket connection;
	private String timeStamp;
	private int ID;
	private static int clientCount = 0;



	private static BoardLogic mBoardLogic;
	private int playerTurn;
	private int player;

	public static void main(String[] args) {
		mBoardLogic = new BoardLogic();
		int port = 8000;
		int count = 0;

		try {
			ServerSocket socket1 = new ServerSocket(port);
			System.out.println("Server initialized...\nWaiting on clients...");
			while (true) {
				Socket connection = socket1.accept();
				Runnable runnable = new Server(connection, ++count);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} catch (Exception e) {

		}
	
	}

	public Server(Socket s, int i) {
  		this.connection = s;
  		this.ID = i;
  		clientCount += 1;
  		System.out.println("Client connected with Unique ID: " + this.ID);
  		System.out.println("Total clients: " + clientCount);
	}

	
	public void run() {
		try {
			System.out.println("Thread started...");
			DataOutputStream outputToClient = new DataOutputStream(this.connection.getOutputStream());
			if (clientCount == 1) {
				outputToClient.writeUTF("You are player one");
				this.player = 1;
			} else if (clientCount == 2) {
				outputToClient.writeUTF("You are player two");
				this.player = 2;
			}
			outputToClient.flush();
			DataInputStream inputFromClient = new DataInputStream(this.connection.getInputStream());
			

			
			

			
			ObjectOutputStream objectToClient = new ObjectOutputStream(this.connection.getOutputStream());
			System.out.println("Sending object...");
			
			
			objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
			objectToClient.flush();
			objectToClient.reset();
			System.out.println("Sent...");
			
			ObjectInputStream objectFromClient = new ObjectInputStream(this.connection.getInputStream());
			
			
			//System.out.println("Sending object!!!!!");
			//MiddleMan outMiddleMan = new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn());
			//objectToClient.writeObject(outMiddleMan);
				
			while(true) {
				MiddleMan inMiddleMan = (MiddleMan) objectFromClient.readObject();
				System.out.println("Got object from Unique Client: " + this.ID);
				System.out.println("The x value contained is: " + inMiddleMan.getX() + ". From Player " + inMiddleMan.getPlayer());
				System.out.println("The y value is: " + mBoardLogic.CheckColumn(inMiddleMan.getX()));
				System.out.println("The playerTurn % 2 = " + mBoardLogic.GetPlayerTurn());
				mBoardLogic.PlaceChip(mBoardLogic.GetPlayerTurn(), inMiddleMan.getX(), mBoardLogic.CheckColumn(inMiddleMan.getX()));
				mBoardLogic.IncrementPlayerTurn();
				
				

				System.out.println("Sending updated data...");
				
				objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
				objectToClient.flush();
				objectToClient.reset();
				System.out.println("Sent!");


			}
			

		} catch (Exception e) {
			if (clientCount == 2) {
				System.out.println("Player 2 has left the game...");
			} else if (clientCount == 1) {
				System.out.println("Player 1 has left the game...");
			}
			System.out.println("Unique Client: " + this.ID + " has disconnected from the server...");
			clientCount -= 1;
			try {
				this.connection.close();
			} catch (IOException e2) {

			}
		}
	}
}
