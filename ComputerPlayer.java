import java.util.Random;

public class ComputerPlayer extends Player {
    
    private int columnToPlay;
    
    // Computer Player Constructor
    ComputerPlayer(String computerName) {
        // Sets name of computer to RAMy
        super(computerName);
    }
    
    
    
    // Method that calls an easy computer turn
        // Picks random open column
    public int easyTurn() {
        
        columnToPlay = randomColumn();
        
        // Returns the index of a random column to drop the chip into
        return columnToPlay;
    }
    
    
    // Method that calls a medium computer turn
        // Attempts to block a player from getting multiple chips in a row
        // Returns the index of the column to place the chip
    public int mediumTurn(Chip[][] chips) {

        // Collects the defense scores of each column choice
        int column1Score = columnDefenseScore(0, chips);
        int column2Score = columnDefenseScore(1, chips);
        int column3Score = columnDefenseScore(2, chips);
        int column4Score = columnDefenseScore(3, chips);
        int column5Score = columnDefenseScore(4, chips);
        int column6Score = columnDefenseScore(5, chips);
        int column7Score = columnDefenseScore(6, chips);
        
        
        // Checks if column1 has the highest score
        if (column1Score >= column2Score && column1Score >= column3Score &&
        column1Score >= column4Score && column1Score >= column5Score &&
        column1Score >= column6Score && column1Score >= column7Score) {
            columnToPlay = 0;
        }
        
        // Checks if column2 has the highest score
        if (column2Score >= column1Score && column2Score >= column3Score &&
        column2Score >= column4Score && column2Score >= column5Score &&
        column2Score >= column6Score && column2Score >= column7Score) {
            columnToPlay = 1;
        }
        
        // Checks if column3 has the highest score
        if (column3Score >= column2Score && column3Score >= column1Score &&
        column3Score >= column4Score && column3Score >= column5Score &&
        column3Score >= column6Score && column3Score >= column7Score) {
            columnToPlay = 2;
        }
        
        // Checks if column4 has the highest score
        if (column4Score >= column2Score && column4Score >= column3Score &&
        column4Score >= column1Score && column4Score >= column5Score &&
        column4Score >= column6Score && column4Score >= column7Score) {
            columnToPlay = 3;
        }
        
        // Checks if column5 has the highest score
        if (column5Score >= column2Score && column5Score >= column3Score &&
        column5Score >= column4Score && column5Score >= column1Score &&
        column5Score >= column6Score && column5Score >= column7Score) {
            columnToPlay = 4;
        }
        
        // Checks if column6 has the highest score
        if (column6Score >= column2Score && column6Score >= column3Score &&
        column6Score >= column4Score && column6Score >= column5Score &&
        column6Score >= column1Score && column6Score >= column7Score) {
            columnToPlay = 5;
        }
        
        // Checks if column7 has the highest score
        if (column7Score >= column2Score && column7Score >= column3Score &&
        column7Score >= column4Score && column7Score >= column5Score &&
        column7Score >= column6Score && column7Score >= column1Score) {
            columnToPlay = 6;
        }



        // For when there's no choice but to make a play where afterwards the other player wins


        // Checks if a column1 play leads the other player to score and there are no other options
        if (column1Score < 0 && column7Score <= 0 && column2Score <= 0 &&
        column3Score <= 0 && column4Score <= 0 && column5Score <= 0 &&
        column6Score <= 0) {
            columnToPlay = 0;
        }

        // Checks if a column2 play leads the other player to score and there are no other options
        if (column2Score < 0 && column1Score <= 0 && column7Score <= 0 &&
        column3Score <= 0 && column4Score <= 0 && column5Score <= 0 &&
        column6Score <= 0) {
            columnToPlay = 1;
        }

        // Checks if a column3 play leads the other player to score and there are no other options
        if (column3Score < 0 && column1Score <= 0 && column2Score <= 0 &&
        column7Score <= 0 && column4Score <= 0 && column5Score <= 0 &&
        column6Score <= 0) {
            columnToPlay = 2;
        }

        // Checks if a column4 play leads the other player to score and there are no other options
        if (column4Score < 0 && column1Score <= 0 && column2Score <= 0 &&
        column3Score <= 0 && column7Score <= 0 && column5Score <= 0 &&
        column6Score <= 0) {
            columnToPlay = 3;
        }

        // Checks if a column5 play leads the other player to score and there are no other options
        if (column5Score < 0 && column1Score <= 0 && column2Score <= 0 &&
        column3Score <= 0 && column4Score <= 0 && column7Score <= 0 &&
        column6Score <= 0) {
            columnToPlay = 4;
        }

        // Checks if a column6 play leads the other player to score and there are no other options
        if (column6Score < 0 && column1Score <= 0 && column2Score <= 0 &&
        column3Score <= 0 && column4Score <= 0 && column5Score <= 0 &&
        column7Score <= 0) {
            columnToPlay = 5;
        }

        // Checks if a column7 play leads the other player to score and there are no other options
        if (column7Score < 0 && column1Score <= 0 && column2Score <= 0 &&
        column3Score <= 0 && column4Score <= 0 && column5Score <= 0 &&
        column6Score <= 0) {
            columnToPlay = 6;
        }
        
        // Returns the index of the best column to drop the chip into
        return columnToPlay;
        
    }
    
    
    
    
    
