import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystem;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileSystemView;

public class Main_RealNumber {

	private JFrame frame;
	private JButton file_search_btn; 
	private JTextPane textPane;
	private JButton mean_sd_button; 
	private JTextPane mean_textPane; 
	private JTextPane textPane_1; 
	private File selected;
	private LinkList myList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_RealNumber window = new Main_RealNumber();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_RealNumber() {
		initialize();
		
		myList = new LinkList();
		//when the user clicks on the 'choose a file' button
		//prompt a popup window that will allow them to access a txt file to be read 
		file_search_btn.addActionListener((ActionListener) new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//@param JFileChooser opens up home directory of the user's computer so that they can 
				//look for a specific file to use 
				JFileChooser myFile = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				
				//@param returnValue is the number that will change depending on the whether the user selects anything 
				int returnValue = myFile.showOpenDialog(myFile);
				
				//if value is approved/bigger than zero?
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					//set the approve directory location as a string
					//@param selected with equal the file directory 
					//@param textPane will hold the directory as a string and display it for the user to see 
					selected = myFile.getSelectedFile();
					textPane.setText(selected.toString());		
					//set a method for that file to be read and interpreted 
					readMyFile(selected);	
				}
			}
		});
		
		//click mean_sd_button to retrieve the mean and sd of the link list 
		mean_sd_button.addActionListener((ActionListener) new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mean_textPane.setText(displayMean());
				//textPane_1.setText(displaySD());
				System.out.println(displaySD());
			}
		});
		

	}
	
	//method that will read the file 
	public void readMyFile (File s) {
		try {
			Scanner src = new Scanner (new File (s.toString()));
			
			while(src.hasNextLine()) {
				//check for possible conditions 
				
				//if txt file is empty 
				if(s == null){
					System.out.print("Your file is empty");
				}
				//if file has one line
				else if (s.length() ==2) {
					System.out.print("You choose a file with only number. There should be at least two to compute the mean and SD.");
				}
				//go through and add to the link list 
				else {
					Integer newNumber = Integer.parseInt(src.nextLine());
					myList.add(newNumber);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*
	 * this is a method that will compute the mean and return the actual value 
	 * @param mean holds the value of all the integer nodes that have been added together and divided by the total length of the linklist 
	 */
	
	public int computeMean () {
		int sum = myList.sumRecursive(myList.getHead());
		int length = myList.length();
		
		int mean = sum / length; 
		
		return mean; 
	}
	
	/*
	 * this next method will return the SD of entire Linklist using the mean founded from the previous method 
	 * 
	 */
	
	public int findStandardDeviation() {
		int overallMean = computeMean();
		int lengthOfll = myList.length();
		int sd=0; 
		while (myList.getHead() !=null) {
			sd+=Math.pow(myList.getHead().myInt - overallMean, 2);
		}
		return (int) Math.sqrt(sd/lengthOfll);
	}
	
	
	
	public String displayMean () {
		int meanTotal = computeMean();
		String mString = Integer.toString(meanTotal);
		//debugging purposes 
		//System.out.println(myList.length());
		return mString;
	}
	
	public String displaySD() {
		int sd = findStandardDeviation();
		String mSD = Integer.toString(sd);
		return mSD;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 102));
		frame.getContentPane().setForeground(new Color(0, 0, 102));
		frame.setBounds(100, 100, 572, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel title_header = new JLabel("Mean and SD Calculator ");
		title_header.setForeground(new Color(255, 153, 102));
		title_header.setFont(new Font("Century", title_header.getFont().getStyle(), title_header.getFont().getSize() + 16));
		title_header.setHorizontalAlignment(SwingConstants.CENTER);
		title_header.setBounds(93, 28, 380, 39);
		frame.getContentPane().add(title_header);
		
		file_search_btn = new JButton("Choose a File ");
		file_search_btn.setFont(new Font("Bell MT", file_search_btn.getFont().getStyle(), file_search_btn.getFont().getSize() + 10));
		file_search_btn.setBounds(172, 84, 221, 39);
		frame.getContentPane().add(file_search_btn);
		
		textPane = new JTextPane();
		textPane.setBounds(145, 146, 280, 27);
		frame.getContentPane().add(textPane);
		
		mean_sd_button = new JButton("Calculate the Mean and SD");
		mean_sd_button.setBounds(194, 189, 175, 39);
		frame.getContentPane().add(mean_sd_button);
		
		mean_textPane = new JTextPane();
		mean_textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		mean_textPane.setBounds(120, 282, 109, 51);
		frame.getContentPane().add(mean_textPane);
		
		textPane_1 = new JTextPane();
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textPane_1.setBounds(366, 282, 107, 51);
		frame.getContentPane().add(textPane_1);
		
		JLabel lblNewLabel = new JLabel("Mean : ");
		lblNewLabel.setForeground(new Color(255, 153, 0));
		lblNewLabel.setFont(new Font("Century", lblNewLabel.getFont().getStyle(), lblNewLabel.getFont().getSize() + 8));
		lblNewLabel.setBounds(46, 297, 82, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel sd_label = new JLabel("SD : ");
		sd_label.setForeground(new Color(255, 153, 0));
		sd_label.setFont(new Font("Century", sd_label.getFont().getStyle(), sd_label.getFont().getSize() + 8));
		sd_label.setBounds(318, 297, 63, 19);
		frame.getContentPane().add(sd_label);
	}
}
