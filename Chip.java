public class Chip {
    // 0 is player 1, 1 is player 2/ComputerAI.
    private int playersChip;
    private boolean visible;
    
    // Chip Constructor
    Chip (int playersChip) {
        this.playersChip = playersChip;
        this.visible = false;
        
    }
    
    // Method to determine who's chip is being used currently.
    public int WhosChip() {
        return this.playersChip;
    }
    
    //Set and get visible
    public boolean setVisible(boolean visible) {
        this.visible = visible;
    }
    
    // Accessor method for retrieving state of chip.
    public boolean isVisible() {
        return this.visible;
    }

    
}


// Player 1 Chip
public class player1Chip extends Chip {
    
    player1Chip(int playersChip) {
        Chip(playersChip)
    }
    
}


// Player 2 Chip 
public class player2Chip extends Chip {
    
    player2Chip(int playersChip) {
        Chip(playersChip);
    }
}