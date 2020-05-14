import javax.swing.border.Border;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;
import java.awt.*;  
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/*Written by Justin, Brenno, Afaq
 *CSCI 185 M06
 *Java class 201
 *5/12/20
 *Spring 2020
 *Homework 4: Create Your Own Java GUI Program
 *This program is a simple recreation of the famous game hang man using Java GUI
 *The user can save their progress, or start a new game at any time
 *The fluid UI interface has been designed to offer the most minimalistic feel possible
 **/

public class Hang_Man extends JFrame
{
	private static final long serialVersionUID = 1L;

	JFrame f; //setting our JFrame variable
	JOptionPane pane = new JOptionPane();//creating a new pane
	private String save_name = null;//save name, to be determined by the user 
	private int press_amount = 0;//Press amount of the top drop down menu buttons, prevents multiple menus from being open at once
	//end of instance variable declarations

	private Hangman game = new Hangman();
	int wordChoice;

	public Hang_Man(){
		// List of words.
		String[] words = {"Banana", "Fox", "Rabbit", "Dog", "Cat", "Hell", "Heaven"};
		String[] hints = {"Yellow Fruit", "Rhymes with Fox", "White Fluffy Animal", "Mans Best Friend", "Garfield", "The Devil lives", "Some people believe in it, some people don't."};
		wordChoice = (int)(Math.random() * ((words.length - 1) + 1));
		game.setGameWord(words[wordChoice]);
		game.setGuessWord();
		//New frame is enumerated
		f = new JFrame();

		//New border styles
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border whiteline = BorderFactory.createLineBorder(Color.white);
		Border grayline = BorderFactory.createLineBorder(Color.gray);

		//Fonts
		Font f1 = new Font("Helvetica",Font.PLAIN, 10);
		Font f2 = new Font("Helvetica",Font.PLAIN, 15);
		Font f3 = new Font("Helvetica",Font.PLAIN, 20);

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
		File_Panel_Expansion.setBackground(Color.CYAN);
		File_Panel_Expansion.setBorder(whiteline);
		File_Panel_Expansion.setVisible(false);
		//File's Drop Down Menu buttons

		//New button
		JPanel File_Menu_Button_New = new JPanel();
		File_Menu_Button_New.setLayout(new BorderLayout());
		File_Menu_Button_New.setBackground(Color.WHITE);
		JButton File_New = new JButton("New");
		File_New.setFont(f2);
		File_New.setHorizontalAlignment(SwingConstants.LEFT);
		File_New.setBackground(Color.white);
		File_New.setForeground(Color.black);
		File_Menu_Button_New.add(File_New);
		File_Panel_Expansion.add(File_Menu_Button_New);

		//Save button
		JPanel File_Menu_Button_Save = new JPanel();
		File_Menu_Button_Save.setLayout(new BorderLayout());
		File_Menu_Button_Save.setBackground(Color.WHITE);
		JButton File_Save = new JButton("Save");
		File_Save.setFont(f2);
		File_Save.setHorizontalAlignment(SwingConstants.LEFT);
		File_Save.setBackground(Color.white);
		File_Save.setForeground(Color.black);
		File_Menu_Button_Save.add(File_Save);
		File_Panel_Expansion.add(File_Menu_Button_Save);

		//Save As button
		JPanel File_Menu_Button_Save_As = new JPanel();
		File_Menu_Button_Save_As.setLayout(new BorderLayout());
		File_Menu_Button_Save_As.setBackground(Color.WHITE);
		JButton File_Save_As = new JButton("Save As...");
		File_Save_As.setFont(f2);
		File_Save_As.setHorizontalAlignment(SwingConstants.LEFT);
		File_Save_As.setBackground(Color.white);
		File_Save_As.setForeground(Color.black);
		File_Menu_Button_Save_As.add(File_Save_As);
		File_Panel_Expansion.add(File_Menu_Button_Save_As);

		//Exit button
		JPanel File_Menu_Button_Exit = new JPanel();
		File_Menu_Button_Exit.setLayout(new BorderLayout());
		File_Menu_Button_Exit.setBackground(Color.WHITE);
		JButton File_Exit = new JButton("Exit");
		File_Exit.setFont(f2);
		File_Exit.setHorizontalAlignment(SwingConstants.LEFT);
		File_Exit.setBackground(Color.white);
		File_Exit.setForeground(Color.black);
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
		//Keyboard Key Buttons [Placed inside of Page_Footer_subset2]

		ArrayList<JButton> buttons = new ArrayList<>();

		//A Button
		JPanel a_Panel = new JPanel();
		a_Panel.setLayout(new BorderLayout());
		a_Panel.setBackground(Color.BLACK);
		JButton a_Button = new JButton("A");
		a_Button.setFont(f2);
		a_Button.setHorizontalAlignment(SwingConstants.CENTER);
		a_Button.setBackground(Color.white);
		a_Button.setForeground(Color.BLACK);
		a_Panel.add(a_Button);
		buttons.add(a_Button);
		//B Button
		JPanel b_Panel = new JPanel();
		b_Panel.setLayout(new BorderLayout());
		b_Panel.setBackground(Color.BLACK);
		JButton b_Button = new JButton("B");
		b_Button.setFont(f2);
		b_Button.setHorizontalAlignment(SwingConstants.CENTER);
		b_Button.setBackground(Color.white);
		b_Button.setForeground(Color.BLACK);
		b_Panel.add(b_Button);
		buttons.add(b_Button);
		//C Button
		JPanel c_Panel = new JPanel();
		c_Panel.setLayout(new BorderLayout());
		c_Panel.setBackground(Color.BLACK);
		JButton c_Button = new JButton("C");
		c_Button.setFont(f2);
		c_Button.setHorizontalAlignment(SwingConstants.CENTER);
		c_Button.setBackground(Color.white);
		c_Button.setForeground(Color.BLACK);
		c_Panel.add(c_Button);
		buttons.add(c_Button);
		//D Button
		JPanel d_Panel = new JPanel();
		d_Panel.setLayout(new BorderLayout());
		d_Panel.setBackground(Color.BLACK);
		JButton d_Button = new JButton("D");
		d_Button.setFont(f2);
		d_Button.setHorizontalAlignment(SwingConstants.CENTER);
		d_Button.setBackground(Color.white);
		d_Button.setForeground(Color.BLACK);
		d_Panel.add(d_Button);
		buttons.add(d_Button);
		//E Button
		JPanel e_Panel = new JPanel();
		e_Panel.setLayout(new BorderLayout());
		e_Panel.setBackground(Color.BLACK);
		JButton e_Button = new JButton("E");
		e_Button.setFont(f2);
		e_Button.setHorizontalAlignment(SwingConstants.CENTER);
		e_Button.setBackground(Color.white);
		e_Button.setForeground(Color.BLACK);
		e_Panel.add(e_Button);
		buttons.add(e_Button);
		//F Button
		JPanel f_Panel = new JPanel();
		f_Panel.setLayout(new BorderLayout());
		f_Panel.setBackground(Color.BLACK);
		JButton f_Button = new JButton("F");
		f_Button.setFont(f2);
		f_Button.setHorizontalAlignment(SwingConstants.CENTER);
		f_Button.setBackground(Color.white);
		f_Button.setForeground(Color.BLACK);
		f_Panel.add(f_Button);
		buttons.add(f_Button);
		//G Button
		JPanel g_Panel = new JPanel();
		g_Panel.setLayout(new BorderLayout());
		g_Panel.setBackground(Color.BLACK);
		JButton g_Button = new JButton("G");
		g_Button.setFont(f2);
		g_Button.setHorizontalAlignment(SwingConstants.CENTER);
		g_Button.setBackground(Color.white);
		g_Button.setForeground(Color.BLACK);
		g_Panel.add(g_Button);
		buttons.add(g_Button);
		//H Button
		JPanel h_Panel = new JPanel();
		h_Panel.setLayout(new BorderLayout());
		h_Panel.setBackground(Color.BLACK);
		JButton h_Button = new JButton("H");
		h_Button.setFont(f2);
		h_Button.setHorizontalAlignment(SwingConstants.CENTER);
		h_Button.setBackground(Color.white);
		h_Button.setForeground(Color.BLACK);
		h_Panel.add(h_Button);
		buttons.add(h_Button);
		//I Button
		JPanel i_Panel = new JPanel();
		i_Panel.setLayout(new BorderLayout());
		i_Panel.setBackground(Color.BLACK);
		JButton i_Button = new JButton("I");
		i_Button.setFont(f2);
		i_Button.setHorizontalAlignment(SwingConstants.CENTER);
		i_Button.setBackground(Color.white);
		i_Button.setForeground(Color.BLACK);
		i_Panel.add(i_Button);
		buttons.add(i_Button);
		//J Button
		JPanel j_Panel = new JPanel();
		j_Panel.setLayout(new BorderLayout());
		j_Panel.setBackground(Color.BLACK);
		JButton j_Button = new JButton("J");
		j_Button.setFont(f2);
		j_Button.setHorizontalAlignment(SwingConstants.CENTER);
		j_Button.setBackground(Color.white);
		j_Button.setForeground(Color.BLACK);
		j_Panel.add(j_Button);
		buttons.add(j_Button);
		//K Button
		JPanel k_Panel = new JPanel();
		k_Panel.setLayout(new BorderLayout());
		k_Panel.setBackground(Color.BLACK);
		JButton k_Button = new JButton("K");
		k_Button.setFont(f2);
		k_Button.setHorizontalAlignment(SwingConstants.CENTER);
		k_Button.setBackground(Color.white);
		k_Button.setForeground(Color.BLACK);
		k_Panel.add(k_Button);
		buttons.add(k_Button);
		//L Button
		JPanel l_Panel = new JPanel();
		l_Panel.setLayout(new BorderLayout());
		l_Panel.setBackground(Color.BLACK);
		JButton l_Button = new JButton("L");
		l_Button.setFont(f2);
		l_Button.setHorizontalAlignment(SwingConstants.CENTER);
		l_Button.setBackground(Color.white);
		l_Button.setForeground(Color.BLACK);
		l_Panel.add(l_Button);
		buttons.add(l_Button);
		//M Button
		JPanel m_Panel = new JPanel();
		m_Panel.setLayout(new BorderLayout());
		m_Panel.setBackground(Color.BLACK);
		JButton m_Button = new JButton("M");
		m_Button.setFont(f2);
		m_Button.setHorizontalAlignment(SwingConstants.CENTER);
		m_Button.setBackground(Color.white);
		m_Button.setForeground(Color.BLACK);
		m_Panel.add(m_Button);
		buttons.add(m_Button);
		//N Button
		JPanel n_Panel = new JPanel();
		n_Panel.setLayout(new BorderLayout());
		n_Panel.setBackground(Color.BLACK);
		JButton n_Button = new JButton("N");
		n_Button.setFont(f2);
		n_Button.setHorizontalAlignment(SwingConstants.CENTER);
		n_Button.setBackground(Color.white);
		n_Button.setForeground(Color.BLACK);
		n_Panel.add(n_Button);
		buttons.add(n_Button);
		//O Button
		JPanel o_Panel = new JPanel();
		o_Panel.setLayout(new BorderLayout());
		o_Panel.setBackground(Color.BLACK);
		JButton o_Button = new JButton("O");
		o_Button.setFont(f2);
		o_Button.setHorizontalAlignment(SwingConstants.CENTER);
		o_Button.setBackground(Color.white);
		o_Button.setForeground(Color.BLACK);
		o_Panel.add(o_Button);
		buttons.add(o_Button);
		//P Button
		JPanel p_Panel = new JPanel();
		p_Panel.setLayout(new BorderLayout());
		p_Panel.setBackground(Color.BLACK);
		JButton p_Button = new JButton("P");
		p_Button.setFont(f2);
		p_Button.setHorizontalAlignment(SwingConstants.CENTER);
		p_Button.setBackground(Color.white);
		p_Button.setForeground(Color.BLACK);
		p_Panel.add(p_Button);
		buttons.add(p_Button);
		//Q Button
		JPanel q_Panel = new JPanel();
		q_Panel.setLayout(new BorderLayout());
		q_Panel.setBackground(Color.BLACK);
		JButton q_Button = new JButton("Q");
		q_Button.setFont(f2);
		q_Button.setHorizontalAlignment(SwingConstants.CENTER);
		q_Button.setBackground(Color.white);
		q_Button.setForeground(Color.BLACK);
		q_Panel.add(q_Button);
		buttons.add(q_Button);
		//R Button
		JPanel r_Panel = new JPanel();
		r_Panel.setLayout(new BorderLayout());
		r_Panel.setBackground(Color.BLACK);
		JButton r_Button = new JButton("R");
		r_Button.setFont(f2);
		r_Button.setHorizontalAlignment(SwingConstants.CENTER);
		r_Button.setBackground(Color.white);
		r_Button.setForeground(Color.BLACK);
		r_Panel.add(r_Button);
		buttons.add(r_Button);
		//S Button
		JPanel s_Panel = new JPanel();
		s_Panel.setLayout(new BorderLayout());
		s_Panel.setBackground(Color.BLACK);
		JButton s_Button = new JButton("S");
		s_Button.setFont(f2);
		s_Button.setHorizontalAlignment(SwingConstants.CENTER);
		s_Button.setBackground(Color.white);
		s_Button.setForeground(Color.BLACK);
		s_Panel.add(s_Button);
		buttons.add(s_Button);
		//T Button
		JPanel t_Panel = new JPanel();
		t_Panel.setLayout(new BorderLayout());
		t_Panel.setBackground(Color.BLACK);
		JButton t_Button = new JButton("T");
		t_Button.setFont(f2);
		t_Button.setHorizontalAlignment(SwingConstants.CENTER);
		t_Button.setBackground(Color.white);
		t_Button.setForeground(Color.BLACK);
		t_Panel.add(t_Button);
		buttons.add(t_Button);
		//U Button
		JPanel u_Panel = new JPanel();
		u_Panel.setLayout(new BorderLayout());
		u_Panel.setBackground(Color.BLACK);
		JButton u_Button = new JButton("U");
		u_Button.setFont(f2);
		u_Button.setHorizontalAlignment(SwingConstants.CENTER);
		u_Button.setBackground(Color.white);
		u_Button.setForeground(Color.BLACK);
		u_Panel.add(u_Button);
		buttons.add(u_Button);
		//V Button
		JPanel v_Panel = new JPanel();
		v_Panel.setLayout(new BorderLayout());
		v_Panel.setBackground(Color.BLACK);
		JButton v_Button = new JButton("V");
		v_Button.setFont(f2);
		v_Button.setHorizontalAlignment(SwingConstants.CENTER);
		v_Button.setBackground(Color.white);
		v_Button.setForeground(Color.BLACK);
		v_Panel.add(v_Button);
		buttons.add(v_Button);
		//W Button
		JPanel w_Panel = new JPanel();
		w_Panel.setLayout(new BorderLayout());
		w_Panel.setBackground(Color.BLACK);
		JButton w_Button = new JButton("W");
		w_Button.setFont(f2);
		w_Button.setHorizontalAlignment(SwingConstants.CENTER);
		w_Button.setBackground(Color.white);
		w_Button.setForeground(Color.BLACK);
		w_Panel.add(w_Button);
		buttons.add(w_Button);
		//X Button
		JPanel x_Panel = new JPanel();
		x_Panel.setLayout(new BorderLayout());
		x_Panel.setBackground(Color.BLACK);
		JButton x_Button = new JButton("X");
		x_Button.setFont(f2);
		x_Button.setHorizontalAlignment(SwingConstants.CENTER);
		x_Button.setBackground(Color.white);
		x_Button.setForeground(Color.BLACK);
		x_Panel.add(x_Button);
		buttons.add(x_Button);
		//Y Button
		JPanel y_Panel = new JPanel();
		y_Panel.setLayout(new BorderLayout());
		y_Panel.setBackground(Color.BLACK);
		JButton y_Button = new JButton("Y");
		y_Button.setFont(f2);
		y_Button.setHorizontalAlignment(SwingConstants.CENTER);
		y_Button.setBackground(Color.white);
		y_Button.setForeground(Color.BLACK);
		y_Panel.add(y_Button);
		buttons.add(y_Button);
		//Z Button
		JPanel z_Panel = new JPanel();
		z_Panel.setLayout(new BorderLayout());
		z_Panel.setBackground(Color.BLACK);
		JButton z_Button = new JButton("Z");
		z_Button.setFont(f2);
		z_Button.setHorizontalAlignment(SwingConstants.CENTER);
		z_Button.setBackground(Color.white);
		z_Button.setForeground(Color.BLACK);
		z_Panel.add(z_Button);
		buttons.add(z_Button);
		//Hint Button
		JPanel Hint_Panel = new JPanel();
		Hint_Panel.setLayout(new BorderLayout());
		Hint_Panel.setBackground(Color.BLACK);
		JButton Hint_Button = new JButton("Hint");
		Hint_Button.setFont(f2);
		Hint_Button.setHorizontalAlignment(SwingConstants.CENTER);
		Hint_Button.setBackground(Color.white);
		Hint_Button.setForeground(Color.BLACK);
		Hint_Panel.add(Hint_Button);
		Hint_Button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// The hints array works in parallel with the words array, thus the wordChoice option can be used.
				JOptionPane.showMessageDialog(null, hints[wordChoice]);
			}
		});
		//End of Key Buttons
		//Keyboard Panels////////////////////////////////////////////////////////////////////////////////////////////
		//Game Word Entry Box [Inside]
		JPanel word_entry_box_Textfield= new JPanel();
		word_entry_box_Textfield.setLayout(new BorderLayout());
		word_entry_box_Textfield.setBounds(200,645,680,30);
		word_entry_box_Textfield.setBorder(whiteline);
		word_entry_box_Textfield.setBackground(Color.WHITE);
		word_entry_box_Textfield.setVisible(false);
		f.add(word_entry_box_Textfield);

		JTextPane word_entry_Box = new JTextPane();
		SimpleAttributeSet center = new SimpleAttributeSet();
		word_entry_Box.setCharacterAttributes(center, true);
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		word_entry_Box.setText("");
		for (String s : game.getGuessWord()) {
			word_entry_Box.setText(word_entry_Box.getText() + s + " ");
		}
		StyledDocument word_entry_Doc = word_entry_Box.getStyledDocument();
		word_entry_Doc.setParagraphAttributes(0, word_entry_Doc.getLength(), center, false);
		word_entry_box_Textfield.add(word_entry_Box);
		word_entry_Box.setFont(f3);
		word_entry_Box.setEditable(false);

		//Game Word Entry Box [Inside]
		JPanel word_entry_box_Inside= new JPanel();
		word_entry_box_Inside.setLayout(new BorderLayout());
		word_entry_box_Inside.setBounds(190,640,700,40);
		word_entry_box_Inside.setBorder(blackline);
		word_entry_box_Inside.setBackground(Color.WHITE);
		word_entry_box_Inside.setVisible(false);
		f.add(word_entry_box_Inside);

		//Game Word Entry Box [Middle]
		JPanel word_entry_box_Middle= new JPanel();
		word_entry_box_Middle.setLayout(new BorderLayout());
		word_entry_box_Middle.setBounds(180,630,720,60);
		word_entry_box_Middle.setBorder(blackline);
		word_entry_box_Middle.setBackground(Color.CYAN);
		word_entry_box_Middle.setVisible(false);
		f.add(word_entry_box_Middle);

		//Game Word Entry Box [outside]
		JPanel word_entry_box_Outside= new JPanel();
		word_entry_box_Outside.setLayout(new BorderLayout());
		word_entry_box_Outside.setBounds(170,620,740,80);
		word_entry_box_Outside.setBorder(blackline);
		word_entry_box_Outside.setBackground(Color.DARK_GRAY);
		word_entry_box_Outside.setVisible(false);
		f.add(word_entry_box_Outside);
		//End

		//Game keyboard Area Padding [Inside]
		JPanel Page_Footer_subset2 = new JPanel();
		Page_Footer_subset2.setLayout(new GridLayout(3, 10));
		Page_Footer_subset2.setBounds(70,730,940,230);
		Page_Footer_subset2.setBorder(blackline);
		Page_Footer_subset2.setBackground(Color.WHITE);
		Page_Footer_subset2.setVisible(false);

		Page_Footer_subset2.add(a_Panel);
		Page_Footer_subset2.add(b_Panel);
		Page_Footer_subset2.add(c_Panel);
		Page_Footer_subset2.add(d_Panel);
		Page_Footer_subset2.add(e_Panel);
		Page_Footer_subset2.add(f_Panel);
		Page_Footer_subset2.add(g_Panel);
		Page_Footer_subset2.add(h_Panel);
		Page_Footer_subset2.add(i_Panel);
		Page_Footer_subset2.add(j_Panel);
		Page_Footer_subset2.add(k_Panel);
		Page_Footer_subset2.add(l_Panel);
		Page_Footer_subset2.add(m_Panel);
		Page_Footer_subset2.add(n_Panel);
		Page_Footer_subset2.add(o_Panel);
		Page_Footer_subset2.add(p_Panel);
		Page_Footer_subset2.add(q_Panel);
		Page_Footer_subset2.add(r_Panel);
		Page_Footer_subset2.add(s_Panel);
		Page_Footer_subset2.add(t_Panel);
		Page_Footer_subset2.add(u_Panel);
		Page_Footer_subset2.add(v_Panel);
		Page_Footer_subset2.add(w_Panel);
		Page_Footer_subset2.add(x_Panel);
		Page_Footer_subset2.add(y_Panel);
		Page_Footer_subset2.add(z_Panel);
		Page_Footer_subset2.add(Hint_Panel);

		f.add(Page_Footer_subset2);
		//Game keyboard Area Padding [Middle]

		JPanel Page_Footer_subset1 = new JPanel();
		Page_Footer_subset1.setLayout(new BorderLayout());
		Page_Footer_subset1.setBounds(60,720,960,250);
		Page_Footer_subset1.setBorder(blackline);
		Page_Footer_subset1.setBackground(Color.CYAN);
		Page_Footer_subset1.setVisible(false);
		f.add(Page_Footer_subset1);
		//Game keyboard Area Padding [out_side]
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
		Help.setBackground(Color.cyan);
		Help.setForeground(Color.BLACK);
		Page_Header1_Help_Button.add(Help);
		f.add(Page_Header1_Help_Button);

		//Page Header 1 File Button
		JPanel Page_Header1_File_Button = new JPanel();
		Page_Header1_File_Button.setLayout(new BorderLayout());
		Page_Header1_File_Button.setBounds(50,10,100,19);
		Page_Header1_File_Button.setBackground(Color.GRAY);
		JButton File = new JButton("File");
		File.setFont(f2);
		File.setBackground(Color.cyan);
		File.setForeground(Color.BLACK);
		Page_Header1_File_Button.add(File);
		f.add(Page_Header1_File_Button);

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

		//The Canvas //Our drawing surface of wordChoice
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
		f.setTitle("Hang_Man");//Adds title header that appears at the top of the page
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
				word_entry_box_Textfield.setVisible(true);
				word_entry_box_Inside.setVisible(true);
				word_entry_box_Middle.setVisible(true);
				word_entry_box_Outside.setVisible(true);
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

		// Efficient way of adding listener to all the buttons, due to them needing to fulfill the same requirements.
		// Only difference is the guess input.
		for (JButton b :  buttons) {
			b.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean correct = game.guess(b.getText());
					if (correct) {
						word_entry_Box.setText("");
						for (String s : game.getGuessWord()) {
							word_entry_Box.setText(word_entry_Box.getText() + s + " ");
						}
						if (game.getWinState()) {
							JOptionPane.showMessageDialog(null, "The game has ended. You Won! Thank you for playing!");
							int option = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
							if (option == JOptionPane.YES_OPTION) {
								wordChoice = (int)(Math.random() * ((words.length) + 1));
								game.reset(words[wordChoice]);
								word_entry_Box.setText("");
								for (String s : game.getGuessWord()) {
									word_entry_Box.setText(word_entry_Box.getText() + s + " ");
								}
							} else {
								System.exit(0);
							}
						}
					}
					if (game.hasReachedLimit()) {
						JOptionPane.showMessageDialog(null, "The game has ended. You Lost. Thank you for playing!");
						int option = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
						if (option == JOptionPane.YES_OPTION) {
							wordChoice = (int)(Math.random() * ((words.length) + 1));
							game.reset(words[wordChoice]);
							word_entry_Box.setText("");
							for (String s : game.getGuessWord()) {
								word_entry_Box.setText(word_entry_Box.getText() + s + " ");
							}
						} else {
							System.exit(0);
						}
					}
				}
			});
		}


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
