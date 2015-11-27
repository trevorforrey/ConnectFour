
// Currently holds all game logic (non gui components of the board)
public class BoardLogic {
  
  
  private boolean mConnectFour;
  private int mChipCount;
  private int mTurnCount;
  private boolean mPlayerTurn;
  private boolean mIsPlaying;


  private int[] mColumnMap;
  private Chip[][] mChips;
  private int[][] mDrawMap;

  public BoardLogic() {
    
    this.mIsPlaying = true;
    this.mConnectFour = false;
    this.mChipCount = 0;
    this.mTurnCount = 0;
    this.mPlayerTurn = true;
    this.mColumnMap = new int[6];
    this.mChips = new Chip[6][7];
    this.mDrawMap = new int[6][7];
    
    // Fills column map with 0's
    for (int i = 0; i < 6; i++) {
    	mColumnMap[i] = 0;
    }
    // Fills chip array (6 by 7) with anonymous Chip objects
    
    for (int i = 0; i < 6; i++) {
    	for (int j = 0; j < 7; j++) {
    		mChips[i][j] = new Chip();
    		mDrawMap[i][j] = 0;
    	}
     }
  }

    // Calls chip constructor for either player1 or player2 chip
    
    private void SetChip(int playerTurn, int x, int y) {
      if (playerTurn == 0) {
        mChips[y][x] = new player1Chip(playerTurn);
        mDrawMap[y][x] = 1;
      } else if (playerTurn == 1) {
        mChips[y][x] = new player2Chip(playerTurn);
        mDrawMap[y][x] = 2;
      }
    }


    // Sets recently placed chip's visibility to true
    public void PlaceChip(int playerTurn, int x, int y) {
      SetChip(playerTurn, x, y);
      mChips[y][x].setVisible(true);
      if (ChipCheck(x, y, playerTurn)) {
        System.out.println("There is a connect 4!");
        YouWin winner = new YouWin();
      }
    }
    
    public int[][] GetDrawMap() {
    	return mDrawMap;
    }

    // Checks a column of the board and returns the amount of empty spaces in the column
    public int CheckColumn(int col) {
      int tempCount = 5;
      for (int i = 5; i > -1; i--) {
        if (mChips[i][col].isVisible() == true) {
          mColumnMap[i] = 1;
          tempCount -= 1;
        } else {
          break;
        }
      }
      return tempCount;
    }

    //Diagonal cases which will return count. These will then be added
    //to check for connect4 in the Check Diagonal
    // comments are first word is the limiter in the given direction: left, right, top, bot
    // the next are the direction in which it is moving
    
