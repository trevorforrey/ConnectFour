import java.io.*;
import java.net.*;
import java.util.*;


public class Server implements Runnable{


  

	private Socket connection;
	private String timeStamp;
	private int ID;
	private static int clientCount = 0;



	private static BoardLogic mBoardLogic;
	private static int playerTurn;
	private int player;
	private static boolean kickPlayers;

	public static void main(String[] args) {
		mBoardLogic = new BoardLogic();
		int port = 8000;
		int count = 0;
		kickPlayers = false;
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
			//System.out.println("Thread started...");
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
			//System.out.println("Sending object...");
			
			
			objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
			objectToClient.flush();
			objectToClient.reset();
			//System.out.println("Sent...");
			
			ObjectInputStream objectFromClient = new ObjectInputStream(this.connection.getInputStream());
			
			
			//System.out.println("Sending object!!!!!");
			//MiddleMan outMiddleMan = new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn());
			//objectToClient.writeObject(outMiddleMan);
			
			while(true) {
				if (mBoardLogic.getWon()) {
					if (mBoardLogic.WhoWon(playerTurn - 1) == 1) {
						System.out.println("\nPlayer one has won! Players now being ejected from server...\n\n");
						kickPlayers = true;
					} else if (mBoardLogic.WhoWon(playerTurn - 1) == 2) {
						System.out.println("\nPlayer two has won! Players now begin ejected from server...\n\n");
						kickPlayers = false;
					}
				}
				if (mBoardLogic.WhoWon(playerTurn - 1) == 3) {
					System.out.println("\nNo one has won. Players being ejected from server...\n\n");
					kickPlayers = true;
				}
				if (kickPlayers) {
					this.connection.close();
				}
				if (this.player == 1) {
					if (mBoardLogic.GetPlayerTurn() == 0) {
						//System.out.println("Sending updated data...");
						
						objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
						objectToClient.flush();
						objectToClient.reset();
						//System.out.println("Sent!");
						//System.out.println("Thread now sleeping...");

						MiddleMan inMiddleMan = (MiddleMan) objectFromClient.readObject();
						System.out.println("\nGot object from Unique Client: " + this.ID);
						System.out.println("The x value contained is: " + inMiddleMan.getX() + ". From Player " + inMiddleMan.getPlayer());
						System.out.println("The y value is: " + mBoardLogic.CheckColumn(inMiddleMan.getX()));
						System.out.println("The playerTurn % 2 = " + mBoardLogic.GetPlayerTurn() + "\n");
						mBoardLogic.PlaceChip(mBoardLogic.GetPlayerTurn(), inMiddleMan.getX(), mBoardLogic.CheckColumn(inMiddleMan.getX()));
						
						mBoardLogic.IncrementPlayerTurn();
						playerTurn++;
						

						/*System.out.println("Sending updated data...");
						
						objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
						objectToClient.flush();
						objectToClient.reset();
						System.out.println("Sent!");*/
					}
					if (mBoardLogic.GetPlayerTurn() == 1) {
						//System.out.println("Sending updated data...");
						
						objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
						objectToClient.flush();
						objectToClient.reset();
						//System.out.println("Sent!");
						//System.out.println("Thread now sleeping...");
						Thread.sleep(2000);

					}
				} else if (this.player == 2) {
					
					if (mBoardLogic.GetPlayerTurn() == 1) {
						//System.out.println("Sending updated data...");
						
						objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
						objectToClient.flush();
						objectToClient.reset();
						//System.out.println("Sent!");
						MiddleMan inMiddleMan = (MiddleMan) objectFromClient.readObject();
						System.out.println("\nGot object from Unique Client: " + this.ID);
						System.out.println("The x value contained is: " + inMiddleMan.getX() + ". From Player " + inMiddleMan.getPlayer());
						System.out.println("The y value is: " + mBoardLogic.CheckColumn(inMiddleMan.getX()));
						System.out.println("The playerTurn % 2 = " + mBoardLogic.GetPlayerTurn() + "\n");
						mBoardLogic.PlaceChip(mBoardLogic.GetPlayerTurn(), inMiddleMan.getX(), mBoardLogic.CheckColumn(inMiddleMan.getX()));
						/*if (mBoardLogic.ChipCheck(inMiddleMan.getX(), mBoardLogic.CheckColumn(inMiddleMan.getX()), playerTurn % 2)) {
							kickPlayers = false;
						}*/
						mBoardLogic.IncrementPlayerTurn();
						playerTurn++;
						

						/*System.out.println("Sending updated data...");
						
						objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
						objectToClient.flush();
						objectToClient.reset();
						System.out.println("Sent!");
						*/
					}
					if(mBoardLogic.GetPlayerTurn() == 0) {
						//System.out.println("Sending updated data...");
						
						objectToClient.writeObject(new MiddleMan(mBoardLogic.GetDrawMap(), mBoardLogic.GetPlayerTurn(), this.player));
						objectToClient.flush();
						objectToClient.reset();
						//System.out.println("Sent!");
						//System.out.println("Thread now sleeping...");
						Thread.sleep(2000);

					}
				}
				


			}
			

		} catch (Exception e) {
			if (player == 1) {
				System.out.println("Player 1 has left the game...");
			} else if (player == 2) {
				System.out.println("Player 2 has left the game...");
			}

			if (clientCount == 1) {
				kickPlayers = false;
				mBoardLogic.resetBoard();
				playerTurn = 0;
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
