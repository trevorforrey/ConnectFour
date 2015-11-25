import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers
 
// A Swing GUI application that creates a small window for the player to enter their name
public class PortPopUp extends JFrame {
   private JTextField portInput;
   private JTextField ipInput;
   private int ipNumber;
   private int portNumber;
 
   /** Constructor to setup the GUI */
   public PortPopUp () {
      // Retrieve the content-pane of the top-level container JFrame
      // All operations done on the content-pane
      Container container = getContentPane();
      container.setLayout(new FlowLayout());
 
      JLabel portNumberPrompt = new JLabel("Enter the port number");
      portInput = new JTextField("", 10);
      portInput.setEditable(true);

      JLabel ipAddressPrompt = new JLabel("Enter the ip address");
      ipInput = new JTextField("", 10);
      ipInput.setEditable(true);

      JButton enterNetworkDataButton = new JButton("Enter");

      container.add(portNumberPrompt);
      container.add(portInput);
      container.add(ipAddressPrompt);
      container.add(ipInput);
      container.add(enterNetworkDataButton);
 
      // Create action listener for button
      enterNetworkDataButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            portNumber = Integer.parseInt(portInput.getText());
            ipNumber = Integer.parseInt(ipInput.getText());

            //Network program creates connection between both players

            //Test
            System.out.println(portNumber);
            System.out.println(ipNumber);
         }
      });
 
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
      setTitle("Setting Up Connection"); // "this" JFrame sets title
      setSize(300, 100);         // "this" JFrame sets initial size
      setVisible(true);          // "this" JFrame shows
   }
 
   /** Testing Pop Up **/
   public static void main(String[] args) {
      // Run the GUI construction in the Event-Dispatching thread for thread-safety
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            new PortPopUp(); // Constructor does the job
         }
      });
   }
}