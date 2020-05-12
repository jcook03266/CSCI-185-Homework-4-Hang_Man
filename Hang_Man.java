package NotAPhotoShopCopy;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;  
import javax.swing.JOptionPane;
import java.awt.*;  
import javax.swing.*;  
import javax.swing.JButton; 
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.Hashtable;

/*Written by Justin, Brenno, Afaq
 *CSCI 185 M06
 *Java class 201
 *5/12/20
 *Spring 2020
 *Homework 4: Create Your Own Java GUI Program
 **/

public class Hang_Man extends JFrame
{ /**
 * 
 */
	private static final long serialVersionUID = 1L;

	JFrame f; //setting our JFrame variable
	JOptionPane pane = new JOptionPane();//creating a new pane
	private int canvas_size_percentage = 100;//canvas zoom percentage, will be utilized later on 
	private String save_name = null;//save name, to be determined by the user 
	private int press_amount = 0;//Press amount of the top drop down menu buttons, prevents multiple menus from being open at once
	private int index = 0;//index for the line drawing process
	private Point[] arr = new Point[1000000];//stored points for drawing, the limit is very high but will never be reached realistically speaking 
	private BufferedImage im;//buffered image variable, will be used to save our masterpiece later on
	private Graphics2D h;//2D graphics variables assigned to the created graphic of the buffered image object 
	private boolean Pen_Tool = true;//the default tool the user will be using, small compact, reliable
	private boolean Marker_Tool = false;//secondary pen tool the user has access to, much larger, and less uniform as a result
	private boolean Eraser_Tool = false;//Erases the mistakes of the pass, used in place of an undo button for now
	private Color pen_color = Color.black;//the default color of the pen 
	private int brush_size_unit = 5;//the default size of the pen
	private int eraser_size_unit = 50;//the default size of the eraser pen 
	//end of instance variable declarations 

