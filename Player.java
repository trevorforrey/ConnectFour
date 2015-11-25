public class Player {
    
    protected String playerName;
    protected String chipDesignColor;
    protected int numberOfWins;
    
    
    Player(String playerName) {
        this.playerName = playerName;
        this.chipDesignColor = "";
    }
    
    public String getPlayerName() {
        return this.playerName;
    }
    
    public String getChipDesignColor() {
        return this.chipDesignColor;
    }
    
    public void setChipDesignColor(String chipDesignColor) {
        this.chipDesignColor = chipDesignColor;
    }
    
    public int getNumberOfWins() {
        return this.numberOfWins;
    }
    
    public void IncrementWIn() {
        this.numberOfWins += 1;
    }
    
}