import javax.swing.*

public class Board {
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
    for (int i = 0; i < 6; i++) {
      this.mColumnMap[i] = 0;
    }
    this.mChips = new Chip[6][7];
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

    private void SetChip(int playerTurn) {

    }

    private void PlaceChip(int playerTurn) {

    }

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

    private void DropChip() {

    }

    private void PlayerTurn(int playerTurn) {

    }

    private void render() {

    }
    private void update() {

    }
  }
}
