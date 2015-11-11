import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class TestGameBoard extends JFrame 
{
    private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel backgroundImagePanel = new JPanel();
    private JPanel chipImagePanel = new JPanel();

    public TestGameBoard()
    {
        frame.setPreferredSize(new Dimension(900, 755));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.add(lpane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
        lpane.setBounds(0, 0, 900, 755);
        //panelBlue.setBackground(Color.BLUE);
        backgroundImagePanel.setBounds(0, 0, 900, 755);

        JLabel boardLabel = new JLabel();
		JLabel chipLabel = new JLabel();

		boardLabel.setIcon(new ImageIcon("Assets/Board.png"));
		chipLabel.setIcon(new ImageIcon("Assets/RedChip.png"));
        //panelBlue.setOpaque(true);
        //panelGreen.setBackground(Color.GREEN);
        chipImagePanel.setBounds(50,15,102,107);        // label.setBounds(origin.x, origin.y, icon.getIconWidth(), icon.getIconHeight());
        //panelGreen.setBounds(200, 100, 100, 100);
        chipImagePanel.setOpaque(true);
        
        chipImagePanel.add(chipLabel);
        backgroundImagePanel.add(boardLabel);
        lpane.add(backgroundImagePanel, new Integer(0), 0);
        lpane.add(chipImagePanel, new Integer(1), 0);
        frame.pack();
        frame.setVisible(true);
        
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TestGameBoard();
    }

}



	/*public static int boardWidth = 900;
	public static int boardHeight = 755;
	private ImageIcon gameBoardBackground = new ImageIcon("/Users/Reza/Desktop/ConnectFour/Connect4Board.png");
	private ImageIcon chipImage = new ImageIcon("/Users/Reza/Desktop/JavaCircleDude.png");

	public static void main(String [] args)
	{
		new GameBoardGUI();
	}
	public TestGameBoard()
	{
		setTitle("Connect 4 Gameboard");
		setVisible(true);
		this.setSize(boardWidth,boardHeight);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//JPanel thePanel = new JPanel();
		//JPanel theOtherPanel = new JPanel();
		//JLabel boardLabel = new JLabel();
		//JLabel theOtherLabel = new JLabel();


		this.add(new JLabel(gameBoardBackground));
		this.add(new JLabel(chipImage));

		this.setVisible(true);

		
		//validate();
	}*/