    // Method that calls a hard computer turn
        // Attempts to win along with blocking player from getting multiple chips in a row
        // Returns the index of the column to place the chip
    public int hardTurn(Chip[][] chips) {
        
        // Collects the defense scores of each column choice
        int column1TotalScore = totalColumnScore(0, chips);
        int column2TotalScore = totalColumnScore(1, chips);
        int column3TotalScore = totalColumnScore(2, chips);
        int column4TotalScore = totalColumnScore(3, chips);
        int column5TotalScore = totalColumnScore(4, chips);
        int column6TotalScore = totalColumnScore(5, chips);
        int column7TotalScore = totalColumnScore(6, chips);
        
        // Checks if column1 has the highest score
        if (column1TotalScore >= column2TotalScore && column1TotalScore >= column3TotalScore &&
        column1TotalScore >= column4TotalScore && column1TotalScore >= column5TotalScore &&
        column1TotalScore >= column6TotalScore && column1TotalScore >= column7TotalScore) {
            columnToPlay = 0;
        }
        
        // Checks if column2 has the highest score
        if (column2TotalScore >= column1TotalScore && column2TotalScore >= column3TotalScore &&
        column2TotalScore >= column4TotalScore && column2TotalScore >= column5TotalScore &&
        column2TotalScore >= column6TotalScore && column2TotalScore >= column7TotalScore) {
            columnToPlay = 1;
        }
        
        // Checks if column3 has the highest score
        if (column3TotalScore >= column2TotalScore && column3TotalScore >= column1TotalScore &&
        column3TotalScore >= column4TotalScore && column3TotalScore >= column5TotalScore &&
        column3TotalScore >= column6TotalScore && column3TotalScore >= column7TotalScore) {
            columnToPlay = 2;
        }
        
        // Checks if column4 has the highest score
        if (column4TotalScore >= column2TotalScore && column4TotalScore >= column3TotalScore &&
        column4TotalScore >= column1TotalScore && column4TotalScore >= column5TotalScore &&
        column4TotalScore >= column6TotalScore && column4TotalScore >= column7TotalScore) {
            columnToPlay = 3;
        }
        
        // Checks if column5 has the highest score
        if (column5TotalScore >= column2TotalScore && column5TotalScore >= column3TotalScore &&
        column5TotalScore >= column4TotalScore && column5TotalScore >= column1TotalScore &&
        column5TotalScore >= column6TotalScore && column5TotalScore >= column7TotalScore) {
            columnToPlay = 4;
        }
        
        // Checks if column6 has the highest score
        if (column6TotalScore >= column2TotalScore && column6TotalScore >= column3TotalScore &&
        column6TotalScore >= column4TotalScore && column6TotalScore >= column5TotalScore &&
        column6TotalScore >= column1TotalScore && column6TotalScore >= column7TotalScore) {
            columnToPlay = 5;
        }
        
        // Checks if column7 has the highest score
        if (column7TotalScore >= column2TotalScore && column7TotalScore >= column3TotalScore &&
        column7TotalScore >= column4TotalScore && column7TotalScore >= column5TotalScore &&
        column7TotalScore >= column6TotalScore && column7TotalScore >= column1TotalScore) {
            columnToPlay = 6;
        }



        // For when there's no choice but to make a play where afterwards the other player wins


        // Checks if a column1 play leads the other player to score and there are no other options
        if (column1TotalScore < 0 && column7TotalScore <= 0 && column2TotalScore <= 0 &&
        column3TotalScore <= 0 && column4TotalScore <= 0 && column5TotalScore <= 0 &&
        column6TotalScore <= 0) {
            columnToPlay = 0;
        }

        // Checks if a column2 play leads the other player to score and there are no other options
        if (column2TotalScore < 0 && column1TotalScore <= 0 && column7TotalScore <= 0 &&
        column3TotalScore <= 0 && column4TotalScore <= 0 && column5TotalScore <= 0 &&
        column6TotalScore <= 0) {
            columnToPlay = 1;
        }

        // Checks if a column3 play leads the other player to score and there are no other options
        if (column3TotalScore < 0 && column1TotalScore <= 0 && column2TotalScore <= 0 &&
        column7TotalScore <= 0 && column4TotalScore <= 0 && column5TotalScore <= 0 &&
        column6TotalScore <= 0) {
            columnToPlay = 2;
        }

        // Checks if a column4 play leads the other player to score and there are no other options
        if (column4TotalScore < 0 && column1TotalScore <= 0 && column2TotalScore <= 0 &&
        column3TotalScore <= 0 && column7TotalScore <= 0 && column5TotalScore <= 0 &&
        column6TotalScore <= 0) {
            columnToPlay = 3;
        }

        // Checks if a column5 play leads the other player to score and there are no other options
        if (column5TotalScore < 0 && column1TotalScore <= 0 && column2TotalScore <= 0 &&
        column3TotalScore <= 0 && column4TotalScore <= 0 && column7TotalScore <= 0 &&
        column6TotalScore <= 0) {
            columnToPlay = 4;
        }

        // Checks if a column6 play leads the other player to score and there are no other options
        if (column6TotalScore < 0 && column1TotalScore <= 0 && column2TotalScore <= 0 &&
        column3TotalScore <= 0 && column4TotalScore <= 0 && column5TotalScore <= 0 &&
        column7TotalScore <= 0) {
            columnToPlay = 5;
        }

        // Checks if a column7 play leads the other player to score and there are no other options
        if (column7TotalScore < 0 && column1TotalScore <= 0 && column2TotalScore <= 0 &&
        column3TotalScore <= 0 && column4TotalScore <= 0 && column5TotalScore <= 0 &&
        column6TotalScore <= 0) {
            columnToPlay = 6;
        }

        
        // Returns the index of the best column to drop the chip into
        return columnToPlay;
    }
    
    
    
    
    
    
    
    
    