	public Hang_Man(){
		//New frame is enumerated 
		f = new JFrame();

		//New border styles
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border whiteline = BorderFactory.createLineBorder(Color.white);
		Border grayline = BorderFactory.createLineBorder(Color.gray);

		//Fonts 
		Font f1 = new Font("Helvetica",Font.PLAIN, 10);
		Font f2 = new Font("Helvetica",Font.PLAIN, 15);


		//Color(s)
		Color white = new Color(255,255,255);

		//Panel Styling note:
		//(x, y, width, height) x & y refer to the coordinates of the upper left corner of the component relative to the frame corner
		//with 0,0 being the actual corner of the frame / window
		//Panels
		//File Drop Down menu Panel
		JPanel File_Panel_Expansion = new JPanel();
		File_Panel_Expansion.setLayout(new GridLayout(4, 1));
		File_Panel_Expansion.setBounds(50,30,120,120); 
		File_Panel_Expansion.setBackground(Color.BLACK);
		File_Panel_Expansion.setBorder(whiteline);
		File_Panel_Expansion.setVisible(false);
		//File's Drop Down Menu buttons

		//New button
		JPanel File_Menu_Button_New = new JPanel();
		File_Menu_Button_New.setLayout(new BorderLayout());
		File_Menu_Button_New.setBackground(Color.BLACK);
		JButton File_New = new JButton("New");
		File_New.setFont(f2);
		File_New.setHorizontalAlignment(SwingConstants.LEFT);
		File_New.setBackground(Color.black);
		File_New.setForeground(Color.WHITE);
		File_Menu_Button_New.add(File_New);
		File_Panel_Expansion.add(File_Menu_Button_New);

		//Save button
		JPanel File_Menu_Button_Save = new JPanel();
		File_Menu_Button_Save.setLayout(new BorderLayout());
		File_Menu_Button_Save.setBackground(Color.BLACK);
		JButton File_Save = new JButton("Save");
		File_Save.setFont(f2);
		File_Save.setHorizontalAlignment(SwingConstants.LEFT);
		File_Save.setBackground(Color.black);
		File_Save.setForeground(Color.WHITE);
		File_Menu_Button_Save.add(File_Save);
		File_Panel_Expansion.add(File_Menu_Button_Save);

		//Save As button
		JPanel File_Menu_Button_Save_As = new JPanel();
		File_Menu_Button_Save_As.setLayout(new BorderLayout());
		File_Menu_Button_Save_As.setBackground(Color.BLACK);
		JButton File_Save_As = new JButton("Save As...");
		File_Save_As.setFont(f2);
		File_Save_As.setHorizontalAlignment(SwingConstants.LEFT);
		File_Save_As.setBackground(Color.black);
		File_Save_As.setForeground(Color.WHITE);
		File_Menu_Button_Save_As.add(File_Save_As);
		File_Panel_Expansion.add(File_Menu_Button_Save_As);

		//Exit button
		JPanel File_Menu_Button_Exit = new JPanel();
		File_Menu_Button_Exit.setLayout(new BorderLayout());
		File_Menu_Button_Exit.setBackground(Color.BLACK);
		JButton File_Exit = new JButton("Exit");
		File_Exit.setFont(f2);
		File_Exit.setHorizontalAlignment(SwingConstants.LEFT);
		File_Exit.setBackground(Color.black);
		File_Exit.setForeground(Color.WHITE);
		File_Menu_Button_Exit.add(File_Exit);
		File_Panel_Expansion.add(File_Menu_Button_Exit);
		f.add(File_Panel_Expansion);
		//End of File's Drop Down Menu

		//Mini Side Panel 3 Subset 3
		JPanel Mini_Side_Panel3_Subset3 = new JPanel();
		Mini_Side_Panel3_Subset3.setLayout(new BorderLayout());
		Mini_Side_Panel3_Subset3.setBounds(1240,660,20,15); 
		Mini_Side_Panel3_Subset3.setBorder(blackline);
		Mini_Side_Panel3_Subset3.setBackground(Color.GRAY);
		f.add(Mini_Side_Panel3_Subset3);

		//Mini Side Panel 3 Subset 2
		JPanel Mini_Side_Panel3_Subset2 = new JPanel();
		Mini_Side_Panel3_Subset2.setLayout(new BorderLayout());
		Mini_Side_Panel3_Subset2.setBounds(1140,655,100,20); 
		Mini_Side_Panel3_Subset2.setBorder(blackline);
		Mini_Side_Panel3_Subset2.setBackground(Color.LIGHT_GRAY);
		f.add(Mini_Side_Panel3_Subset2);

		//Mini Side Panel 3 Subset 1
		JPanel Mini_Side_Panel3_Subset1 = new JPanel();
		Mini_Side_Panel3_Subset1.setLayout(new BorderLayout());
		Mini_Side_Panel3_Subset1.setBounds(1040,650,100,25);
		Mini_Side_Panel3_Subset1.setBorder(blackline);
		Mini_Side_Panel3_Subset1.setBackground(Color.DARK_GRAY);
		Mini_Side_Panel3_Subset1.add(new JLabel("<html><font size='3.5'color=white> Event Log </font></html>"));
		f.add(Mini_Side_Panel3_Subset1);

		//Mini Side Panel 3.5 //The event log //Write to the textarea to display the triggered events for the User
		JPanel Mini_Side_Panel3_5 = new JPanel();
		Mini_Side_Panel3_5.setLayout(new BorderLayout());
		Mini_Side_Panel3_5.setBounds(1050,685,200,230); 
		Mini_Side_Panel3_5.setBorder(blackline);
		Mini_Side_Panel3_5.setBackground(Color.DARK_GRAY);
		JTextArea MSP3_5 = new JTextArea();
		Mini_Side_Panel3_5.add(MSP3_5);
		MSP3_5.setEditable(false);
		JScrollPane MSP3_5SP = new JScrollPane(MSP3_5);
		MSP3_5SP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		MSP3_5SP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		Mini_Side_Panel3_5.add(MSP3_5SP);
		f.add(Mini_Side_Panel3_5);

		//Mini Side Panel 3
		JPanel Mini_Side_Panel3 = new JPanel();
		Mini_Side_Panel3.setLayout(new BorderLayout());
		Mini_Side_Panel3.setBounds(1040,675,220,250); 
		Mini_Side_Panel3.setBorder(blackline);
		Mini_Side_Panel3.setBackground(Color.GRAY);
		f.add(Mini_Side_Panel3);

		//Mini Side Panel 2 Subset 3
		JPanel Mini_Side_Panel2_Subset3 = new JPanel();
		Mini_Side_Panel2_Subset3.setLayout(new BorderLayout());
		Mini_Side_Panel2_Subset3.setBounds(1240,280,20,15); 
		Mini_Side_Panel2_Subset3.setBorder(blackline);
		Mini_Side_Panel2_Subset3.setBackground(Color.GRAY);
		f.add(Mini_Side_Panel2_Subset3);

		//Mini Side Panel 2 Subset 2
		JPanel Mini_Side_Panel2_Subset2 = new JPanel();
		Mini_Side_Panel2_Subset2.setLayout(new BorderLayout());
		Mini_Side_Panel2_Subset2.setBounds(1140,275,100,20); 
		Mini_Side_Panel2_Subset2.setBorder(blackline);
		Mini_Side_Panel2_Subset2.setBackground(Color.LIGHT_GRAY);
		f.add(Mini_Side_Panel2_Subset2);

		//Mini Side Panel 2 Subset 1
		JPanel Mini_Side_Panel2_Subset1 = new JPanel();
		Mini_Side_Panel2_Subset1.setLayout(new BorderLayout());
		Mini_Side_Panel2_Subset1.setBounds(1040,270,100,25);
		Mini_Side_Panel2_Subset1.setBorder(blackline);
		Mini_Side_Panel2_Subset1.setBackground(Color.DARK_GRAY);
		Mini_Side_Panel2_Subset1.add(new JLabel("<html><font size='3.5'color=white> JLabel Here </font></html>"));
		f.add(Mini_Side_Panel2_Subset1);

		//Mini Side Panel 2.5 
		JPanel Mini_Side_Panel2_5 = new JPanel();
		Mini_Side_Panel2_5.setLayout(new GridLayout(2, 2));
		Mini_Side_Panel2_5.setBounds(1050,305,200,330); 
		Mini_Side_Panel2_5.setBorder(blackline);
		Mini_Side_Panel2_5.setBackground(Color.DARK_GRAY);
		f.add(Mini_Side_Panel2_5);

		//Mini Side Panel 2 
		JPanel Mini_Side_Panel2 = new JPanel();
		Mini_Side_Panel2.setLayout(new BorderLayout());
		Mini_Side_Panel2.setBounds(1040,295,220,350); 
		Mini_Side_Panel2.setBorder(blackline);
		Mini_Side_Panel2.setBackground(Color.GRAY);
		f.add(Mini_Side_Panel2);

		//Mini Side Panel 1 Subset 3
		JPanel Mini_Side_Panel1_Subset3 = new JPanel();
		Mini_Side_Panel1_Subset3.setLayout(new BorderLayout());
		Mini_Side_Panel1_Subset3.setBounds(1240,30,20,15); 
		Mini_Side_Panel1_Subset3.setBorder(blackline);
		Mini_Side_Panel1_Subset3.setBackground(Color.GRAY);
		f.add(Mini_Side_Panel1_Subset3);

		//Mini Side Panel 1 Subset 2
		JPanel Mini_Side_Panel1_Subset2 = new JPanel();
		Mini_Side_Panel1_Subset2.setLayout(new BorderLayout());
		Mini_Side_Panel1_Subset2.setBounds(1140,25,100,20); 
		Mini_Side_Panel1_Subset2.setBorder(blackline);
		Mini_Side_Panel1_Subset2.setBackground(Color.LIGHT_GRAY);
		f.add(Mini_Side_Panel1_Subset2);

		//Mini Side Panel 1 Subset 1
		JPanel Mini_Side_Panel1_Subset1 = new JPanel();
		Mini_Side_Panel1_Subset1.setLayout(new BorderLayout());
		Mini_Side_Panel1_Subset1.setBounds(1040,20,100,25); 
		Mini_Side_Panel1_Subset1.setBorder(blackline);
		Mini_Side_Panel1_Subset1.setBackground(Color.DARK_GRAY);
		Mini_Side_Panel1_Subset1.add(new JLabel("<html><font size='3.5'color=white> JLabel Here </font></html>"));
		f.add(Mini_Side_Panel1_Subset1);

		//Mini Side Panel 1.5 
		JPanel Mini_Side_Panel1_5 = new JPanel();
		Mini_Side_Panel1_5.setLayout(new GridLayout(10, 2));
		Mini_Side_Panel1_5.setBounds(1050,55,200,200); 
		Mini_Side_Panel1_5.setBorder(blackline);
		Mini_Side_Panel1_5.setBackground(Color.DARK_GRAY);
		f.add(Mini_Side_Panel1_5);

		//Mini Side Panel 1 
		JPanel Mini_Side_Panel1 = new JPanel();
		Mini_Side_Panel1.setLayout(new BorderLayout());
		Mini_Side_Panel1.setBounds(1040,45,220,220); 
		Mini_Side_Panel1.setBorder(blackline);
		Mini_Side_Panel1.setBackground(Color.GRAY);
		f.add(Mini_Side_Panel1);

		//Side Panel 1
		JPanel Side_Panel1 = new JPanel();
		Side_Panel1.setLayout(new BorderLayout());
		Side_Panel1.setBounds(1030,15,250,940); 
		Side_Panel1.setBorder(blackline);
		Side_Panel1.setBackground(Color.DARK_GRAY);
		f.add(Side_Panel1);

		//Window Padding 1
		JPanel Window_Padding1 = new JPanel();
		Window_Padding1.setLayout(new BorderLayout());
		Window_Padding1.setBounds(1040,30,220,900); 
		Window_Padding1.setBorder(blackline);
		Window_Padding1.setBackground(Color.GRAY);
		f.add(Window_Padding1);

		//Left Side panel1
		JPanel Left_Side_Panel1 = new JPanel();
		Left_Side_Panel1.setLayout(new BorderLayout());
		Left_Side_Panel1.setBounds(0,30,50,950); 
		Left_Side_Panel1.setBorder(blackline);
		Left_Side_Panel1.setBackground(Color.DARK_GRAY);
		f.add(Left_Side_Panel1);

		//Page Header 2 Readout Console 
		JTextArea Page_Readout_Console = new JTextArea();
		Page_Readout_Console.setLayout(new BorderLayout());
		Page_Readout_Console.setBounds(140,33,860,18); 
		Page_Readout_Console.setBorder(grayline);
		Page_Readout_Console.setFont(f2);
		Page_Readout_Console.setEditable(false);
		Page_Readout_Console.setBackground(Color.WHITE);
		f.add(Page_Readout_Console);

		//Page Header 2 Readout Console subset 
		JTextArea Page_Readout_Console_Label = new JTextArea();
		Page_Readout_Console_Label.setLayout(new BorderLayout());
		Page_Readout_Console_Label.setBounds(70,33,70,18); 
		Page_Readout_Console_Label.setBorder(grayline);
		Page_Readout_Console_Label.setFont(f2);
		Page_Readout_Console_Label.setText("\\\\Console:");
		Page_Readout_Console_Label.setEditable(false);
		Page_Readout_Console_Label.setBackground(Color.WHITE);
		f.add(Page_Readout_Console_Label);

		//Page Header 2 Readout Console subset 2 
		JTextArea Page_Readout_Console_Label2 = new JTextArea();
		Page_Readout_Console_Label2.setLayout(new BorderLayout());
		Page_Readout_Console_Label2.setBounds(1000,33,30,18); 
		Page_Readout_Console_Label2.setBorder(grayline);
		Page_Readout_Console_Label2.setFont(f2);
		Page_Readout_Console_Label2.setText(":\\\\");
		Page_Readout_Console_Label2.setEditable(false);
		Page_Readout_Console_Label2.setBackground(Color.WHITE);
		f.add(Page_Readout_Console_Label2);

		//Page Header 2 Inside Panel
		JPanel Page_Header2_Inside = new JPanel();
		Page_Header2_Inside.setLayout(new BorderLayout());
		Page_Header2_Inside.setBounds(60,30,1015,35); 
		Page_Header2_Inside.setBorder(blackline);
		Page_Header2_Inside.setBackground(Color.DARK_GRAY);
		f.add(Page_Header2_Inside);

		//Page Header 2  
		JPanel Page_Header2 = new JPanel();
		Page_Header2.setLayout(new BorderLayout());
		Page_Header2.setBounds(50,30,1005,40); 
		Page_Header2.setBorder(blackline);
		Page_Header2.setBackground(Color.BLACK);
		f.add(Page_Header2);

		//Page footer 2 subset  
		JPanel Page_Footer2_subset = new JPanel();
		Page_Footer2_subset.setLayout(new BorderLayout());
		Page_Footer2_subset.setBounds(1035,960,230,15); 
		Page_Footer2_subset.setBorder(blackline);
		Page_Footer2_subset.setBackground(Color.DARK_GRAY);
		f.add(Page_Footer2_subset);

		//Page footer 2  
		JPanel Page_Footer2 = new JPanel();
		Page_Footer2.setLayout(new BorderLayout());
		Page_Footer2.setBounds(1030,950,245,60); 
		Page_Footer2.setBorder(blackline);
		Page_Footer2.setBackground(Color.DARK_GRAY);
		f.add(Page_Footer2);

		//Keyboard Panels////////////////////////////////////////////////////////////////////////////////////////////
		//Game keyboard Area Padding [Inside];
		JPanel Page_Footer_subset2 = new JPanel();
		Page_Footer_subset2.setLayout(new BorderLayout());
		Page_Footer_subset2.setBounds(70,730,940,230); 
		Page_Footer_subset2.setBorder(blackline);
		Page_Footer_subset2.setBackground(Color.DARK_GRAY);
		Page_Footer_subset2.setVisible(false);
		f.add(Page_Footer_subset2);
		//Game keyboard Area Padding [Middle];
		JPanel Page_Footer_subset1 = new JPanel();
		Page_Footer_subset1.setLayout(new BorderLayout());
		Page_Footer_subset1.setBounds(60,720,960,250); 
		Page_Footer_subset1.setBorder(blackline);
		Page_Footer_subset1.setBackground(Color.CYAN);
		Page_Footer_subset1.setVisible(false);
		f.add(Page_Footer_subset1);
		//Game keyboard Area Padding [out_side];
		JPanel Page_Footer1 = new JPanel();
		Page_Footer1.setLayout(new BorderLayout());
		Page_Footer1.setBounds(50,700,980,500); 
		Page_Footer1.setBorder(blackline);
		Page_Footer1.setBackground(Color.DARK_GRAY);
		Page_Footer1.setVisible(false);
		f.add(Page_Footer1);
		//End of Keyboard Panels//////////////////////////////////////////////////////////////////////////////////

		//Page Header 1 Help Button
		JPanel Page_Header1_Help_Button = new JPanel();
		Page_Header1_Help_Button.setLayout(new BorderLayout());
		Page_Header1_Help_Button.setBounds(150,10,100,19); 
		Page_Header1_Help_Button.setBackground(Color.GRAY);
		JButton Help = new JButton("Help");
		Help.setFont(f2);
		Help.setBackground(Color.black);
		Help.setForeground(Color.WHITE);
		Page_Header1_Help_Button.add(Help);
		f.add(Page_Header1_Help_Button);

		//Page Header 1 File Button
		JPanel Page_Header1_File_Button = new JPanel();
		Page_Header1_File_Button.setLayout(new BorderLayout());
		Page_Header1_File_Button.setBounds(50,10,100,19); 
		Page_Header1_File_Button.setBackground(Color.GRAY);
		JButton File = new JButton("File");
		File.setFont(f2);
		File.setBackground(Color.black);
		File.setForeground(Color.WHITE);
		Page_Header1_File_Button.add(File);
		f.add(Page_Header1_File_Button);        

		//Page Header 1 subset  
		JPanel Page_Header1_subset = new JPanel();
		Page_Header1_subset.setLayout(new BorderLayout());
		Page_Header1_subset.setBounds(500,10,530,20); 
		Page_Header1_subset.setBorder(blackline);
		Page_Header1_subset.setBackground(Color.BLACK);
		f.add(Page_Header1_subset);

		//Page Header 1  
		JPanel Page_Header1 = new JPanel();
		Page_Header1.setLayout(new BorderLayout());
		Page_Header1.setBounds(5,5,1025,25); 
		Page_Header1.setBorder(blackline);
		Page_Header1.setBackground(Color.DARK_GRAY);
		f.add(Page_Header1);

		//Outer border 
		JPanel Outer_Border_panel = new JPanel ();
		Outer_Border_panel.setLayout(new BorderLayout());
		Outer_Border_panel.setBounds(50,70,980,850); 
		Outer_Border_panel.setBorder(blackline);
		Outer_Border_panel.setBackground(Color.BLACK);

		//Begin button (Opens up the keyboard panels, paints the hang man design and begins the game) 
		JPanel Begin_Panel_Button = new JPanel();
		Begin_Panel_Button.setLayout(new BorderLayout());
		Begin_Panel_Button.setBounds(470,370,130,40); 
		Begin_Panel_Button.setBackground(Color.BLACK);
		JButton Begin_Button = new JButton("START GAME");
		Begin_Button.setFont(f2);
		Begin_Button.setHorizontalAlignment(SwingConstants.LEFT);
		Begin_Button.setBackground(Color.black);
		Begin_Button.setForeground(Color.WHITE);
		Begin_Panel_Button.add(Begin_Button);
		f.add(Begin_Panel_Button);

		//The Canvas //Our drawing surface of choice 
		Canvas Canvas1 = new Canvas(){ 
			public void paint(Graphics g) 
			{ 
			} 
		}; 
		Canvas1.setBounds(0,0,975,845); 
		Canvas1.setBackground(Color.WHITE);
		Canvas1.setVisible(true);
		Outer_Border_panel.add(Canvas1);
		f.add(Outer_Border_panel);

		//Window1 border 
		JPanel Window1 = new JPanel();
		Window1.setLayout(new BorderLayout());
		Window1.setBounds(0,0,1280,1020); 
		Window1.setBorder(blackline);
		Window1.setBackground(Color.DARK_GRAY);
		f.add(Window1);
		//end of Panels

		//our frame specifications 
		f.setTitle("LD");//Adds title header that appears at the top of the page
		f.setSize(1280,1020);//setting the default size of our window to 270 (H) pixels by 260 (V)
		f.setResizable(false);//Makes it impossible to resize the window, for styling purposes and uniformity of the program in its current state
		f.setVisible(true);//making the window visible to the use

		//Action listeners
		//Button Action Listeners 
		//Begin Button Action Listener
		//Expands the keyboard Area and starts the game by painting the canvas with the hang man 
		Begin_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Page_Footer_subset2.setVisible(true);
				Page_Footer_subset1.setVisible(true);
				Page_Footer1.setVisible(true);
				Begin_Panel_Button.setVisible(false);

				Graphics g = Canvas1.getGraphics(); 
				paintComponent(g);
			}
		});
		//File Drop Down Menu Action Listeners
		//Expands the drop down menu by deploying the grid panel containing all of the secondary buttons after the first button is consequently pressed 
		File.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(press_amount == 0){
					File_Panel_Expansion.setVisible(true); press_amount += 1; 
				}else{
					File_Panel_Expansion.setVisible(false); press_amount = 0; 
				}
			}

		});
		//Creates a new Hang_Man object, and prompts the user to save their work as a precaution
		File_New.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int response = JOptionPane.showConfirmDialog(pane,"Would you like to save the current game?","Advisory",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {
					f.setVisible(false);
					Hang_Man HM1 = new Hang_Man();
				} else if (response == JOptionPane.YES_OPTION) {
					File_Save_As.doClick();
					f.setVisible(false);
					Hang_Man HM1 = new Hang_Man();
				} else if (response == JOptionPane.CLOSED_OPTION) {

				}      
			}

		});
		//Writes the Buffered Image to the user's desktop using the appropriate name chosen by them as well
		File_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {                    
				if(File_Panel_Expansion.isVisible() == true){
					File_Panel_Expansion.setVisible(false);
					press_amount = 0;
				}                  
			}

		});

		//Resave or save under a different name 
		File_Save_As.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		//Exits the program
		File_Exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});
		//End of File Drop Menu Action Listeners

		//Help Button Action Listener, provides a short and decent tutorial about this current piece of software 
		Help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(File_Panel_Expansion.isVisible() == true){
					File_Panel_Expansion.setVisible(false);
				}
				press_amount = 0;

				JOptionPane.showMessageDialog(pane,"Welcome to Hang_Man's General Manual");
			}

		});
		//end of Help Button Action Listener 


		//Frame event listeners, general precautions for the user
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int response = JOptionPane.showConfirmDialog(pane,"Would you like to save the current game?","Advisory",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
				if (response == JOptionPane.NO_OPTION) {

				} else if (response == JOptionPane.YES_OPTION) {
					File_Save_As.doClick();
				} else if (response == JOptionPane.CLOSED_OPTION) {

				}   
			}
		});

		f.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(File_Panel_Expansion.isVisible() == true){
					File_Panel_Expansion.setVisible(false);
					press_amount = 0;
				}
			}
		});
		//end of frame event listeners

	}

	//paintComponent 
	public void paintComponent(Graphics g) {
		super.paintComponents(g);

	}

	//paint method
	public void paint(Graphics g) {
		super.paint(g);
	}

	public void close_all_windows(){
		System.exit(0);
	}

	//Main method
	public static void main(String[] args){
		Hang_Man HM1 = new Hang_Man();
	}
}
