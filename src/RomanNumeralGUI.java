//Display the results in a GUI with a GridLayout of one row and three columns. 
//The 1st column should contain the original RomanNumerals, the 2nd column the unsorted Arabic values, 
// &the 3rd column the sorted Arabic values.

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.swing.*;

public class RomanNumeralGUI extends JFrame {

	//Creating Global Variables 
	TextArea romanNum;
	TextArea SortedArabic;
	TreeMap<RomanNumeral, Integer> SortedRoman;
	

	/**
	 * RomanNumeralGUI creates a GUI with three columns
	 * 
	 */
	public RomanNumeralGUI() { //

		setSize(600, 300); // size of GUI
		setLocation(400, 200); // where GUI opens up on screen
		setTitle("Roman Numerals"); // Name of GUI
		this.setLayout(new GridLayout(1, 2)); // 1 row , 3 columns
		createMenu();

		romanNum = new TextArea(10, 20);
		SortedArabic = new TextArea(10, 20);
		this.add(romanNum);
		// this.add appends the romanNum to the end of the component
		this.add(SortedArabic);
		// set to exist program when closed

		SortedRoman = new TreeMap<RomanNumeral, Integer>(new RomanNumeralComparator());
		//Create TreeMap which passes a key: RomannNumeral with a value: integer 
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
/**
 * Creates tool bar: with options of open, quit and converter 
 */
	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		//Creating the menu options-> Open, Quit, and Converter 
		JMenuItem open;
		JMenuItem quit;
		JMenuItem converter;

		open = new JMenuItem("Open"); //Instructs open operation 

		open.addActionListener(e -> openFile());
		// Lambda expression : an anonymous class that implements a functional interface
		// a coding short cut for the method actionPerformed(ActionEvent event)

		fileMenu.add(open);
		fileMenu.addSeparator();
		quit = new JMenuItem("Quit");

		quit.addActionListener(e -> System.exit(0));
		//Lambda expression: will instantly close program when quit is selected 

		fileMenu.add(quit);
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		JMenu convertMenu = new JMenu("Convert");
		converter = new JMenuItem("Roman to Arabic");
		//converter tool will have the Roman To Arabic item option 
		convertMenu.add(converter);
		menuBar.add(convertMenu);
		converter.addActionListener(e -> convertToArabic());
		//Lambda expression: will convert the Roman To Arabic 

	} // createMenu

	/**
	 * When Converter is selected JOptionPane will ask user to enter a Roman Numeral
	 * user will have to enter a character 
	 * if character matches a Roman Numeral then the Arabic value will appear 
	 * if it does not then an error message will appear : if the character entered by user 
	 * is =0; which means user input is invalid 
	 */
	public void convertToArabic() {
		String input = JOptionPane.showInputDialog("Please input Roman Numeral: ");
		RomanNumeral temp = new RomanNumeral(input);
		if (temp.getArabicValue() == 0)
			JOptionPane.showMessageDialog(null, "Invalid", "Incorrect", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(null, "Arabic Value= " + temp.getArabicValue());

		// TODO Auto-generated method stub
	}
	/**
	 * openFile which takes in no arguments does a file check when user selects open file
	 * if the file is approved then file will be able to be read 
	 * if file is empty then a message will appear "Open File Dialog canceled"
	 */
	public void openFile() {
		System.out.println("Open File");
		JFileChooser chooser;
		int status; 
		chooser = new JFileChooser();
		status = chooser.showOpenDialog(null);
		if (status == JFileChooser.APPROVE_OPTION)
			readSource(chooser.getSelectedFile().getAbsolutePath());
		// getAbsolutePath tells were SelctedFile lives
		else
			JOptionPane.showMessageDialog(null, "Open File dialog canceled");

		// TODO Auto-generated method stub

	}
	
	/**
	 * 
	 * @param filePath : calls clear method to clear out any previous open files 
	 * Reads through file while storing data 
	 * sets key data , while looping through tree map 
	 */
	public void readSource(String filePath) {
		clear();
		//calls clear method to clear out all previous file's imported
		TextFileInput input = new TextFileInput(filePath);
		String line = input.readLine();
		// we want to continue reading the file until there are no more lines so we do
		// while line is not null

		while (line != null) {
			// Because there is more than one Roman numeral one each line separated by a
			// comma we want to make sure we separate and recognize there are elements after
			// and before each ","
			StringTokenizer tokens = new StringTokenizer(line, ",");
			// if there still more Roman Numerals on the line we want to continue storing
			// them
			while (tokens.hasMoreTokens()) {
				String data = tokens.nextToken();
				
//						 take data string -> Roman numeral pass it through the list 
				RomanNumeral r = new RomanNumeral(data);
				SortedRoman.put(r, r.getArabicValue());

			}
			// when we are done with the Roman numeral on that line we want to take in and
			// store the Roman Numerals
			line = input.readLine();
		}
		input.close();
		for(RomanNumeral key: SortedRoman.keySet()) {
			Object value = SortedRoman.get(key);
	//looping through tree Map and we add each one to the GUI
			SortedArabic.append (value.toString()+"\n");
			romanNum.append(key.toStringGUI());
		}
	}
/**
 * clear method clears out all texts from previous selected files
 * when a new file is selected the prior file won't show in GUI 
 */
	public void clear() {
		SortedRoman.clear();
		romanNum.setText("");//clears text areas
		SortedArabic.setText("");
	}
}
