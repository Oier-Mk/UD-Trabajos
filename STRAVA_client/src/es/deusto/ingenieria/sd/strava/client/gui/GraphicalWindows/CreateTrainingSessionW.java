package es.deusto.ingenieria.sd.strava.client.gui.GraphicalWindows;
import java.awt.BorderLayout;

import es.deusto.ingenieria.sd.strava.client.controller.HomeController;
import es.deusto.ingenieria.sd.strava.client.gui.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;
import javax.swing.DefaultComboBoxModel;

public class CreateTrainingSessionW extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, jpNorth, jpCenter, jpTitle, jpInfo, jpSport, jpDistance, jpDate, jpTime, jpDuration, jpSouth;
	private JLabel jlNorth, jlTitle, jlSport, jlDist, jlDate, jlTime, jlHours1, jlHours2 , jlDuration, jlTotal;
	private JTextField jtfTitle;
	private JTextField jtfDist;
	private HomeWindow h;
	private JButton jbBack, jbReset, jbCreate;
	private JComboBox<String> jcbSport, jcbDay, jcbMonth, jcbYear, jcbMinutes1, jcbMinutes2, jcbHours1, jcbHours2;
	private LoginDialog l;
	
	public CreateTrainingSessionW(LoginDialog l,HomeWindow h) {
		this.h = h;
		this.l = l;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jpNorth = new JPanel();
		jpNorth.setBackground(new Color(175, 238, 238));
		contentPane.add(jpNorth, BorderLayout.NORTH);

		jlNorth = new JLabel("TRAINING SESSION");
		jlNorth.setFont(new Font("Calibri", Font.BOLD, 17));
		jpNorth.add(jlNorth);

		jpCenter = new JPanel();
		jpCenter.setBackground(new Color(224, 255, 255));
		contentPane.add(jpCenter, BorderLayout.CENTER);
		jpCenter.setLayout(new GridLayout(0, 1, 0, 0));

		jpTitle = new JPanel();
		jpTitle.setBackground(new Color(224, 255, 255));
		jpCenter.add(jpTitle);
		jpTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		jlTitle = new JLabel("Title:");
		jpTitle.add(jlTitle);

		jtfTitle = new JTextField();
		jpTitle.add(jtfTitle);
		jtfTitle.setColumns(10);

		jpInfo = new JPanel();
		jpCenter.add(jpInfo);
		jpInfo.setLayout(new GridLayout(0, 2, 0, 0));

		jpSport = new JPanel();
		FlowLayout flowLayout = (FlowLayout) jpSport.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		jpSport.setBackground(new Color(224, 255, 255));
		jpInfo.add(jpSport);

		jlSport = new JLabel("Sport:");
		jpSport.add(jlSport);

		jcbSport = new JComboBox();
		jcbSport.setModel(new DefaultComboBoxModel(new String[] {"Cycling", "Running"}));
		jpSport.add(jcbSport);

		jpDistance = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) jpDistance.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		jpDistance.setBackground(new Color(224, 255, 255));
		jpInfo.add(jpDistance);

		jtfDist = new JTextField();
		jtfDist.setHorizontalAlignment(SwingConstants.CENTER);
		jtfDist.setColumns(5);
		jpDistance.add(jtfDist);

		jlDist = new JLabel("km");
		jpDistance.add(jlDist);

		jpDate = new JPanel();
		jpDate.setBackground(new Color(224, 255, 255));
		jpCenter.add(jpDate);

		jlDate = new JLabel("Date:");
		jpDate.add(jlDate);

		jcbDay = new JComboBox();
		jcbDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		jpDate.add(jcbDay);

		JLabel jlBar1 = new JLabel("/");
		jpDate.add(jlBar1);

		jcbMonth = new JComboBox();
		jcbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		jpDate.add(jcbMonth);

		JLabel jlBar2 = new JLabel("/");
		jpDate.add(jlBar2);

		jcbYear = new JComboBox();
		jcbYear.setModel(new DefaultComboBoxModel(new String[] {"2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		jpDate.add(jcbYear);

		jpTime = new JPanel();
		jpTime.setBackground(new Color(224, 255, 255));
		jpCenter.add(jpTime);

		jlTime = new JLabel("Time:");
		jpTime.add(jlTime);

		jcbHours1 = new JComboBox();
		jcbHours1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		jpTime.add(jcbHours1);

		JLabel jlPoint1 = new JLabel(":");
		jpTime.add(jlPoint1);

		jcbMinutes1 = new JComboBox();
		jcbMinutes1.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		jpTime.add(jcbMinutes1);

		jlHours1 = new JLabel("h");
		jpTime.add(jlHours1);

		JLabel jlSym = new JLabel("-");
		jpTime.add(jlSym);

		jcbHours2 = new JComboBox();
		jcbHours2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		jpTime.add(jcbHours2);

		JLabel jlPoint2 = new JLabel(":");
		jpTime.add(jlPoint2);

		jcbMinutes2 = new JComboBox();
		jcbMinutes2.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"}));
		jpTime.add(jcbMinutes2);

		jlHours2 = new JLabel("h");
		jpTime.add(jlHours2);

		jpDuration = new JPanel();
		jpDuration.setBackground(new Color(224, 255, 255));
		jpCenter.add(jpDuration);

		jlDuration = new JLabel("Total duration:");
		jpDuration.add(jlDuration);

		jlTotal = new JLabel("");
		jpDuration.add(jlTotal);

		jpSouth = new JPanel();
		jpSouth.setBackground(new Color(175, 238, 238));
		contentPane.add(jpSouth, BorderLayout.SOUTH);

		jbBack = new JButton("BACK");
		jpSouth.add(jbBack);

		jbReset = new JButton("RESET");
		jpSouth.add(jbReset);

		jbCreate = new JButton("CREATE");
		jpSouth.add(jbCreate);

		jbBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		jbReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jtfTitle.setText(""+jtfTitle.getText().substring(0, jtfTitle.getText().length() - jtfTitle.getText().length()));
				jtfDist.setText(""+jtfDist.getText().substring(0, jtfDist.getText().length() - jtfDist.getText().length()));
				jcbSport.setSelectedItem("Cycling");
				jcbDay.setSelectedItem("1");
				jcbMonth.setSelectedItem("1");
				jcbYear.setSelectedItem("2021");
				jcbHours1.setSelectedItem("00");
				jcbHours2.setSelectedItem("00");
				jcbMinutes1.setSelectedItem("00");
				jcbMinutes2.setSelectedItem("00");
			}
		});
		
		jbCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkCreate();
			}
		});
		this.setVisible(true);

	}

	@SuppressWarnings("deprecation")
	public void checkCreate() {
		int hs = Integer.parseInt((String)jcbHours1.getSelectedItem());
		int hf = Integer.parseInt((String)jcbHours2.getSelectedItem());
		int ms = Integer.parseInt((String)jcbMinutes1.getSelectedItem());
		int mf = Integer.parseInt((String)jcbMinutes2.getSelectedItem());
		if(hs == hf) {
			if(mf > ms) {
				if(jtfTitle.getText() != "" && jtfDist.getText() != "") {
					panelCheck();
					//long token, String title, String sport, double distance, Date date, int hours, int minutes, double duration
					h.createTrainingSession(l.getToken(), jtfTitle.getText(), jcbSport.getSelectedItem().toString(), Double.parseDouble(jtfDist.getText()), new Date((int)jcbYear.getSelectedItem(),(int)jcbMonth.getSelectedItem(),(int)jcbDay.getSelectedItem()), hf, mf, (hf-hs + (mf-ms)/60) );
				}else {
					panelInfo();
				}
			}else {
				panelError();
			}
		}else if(hs < hf) {
			if(jtfTitle.getText() != "" && jtfDist.getText() != "") {
				panelCheck();
			}else {
				panelInfo();
			}
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
	
	public void panelInfo() {
		JOptionPane.showMessageDialog(jbCreate, "Impossible to create a new challenge, missing data!");
	}
	

}



