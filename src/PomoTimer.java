import java.awt.event.*; //For action listener
import java.awt.*;
import javax.swing.*; //For GUI
import javax.swing.border.TitledBorder;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

class Content extends JPanel{
	private JPanel panel1, panel2, panel3;
	private JLabel screen1, screen2;
	private JButton start, stop, reset;
	private Timer timer;
	private int minutesTracker1, secondsCounter1, minutesCounter1, minutes,  minutesTracker2, secondsCounter2, loopCounter, minutes2, minutesCounter2, soundCounter1, soundCounter2;
	//Times that can be modified
	private final int workMinutesDesired = 25;
	private int breakMinutesDesired = 5;
	private final int longBreakMinutesDesired = 15;
	private final int intervalWithLongBreak = 4; //Should be >=2
	//Conversions, that should not be touched
	private final int workSecondsDesired = workMinutesDesired*60;
	private int breakSecondsDesired = breakMinutesDesired*60;
	private int tempTime = breakSecondsDesired;
	private int tempTime2 = breakMinutesDesired;

	public Content(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		timer = new Timer(1000, new ButtonListener());
		//Work Panel
		panel1 = new JPanel();
		TitledBorder titled1 = new TitledBorder("Work");
		titled1.setTitleFont(new Font("Arial", Font.BOLD, 50));
		panel1.setBorder(titled1);
		screen1 = new JLabel(workMinutesDesired + "m 00s");
		screen1.setFont(new Font("Arial", Font.PLAIN, 40));
		panel1.add(screen1);
		add(panel1);
		//Break Panel
		panel2 = new JPanel();
		TitledBorder titled2 = new TitledBorder("Break");
		titled2.setTitleFont(new Font("Arial", Font.BOLD, 50));
		panel2.setBorder(titled2);
		screen2 = new JLabel(breakMinutesDesired + "m 00s");
		screen2.setFont(new Font("Arial", Font.PLAIN, 40));
		panel2.add(screen2);
		add(panel2);
		//Buttons
		panel3 = new JPanel();
		start = new JButton("Start");
		start.addActionListener(new ButtonListener());
		start.addKeyListener(new mnemonicListener());
		start.setToolTipText("Shortcut: 1");
		panel3.add(start);
		stop = new JButton("Pause");
		stop.addActionListener(new ButtonListener());
		stop.addKeyListener(new mnemonicListener());
		stop.setToolTipText("Shortcut: 2");
		panel3.add(stop);
		reset = new JButton("Reset");
		reset.addActionListener(new ButtonListener());
		reset.addKeyListener(new mnemonicListener());
		reset.setToolTipText("Shortcut:  3");
		panel3.add(reset);
		add(panel3);
	}
	private class mnemonicListener implements KeyListener{
		public void keyPressed(KeyEvent event) {
			char ch = event.getKeyChar();
			if(ch == '1'){
				start.doClick();
			} else if (ch == '2'){
				stop.doClick();
			} else if (ch == '3'){
				reset.doClick();
			}
		}
		public void keyReleased(KeyEvent event) {
		}
		public void keyTyped(KeyEvent event) {
		}
	}
	private void playSound(){
		try {
	        java.io.InputStream inputStream = getClass().getResourceAsStream("/basic_bell.wav");
	        AudioStream audioStream = new AudioStream(inputStream);
	        AudioPlayer.player.start(audioStream);
	    } catch (Exception exc) {
	    	exc.printStackTrace(System.out);
	    }
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == stop){
				timer.stop();
			} else if (event.getSource() == reset){
				timer.stop();
				panel1.setBackground(Color.white);
				panel2.setBackground(Color.white);
				secondsCounter1 = 0;
				secondsCounter2 = 0;
				minutesTracker1 = 0;
				minutesCounter1 = 0;
				minutesTracker2 = 0;
				minutesCounter2 = 0;
				soundCounter1 = 0;
				soundCounter2 = 0;
				breakSecondsDesired = tempTime;
				breakMinutesDesired = tempTime2;
				
				screen1.setText(workMinutesDesired + "m 00s");
				screen2.setText(breakMinutesDesired + "m 00s");
			} else {
				timer.start();
				//Work Timer
				if(secondsCounter1 != 60 || minutes <0){
					panel1.setBackground(Color.green);
					minutesTracker1++;
					minutes = (workSecondsDesired-minutesTracker1)/60;
					screen1.setText(String.valueOf(minutes + "m " + (59-secondsCounter1) +"s"));
					secondsCounter1++;
				} else {
					secondsCounter1 = 0;
					minutesCounter1++;
				}
				//Break Timer
				if(minutesCounter1 == workMinutesDesired){
					soundCounter1++;
					if(soundCounter1 == 1){
						playSound();
					}
					panel1.setBackground(Color.white);
					screen1.setText(workMinutesDesired + "m 00s");
					if(secondsCounter2 != 60 || minutes2 <0){
						panel2.setBackground(Color.red);
						minutesTracker2++;
						minutes2 = (breakSecondsDesired-minutesTracker2)/60;
						screen2.setText(String.valueOf(minutes2 + "m " + (59-secondsCounter2) +"s"));
						secondsCounter2++;
					} else {
						secondsCounter2 = 0;
						minutesCounter2++;
						if(minutesCounter2 == breakMinutesDesired){
							soundCounter2++;
							if(soundCounter2 == 1){
								playSound();
							}
							secondsCounter1 = 0;
							secondsCounter2 = 0;
							minutesTracker1 = 0;
							minutesCounter1 = 0;
							minutesTracker2 = 0;
							minutesCounter2 = 0;
							soundCounter1 = 0;
							soundCounter2 = 0;
							panel2.setBackground(Color.white);
							screen2.setText(breakMinutesDesired + "m 00s");
							loopCounter++;
							if(loopCounter == (intervalWithLongBreak-1)){
								screen2.setText(longBreakMinutesDesired + "m 00s");
								breakSecondsDesired = longBreakMinutesDesired*60;
								breakMinutesDesired = longBreakMinutesDesired;
							} 
						}
					}
				}
				if (loopCounter == intervalWithLongBreak){
					loopCounter = 0;
					breakSecondsDesired = tempTime;
					breakMinutesDesired = tempTime2;
					screen2.setText(breakMinutesDesired + "m 00s");
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