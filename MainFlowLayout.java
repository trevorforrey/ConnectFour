import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.util.*;


// Creates a main menu window for the user to begin a connect four game
public class MainFlowLayout extends JFrame {
 
   /** Constructor to setup the GUI */
   public MainFlowLayout () {

      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();

      JPanel gameModes = new JPanel();
      gameModes.setLayout(new FlowLayout());

      JPanel cover = new JPanel();

      /**
         We might want to do a flow layout so that the menu is responsive
         Let me know what you guys think
         -Trevor
      **/
      container.setLayout(new BorderLayout(0,0)); 

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Connect Four"); // Sets title
      setSize(970, 710);         // Sets initial size
      setVisible(true);          // Shows
      setLocationRelativeTo(null); // Displays frame in the middle of the screen



      // Creation of components

      // Creates menu title cover and sets image for the menu
      JLabel menuTitleCover = new JLabel();
      menuTitleCover.setIcon(new ImageIcon(getClass().getResource("Assets/ConnectFourCover2.jpg"))); // Placeholder image

      
      // Creates player vs player button and sets image for the button
      JButton pvpButton = new JButton();
      pvpButton.setIcon(new ImageIcon(getClass().getResource("Assets/pvpFlowLayout.jpg"))); // Placeholder image
      //pvpButton.setBorder(BorderFactory.createEmptyBorder());
      //pvpButton.setContentAreaFilled(false);


      // Creates player vs computer button and sets image for the button
      JButton pvcButton = new JButton();
      pvcButton.setIcon(new ImageIcon(getClass().getResource("Assets/pvcFlow.jpg"))); // Placeholder image
      //pvcButton.setBorder(BorderFactory.createEmptyBorder());
      //pvcButton.setContentAreaFilled(false);

      // Creates player vs player online button and sets image for the button
      JButton pvpOnlineButton = new JButton();
      pvpOnlineButton.setIcon(new ImageIcon(getClass().getResource("Assets/pvpOnlineFlow.jpg"))); // Placeholder image
      //pvpOnlineButton.setBorder(BorderFactory.createEmptyBorder());
      //pvpOnlineButton.setContentAreaFilled(false);




      // Components added to the frame

      cover.add(menuTitleCover);

      gameModes.add(pvpButton);
      gameModes.add(pvcButton);
      gameModes.add(pvpOnlineButton);

      
      container.add(gameModes, BorderLayout.SOUTH);
      container.add(cover, BorderLayout.NORTH);
 


      // Action Listeners


      // Player vs Player action listener
      pvpButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // Components are deleted from frame, new components are brought up
            dispose();
            Connect4TimerExample board = new Connect4TimerExample(0);
            board.setVisible(true);


            //Test
            System.out.println("Player vs Player was clicked");
         }
      });


      // Player vs Computer action listener
      pvcButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // Components are deleted from frame, new components are brought up
            dispose();
            // Menu to choose computer difficulty is brought up
            ComputerDifficultyMenu difficultyMenu = new ComputerDifficultyMenu();


            //Test
            System.out.println("Player vs Computer was clicked");
         }
      });
   }
 

   /** Testing Main Menu **/
   public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new MainFlowLayout(); // Constructor does the job
         }
      });
   }
}
