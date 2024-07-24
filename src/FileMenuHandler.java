import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

//Java ActionListener is alerted whenever the button or menu item is clicked.
//When the user clicks a button, chooses a menu item, presses Enter
public class FileMenuHandler implements ActionListener {
   JFrame jframe;
   public FileMenuHandler (JFrame jf) {
      jframe = jf;
   }
   /**
    * actionPerformed is a method that will set options in tool bar
    * will perform the selection user made   
    * if user selects open, the selected file will open
    * if user selects quit file will close 
    */
   public void actionPerformed(ActionEvent event) {
      String  menuName;
      menuName = event.getActionCommand();
      if (menuName.equals("Open"))
         openFile(); 
      else if (menuName.equals("Quit"))
         System.exit(0);
   } 
/**
 * openFile method will process the selected file by user 
 * and will open file 
 */
    private void openFile( ) {
       JFileChooser chooser;
       int          status; 
       chooser = new JFileChooser( );
       status = chooser.showOpenDialog(null);
       if (status == JFileChooser.APPROVE_OPTION) 
          readSource(chooser.getSelectedFile());
       else 
          JOptionPane.showMessageDialog(null, "Open File dialog canceled");
    } 
  
    /**
     * 
     * @param chosenFile
     * will create a layout of where the text info will be placed 
     * The info in input.txt will read line by line and appending it to a string
     */
    private void readSource(File chosenFile) {
       String chosenFileName = chosenFile.getName();
       TextFileInput inFile = new TextFileInput(chosenFileName);
       String ssn;
       int subscript = 0;
       Container myContentPane = jframe.getContentPane();
       TextArea myTextArea = new TextArea();
       TextArea mySubscripts = new TextArea();
       myContentPane.add(myTextArea, BorderLayout.EAST);
       myContentPane.add(mySubscripts, BorderLayout.WEST);
    
       ssn = inFile.readLine();
       while (ssn != null) {
          mySubscripts.append(Integer.toString(subscript++)+"\n");
          myTextArea.append(ssn+"\n");
          ssn = inFile.readLine();
       } 
       //    allows information to be displayed/ visible 
     jframe.setVisible(true);  
    }
 
}