import java.io.Serializable;
import java.util.*;

public class MiddleMan implements Serializable {
  private int [][] drawMap;   // Private variables so they cannot be interfered with outside of scope.
  private int x, y;           // Variables that will be used for the accessor and mutator methods in regards to x&y.
  private int playerTurn;
  private int player;
  // Overloaded Constructors
  // -Basic Constructor
  // -From server
  // -From Client
  

  public MiddleMan(int[][] drawMap, int playerTurn, int player) {    // Using multidimensional array for storing chips.
    System.out.println("this constructor was called");

    this.drawMap = drawMap;             // Setting program drawMap array equal to user given drawMap array for each change made from client to server.
    this.x = 0;
    this.y = 0;
    this.playerTurn = playerTurn;
    this.player = player;
  }
  

  public MiddleMan(int x, int player) {
    
    this.y = 0;
    this.x = x;
    this.playerTurn = 0;
    this.player = player;
  }

  public int[][] getDrawMap() {     // Retrieving the current gameboard with all the chips in place.
    return this.drawMap;                 // Return the array with all the proper assigned values for chips placed.
  }
  public int getX() {   // Accessor method for x coordinate on the grid.
    return this.x;
  }
  public int getY() {   // Accessor method for y coordinate on the grid.
    return this.y;
  }
  public int getPlayerTurn() {
    return this.playerTurn;
  }
  public int getPlayer() {
    return this.player;
  }


}
