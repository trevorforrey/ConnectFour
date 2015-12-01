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



      DataInputStream inputFromClient;
      DataOutputStream outputToClient;
      ServerSocket serverSocket;
      Socket connectionToTheServer, connectionToClient;
      int port = 8000;

      // Create a server socket
      serverSocket = new ServerSocket(port);   // The default port we will be listening for to establish a 'accept' connection between s & client.
      jta.append("Server started at " + new Date() + '\n'); // Calls date function to state when connection was made, this is just part of the SER215 notes example however.

      // Connecting our client would be done this way:
      //connectionToTheServer = new Socket("192.168.1.109" /* localhost*/, 8000);

      // Listen for a connection request
      connectionToClient = serverSocket.accept();  // This is the most crucial part of the program, it listens to clients trying to connect to
                                                          // establish our connection and make a new socket for hosting that connection, hence "Socket socket".
                                                          // It accepts the original socket establishment, the first socket we create to host connection.

      /* Client side of things will look as follows:
            Client socket
            Socket socket = new Socket(host, 8000).
      */

      // Create an input stream from the socket
      //inputFromClient = new ObjectInputStream(socket.getInputStream());

      // Read from input
      
      // The object creation below would be the example/template for the object created of the actual gameboard, to which it'll render and re-draw 
      // the gameboard by calling to the BoardLogic class which in turn calls to all the other classes and methods to paint componenet's, set board
      // values to true, false, etc.
      // Object object = inputFromClient.readObject();


      // Create data input and output streams
      inputFromClient = new DataInputStream(connectionToClient.getInputStream());     // in is for accepting input to the server to make the outted changes.
      outputToClient = new DataOutputStream(connectionToClient.getOutputStream());    // out is for writing to the server.
        // Basically for every input we get, we clarify that input with inputFromClient.action/input,
        // we output the actions as follows, outputToClient.action/output.  
        // in.readDouble()  out.writeDouble(number).

      while (true) {
        // Could also implement a socket session with threads in the while loop to create multiple threads for multiple connections,
        // but that isn't needed for the sake of this assignment.

        // Maybe will want to run the connection establishments within the true loop, debatable. 


        // Receive radius from the client
        double radius = inputFromClient.readDouble();
        // Compute area
        double area = radius * radius * Math.PI;


        // Send area back to the client
        outputToClient.writeDouble(area);
        jta.append("Radius received from client: " + radius + '\n');
        jta.append("Area found: " + area + '\n');
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
