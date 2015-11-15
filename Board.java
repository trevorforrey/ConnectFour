import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*

// Currently holds all game logic (non gui components of the board)
public class Board implements JFrame{
  
  
  private boolean mConnectFour;
  private int mChipCount;
  private int mTurnCount;
  private boolean mPlayerTurn;
  private boolean mIsPlaying;
  private int mXPositionOfMouse;
  private int mYPositionOfMouse;


  private int[] mColumnMap;
  private Chip[][] mChips;

  public Board() {
    
    this.mIsPlaying = true;
    this.mConnectFour = false;
    this.mChipCount = 0;
    this.mTurnCount = 0;
    this.mPlayerTurn = true;
    this.mColumnMap = new int[6];
    this.mChips = new Chip[6][7];
    this.mXPositionOfMouse = 0;
    this.mYPositionOfMouse = 0;
    
    // Fills column map with 0's
    for (int i = 0; i < 6; i++) {
      this.mColumnMap[i] = 0;
    }
    
    // Fills chip array (6 by 7) with anonymous Chip objects
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        this.mChips[i][j] = new Chip;
      }
    }


    public void Begin() {
      while (mIsPlaying) {
        update();
        render();
      }
    }


    // Calls chip constructor for either player1 or player2 chip
    
    private void SetChip(int playerTurn, int x, int y) {
      playerTurn = playerTurn % 2;
      if (playerTurn == 0) {
        Chips[x][y] = new Player1Chip();
      } else if (playerTurn == 1) {
        Chips[x][y] = new Player2Chip();
      }
    }


    // Sets recently placed chip's visibility to true
    private void PlaceChip(int playerTurn, int x, int y) {
      Chips[x][y].setVisible(true);
    }


    // Checks a column of the board and returns the amount of empty spaces in the column
    private int CheckColumn(int col) {
      int tempCount = 6;
      for (int i = 6; i > 0; i--) {
        if (Chips[i][col].isVisible() == true) {
          this.mColumnMap[i] = 1;
          tempCount -= 1;
        } else {
          break;
        }
      }
      return tempCount;
    }

    private boolean CheckVertical(int playerTurn, int x, int y) {
      int tempX = x;
      int tempY = y;
      int tempCount = 0;
      int player = 0;
      
      //Determine who's chip is being checked
      if (Player1Chip.class.equals(Chips[y][x].getClass()) {
        player = 1;
      } else if (Player2Chip.class.equals(Chips[y][x].getClass()) {
        player = 2;
      } else {
        player = 0;
      }
      
      //if the chip is at the top
      if (y == 0) {
        for (int i = 1; i < 6; i++) {
          if (Chips[i][tempX].getVisible() && Chips[i][tempX].whosChip() == player) {
            tempCount++;
          } else if (count > 3) {
            return true;
          } else {
            return false;
          }
        }
      }
      //if the chip is at the bottom
      else if (y == 5) {
        for (int i = 4; i >= 0; i--) {
          if (Chips[i][tempX].getVisible() && Chips[i][tempX].whosChip() == player) {
            tempCount++;
          } else if (tempCount > 3) {
            return true;
          } else {
            return false;
          }
        }
      }
      //if the chip is not at the edge of the board
      else if (y > 1 && y < 4) {
        // 0  
        // -
        // 0 - [chip to check from], top is a match and bot is not
        // -
        // X
        if ((Chips[tempY - 1][tempX].getVisible() && Chips[tempY - 1][tempX].whosChip == player) && 
          (!Chips[tempY + 1][tempX].getVisible() || Chips[tempY + 1][tempX].whosChip != player)) {
            for (int i = tempY - 1; i >= 0; i--) {
              if (Chips[i][tempX].getVisible() && Chips[i][tempX].whosChip == player) {
                tempCount++;
              } else if (tempCount == 3) {
                return true;
              } else {
                return false;
              }
            }
          }
        // X  
        // -
        // 0 - [chip to check from], bot is a match and top is not
        // -
        // 0
        else if ((Chips[tempY + 1][tempX].getVisible() && Chips[tempY + 1][tempX].whosChip == player) && 
          (!Chips[tempY - 1][tempX].getVisible() || Chips[tempY - 1][tempX].whosChip != player)) {
            for (int i = tempY + 1; i < 6; i++) {
              if (Chips[i][tempX].getVisible() && Chips[i][tempX].whosChip == player) {
                tempCount++;
              } else if (tempCount == 3) {
                return true;
              } else {
                return false;
              }
            }
          }
        // 0
        // -
        // 0 - [chip to check from], bot and top are a match
        // -
        // 0
        else {
          
        }
      }
    }
    private boolean CheckHorizontal(int x, int y) {
      int tempX = x;
      int tempY = y;
    }
    private boolean CheckDiagonal(int x, int y) {
      int tempX = x;
      int tempY = y;
    }

    private boolean ChipCheck(int x, int y) {
      
    }

    // Handles the animation of a recently placed chip
    private void DropChip() {

    }

    // Handles each players turn
    /** Will need to take in position of player's mouse when they click
     * Find lowest empty chip position in the column they clicked
     * Place, Set, and Drop their specific chip
    **/
    private void PlayerTurn(int playerTurn) {

    }


    private void render() {

    }
    
    
    private void update() {

    }
    
    
    // Basic Board Frame attributes
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
    setTitle("Connect Four"); // Sets title
    setSize(/**boardsize**/);         // Sets initial size
    setVisible(true)
    
    
  }
}