    //*******Defense Score Method*******//
    
    
    // Method that takes in a column and returns a score based on how many enemy chips would be next to the computer chip in a row
    public int columnDefenseScore(int trueColumn, Chip[][] chips) {

        // Initializes score of move
        int scoreOfMove = 0;
        int scoreOfDirection = 0;
        
        // Creates a temp column to move around the chip array
        int tempColumn = trueColumn;
        
        // Gets row the chip would be placed if in the column
        int trueRow = getRowPosition(tempColumn, chips);
        
        // Creates a temp row to move around the chip array
        int tempRow = trueRow;
        
        // If the Column is filled
        if (trueRow == -1) {
            // No additional chips can be dropped
            // Returns column score as zero
            return 0;
        }
        
        
        
        
        // Starts scoring the position for every direction
        
        
        // Counts how many enemy chips are to the right of where the chip would be placed in a row
        while (tempColumn < 6) {
            tempColumn++;
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection++;
                // If there are three enemy chips to the right in a row of the "ghost chip"
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move
                    scoreOfDirection += 50;
                    System.out.println("Block from the left");
                }
            } else {
                break;
            }
        }


        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        

        // Counts how many enemy chips are to the left of where the chip would be placed in a row
        while (tempColumn > 0) {
            tempColumn--;
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection++;
                // If there are three enemy chips to the left in a row of the "ghost chip"
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move
                    scoreOfDirection += 50;
                    System.out.println("Block from the right");
                }
            } else {
                break;
            }
        }
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        


        // Checks down the column for enemy chips
        while (tempRow < 5) {
    
            tempRow++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection++;
                 // If there are three enemy chips in a row below the "ghost chip"
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move
                    scoreOfDirection += 50;
                    System.out.println("Block from above");
                }
            } else {
                break;
            }
        }
        
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;
        

        
// Bottom Left to Up Right

        // Checks from bottom left to upper right diagonal for enemy chips
        while (tempRow > 0 && tempColumn < 6) {
            tempRow--;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection++;
                // If there are three enemy chips to the (bottom-left to upper right) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move
                    scoreOfDirection += 50;
                    System.out.println("Block bottom left to upper right diagonal");
                }
            } else {
                break;
            }
        }


        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;


