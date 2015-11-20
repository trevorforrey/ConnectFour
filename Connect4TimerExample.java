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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/* IMPORTANT LINK FOR INFO: http://zetcode.com/tutorials/javagamestutorial/ 
*/

public class Connect4TimerExample extends JFrame {


    public Connect4TimerExample() {
        
        initUI();
    }
    
    private void initUI() {

        add(new Board());           // Add gameboard class created below which implements draw function and timer for moving object.
        
        setResizable(false);
        pack();
        
        setTitle("Connect Four");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                JFrame ex = new Connect4TimerExample();
                ex.setVisible(true);                
            }
        });
    }
}

class Board extends JPanel
        implements ActionListener {         // Example already implemented a action listener in the signature, therefore you have to overload the ActionPerformed task.

    private final int B_WIDTH = 890;        // Realy peculiar dimensions, but needed to create perfect alignment between chip and gameboard.
    private final int B_HEIGHT = 750;
    private final int INITIAL_X = 36;       // INITIAL X for the gameboard.
    private final int INITIAL_Y = 4;        // INboardTAL Y for the gameboard.
    private final int DELAY = 25;           // Time delay for response to animation rendering.

    private Image backgroundGameBoard;      // Create variable type of image for background gameboard.
    private Image chipPicture;              // Create chip image variable for animated chip.
    private Timer timer;
    private int x, y;                       // x & y coordinates for moving the picture animation.


    public Board() {

        initBoard();
        
    }

    private void loadImage() {

        ImageIcon board = new ImageIcon("Assets/Board.png");       // Change directory depending on your root file.
        ImageIcon redChip = new ImageIcon("Assets/RedChip.png");
        backgroundGameBoard = board.getImage();
        chipPicture = redChip.getImage();
        this.addMouseListener(new BoardMouseListener());
    }

    private void initBoard() {

        //setBackground(Color.BLACK);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        loadImage();                    // Calls to loadImage method which derives the gameboard and chip pictures.
        
        x = INITIAL_X;                  // Calls to the constant initialization for gameboard.
        y = INITIAL_Y;
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {        // Paint our pictures onto the panel.   
        super.paintComponent(g);                    // A call to the super, states the requirements in the API but cannot remember at this time.
                                                    // Keep in mind I just discovered my previous issue and its 12 at night haha.
        drawBoard(g);
    }

    private void drawBoard(Graphics g) {

        g.drawImage(backgroundGameBoard, x, y, this);   // Render image onto the gameboard, notice we call to this method in the paintcomponent method.
        g.drawImage(chipPicture,50,18,this);            // Specifics again, those are the required coordinates for the chip to be initialized, we will
        Toolkit.getDefaultToolkit().sync();         // have to add 30 to the x across to consult with column, 30 for row as well.
    }


    class BoardMouseListener implements MouseListener{

      // Method that takes the x position of where the user clicked, translates that to the column they clicked in
      // Calls all running/logical functions that deal with a chip being placed on the board
      public void mouseClicked(MouseEvent e) {

        int row = 0;
        int columnClicked = 0;

        System.out.print("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");

        // If player clicked in 1st column
        if (e.getX() >= 51 && e.getX() <= 149) {

          // Assigns values for the column and row the chip will be placed
          columnClicked = 0;
          //row = CheckColumn(columnClicked);


          // Logical sequence of functions that will be called to handle chip animation and creation

          //SetChip(int playerTurn, int column, int row);
          //DropChip();
          //PlaceChip(int playerTurn, int column, int row);


          System.out.println("Column 1 clicked");
        }


        // If player clicked in 2nd column
        else if (e.getX() >= 164 && e.getX() <= 264) {

          columnClicked = 1;
          //row = CheckColumn(columnClicked);
          
          System.out.println("Column 2 clicked");


          // Logical sequence of functions that will be called to handle chip animation and creation

          //SetChip(int playerTurn, int column, int row);
          //DropChip();
          //PlaceChip(int playerTurn, int column, int row);
        }
 
        // If player clicked in 3rd column
        else if (e.getX() >= 279 && e.getX() <= 379) {

          columnClicked = 2;
          //row = CheckColumn(columnClicked);
          
          System.out.println("Column 3 clicked");


          // Logical sequence of functions that will be called to handle chip animation and creation

          //SetChip(int playerTurn, int column, int row);
          //DropChip();
          //PlaceChip(int playerTurn, int column, int row);
        }


        // If player clicked in 4th column
        else if (e.getX() >= 394 && e.getX() <= 494) {

          columnClicked = 3;
          //row = CheckColumn(columnClicked);
          
          System.out.println("Column 4 clicked");


          // Logical sequence of functions that will be called to handle chip animation and creation

          //SetChip(int playerTurn, int column, int row);
          //DropChip();
          //PlaceChip(int playerTurn, int column, int row);
        }


        // If player clicked in 5th column
        else if (e.getX() >= 509 && e.getX() <= 609) {

          columnClicked = 4;
          //row = CheckColumn(columnClicked);
          
          System.out.println("Column 5 clicked");


          // Logical sequence of functions that will be called to handle chip animation and creation

          //SetChip(int playerTurn, int column, int row);
          //DropChip();
          //PlaceChip(int playerTurn, int column, int row);
        }


        // If player clicked in 6th column
        else if (e.getX() >= 625 && e.getX() <= 725) {

          columnClicked = 5;
          //row = CheckColumn(columnClicked);
          
          System.out.println("Column 6 clicked");


          // Logical sequence of functions that will be called to handle chip animation and creation

          //SetChip(int playerTurn, int column, int row);
          //DropChip();
          //PlaceChip(int playerTurn, int column, int row);
        }


        // If player clicked in 7th column
        else if (e.getX() >= 748 && e.getX() <= 848) {

          columnClicked = 6;
          //row = CheckColumn(columnClicked);
          
          System.out.println("Column 7 clicked");


          // Logical sequence of functions that will be called to handle chip animation and creation

          //SetChip(int playerTurn, int column, int row);
          //DropChip();
          //PlaceChip(int playerTurn, int column, int row);
        }


      }

      public void mousePressed(MouseEvent e) {
      }

      public void mouseReleased(MouseEvent e) {
      }

      // Logic to show the "shadow" chip of where the player's chip will fall if they click in the column
      public void mouseEntered(MouseEvent e) {
      }

      public void mouseExited(MouseEvent e) {
      }
    }



    @Override
    public void actionPerformed(ActionEvent e) {    // Need to implement the action performed to suit our needs.

        /*x += 1;
        y += 1;

        if (y > B_HEIGHT) {

            y = INITIAL_Y;
            x = INITIAL_X;
        }

        repaint();*/
    }
}