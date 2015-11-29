import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
 
// A Swing GUI application that creates a small window for the player to enter their name
public class TieGame extends JFrame {
 
   /** Constructor to setup the GUI */
   public TieGame () {
      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();
      container.setLayout(new FlowLayout());
 
      container.add(new JLabel("Tie Game!"));
 
      JButton rematchBtn = new JButton("Rematch");
      JButton backToMenu = new JButton("Main Menu");
      container.add(rematchBtn);
      container.add(backToMenu);
 
      // Create action listener for button
      rematchBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //What happens after button is clicked
            // components are deleted from frame, new components are brought up
            //Test
            System.out.println("rematch twas clicked");
         }
      });


      backToMenu.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            //What happens after button is clicked
            // components are deleted from frame, new components are brought up
            //Test
            System.out.println("menu twas clicked");
         }
      });
 

      setLocationRelativeTo(null);      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Tie Game"); // "this" JFrame sets title
      setSize(300, 100);         // "this" JFrame sets initial size
      setVisible(true);          // "this" JFrame shows
   }
 
   /** Testing Pop Up **/
   public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new TieGame(); // Constructor does the job
         }
      });
   }
}