// Bottom Left to Up Right Not Play

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1;

        // Checks to make sure it doesn't set up the opponenent for a diagonal connect 4
        while (tempRow > 0 && tempColumn < 6) {

            tempRow--;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection--;
                // If there are three enemy chips to the (bottom-left to upper right) diagonal in a row
                if (scoreOfDirection == -3) {
                    // Make it a very high scoring move
                    scoreOfDirection -= 100;
                    System.out.println("Won't hit it");
                }
            } else {
                break;
            }
        }
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;


// Up Right to Bottom Left

        // Checks from upper right to bottom left diagonal for enemy chips
        while (tempRow < 5 && tempColumn > 0) {
            tempRow++;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection++;
                // If there are three enemy chips to the (upper right to bottom left) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move
                    scoreOfDirection += 50;
                    System.out.println("Block up right to left bottom diagonal");
                }
            } else {
                break;
            }
        }
        
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1 ;
        
        
// Up Right to Bottom Left Not Play

        // Checks from upper right to bottom left diagonal for enemy chips
        while (tempRow < 5 && tempColumn > 0) {
            tempRow++;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection--;
                // If there are three enemy chips to the (upper right to bottom left) diagonal in a row
                if (scoreOfDirection == -3) {
                    // Make it a very high scoring move
                    scoreOfDirection -= 100;
                    System.out.println("wont play");
                }
            } else {
                break;
            }
        }

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;


// Bottom Right to Up Left
        // Checks bottom right to the upper left diagonal for enemy chips
        while(tempRow > 0 && tempColumn > 0) {
            tempRow--;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection++;
                // If there are three enemy chips to the (bottom right to upper left) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move
                    scoreOfDirection += 50;
                    System.out.println("Block up right to left up diagonal");
                }
            } else {
                break;
            }
        }
        

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1 ;

        
// Bottom Right to Up Left Not Play

        // Checks bottom right to the upper left diagonal for enemy chips
        while(tempRow > 0 && tempColumn > 0) {
            tempRow--;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection--;
                // If there are three enemy chips to the (bottom right to upper left) diagonal in a row
                if (scoreOfDirection == -3) {
                    // Make it a very high scoring move
                    scoreOfDirection -= 100;
                    System.out.println("wont hit it");
                }
            } else {
                break;
            }
        }



        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;
        
        
