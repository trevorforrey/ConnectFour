public class MiddleMan {
  private int [][] drawMap;   // Private variables so they cannot be interfered with outside of scope.
  private int x, y;           // Variables that will be used for the accessor and mutator methods in regards to x&y.

  // Overloaded Constructors
  // -Basic Constructor
  // -From server
  // -From Client
  public MiddleMan() {

    for (int i = 0; i < 6; i++) {       // Set multidimensional array to 0
      for (int j = 0; j < 7; j++) {     // for standard constructor
        drawMap[i][j] = 0;              //
      }
    }
    x = 0;
    y = 0;

  }

  public MiddleMan(int[][] drawMap, int x, int y) {    // Using multidimensional array for storing chips.
    this.drawMap = drawMap;             // Setting program drawMap array equal to user given drawMap array for each change made from client to server.
    this.x = x;
    this.y = y;
  }

  public MiddleMan(int x) {
    MiddleMan();
    this.x = x;
  }

  public int[][] getDrawMap() {     // Retrieving the current gameboard with all the chips in place.
    return drawMap;                 // Return the array with all the proper assigned values for chips placed.
  }
  public int getX() {   // Accessor method for x coordinate on the grid.
    return x;
  }
  public int getY() {   // Accessor method for y coordinate on the grid.
    return y;
  }


}
