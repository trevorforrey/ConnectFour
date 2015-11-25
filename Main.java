import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
 
// Creates a main menu window for the user to begin a connect four game
public class Main extends JFrame {
 
   /** Constructor to setup the GUI */
   public Main () {

      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();

      /**
         We might want to do a flow layout so that the menu is responsive
         Let me know what you guys think
         -Trevor
      **/
      container.setLayout(new BorderLayout(0,0)); 

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Connect Four"); // Sets title
      setSize(900, 700);         // Sets initial size
      setVisible(true);          // Shows
      setLocationRelativeTo(null); // Displays frame in the middle of the screen



      // Creation of components

      // Creates menu title cover and sets image for the menu
      JLabel menuTitleCover = new JLabel();
      menuTitleCover.setIcon(new ImageIcon("Assets/ConnectFourCover.jpg")); // Placeholder image

      
      // Creates player vs player button and sets image for the button
      JButton pvpButton = new JButton();
      pvpButton.setIcon(new ImageIcon("Assets/PlayerVsPlayerCover2.jpg")); // Placeholder image


      // Creates player vs computer button and sets image for the button
      JButton pvcButton = new JButton();
      pvcButton.setIcon(new ImageIcon("Assets/PlayerVsComputerCover2.jpg")); // Placeholder image




      // Components added to the frame

      container.add(menuTitleCover, BorderLayout.CENTER);
      container.add(pvpButton, BorderLayout.WEST);
      container.add(pvcButton, BorderLayout.EAST);
 


      // Action Listeners


      // Player vs Player action listener
      pvpButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            // Components are deleted from frame, new components are brought up
            dispose();
            Connect4TimerExample board = new Connect4TimerExample(1);
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
            Connect4TimerExample board = new Connect4TimerExample(2);
            board.setVisible(true);


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
            new Main(); // Constructor does the job
         }
      });
   }
}
