import java.io.*;
import java.net.*;

public class HelperThreadServer extends Thread {

    private Socket socket = null;
    
    ObjectInputStream serverInputStream;
    ObjectOutputStream serverOutputStream;
    
    private MiddleMan inMiddleMan;

    public HelperThreadServer(Socket socket) {
        try {
            this.socket = socket;
            serverInputStream = new ObjectInputStream(socket.getInputStream());
            serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
            
        } catch (IOException ex) {
            System.err.println(ex);
        }
        

    }

    public void run(){
        //Read input and process here
        
            
    }
    // Needs to gather all information client to push to the server, accessor methods to update the board. 
            //implement your methods here





}