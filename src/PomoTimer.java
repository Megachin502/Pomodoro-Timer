import java.awt.event.*; //For action listener
import java.awt.*;
import javax.swing.*; //For GUI
import javax.swing.border.TitledBorder;

class Content extends JPanel{
	private JPanel panel1, panel2, panel3;
	private JLabel screen1, screen2;
	private JButton start, stop, reset;
	private Timer timer;
	private int minutesTracker1, secondsCounter1, minutesCounter1, minutes,  minutesTracker2, secondsCounter2, minutesCounter2, loopCounter, minutes2; 
	private final int workSecondsDesired = 1500;
	private final int workMinutesDesired = workSecondsDesired/60;
	private int breakSecondsDesired = 300;
	private final int breakMinutesDesired = breakSecondsDesired/60;

	
	public Content(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		timer = new Timer(1000, new ButtonListener());
		
		panel1 = new JPanel();
		TitledBorder titled1 = new TitledBorder("Work");
		titled1.setTitleFont(new Font("Arial", Font.BOLD, 50));

		panel1.setBorder(titled1);
		screen1 = new JLabel("25m 00s");
		screen1.setFont(new Font("Arial", Font.PLAIN, 40));
		panel1.add(screen1);
		add(panel1);
		
		panel2 = new JPanel();
		TitledBorder titled2 = new TitledBorder("Break");
		titled2.setTitleFont(new Font("Arial", Font.BOLD, 50));
		panel2.setBorder(titled2);
		screen2 = new JLabel("5m 00s");
		screen2.setFont(new Font("Arial", Font.PLAIN, 40));
		panel2.add(screen2);
		add(panel2);
		
		panel3 = new JPanel();
		start = new JButton("Start");
		start.addActionListener(new ButtonListener());
		panel3.add(start);
		stop = new JButton("Pause");
		stop.addActionListener(new ButtonListener());
		panel3.add(stop);
		reset = new JButton("Reset");
		reset.addActionListener(new ButtonListener());
		panel3.add(reset);
		add(panel3);
		
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == stop){
				timer.stop();
			} else if (event.getSource() == reset){
				timer.stop();
				panel1.setBackground(Color.white);
				panel2.setBackground(Color.white);
				minutesTracker1 = 0;
				secondsCounter1 = 0;
				minutesTracker2 = 0;
				secondsCounter2 = 0;
				minutesTracker1 = 0;
				minutesCounter1 = 0;
				minutesTracker2 = 0;
				minutesCounter2 = 0;
				screen1.setText("25m 00s");
				screen2.setText("5m 00s");
			} else { //can't be else if start
				timer.start();
				if(secondsCounter1 != 60 || minutes <0){
					panel1.setBackground(Color.green);
					minutesTracker1++;
					minutes = (workSecondsDesired-minutesTracker1)/60;
					screen1.setText(String.valueOf(minutes + "m " + (60-secondsCounter1) +"s"));
					secondsCounter1++;
				} else {
					secondsCounter1 = 0;
					minutesCounter1++;
				}
				if(minutesCounter1 == breakMinutesDesired){
					panel1.setBackground(Color.white);
					screen1.setText("25m 00s");
					if(secondsCounter2 != 60 || minutes2 <0){
						panel2.setBackground(Color.red);
						minutesTracker2++;
						minutes2 = (breakSecondsDesired-minutesTracker2)/60;
						screen2.setText(String.valueOf(minutes2 + "m " + (60-secondsCounter2) +"s"));
						secondsCounter2++;
					} else {
						minutesTracker1 = 0;
						secondsCounter1 = 0;
						minutesTracker2 = 0;
						secondsCounter2 = 0;
						minutesTracker1 = 0;
						minutesCounter1 = 0;
						minutesTracker2 = 0;
						minutesCounter2 = 0;
						panel2.setBackground(Color.white);
						screen2.setText("5m 00s");
						loopCounter++;
						if(loopCounter ==3){
							screen2.setText("15m 00s");
							breakSecondsDesired = 900;
						} 
					}
				}
				if (loopCounter == 4){
					loopCounter = 0;
					breakSecondsDesired = 60;
				}
			}
		}
	}	
}
public class PomoTimer {
	public static void main(String[] args) {
		//Sets up the window for the GUI
		JFrame frame = new JFrame("Elam's P.T"); //Title of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Adds content to GUI
		Content panel = new Content();
		frame.getContentPane().add(panel);
		//Allows it to open when executed
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false); //Disables window resize

	}

}
