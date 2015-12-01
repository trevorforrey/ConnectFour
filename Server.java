import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame {

  // private ObjectOutputStream outputToFile, if needed be.
  // private ObjectInputStream inputToFile, if needed be for objects of gameboard/chips.

  // Text area for displaying contents
  private JTextArea jta = new JTextArea();    // Not necessarily necessary.


  public static void main(String[] args) {
    JFrame serverFrame = new Server();
    serverFrame.setVisible(true);
  }

  public Server() {
    /* THIS SECTION COULD BE IMPLEMENTED INTO ANOTHER CLASS, FOR NOW A SEPERATE PANEL IS CREATED TO GATHER THIS INFO FOR THE SAKE OF WRITING THE CLASS. */
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);     // Position the panel in the center, make it scrollable as well.
    setTitle("Server");
    setSize(500, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!



    /*while (true) {
      // Place all the socket connections within here after further implementation to make code more robust.
    }*/


    try {
      // SUPER IMPORTANT, STORING PORT INTO VARIABLE SO IF NEEDED BE CHANGE WE DON'T HAVE TO GO THROUGH EACH SECTION TO CHANGE THE PORT.
      // new ServerSocket(8000, 0, 192.168.1.109)
                          // (designated port, specified backlog, local IP address to bind to for server connection)



      ObjectInputStream inputFromClient;
      ObjectOutputStream outputToClient;
      ServerSocket serverSocket;
      Socket connectionToClient;
      int port = 8000;

      // Create a server socket
      serverSocket = new ServerSocket(port);   // The default port we will be listening for to establish a 'accept' connection between s & client.

      // Connecting our client would be done this way:
      //connectionToTheServer = new Socket("192.168.1.109" /* localhost*/, 8000);

      // Listen for a connection request      
      // Create data input and output streams
      inputFromClient = new ObjectInputStream(connectionToClient.getInputStream());       // in is for accepting input to the server to make the outted changes.
      outputToClient = new ObjectOutputStream(connectionToClient.getOutputStream());     // out is for writing to the server.
        // Basically for every input we get, we clarify that input with inputFromClient.action/input,
        // we output the actions as follows, outputToClient.action/output.  
        // in.readDouble()  out.writeDouble(number).

      HelperThreadServer Player1;
      HelperThreadServer Player2;
      int playersConnected = 0;
      
      while (true) {
        // Could also implement a socket session with threads in the while loop to create multiple threads for multiple connections,
        // but that isn't needed for the sake of this assignment.
        if (playersConnected < 2) {                                 // If playersConnect is less than 2, so a client is connected to server.
          if (playersConnected == 0) {                              // If playersConnected is 0, we have yet to establish connection.  
            Socket clientSocket = serverSocket.accept();            // Thus we accept a client connection through a socket.
            Player1 = new HelperThreadServer(clientSocket);         // Put client into the thread.
          }
          if (playersConnected == 1) {                              // If playersConnected push them into the thread to begin our game.
            Socket clientSocket = serverSocket.accept();            // Accept client into clientSocket, so we can push socket
            Player2 = new HelperThreadServer(clientSocket);         // into our HelperThreadServer as we do here,
          }
          
        }
        if (playersConnected == 2) {              // Begin the Conenct4Game between the 2 clients.
          Player1.start();
          Player2.start();
        }

      }
    }

    /*******************************************************************
    /       If our connection doesn't get established from the trying  /
    /   then in our catch is where we would handle the exceptional     / 
    /   error, for now it is just going to output a failed connection. /
    *******************************************************************/
    
    /*catch(ClassNotFoundException ex) {
      ex.printStackTrace();
      System.exit(1);     // Could take out, just want to finish execution in case program doesn't automatically close with failed connection.
                          // a 0 would establish a good connection, -1, 1 is when something went wrong.
    }*/
    catch(IOException ex) {
      System.err.println(ex);
    }
    // At the end of the program execution sockets will automatically close itself so no need to create closed socket's.
  }
}
