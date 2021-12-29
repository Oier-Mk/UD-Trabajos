package es.deusto.ingenieria.sd.strava.client.gui.GraphicalWindows;

import java.awt.BorderLayout;

import es.deusto.ingenieria.sd.strava.client.controller.HomeController;
import es.deusto.ingenieria.sd.strava.client.gui.HomeWindow;
import es.deusto.ingenieria.sd.strava.client.gui.LoginDialog;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SetUpChallengeW extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<String> jcbDayFinish, jcbMonthFinish, jcbYearFinish, jcbDayStart, jcbMonthStart, jcbYearStart, jcbSport;
	private JTextField jtfName, jtfDist;
	private JLabel jlTotal, jlName, jlNorth, jlSport, jlDist, jlDate, jlDateFinish;
	private JPanel jpDuration, jpCenter, jpSport, jpSouth;
	private JButton jbCreate, jbTotal, jbBack, jbReset ;
	private JPanel jpInfo, jpName, jpNorth, jpDistance, jpDateStart, jpDateFinish;
	private HomeWindow h;
	private LoginDialog l;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SetUpChallengeW(LoginDialog l,HomeWindow h) {
		this.h = h;
		this.l = l;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jpNorth = new JPanel();
		jpNorth.setBackground(new Color(255, 182, 193));
		jpNorth.setForeground(Color.WHITE);
		contentPane.add(jpNorth, BorderLayout.NORTH);

		jlNorth = new JLabel("CHALLENGE");
		jlNorth.setBackground(new Color(255, 255, 255));
		jlNorth.setForeground(new Color(0, 0, 0));
		jlNorth.setFont(new Font("Calibri", Font.BOLD, 17));
		jpNorth.add(jlNorth);

		jpCenter = new JPanel();
		jpCenter.setForeground(new Color(255, 255, 255));
		jpCenter.setBackground(new Color(255, 182, 193));
		contentPane.add(jpCenter, BorderLayout.CENTER);
		jpCenter.setLayout(new GridLayout(5, 1, 0, 0));

		jpName = new JPanel();
		jpName.setForeground(UIManager.getColor("ToggleButton.foreground"));
		jpName.setBackground(new Color(255, 240, 245));
		jpName.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		jpCenter.add(jpName);
		
		jlName = new JLabel("Name:");
		jpName.add(jlName);

		jtfName = new JTextField();
		jpName.add(jtfName);
		jtfName.setColumns(10);

		jpInfo = new JPanel();
		jpCenter.add(jpInfo);
		jpInfo.setLayout(new GridLayout(0, 2, 0, 0));

		jpSport = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jpSport.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		jpSport.setBackground(new Color(255, 240, 245));
		jpInfo.add(jpSport);

		jlSport = new JLabel("Sport:");
		jpSport.add(jlSport);

		jcbSport = new JComboBox();
		jcbSport.setModel(new DefaultComboBoxModel(new String[] {"Cycling", "Running"}));
		jpSport.add(jcbSport);

		jpDistance = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jpDistance.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		jpDistance.setBackground(new Color(255, 240, 245));
		jpInfo.add(jpDistance);

		jtfDist = new JTextField();
		jtfDist.setHorizontalAlignment(SwingConstants.CENTER);
		jtfDist.setColumns(5);
		jpDistance.add(jtfDist);

		jlDist = new JLabel("km");
		jpDistance.add(jlDist);

		jpDateStart = new JPanel();
		jpDateStart.setBackground(new Color(255, 240, 245));
		jpCenter.add(jpDateStart);

		jlDate = new JLabel("Start date:");
		jpDateStart.add(jlDate);

		jcbDayStart = new JComboBox();
		jcbDayStart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		jpDateStart.add(jcbDayStart);

		JLabel jlBar1 = new JLabel("/");
		jpDateStart.add(jlBar1);

		jcbMonthStart = new JComboBox();
		jcbMonthStart.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		jpDateStart.add(jcbMonthStart);

		JLabel jlBar2 = new JLabel("/");
		jpDateStart.add(jlBar2);

		jcbYearStart = new JComboBox();
		jcbYearStart.setModel(new DefaultComboBoxModel(new String[] {"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		jpDateStart.add(jcbYearStart);

		jpDateFinish = new JPanel();
		jpDateFinish.setBackground(new Color(255, 240, 245));
		jpCenter.add(jpDateFinish);

		jlDateFinish = new JLabel("Finish date:");
		jpDateFinish.add(jlDateFinish);

		jcbDayFinish = new JComboBox();
		jcbDayFinish.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		jpDateFinish.add(jcbDayFinish);

		JLabel jlPoint1 = new JLabel("/");
		jpDateFinish.add(jlPoint1);

		jcbMonthFinish = new JComboBox();
		jcbMonthFinish.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		jpDateFinish.add(jcbMonthFinish);

		JLabel jlSym = new JLabel("/");
		jpDateFinish.add(jlSym);

		jcbYearFinish = new JComboBox();
		jcbYearFinish.setModel(new DefaultComboBoxModel(new String[] {"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		jpDateFinish.add(jcbYearFinish);

		jpDuration = new JPanel();
		jpDuration.setBackground(new Color(255, 240, 245));
		jpCenter.add(jpDuration);

		jbTotal = new JButton("Total duration");
		jpDuration.add(jbTotal);

		jlTotal = new JLabel();
		jpDuration.add(jlTotal);

		jbTotal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jlTotal.setText(calDuration());
			}
		});

		jlTotal = new JLabel("");
		jpDuration.add(jlTotal);

		jpSouth = new JPanel();
		jpSouth.setForeground(new Color(255, 255, 255));
		jpSouth.setBackground(new Color(255, 182, 193));
		contentPane.add(jpSouth, BorderLayout.SOUTH);

		jbBack = new JButton("BACK");
		jpSouth.add(jbBack);

		jbReset = new JButton("RESET");
		jbReset.setFont(new Font("Tahoma", Font.BOLD, 11));

		jbReset.setBackground(UIManager.getColor("ToolBar.dockingForeground"));
		jpSouth.add(jbReset);

		jbCreate = new JButton("CREATE");
		jbCreate.setFont(new Font("Tahoma", Font.BOLD, 11));
		jbCreate.setBackground(UIManager.getColor("TextPane.selectionBackground"));
		jpSouth.add(jbCreate);

		jbReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetChallenge();
			}
		});

		jbCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkCreate();
			}
		});

		jbBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		this.setVisible(true);
	}

	public String calDuration() {
		int yf = Integer.parseInt((String)jcbYearFinish.getSelectedItem());
		int ys = Integer.parseInt((String)jcbYearStart.getSelectedItem());
		int mf = Integer.parseInt((String)jcbMonthFinish.getSelectedItem());
		int ms = Integer.parseInt((String)jcbMonthStart.getSelectedItem());
		int df = Integer.parseInt((String)jcbDayFinish.getSelectedItem());
		int ds = Integer.parseInt((String)jcbDayStart.getSelectedItem());
		int day = df - ds;
		int month = mf - ms;
		int year = yf - ys;
		String totalDuration = "";
		if(yf == ys) { //Same year
			if(mf == ms) { //Same month
				if(df>ds) { //Finish Date > Start Date
					totalDuration = day + " days";
					if(day == 1) {
						totalDuration = day + " day";
					}
				}
			}else if (mf > ms) { //Finish month > Start Month
				totalDuration = day + " days " + month + " months ";
				if(month == 1 && day == 1){
					totalDuration = day + " day " + month + " month";
				}else if(month == 1){
					totalDuration = day + " days " + month + " month";
				}else if (day == 1) {
					totalDuration = day + " day " + month + " months";
				}
			}
		}else if (yf > ys) {
			totalDuration = day + " days " + month + " months " + year + " years ";
			if(year == 1 && month == 1 && day == 1) {
				totalDuration = day + " day " + month + " month " + year + " year";
			}else if(month == 1) {
					totalDuration = day + " days " + month + " month" + year + " years";
				if(day == 1){
					totalDuration = day + " day " + month + " month" + year + " years";
				}else if(year == 1) {
					totalDuration = day + " days " + month + " month" + year + " year";
				}
			}else if(day == 1){
				totalDuration = day + " day " + month + " months " + year + " years";
			}else if(year == 1){
				totalDuration = day + " days " + month + " months" + year + " year";
			}
		}else {
			totalDuration = "";
		}
		return totalDuration;
	}

	@SuppressWarnings("deprecation")
	public void checkCreate() {
		int yf = Integer.parseInt((String)jcbYearFinish.getSelectedItem());
		int ys = Integer.parseInt((String)jcbYearStart.getSelectedItem());
		int mf = Integer.parseInt((String)jcbMonthFinish.getSelectedItem());
		int ms = Integer.parseInt((String)jcbMonthStart.getSelectedItem());
		int df = Integer.parseInt((String)jcbDayFinish.getSelectedItem());
		int ds = Integer.parseInt((String)jcbDayStart.getSelectedItem());
		if(yf == ys) { //Same year
			if(mf == ms) { //Same month
				if(df>ds) { //Finish Date > Start Date
					panelCheck();
					//TODO
					//long token, String name, Date start, Date end, double distance, double duration, String sport) throws Exception;
					h.setupChallenge(l.getToken(), jtfName.getText(), new Date(ys,ms,ds), new Date(yf,mf,df), Double.parseDouble(jtfDist.getText()), Double.parseDouble(calDuration()), jcbSport.getSelectedItem().toString());
				}else {
					panelError();
				}
			}else if (mf > ms) { //Finish month > Start Month
				panelCheck();
			}else {
				panelError();
			}
		}else if (yf > ys) { //Finish year > Start year
			panelCheck();
		}else {
			panelError();
		}
	}

	public void panelCheck() {
		JOptionPane.showMessageDialog(jbCreate, "New challenge successfully created!");
	}

	public void panelError() {
		JOptionPane.showMessageDialog(jbCreate, "The selected dates are invalid. Please check and re-enter them.");
	}

	public void resetChallenge() {
		jtfName.setText(""+jtfName.getText().substring(0, jtfName.getText().length() - jtfName.getText().length()));
		jtfDist.setText(""+jtfDist.getText().substring(0, jtfDist.getText().length() - jtfDist.getText().length()));
		jcbSport.setSelectedItem("Cycling");
		jcbDayStart.setSelectedItem("1");
		jcbDayFinish.setSelectedItem("1");
		jcbMonthStart.setSelectedItem("1");
		jcbMonthFinish.setSelectedItem("1");
		jcbYearStart.setSelectedItem("2021");
		jcbYearFinish.setSelectedItem("2021");
	}

}
