import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;

/* IMPORTANT LINK FOR INFO: http://zetcode.com/tutorials/javagamestutorial/ 
*/

public class Client extends JFrame {
    private static int portNum;
    private static String ipAdd;
    public Client(int port, String ip) {

        portNum = port;
        ipAdd = ip;
        initUI();
    }
    
    private void initUI() {

        add(new Board(portNum, ipAdd));           // Add gameboard class created below which implements draw function and timer for moving object.
        
        setResizable(false);
        pack();
        
        setTitle("Connect Four");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /*public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {            
                JFrame ex = new Client(8000, "192.168.1.127");
                ex.setVisible(true);                
            }
        });
        
    }*/

}

class Board extends JPanel
        implements ActionListener {         // Example already implemented a action listener in the signature, therefore you have to overload the ActionPerformed task.

  


    private final int B_WIDTH = 1190;        // Realy peculiar dimensions, but needed to create perfect alignment between chip and gameboard.
    private final int B_HEIGHT = 880;
    private final int INITIAL_X = 36;       // INITIAL X for the gameboard.
    private final int INITIAL_Y = 134;        // INboardTAL Y for the gameboard.
    private final int DELAY = 25;           // Time delay for response to animation rendering.

    private Image backgroundGameBoard;      // Create variable type of image for background gameboard.
    private Image chipPicture;              // Create chip image variable for animated chip.
    private Image chipSetPicture;
    private ImageIcon board, redChip, yellowChip;
    private Timer timer;
    private int x, y;                       // x & y coordinates for moving the picture animation.
    private int chipX, chipY;
    private int[][] drawMap;
    private int playerTurn;
    
    private boolean takeInput;
    private boolean animationOccuring;
    private int columnClicked, columnHeight;
    private boolean needUpdate;
    private boolean canPlace;

    private int player;

    private String host;
    private int portNumber;
    private ObjectOutputStream objectToServer = null;
    private ObjectInputStream objectFromServer = null;

    private DataOutputStream dataToServer = null;
    private DataInputStream dataFromServer = null;
    private MiddleMan inMiddleMan = null;
    private Socket socket;
    
    public Board(int port, String ip) {
        initBoard();
        player = 0 ;
        host = ip;
        portNumber = port;
        needUpdate = false;
        canPlace = true;
        
        try {
            socket = new Socket(host, portNumber);
              
            dataToServer = new DataOutputStream(socket.getOutputStream());
            dataToServer.flush();
            dataFromServer = new DataInputStream(socket.getInputStream());

            System.out.println("connected");
            System.out.println(dataFromServer.readUTF());

            objectToServer = new ObjectOutputStream(socket.getOutputStream());
            objectToServer.flush();
            objectFromServer = new ObjectInputStream(socket.getInputStream());
            
            
            inMiddleMan = (MiddleMan) objectFromServer.readObject();
            drawMap = inMiddleMan.getDrawMap();
            player = inMiddleMan.getPlayer();
            playerTurn = inMiddleMan.getPlayerTurn();

            System.out.println("You are player: " + player);

            if (player == 1) {
                takeInput = true;
                System.out.println("Taking input from player one");
            }
            if (player == 2) {
                takeInput = false;
                System.out.println("Waiting on player one");
            }
            
            
      
          
          
          
        } catch (IOException ex) {
            System.err.println(ex);
        } catch(ClassNotFoundException ec) {
            System.err.println(ec);
        }
                
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (playerTurn + 1 == player) {


                    if (takeInput && !animationOccuring) {
                        if (e.getX() >= 0 && e.getX() <= 157) {          // X is in row 1.
                            columnClicked = 0;
                        }  
                        else if (e.getX() >= 158 && e.getX() <= 271) {   // X is in row 2.
                            columnClicked = 1;
                        }
                        else if (e.getX() >= 272 && e.getX() <= 386) {   // X is in row 3.
                            columnClicked = 2;
                        }
                        else if (e.getX() >= 387 && e.getX() <= 503) {   // X is in row 4.
                            columnClicked = 3;
                        }
                        else if (e.getX() >= 504 && e.getX() <= 616) {   // X is in row 5.
                            columnClicked = 4;
                        }
                        else if (e.getX() >= 617 && e.getX() <= 732) {   // X is in row 6.
                            columnClicked = 5;
                        }
                        else if (e.getX() >= 733 && e.getX() <= 890) {   // X is in row 7.
                            columnClicked = 6;
                        }
                        System.out.println("Mouse clicked column " + columnClicked);
                        //Need to add client side column height check function
                        //columnHeight = mBoardLogic.CheckColumn(columnClicked);         // Calling to the CheckColumn method within the BoardLogic, which returns the amount of empty spaces we have.
                        int count = 5;
                        for (int i = 5; i > -1; i--) {
                            if (drawMap[i][columnClicked] == 1 || drawMap[i][columnClicked] == 2) {
                                count -= 1;
                            } else {
                                break;
                            }
                        }
                        if (count == -1) {
                            System.out.println("Cannot place at selected position.");
                        } else {
                            try {
                                MiddleMan outMiddleMan = new MiddleMan(columnClicked, player);
                                objectToServer.writeObject(outMiddleMan);
                                objectToServer.flush();
                                //dataToServer.writeInt(columnClicked);
                                //dataToServer.flush();
                                takeInput = false;
                                needUpdate = true;
                                
                            } catch (IOException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                }
                revalidate();
            }
        });
        addMouseMotionListener(new MouseAdapter() {                               // Overriding the mouseMotionListener for sake of program.
          public void mouseMoved(MouseEvent e) {
            
//*****************


            if (animationOccuring == false && takeInput) {
              if (e.getX() >= 0 && e.getX() <= 157) {                           // X is in row 1.
                  chipX = 50 + (0 * 100) + (0 * 15);
                  }  
                  else if (e.getX() >= 158 && e.getX() <= 271) {                // X is in row 2.
                    chipX = 50 + (1 * 100) + (1 * 15);
                  }
                  else if (e.getX() >= 272 && e.getX() <= 386) {                // X is in row 3.
                    chipX = 50 + (2 * 100) + (2 * 15);
                  }
                  else if (e.getX() >= 387 && e.getX() <= 503) {                // X is in row 4.
                    chipX = 50 + (3 * 100) + (3 * 15);
                  }
                  else if (e.getX() >= 504 && e.getX() <= 616) {                // X is in row 5.
                    chipX = 50 + (4 * 100) + (4 * 15);
                  }
                  else if (e.getX() >= 617 && e.getX() <= 732) {                // X is in row 6.
                    chipX = 50 + (5 * 100) + (5 * 15);
                  }
                  else if (e.getX() >= 733 && e.getX() <= 890) {                // X is in row 7.
                    chipX = 50 + (6 * 100) + (6 * 15);
                  }
            }

            

            revalidate();
          }
        });
        
    }

    private void loadImage() {

        board = new ImageIcon(getClass().getResource("Assets/Board.png"));       // Change directory depending on your root file.
        redChip = new ImageIcon(getClass().getResource("Assets/RedChip.png"));
        yellowChip = new ImageIcon(getClass().getResource("Assets/YellowChip.png"));
        backgroundGameBoard = board.getImage();
        chipPicture = redChip.getImage();
        chipSetPicture = chipPicture;
        
    }

    private void initBoard() {

        //setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        loadImage();                    // Calls to loadImage method which derives the gameboard and chip pictures.
        
        
        x = INITIAL_X;                  // Calls to the constant initialization for gameboard.
        y = INITIAL_Y;
        
        chipX = 50;
        chipY = 18;
        
        columnClicked = 0;
        takeInput = true;
        animationOccuring = false;
        
        timer = new Timer(DELAY, this);
        timer.start();
        //timer.start();
        //this.addMouseListener(new BoardMouseListener(mBoardLogic));
        //this.addMouseMotionListener(new BoardMouseListener(mBoardLogic));
    }

    @Override
    public void paintComponent(Graphics g) {        // Paint our pictures onto the panel.   
        super.paintComponent(g);                    // A call to the super, states the requirements in the API but cannot remember at this time.
                                                    // Keep in mind I just discovered my previous issue and its 12 at night haha.
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {
        
        
        if (player == 1) {
          chipPicture = redChip.getImage();
        }
        if (player == 2) {
          chipPicture = yellowChip.getImage();
        }
        g.drawImage(chipPicture,chipX, chipY,this);
        for (int i = 0; i < 6; i++) {
          for (int j = 0; j < 7; j++) {
            if (drawMap[i][j] == 1) {
              chipSetPicture = redChip.getImage();
              g.drawImage(chipSetPicture,50 + (j * 100) + (j * 15),148 + (i * 100) + (i * 15),this);
            } else if (drawMap[i][j] == 2) {
              chipSetPicture = yellowChip.getImage();
              g.drawImage(chipSetPicture,50 + (j * 100) + (j * 15),148 + (i * 100) + (i * 15),this);
            }
          }
        }
        g.drawImage(backgroundGameBoard, x, y, this);// Render image onto the gameboard, notice we call to this method in the paintcomponent method.
        //g.drawImage(chipPicture,chipX,chipY,this);            // Specifics again, those are the required coordinates for the chip to be initialized, we will
        Toolkit.getDefaultToolkit().sync();         // have to add 30 to the x across to consult with column, 30 for row as well.
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {    // Need to implement the action performed to suit our needs.
    
    /*try {
      MiddleMan inMiddleMan = (MiddleMan)fromServer.readObject();
      drawMap = inMiddleMan.getDrawMap();
      playerTurn = inMiddleMan.getPlayerTurn();
      inMiddleMan = null;
    } catch (IOException ex | ClassNotFoundException(s, ex)) {
      System.err.println(ex);
    }*/
    /*try {
        MiddleMan inMiddleMan = (MiddleMan) objectFromServer.readObject();
        drawMap = inMiddleMan.getDrawMap();
        player = inMiddleMan.getPlayer();
        playerTurn = inMiddleMan.getPlayerTurn();
    } catch (IOException ex) {
        System.out.println(ex);
    } catch (ClassNotFoundException ec) {

    }*/
    
    if (needUpdate) {
        System.out.println("Need update Called");
        try {
            inMiddleMan = (MiddleMan) objectFromServer.readObject();
            this.drawMap = inMiddleMan.getDrawMap();
            
            if (playerTurn == inMiddleMan.getPlayerTurn()) {
                if (playerTurn == 0) {
                    playerTurn = 1;
                } else if (playerTurn == 1) {
                    playerTurn = 0;
                }
            } else {
                playerTurn = inMiddleMan.getPlayerTurn();
            }
            
            System.out.println("Player turn = " + playerTurn);
            
            System.out.println("Retrieved the junk");
            if (playerTurn +1  == player) {
                takeInput = true;
            }
            needUpdate = false;
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ec) {
            System.out.println(ec);
        }
        
    }
    if ((playerTurn + 1 != player )) {
        System.out.println("Continuous Update Called on Client");
        try {
            inMiddleMan = (MiddleMan) objectFromServer.readObject();
            this.drawMap = inMiddleMan.getDrawMap();
            
            playerTurn = inMiddleMan.getPlayerTurn();
            
            System.out.println("Retrieved the junk");
            if (playerTurn + 1 == player) {
                takeInput = true;
            }
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ec) {
            System.out.println(ec);
        }
        
    }


    if (animationOccuring) {
        if (playerTurn % 2 == 0) {
            if (chipY < 18 + (columnHeight * 100) + (15 * columnHeight) + 100 + 30) {
                chipY += 10;
            } else {      
                if (columnHeight > -1) {

                }
                animationOccuring = false;
                chipY = 18;        
            }
        }
    } else {
        chipY = 18;
    }

      
    validate();
    repaint();
    }

    



}