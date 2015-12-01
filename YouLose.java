import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.*;


// Creates a Post game menu for the user to decide what to do after a loss
public class YouLose extends JFrame {
 
   /** Constructor to setup the GUI */
   public YouLose(BoardLogic mBoardLogic) {

      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();

      JPanel postGameOptions = new JPanel();
      postGameOptions.setLayout(new FlowLayout());

      JPanel youLoseCover = new JPanel();

      container.setLayout(new BorderLayout(0,0)); 

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("You Lose"); // Sets title
      setSize(650, 450);         // Sets initial size
      setVisible(true);          // Shows
      setLocationRelativeTo(null); // Displays frame in the middle of the screen



      // Creation of components

      // Creates you win cover and sets image for the menu
      JLabel youLoseTitleCover = new JLabel();
      youLoseTitleCover.setIcon(new ImageIcon(getClass().getResource("Assets/YouLoseCover.jpg"))); // Placeholder image

      
      // Creates rematch button and sets image for the button
      JButton rematchButton = new JButton();
      rematchButton.setIcon(new ImageIcon(getClass().getResource("Assets/RematchButton.jpg"))); // Placeholder image
      //pvpButton.setBorder(BorderFactory.createEmptyBorder());
      //pvpButton.setContentAreaFilled(false);


      // Creates main menu button and sets image for the button
      JButton mainMenuButton = new JButton();
      mainMenuButton.setIcon(new ImageIcon(getClass().getResource("Assets/MainMenuButton.jpg"))); // Placeholder image
      //pvcButton.setBorder(BorderFactory.createEmptyBorder());
      //pvcButton.setContentAreaFilled(false);



      // Components added to the frame

      youLoseCover.add(youLoseTitleCover);

      postGameOptions.add(rematchButton);
      postGameOptions.add(mainMenuButton);

      
      container.add(postGameOptions, BorderLayout.SOUTH);
      container.add(youLoseCover, BorderLayout.NORTH);
 


      // Action Listeners


      // Rematch button action listener
      rematchButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // Components are deleted from frame, new components are brought up
            dispose();
            

            /**

               Code that calls the resetting of the board
               

            **/


            //Test
            mBoardLogic.resetBoard();
            System.out.println("Rematch clicked");
         }
      });


      // Main menu button action listener
      mainMenuButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // Components are deleted from frame, new components are brought up
            dispose();
            
            /**

               Code that brings the player back to the main menu
               

            **/


            //Test
            System.out.println("Main Menu clicked");
         }
      });
   }
 

   /** Testing Main Menu **/
   /*public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new YouLose(); // Constructor does the job
         }
      });
   }*/
}
