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

/* IMPORTANT LINK FOR INFO: http://zetcode.com/tutorials/javagamestutorial/ 
*/

public class Connect4TimerExample extends JFrame {

    private int typeOfGame4Timer;

    public Connect4TimerExample(int gameType) {

        typeOfGame4Timer = gameType;
        
        initUI();
    }
    
    private void initUI() {

        add(new Board(typeOfGame4Timer));           // Add gameboard class created below which implements draw function and timer for moving object.
        
        setResizable(false);
        pack();
        
        setTitle("Connect Four");
        setLocationRelativeTo(null);        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Board extends JPanel
        implements ActionListener {         // Example already implemented a action listener in the signature, therefore you have to overload the ActionPerformed task.

    //Game Type
        // 0 - Pvp hotseat
        // 1 - Pvc easy
        // 2 - Pvc medium
		// 3 - Pvc hard
    private int typeOfGame;
    private ComputerPlayer computerPlayer;


    private final int B_WIDTH = 890;        // Realy peculiar dimensions, but needed to create perfect alignment between chip and gameboard.
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
    private int rowComputerPlay, columnComputerPlay;
    
    private BoardLogic mBoardLogic = new BoardLogic();
    private boolean takeInput;
    private boolean animationOccuring;
    private int columnClicked, columnHeight;

    private boolean gameOver;
    
    public Board(int gameType) {
    	drawMap = mBoardLogic.GetDrawMap();
        initBoard();

        typeOfGame = gameType;
        computerPlayer = new ComputerPlayer("RAMy");
        gameOver = false;
                
        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                if (takeInput && !animationOccuring) {
                	if (e.getX() >= 0 && e.getX() <= 157) {
            			columnClicked = 0;
            	    }  
            	    else if (e.getX() >= 158 && e.getX() <= 271) {
            	    	columnClicked = 1;
            	    }
            	    else if (e.getX() >= 272 && e.getX() <= 386) {
            	    	columnClicked = 2;
            	    }
            	    else if (e.getX() >= 387 && e.getX() <= 503) {
            	    	columnClicked = 3;
            	    }
            	    else if (e.getX() >= 504 && e.getX() <= 616) {
            	    	columnClicked = 4;
            	    }
            	    else if (e.getX() >= 617 && e.getX() <= 732) {
            	    	columnClicked = 5;
            	    }
            	    else if (e.getX() >= 733 && e.getX() <= 890) {
            	    	columnClicked = 6;
            	    }
                    System.out.println("Mouse clicked column " + columnClicked);
                    System.out.println(mBoardLogic.CheckColumn(columnClicked));
                    columnHeight = mBoardLogic.CheckColumn(columnClicked);
                    if (columnHeight == -1) {
                    	columnHeight = mBoardLogic.CheckColumn(columnClicked);
                    } else {
                    	chipX = 50 + (columnClicked * 100) + (columnClicked * 15);
                        animationOccuring = true;
                    }
                    
                    
                }
                revalidate();
              }
        });
        addMouseMotionListener(new MouseAdapter() {
        	public void mouseMoved(MouseEvent e) {
        		

        		if (animationOccuring == false && takeInput) {
        			if (e.getX() >= 0 && e.getX() <= 157) {
            			chipX = 50 + (0 * 100) + (0 * 15);
            	    }  
            	    else if (e.getX() >= 158 && e.getX() <= 271) {
            	    	chipX = 50 + (1 * 100) + (1 * 15);
            	    }
            	    else if (e.getX() >= 272 && e.getX() <= 386) {
            	    	chipX = 50 + (2 * 100) + (2 * 15);
            	    }
            	    else if (e.getX() >= 387 && e.getX() <= 503) {
            	    	chipX = 50 + (3 * 100) + (3 * 15);
            	    }
            	    else if (e.getX() >= 504 && e.getX() <= 616) {
            	    	chipX = 50 + (4 * 100) + (4 * 15);
            	    }
            	    else if (e.getX() >= 617 && e.getX() <= 732) {
            	    	chipX = 50 + (5 * 100) + (5 * 15);
            	    }
            	    else if (e.getX() >= 733 && e.getX() <= 890) {
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
        playerTurn = 0;
        takeInput = true;
        animationOccuring = false;
        
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
        
        
        if (playerTurn % 2 == 0) {
        	chipPicture = redChip.getImage();
        }
        if (playerTurn % 2 == 1) {
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

        // Computer Turns

        // If its a game against an easy computer player and the game isn't over or a chip is droped
        if (typeOfGame == 1 && playerTurn % 2 == 1 && !gameOver && !animationOccuring) {

            columnComputerPlay = computerPlayer.easyTurn(mBoardLogic.getChips());
            rowComputerPlay = mBoardLogic.CheckColumn(columnComputerPlay);
            System.out.println(rowComputerPlay);

            chipX = 50 + (columnComputerPlay * 100) + (columnComputerPlay * 15);
            animationOccuring = true;    
        }


        // If its a game against a medium computer player and the game isn't over or a chip is droped
        if (typeOfGame == 2 && playerTurn % 2 == 1 && !gameOver && !animationOccuring) {

            columnComputerPlay = computerPlayer.mediumTurn(mBoardLogic.getChips());
            rowComputerPlay = mBoardLogic.CheckColumn(columnComputerPlay);
            System.out.println(rowComputerPlay);

            chipX = 50 + (columnComputerPlay * 100) + (columnComputerPlay * 15);
            animationOccuring = true;    
        }


        // If its a game against a hard computer player and the game isn't over or a chip is dropped
        if (typeOfGame == 3 && playerTurn % 2 == 1 && !gameOver && !animationOccuring) {

            columnComputerPlay = computerPlayer.hardTurn(mBoardLogic.getChips());
            rowComputerPlay = mBoardLogic.CheckColumn(columnComputerPlay);
            System.out.println(rowComputerPlay);

            chipX = 50 + (columnComputerPlay * 100) + (columnComputerPlay * 15);
            animationOccuring = true;    
        }
    	

    	if (animationOccuring) {
    		if (playerTurn % 2 == 0) {
    			if (chipY < 18 + (columnHeight * 100) + (15 * columnHeight) + 100 + 30) {
                	chipY += 10;
                } else {
                	
                    	if (columnHeight > -1) {
                        	mBoardLogic.PlaceChip(playerTurn%2, columnClicked, columnHeight);
                        	playerTurn++;
                        }
                    
                	animationOccuring = false;
                	chipY = 18;

                    if (mBoardLogic.ChipCheck(columnClicked, columnHeight, ((playerTurn-1)%2)) && animationOccuring == false) {
                        System.out.println("You Win Called");
                        gameOver = true;
                        takeInput = false;
                        YouWin youWin = new YouWin(mBoardLogic, this);
                    }
                }
    		} else if (playerTurn % 2 == 1) {
    			if (typeOfGame == 3 || typeOfGame == 2 || typeOfGame == 1) {
    				if (chipY < 18 + (rowComputerPlay * 100) + (15 * rowComputerPlay) + 100 + 30) {
                    	chipY += 10;
                    } else {
                    	
                        	if (rowComputerPlay > -1) {
                            	mBoardLogic.PlaceChip(playerTurn%2, columnComputerPlay, rowComputerPlay);
                            	playerTurn++;
                            }
                        
                    	animationOccuring = false;
                    	chipY = 18;

                        if (mBoardLogic.ChipCheck(columnComputerPlay, rowComputerPlay, ((playerTurn-1)%2)) && animationOccuring == false) {
                            System.out.println("You Lose Called");
                            takeInput = false;
                            gameOver = true;
                            YouLose youLose = new YouLose(mBoardLogic, this);
                        }

                    }
    			} else if (typeOfGame == 0) {
    				if (chipY < 18 + (columnHeight * 100) + (15 * columnHeight) + 100 + 30) {
                    	chipY += 10;
                    } else {
                    	
                        	if (columnHeight > -1) {
                            	mBoardLogic.PlaceChip(playerTurn%2, columnClicked, columnHeight);
                            	playerTurn++;
                            }
                        
                    	animationOccuring = false;
                    	chipY = 18;

                        if (mBoardLogic.ChipCheck(columnClicked, columnHeight, ((playerTurn-1)%2)) && animationOccuring == false) {
                            System.out.println("You Win Called");
                            gameOver = true;
                            takeInput = false;
                            YouWin youWin = new YouWin(mBoardLogic, this);
                        }
                    }
    			}
    			
    		}

    		
    	} else {
    		chipY = 18;
    	}

    	if (mBoardLogic.WhoWon(playerTurn) == 3) {
    		takeInput = false;
    		TieGame tieGame = new TieGame(mBoardLogic, this);
    	}
    	validate();
        repaint();
    }
    public void resetGame() {
        gameOver = false;
        takeInput = true;
        playerTurn = 0;
    }
}