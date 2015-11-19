import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*

// Currently holds all game logic (non gui components of the board)
public class Board extends JPanel{
  
  
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


    // Calls chip constructor for either player1 or player2 chip
    
    private void SetChip(int playerTurn, int x, int y) {
      playerTurn = playerTurn % 2;
      if (playerTurn == 0) {
        mChips[x][y] = new Player1Chip();
      } else if (playerTurn == 1) {
        mChips[x][y] = new Player2Chip();
      }
    }


    // Sets recently placed chip's visibility to true
    private void PlaceChip(int playerTurn, int x, int y) {
      mChips[x][y].setVisible(true);
    }


    // Checks a column of the board and returns the amount of empty spaces in the column
    private int CheckColumn(int col) {
      int tempCount = 6;
      for (int i = 6; i > 0; i--) {
        if (mChips[i][col].isVisible() == true) {
          this.mColumnMap[i] = 1;
          tempCount -= 1;
        } else {
          break;
        }
      }
      return tempCount;
    }

    private boolean CheckVertical(int init_X, int init_Y, int playerTurn) {
  		int x = init_X;
  		int y = init_Y;
  		int count = 0;
  		if (y == 0) {
  			System.out.println("Starting Top check...");
  			for (int i = 0; i < 6; i++) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				System.out.println("Success");
  				return true;
  			} else {
  				System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (y == 5) {
  			System.out.println("Startign Bot check...");
  			for (int i = 5; i > -1; i--) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				System.out.println("Success");
  				return true;
  			} else {
  				System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (y > 0 && y < 5) {
  			System.out.println("Starting vertical check not from edge");
  			count += 1;
  			for (int i = y - 1; i > -1; i--) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			for (int i = y + 1; i < 6; i++) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				System.out.println("Success");
  				return true;
  			} else {
  				System.out.println("Failure");
  				return false;
  			}
  		}
  		
  		
  		return false;
  	}
  	private boolean CheckHorizontal(int init_X, int init_Y, int playerTurn) {
  		int x = init_X;
  		int y = init_Y;
  		int count = 0;
  		if (x == 0) {
  			System.out.println("Starting check from left edge...");
  			for (int i = 0; i < 7; i++) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				System.out.println("Success");
  				return true;
  			} else {
  				System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (x == 6) {
  			System.out.println("Starting check from right edge...");
  			for (int i = 6; i > -1; i--) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				System.out.println("Success");
  				return true;
  			} else {
  				System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (x > 0 && x < 6) {
  			System.out.println("Starting horizontal check not from edge...");
  			count += 1;
  			for (int i = x - 1; i > -1; i--) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn ) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			for (int i = x + 1; i < 7; i++) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn) {
  					count++;
  					System.out.println("Count: " + count);
  				} else {
  					System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				System.out.println("Success");
  				return true;
  			} else {
  				System.out.println("Failure");
  				return false;
  			}
  		}
  		
  		
  		return false;
  	}
  	
  	private boolean CheckDiagonalDownUp(int init_X, int init_Y, int playerTurn) {
  		int x = init_X;
  		int y = init_Y;
  		int count = 0;
  		if (x == 0) {
  		  if (y == 0) {
  		    System.out.println("Starting check from top left corner...");
  		    for (int i = 0; i < 6; i++) {
  		      if (mChips[i][i].isVisible() && mChips[i][i].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		    }
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y == 5) {
  		    System.out.println("Starting check from bot left corner...");
  		    int loopCount = 0;
  		    for (int i = 5; i > -1; i--) {
  		      if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y > 0 && y < 5) {
  		    System.out.println("Starting check from left edge...");
  		    int loopCount = 0;
  		    for (int i = y; i > -1; i--) {
  		      if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++
  		    }
  		    loopCount = 0;
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Continuing...");
  		      count = 0;
  		    }
  		    for (int i = y; i < 6; i++) {
  		      if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		}
  		else if (x == 6) {
  		  if (y == 0) {
  		    System.out.println("Starting check from top right corner...");
  		    int loopCount = 0;
  		    for (int i = 0; i < 6; i++) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y == 5) {
  		    System.out.println("Starting check from bot left corner...");
  		    int loopCount = 0;
  		    for (int i = 5; i > -1; i--) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y > 0 && y < 5) {
  		    System.out.println("Starting check from left edge...");
  		    int loopCount = 0;
  		    for (int i = y; i > -1; i--) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++
  		    }
  		    loopCount = 0;
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Continuing...");
  		      count = 0;
  		    }
  		    for (int i = y; i < 6; i++) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        System.out.println("Count: " + count);
  		      } else {
  		        System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      System.out.println("Success");
  		      return true;
  		    } else {
  		      System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		}
  		else if (x > 0 && x < 6) {
  		  if (x == 1) {
  		    if (y == 0) {
  		      
    		  }
    		  else if (y == 5) {
    		    
    		  }
    		  else if (y > 0 && y < 5) {
    		    
    		  }
  		  }
  		  else if (x == 5) {
  		    if (y == 0) {
  		    
    		  }
    		  else if (y == 5) {
    		    
    		  }
    		  else if (y > 0 && y < 5) {
    		    
    		  }
  		  }
  		  else {
  		    if (y == 0) {
  		    
    		  }
    		  else if (y == 5) {
    		    
    		  }
    		  else if (y > 0 && y < 5) {
    		    
    		  }
  		  }
  		}
  		
  		return false;
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


    
    
    
    // Basic Board Frame attributes
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
    setTitle("Connect Four"); // Sets title
    setSize(/**boardsize**/);         // Sets initial size
    setVisible(true)
    
    
  }
}
