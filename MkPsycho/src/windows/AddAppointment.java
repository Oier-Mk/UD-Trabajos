package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

import MkNotepad.JPanelConFondo;
import appointments.Day;
import program.ContainingClass;
import program.MkPsycho;

public class AddAppointment extends JFrame{

	public static final long serialVersionUID = 1L;

	public static JCheckBox hour1;
	public static JCheckBox hour15;
	public static JLabel error;
	public static ButtonGroup radioB;
	public final int width = 500;
	public final int height = 130;
	public static String background = "/media/BackgroundMini.jpg";
	public static String backgroundMini = "/media/BackgroundLittle.png";
	public static int appointmentDay;
	public static Dimension screenSize;
	public static JPanel panel;	
	public static JButton accept;
	public static JTextField hour;
	public static SpinnerModel value;
	public static JSpinner spinner;
	public static String spinnerHour = "0";
	public static String spinnerMinutes = "0";
	public static String spinnerTime;
	public static int appointmentHeight;
	public static Integer newDayKey;
	public static Day newDay;
	
	public AddAppointment(JButton b,ContainingClass ContainingClass) {
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setSize( width, height );
		setUndecorated(true);
		JPanel bg = new JPanelConFondo(backgroundMini);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation( screenSize.width/2 - width/2, screenSize.height/2 - height/2);  
		add(bg);


		panel = new JPanelConFondo(background);
		panel.setOpaque(false);
		panel.setLayout( null );

		hour1 = new JCheckBox( "1 hour" );
		hour1.setBounds(250-85, 40, 85, 20);
		hour1.setOpaque(false);
		hour1.setBorderPainted(false);
		hour1.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(hour1);

		hour15 = new JCheckBox( "1'5 hour" );
		hour15.setBounds(250+100-105, 40, 100, 20);
		hour15.setOpaque(false);
		hour15.setBorderPainted(false);
		hour15.setFont(new Font("Arial",Font.PLAIN,20));
		panel.add(hour15);

		radioB = new ButtonGroup();
		radioB.add(hour15); radioB.add(hour1);	

		JLabel title = new JLabel( "How long is the appointment is going to be?" );
		title.setFont(new Font("Arial",Font.PLAIN,20));
		title.setBounds(60, 10, 395, 20);
		panel.add(title);

		add( panel );


		value =  new SpinnerDateModel();
		spinner = new JSpinner(value);  
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner, "HH:mm");
		spinner.setEditor(de);
		spinner.setBounds(220,90,60,20);    
		panel.add(spinner);    



		JLabel label = new JLabel("The hour of the beggining");
		label.setBounds(50, 80, 290, 20);
		panel.add(label);

		JLabel label2 = new JLabel("of the appointment is:");
		label2.setBounds(50, 95, 290, 20);
		panel.add(label2);

		accept = new JButton("Accept");
		accept.setBounds(390, 90, 100, 30);
		panel.add(accept);

		error = new JLabel("You have to select one hour gap");
		error.setFont(new Font("Arial",Font.PLAIN,10));
		error.setForeground(Color.RED);
		error.setBounds(0, 0, 350, 10);

		accept.addActionListener(new ActionListener() {


			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String Shour = spinner.getValue().toString();
				spinnerHour = Shour.charAt(11)+""+Shour.charAt(12);
				spinnerMinutes = Shour.charAt(14)+""+Shour.charAt(15);
				spinnerTime = spinnerHour+""+spinnerMinutes;
				int nTime = Integer.parseInt(spinnerTime);
				if(!hour1.isSelected()&&!hour15.isSelected()) {
					panel.add(error);
					revalidate();
					repaint();
				}
				if(nTime>=830 && nTime<=2130) {
					dispose();
					
					newDay = new Day(new Date(MkPsycho.actualDay.getTime()));

					int inc;
					if(newDay.getDay().getDay()==0){
						inc=6;
					}
					else {
						inc=newDay.getDay().getDay()-1;
					}
					
					if (MkPsycho.dayNum != 1) {
						newDay = new Day(new Date((newDay.getDay().getTime()-24*60*60*1000*inc)+(24*3600000*(MkPsycho.dayNum-1))));
					}else{
						newDay = new Day(new Date(newDay.getDay().getTime()-24*60*60*1000*inc));	
					}
					
					newDayKey = Integer.parseInt(newDay.getDay().getYear()+""+(newDay.getDay().getMonth()+1)+""+newDay.getDay().getDate());
					
					if (!MkPsycho.windowWorker.calendar.getCalendar().containsKey(newDayKey)) {
						MkPsycho.windowWorker.calendar.getCalendar().put(newDayKey, newDay);
					}
						ContainingClass.putAppointment(b);
						MkPsycho.paintCalendar(MkPsycho.actualDay);	
				}
				else {
					error.setText("You only can add sessions between 8:30AM and 9:30PM");
					panel.add(error);
					revalidate();
					repaint();
				}
			}
		});
	}
}