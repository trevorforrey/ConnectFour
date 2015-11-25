import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
 
// A Swing GUI application that creates a small window for the player to enter their name
public class PlayerNamePopUp extends JFrame {
   private JTextField playerNameInput;
   private String playerName;
 
   /** Constructor to setup the GUI */
   public PlayerNamePopUp () {
      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();
      container.setLayout(new FlowLayout());
 
      container.add(new JLabel("Enter your name"));
      playerNameInput = new JTextField("", 10);
      playerNameInput.setEditable(true);
      container.add(playerNameInput);
 
      JButton enterNameButton = new JButton("Enter");
      container.add(enterNameButton);
 
      // Create action listener for button
      enterNameButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //What happens after button is clicked
            playerName = playerNameInput.getText();
            // components are deleted from frame, new components are brought up
            //Test
            System.out.println(playerName);
         }
      });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Player Name"); // "this" JFrame sets title
      setSize(300, 100);         // "this" JFrame sets initial size
      setVisible(true);          // "this" JFrame shows
   }
 
   /** Testing Pop Up **/
   public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new PlayerNamePopUp(); // Constructor does the job
         }
      });
   }
}