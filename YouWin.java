import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.*;


// Creates a Post game menu for the user to decide what to do after a win
public class YouWin extends JFrame {
 
   /** Constructor to setup the GUI */
   public YouWin(BoardLogic mBoardLogic, Board mboard) {

      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();

      JPanel postGameOptions = new JPanel();
      postGameOptions.setLayout(new FlowLayout());

      JPanel youWinCover = new JPanel();

      container.setLayout(new BorderLayout(0,0)); 

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("You Won"); // Sets title
      setSize(800, 450);         // Sets initial size
      setVisible(true);          // Shows
      setLocationRelativeTo(null); // Displays frame in the middle of the screen



      // Creation of components

      // Creates you win cover and sets image for the menu
      JLabel youWinTitleCover = new JLabel();
      youWinTitleCover.setIcon(new ImageIcon(getClass().getResource("Assets/YouWinCover.jpg"))); // Placeholder image

      
      // Creates rematch button and sets image for the button
      JButton rematchButton = new JButton();
      rematchButton.setIcon(new ImageIcon(getClass().getResource("Assets/RematchButton.jpg"))); // Placeholder image



      // Components added to the frame

      youWinCover.add(youWinTitleCover);

      postGameOptions.add(rematchButton);

      
      container.add(postGameOptions, BorderLayout.SOUTH);
      container.add(youWinCover, BorderLayout.NORTH);
 


      // Action Listeners


      // Rematch button action listener
      rematchButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // Components are deleted from frame, new components are brought up
            dispose();
            
            // Empties the board of all chips and allows players to play on the board again
            mBoardLogic.resetBoard();
            mboard.resetGame();
            System.out.println("Rematch clicked");
         }
      });
   }
}