    //Top leftward
    private int DiagonalTopLeftward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i = y; i > -1; i--) {
        if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    //top rightward
    private int DiagonalTopRightward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i = y; i > -1; i--) {
        if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    
    //Bot leftward
    private int DiagonalBotLeftward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i = y; i < 6; i++) {
        if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    //Bot rightward
    private int DiagonalBotRightward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i = y; i < 6; i++) {
        if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    
    //Left upward
    private int DiagonalLeftUpward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i = x; i > -1; i--) {
        if (mChips[y - loopCount][i].isVisible() && mChips[y - loopCount][i].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    //Left downward
    private int DiagonalLeftDownward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i  = x; i > -1; i--) {
        if (mChips[y + loopCount][i].isVisible() && mChips[y + loopCount][i].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    
    //Right upward
    private int DiagonalRightUpward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i = x; i < 7; i++) {
        if (mChips[y - loopCount][i].isVisible() && mChips[y - loopCount][i].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    //Right downward
    private int DiagonalRightDownward(int x, int y, int playerTurn) {
      int count = 0;
      int loopCount = 0;
      for (int i = x; i < 7; i++) {
        if (mChips[y + loopCount][i].isVisible() && mChips[y + loopCount][i].WhosChip() == playerTurn) {
          count++;
        } else {
          break;
        }
        loopCount++;
      }
      return count;
    }
    
    private boolean CheckVertical(int init_X, int init_Y, int playerTurn) {
  		int x = init_X;
  		int y = init_Y;
  		int count = 0;
  		if (y == 0) {
  			//System.out.println("Starting Top check...");
  			for (int i = 0; i < 6; i++) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				//System.out.println("Success");
  				return true;
  			} else {
  				//System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (y == 5) {
  			//System.out.println("Startign Bot check...");
  			for (int i = 5; i > -1; i--) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				//System.out.println("Success");
  				return true;
  			} else {
  				//System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (y > 0 && y < 5) {
  			//System.out.println("Starting vertical check not from edge");
  			count += 1;
  			for (int i = y - 1; i > -1; i--) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			for (int i = y + 1; i < 6; i++) {
  				if (mChips[i][x].isVisible() && mChips[i][x].WhosChip() == playerTurn) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				//System.out.println("Success");
  				return true;
  			} else {
  				//System.out.println("Failure");
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
  			//System.out.println("Starting check from left edge...");
  			for (int i = 0; i < 7; i++) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				//System.out.println("Success");
  				return true;
  			} else {
  				//System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (x == 6) {
  			//System.out.println("Starting check from right edge...");
  			for (int i = 6; i > -1; i--) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				//System.out.println("Success");
  				return true;
  			} else {
  				//System.out.println("Failure");
  				return false;
  			}
  		}
  		else if (x > 0 && x < 6) {
  			//System.out.println("Starting horizontal check not from edge...");
  			count += 1;
  			for (int i = x - 1; i > -1; i--) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn ) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			for (int i = x + 1; i < 7; i++) {
  				if (mChips[y][i].isVisible() && mChips[y][i].WhosChip() == playerTurn) {
  					count++;
  					//System.out.println("Count: " + count);
  				} else {
  					//System.out.println("Breaking...");
  					break;
  				}
  			}
  			if (count >= 4) {
  				//System.out.println("Success");
  				return true;
  			} else {
  				//System.out.println("Failure");
  				return false;
  			}
  		}
  		
  		
  		return false;
  	}
  	
  	private boolean CheckDiagonal(int init_X, int init_Y, int playerTurn) {
  		int x = init_X;
  		int y = init_Y;
  		int count = 0;
  		int loopCount = 0;
  		if (x == 0) {
  		  if (y == 0) {
  		    //System.out.println("Starting check from top left corner...");
  		    for (int i = 0; i < 6; i++) {
  		      if (mChips[i][i].isVisible() && mChips[i][i].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y == 5) {
  		    //System.out.println("Starting check from bot left corner...");
  		    loopCount = 0;
  		    for (int i = 5; i > -1; i--) {
  		      if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y > 0 && y < 5) {
  		    //System.out.println("Starting check from left edge...");
  		    loopCount = 0;
  		    for (int i = y; i > -1; i--) {
  		      if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    loopCount = 0;
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Continuing...");
  		      count = 0;
  		    }
  		    for (int i = y; i < 6; i++) {
  		      if (mChips[i][x + loopCount].isVisible() && mChips[i][x + loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		}
  		else if (x == 6) {
  		  if (y == 0) {
  		    //System.out.println("Starting check from top right corner...");
  		    loopCount = 0;
  		    for (int i = 0; i < 6; i++) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y == 5) {
  		    //System.out.println("Starting check from bot right corner...");
  		    loopCount = 0;
  		    for (int i = 5; i > -1; i--) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y > 0 && y < 5) {
  		    //System.out.println("Starting check from left edge...");
  		    loopCount = 0;
  		    for (int i = y; i > -1; i--) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    loopCount = 0;
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Continuing...");
  		      count = 0;
  		    }
  		    for (int i = y; i < 6; i++) {
  		      if (mChips[i][x - loopCount].isVisible() && mChips[i][x - loopCount].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking...");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		}
  		else if (x > 0 && x < 6) {
  		  if (y == 0) {
  		    //System.out.println("Starting check from top edge...");
  		    loopCount = 0;
  		    for (int i = x; i > -1; i--) {
  		      if (mChips[y + loopCount][i].isVisible() && mChips[y + loopCount][i].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Continuing...");
  		      count = 0;
  		    }
  		    loopCount = 0;
  		    for (int i = x; i < 7; i++) {
  		      if (mChips[y + loopCount][i].isVisible() && mChips[y + loopCount][i].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y == 5) {
  		    //System.out.println("Starting check from bot edge...");
  		    loopCount = 0;
  		    for (int i = x; i > -1; i--) {
  		      if (mChips[y - loopCount][i].isVisible() && mChips[y - loopCount][i].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Continuing...");
  		      count = 0;
  		    }
  		    loopCount = 0;
  		    for (int i = x; i < 7; i++) {
  		      if (mChips[y - loopCount][i].isVisible() && mChips[y - loopCount][i].WhosChip() == playerTurn) {
  		        count++;
  		        //System.out.println("Count: " + count);
  		      } else {
  		        //System.out.println("Breaking");
  		        break;
  		      }
  		      loopCount++;
  		    }
  		    if (count >= 4) {
  		      //System.out.println("Success");
  		      return true;
  		    } else {
  		      //System.out.println("Failure");
  		      return false;
  		    }
  		  }
  		  else if (y > 0 && y < 5) {
  		    //8 distinct cases
  		    if (y == 1) {
  		      if (x > 0 && x < 3) {
  		        //1
  		        if ((DiagonalTopLeftward(x, y, playerTurn) + DiagonalBotRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else if ((DiagonalLeftDownward(x, y, playerTurn) + DiagonalTopRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else {
  		          return false;
  		        }
  		      }
  		      else if (x > 3 && x < 6) {
  		        //2
  		        if ((DiagonalTopLeftward(x, y, playerTurn) + DiagonalRightDownward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else if ((DiagonalBotLeftward(x, y, playerTurn) + DiagonalTopRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else {
  		          return false;
  		        }
  		      }
  		    }
  		    else if ( y == 4) {
  		      if (x > 0 && x < 3) {
  		        //3
  		        if ((DiagonalLeftUpward(x, y, playerTurn) + DiagonalBotRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else if ((DiagonalBotLeftward(x, y, playerTurn) + DiagonalTopRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else {
  		          return false;
  		        }
  		      }
  		      else if (x > 3 && x < 6) {
  		        //4
  		        if ((DiagonalTopLeftward(x, y, playerTurn) + DiagonalBotRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else if ((DiagonalBotLeftward(x, y, playerTurn) + DiagonalRightUpward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else {
  		          return false;
  		        }
  		      }
  		    }
  		    else if (y > 1 && y < 4 && x != 3) {
  		      if (x > 0 && x < 3) {
  		        //5
  		        if ((DiagonalLeftUpward(x, y, playerTurn) + DiagonalBotRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else if ((DiagonalLeftDownward(x, y, playerTurn) + DiagonalTopRightward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else {
  		          return false;
  		        }
  		      }
  		      else if (x > 3 && x < 6) {
  		        //6
  		        if ((DiagonalTopLeftward(x, y, playerTurn) + DiagonalRightDownward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else if ((DiagonalBotLeftward(x, y, playerTurn) + DiagonalRightUpward(x, y, playerTurn) - 1) >= 4) {
  		          return true;
  		        }
  		        else {
  		          return false;
  		        }
  		      }
  		    }
  		    else if (y > 0 && y < 5 && x == 3) {
  		      if (x == 3) {
  		        if (y > 0 && y < 3) {
  		          //7
  		          if ((DiagonalTopLeftward(x, y, playerTurn) + DiagonalRightDownward(x, y, playerTurn) - 1) >= 4) {
  		            return true;
    		        }
    		        else if ((DiagonalTopRightward(x, y, playerTurn) + DiagonalLeftDownward(x, y, playerTurn) - 1) >= 4) {
    		          return true;
    		        }
    		        else {
    		          return false;
    		        }
  		        }
  		        else if (y > 2 && y < 5) {
  		          //8
  		          if ((DiagonalLeftUpward(x, y, playerTurn) + DiagonalBotRightward(x, y, playerTurn) - 1) >= 4) {
    		          return true;
    		        }
    		        else if ((DiagonalRightUpward(x, y, playerTurn) + DiagonalBotLeftward(x, y, playerTurn) - 1) >= 4) {
    		          return true;
    		        }
    		        else {
    		          return false;
    		        }
  		        }
  		      }
  		    }
  		  }
  		}
  		
  		return false;
  	}

    // Chip array getter
    public Chip[][] getChips() {
      return mChips;
    }

    public boolean ChipCheck(int x, int y, int playerTurn) {
      if (CheckHorizontal(x, y, playerTurn) || 
      CheckVertical(x, y, playerTurn) ||
      CheckDiagonal(x, y, playerTurn)) {
        return true;
      } else {
        return false;
      }
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


    
    
    /*
    // Basic Board Frame attributes
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
    setTitle("Connect Four"); // Sets title
    setSize(*boardsize*);         // Sets initial size
    setVisible(true)
    */
    
}
