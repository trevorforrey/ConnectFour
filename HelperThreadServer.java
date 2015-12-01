public class HelperThreadServer extends Thread {

    private Socket socket = null;
    
    private ObjectInputStream serverInputStream;
    private ObjectOutputStream serverOutputStream;
    
    private MiddleMan inMiddleMan;

    public HelperThreadServer(Socket socket) {
        serverInputStream = new ObjectInputStream(socket.getInputStream());
        new ObjectOutputStream(socket.getOutputStream());
        this.socket = socket;

    }

    public void run(){
        //Read input and process here
        
            
    }
    // Needs to gather all information client to push to the server, accessor methods to update the board. 
            //implement your methods here





}