// Up Left to Bottom Right Block

        // Checks top left to bottom right for enemy chips
        while (tempRow < 5 && tempColumn < 6) {
            tempRow++;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection++;
                // If there are three enemy chips to the (upper left to bottom right) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move
                    scoreOfDirection += 50;
                    System.out.println("Block up left to right bottom diagonal");
                }
            } else {
                break;
            }
        }
        

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1;


// Up Left to Bottom Right Not Play

        // Checks top left to bottom right for enemy chips
        while (tempRow < 5 && tempColumn < 6) {
            tempRow++;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 0) {
                scoreOfDirection--;
                // If there are three enemy chips to the (upper left to bottom right) diagonal in a row
                if (scoreOfDirection == -3) {
                    // Make it a very high scoring move
                    scoreOfDirection -= 100;
                    System.out.println("wont hit");
                }
            } else {
                break;
            }
        }

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Returns the defensive score of the move of a specific column
        return scoreOfMove;
    }
    
    






    
//*******Offense Score Method*******//


    // Method that takes in a column and returns a score based on how many computer chips would be next to the placed computer chip in a row
    public int columnOffenseScore(int trueColumn, Chip[][] chips) {
        

        // Initializes score of move
        int scoreOfMove = 0;
        int scoreOfDirection = 0;
        
        // Creates a temp column to move around the chip array
        int tempColumn = trueColumn;
        
        // Gets row the chip would be placed if in the column
        int trueRow = getRowPosition(tempColumn, chips);
        
        // Creates a temp row to move around the chip array
        int tempRow = trueRow;
        
        // If the Column is filled
        if (trueRow == -1) {
            // No additional chips can be dropped
            // Returns column score as zero
            return 0;
        }
        
        
        // Starts scoring the position for every direction
        
        
        // Counts how many of the computer's own chips are to the right of where the chip would be placed in a row
        while (tempColumn < 6) {
            tempColumn++;
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection++;
                // If there are three of its own chips to the right in a row of the "ghost chip"
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move (take the win)
                    scoreOfDirection += 400;
                }
            } else {
                break;
            }
        }
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        


        // Counts how many of the computer's own chips are to the left of where the chip would be placed in a row
        while (tempColumn > 0) {
            tempColumn--;
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection++;
                // If there are three of its own chips to the left in a row of the "ghost chip"
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move (take the win)
                    scoreOfDirection += 400;
                }
            } else {
                break;
            }
        }
        
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        


        // Checks down the column for its own chips
        while (tempRow < 5) {
            
            tempRow++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection++;
                 // If there are three of its own chips in a row below the "ghost chip"
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move (take the win)
                    scoreOfDirection += 400;
                }
            } else {
                break;
            }
        }
        
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;
        

        // Checks from bottom left to upper right diagonal for its own chips
        while (tempRow > 0 && tempColumn < 6) {
            tempRow--;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection++;
                // If there are three of its own chips to the (bottom-left to upper right) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move (take the win)
                    scoreOfDirection += 400;
                }
            } else {
                break;
            }
        }
        

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1;


// Bottom Left to Up Right Not Play (To Save Potential Win)

        // Checks to make sure it doesn't allow the opponent to block a potential diagonal score
        while (tempRow > 0 && tempColumn < 6) {

            tempRow--;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection--;
                // If there are three of the computer's own chips to the (bottom-left to upper right) diagonal in a row
                if (scoreOfDirection == -3) {
                    // Make it a very low scoring move (keep the potential for win)
                    scoreOfDirection -= 49;
                    System.out.println("Want to win");
                }
            } else {
                break;
            }
        }
        

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;
        
        
        // Checks from upper right to bottom left diagonal for its own chips
        while (tempRow < 5 && tempColumn > 0) {
            tempRow++;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection++;
                // If there are three of its own chips to the (upper right to bottom left) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move (take the win)
                    scoreOfDirection += 400;
                }
            } else {
                break;
            }
        }
        

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1;


