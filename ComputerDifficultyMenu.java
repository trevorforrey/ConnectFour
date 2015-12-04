import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers


// Creates a main menu window for the user to begin a connect four game
public class ComputerDifficultyMenu extends JFrame {

   private int easyComputerGame = 1;
   private int mediumComputerGame = 2;
   private int hardComputerGame = 3;
 
   /** Constructor to setup the GUI */
   public ComputerDifficultyMenu () {

      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();


      container.setLayout(new BorderLayout(0,0)); 

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Computer Difficulty"); // Sets title
      setSize(900, 630);         // Sets initial size
      setVisible(true);          // Shows
      setLocationRelativeTo(null); // Displays frame in the middle of the screen



      // Creation of components

      // Creates easy computerPlayer button and sets image for the button
      JButton easyComputerButton = new JButton();
      easyComputerButton.setIcon(new ImageIcon(getClass().getResource("Assets/easyComputerButtonCover.jpg"))); // Placeholder image
   
      
      // Creates medium computerPlayer button and sets image for the button
      JButton mediumComputerButton = new JButton();
      mediumComputerButton.setIcon(new ImageIcon(getClass().getResource("Assets/mediumComputerButtonCover.jpg"))); // Placeholder image


      // Creates hard computerPlayer button and sets image for the button
      JButton hardComputerButton = new JButton();
      hardComputerButton.setIcon(new ImageIcon(getClass().getResource("Assets/hardComputerButtonCover.jpg"))); // Placeholder image




      // Components added to the frame

      container.add(easyComputerButton, BorderLayout.NORTH);
      container.add(mediumComputerButton, BorderLayout.CENTER);
      container.add(hardComputerButton, BorderLayout.SOUTH);
 


      // Action Listeners


      // Easy ComputerPlayer Button action listener
      easyComputerButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // Components are deleted from frame, new components are brought up
            dispose();

            // Calls board board and integer representing the type of game the player chose (easy computer)
            ConnectFourGUI board = new ConnectFourGUI(easyComputerGame);
            board.setVisible(true);


            //Test
            System.out.println("Easy Computer was clicked");
         }
      });


      // Medium ComputerPlayer Button action listener
      mediumComputerButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // Components are deleted from frame, new components are brought up
            dispose();

            // Calls board board and integer representing the type of game the player chose (medium computer)
            ConnectFourGUI board = new ConnectFourGUI(mediumComputerGame);
            board.setVisible(true);


            //Test
            System.out.println("Medium Computer was clicked");
         }
      });

      
      // Hard ComputerPlayer Button action listener
      hardComputerButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // Components are deleted from frame, new components are brought up
            dispose();

            // Calls board board and integer representing the type of game the player chose (hard computer)
            ConnectFourGUI board = new ConnectFourGUI(hardComputerGame);
            board.setVisible(true);


            //Test
            System.out.println("Hard Computer was clicked");
         }
      });
   }
}
