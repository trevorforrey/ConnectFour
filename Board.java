import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*

public class Board implements JFrame{
  
  
  private boolean mConnectFour;
  private int mChipCount;
  private int mTurnCount;
  private boolean mPlayerTurn;
  private boolean mIsPlaying;


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
    private void SetChip(int playerTurn) {
      if (playerTurn == 1) {
        Chips[/**rowPicked**/][/**columnPicked**/] = new Player1Chip();
      } else {
        Chips[/**rowPicked**/][/**columnPicked**/] = new Player2Chip();
      }
    }


    // Sets recently placed chip's visibility to true
    private void PlaceChip(int playerTurn) {
      
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


    private boolean ChipCheck(int positionX, int positionY) {

    }

    // Handles the animation of a revently placed chip
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