// Up Right to Bottom Left Not Play (To Save Potential Win)

        // Checks to make sure it doesn't allow the opponent to block a potential diagonal score
        while (tempRow < 5 && tempColumn > 0) {
            tempRow++;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection--;
                // If there are three enemy chips to the (upper right to bottom left) diagonal in a row
                if (scoreOfDirection == -3) {
                    // Make it a very low scoring move (keep the potential for win)
                    scoreOfDirection -= 49;
                    System.out.println("want to win");
                }
            } else {
                break;
            }
        }


        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;
        
        
        // Checks bottom right to the upper left diagonal for its own chips
        while(tempRow > 0 && tempColumn > 0) {
            tempRow--;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection++;
                // If there are three of its own chips to the (bottom right to upper left) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move (take the win)
                    scoreOfDirection += 400;
                }
            } else {
                break;
            }
        }
        
        
        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1;
        
        
// Bottom Right to Up Left Not Play (To Save Potential Win)

        // Checks to make sure it doesn't allow the opponent to block a potential diagonal score
        while(tempRow > 0 && tempColumn > 0) {
            tempRow--;
            tempColumn--;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection--;
                // If there are three of its own chips to the (bottom right to upper left) diagonal in a row
                if (scoreOfDirection == -3) {
                    // Make it a very low scoring move (keep the potential for win)
                    scoreOfDirection -= 49;
                    System.out.println("want to win");
                }
            } else {
                break;
            }
        }


        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow;

        
        // Checks top left to bottom right for its own chips
        while (tempRow < 5 && tempColumn < 6) {
            tempRow++;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection++;
                // If there are three of its own chips to the (upper left to bottom right) diagonal in a row
                if (scoreOfDirection == 3) {
                    // Make it a very high scoring move (take the win)
                    scoreOfDirection += 400;
                }
            } else {
                break;
            }
        }


        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;

        // Resets tempColumn back to the column being checked
        tempColumn = trueColumn;
        // Resets tempRow back to the row being checked
        tempRow = trueRow - 1;


// Up Left to Bottom Right Not Play (To Save Potential Win)

        // Checks to make sure it doesn't allow the opponent to block a potential diagonal score
        while (tempRow < 5 && tempColumn < 6) {
            tempRow++;
            tempColumn++;
            
            if (chips[tempRow][tempColumn].isVisible() && chips[tempRow][tempColumn].WhosChip() == 1) {
                scoreOfDirection--;
                // If there are three of its own chips to the (upper left to bottom right) diagonal in a row just above where chip would land
                if (scoreOfDirection == -3) {
                    // Make it a very low scoring move (keep the potential for win)
                    scoreOfDirection -= 49;
                    System.out.println("want to win");
                }
            } else {
                break;
            }
        }

        scoreOfMove += scoreOfDirection;
        scoreOfDirection = 0;
        
        // Returns the offensive score of the move of a specific column
        return scoreOfMove;
    }
    
    
    
    
    
    
    
    // Method that calculates and returns the combined defensive and offensive score of a column choice
    private int totalColumnScore(int column, Chip[][] chips) {
        
        int defensiveScore = columnDefenseScore(column, chips);
        int offensiveScore = columnOffenseScore(column, chips);
        
        return defensiveScore + offensiveScore;
    }
    
    
    
    
    // Method that takes in a column and returns what row the chip would be in if it was dropped
    private int getRowPosition(int col, Chip[][] chips) {
      int rowPosition = 5;
      for (int i = 5; i >= 0; i--) {
        if (chips[i][col].isVisible() == true) {
          rowPosition -= 1;
        } else {
          break;
        }
      }
      return rowPosition;
    }
    
    
    
    
    public int randomColumn() {
        Random rand = new Random();
        
        int randomColumn = rand.nextInt(6);
        
        return randomColumn;
    }
    
}