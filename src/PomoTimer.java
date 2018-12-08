import java.awt.event.*; //For action listener
import java.awt.*;
import javax.swing.*; //For GUI
import javax.swing.border.TitledBorder;

import Content.ButtonListener;
class Content extends JPanel{
	private JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8;
	private JLabel screen1, screen2, screen3, screen4, screen5, screen6, screen7, screen8;
	private JButton start, stop, reset;
	private Timer timer;
	private int minutesTracker1, secondsCounter1, minutesCounter1, minutes,  minutesTracker2, secondsCounter2, minutesCounter2, minutesTracker3, secondsCounter3, minutesCounter3, minutesTracker4, secondsCounter4, minutesCounter4, minutesTracker5, secondsCounter5, minutesCounter5, minutesTracker6, secondsCounter6, minutesCounter6, minutesTracker7, secondsCounter7, minutesCounter7, minutesTracker8, secondsCounter8, minutesCounter8; 
	private final int workSecondsDesired = 60;
	private final int workMinutesDesired = workSecondsDesired/60;
	private final int breakSecondsDesired = 60;
	private final int breakMinutesDesired = breakSecondsDesired/60;

	
	public Content(){
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		timer = new Timer(1000, new ButtonListener());
		
		panel1 = new JPanel();
		TitledBorder titled1 = new TitledBorder("Work");
		panel1.setBorder(titled1);
		screen1 = new JLabel("25m 00s");
		panel1.add(screen1);
		add(panel1);
		
		panel2 = new JPanel();
		TitledBorder titled2 = new TitledBorder("Break");
		panel2.setBorder(titled2);
		screen2 = new JLabel("5m 00s");
		panel2.add(screen2);
		add(panel2);
		
		panel3 = new JPanel();
		TitledBorder titled3 = new TitledBorder("Work");
		panel3.setBorder(titled3);
		screen3 = new JLabel("25m 00s");
		panel3.add(screen3);
		add(panel3);
		
		panel4 = new JPanel();
		TitledBorder titled4 = new TitledBorder("Break");
		panel4.setBorder(titled4);
		screen4 = new JLabel("5m 00s");
		panel4.add(screen4);
		add(panel4);
		
		panel5 = new JPanel();
		TitledBorder titled5 = new TitledBorder("Work");
		panel5.setBorder(titled5);
		screen5 = new JLabel("25m 00s");
		panel5.add(screen5);
		add(panel5);
		
		panel6 = new JPanel();
		TitledBorder titled6 = new TitledBorder("Break");
		panel6.setBorder(titled6);
		screen6 = new JLabel("5m 00s");
		panel6.add(screen6);
		add(panel6);
		
		panel7 = new JPanel();
		TitledBorder titled7 = new TitledBorder("Work");
		panel7.setBorder(titled7);
		screen7 = new JLabel("25m 00s");
		panel7.add(screen7);
		add(panel7);
		
		
		
		panel8 = new JPanel();
		TitledBorder titled8 = new TitledBorder("Break");
		panel8.setBorder(titled8);
		screen8 = new JLabel("5m 00s");
		panel8.add(screen8);
		add(panel8);
		
		start = new JButton("Start");
		start.addActionListener(new ButtonListener());
		add(start);
		stop = new JButton("Stop");
		stop.addActionListener(new ButtonListener());
		add(stop);
		reset = new JButton("Reset");
		reset.addActionListener(new ButtonListener());
		add(reset);
		
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == stop){
				timer.stop();
			} else if (event.getSource() == reset){
				timer.stop();
				minutesTracker1 = 0;
				secondsCounter1 = 0;
				screen1.setText("0m 00s");
			} else { //can't be else if start
				timer.start();
				if(secondsCounter1 != 60){
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
					screen1.setText("Finished");
					if(secondsCounter2 != 60){
						panel2.setBackground(Color.red);
						minutesTracker2++;
						minutes = (breakSecondsDesired-minutesTracker2)/60;
						screen2.setText(String.valueOf(minutes + "m " + (60-secondsCounter2) +"s"));
						secondsCounter2++;
					} else {
						secondsCounter2 = 0;
						minutesCounter2++;
					}
					if(minutesCounter2 == workMinutesDesired){
						panel2.setBackground(Color.white);
						screen2.setText("Finished");
						if(secondsCounter3 != 60){
							panel3.setBackground(Color.green);
							minutesTracker3++;
							minutes = (workSecondsDesired-minutesTracker3)/60;
							screen3.setText(String.valueOf(minutes + "m " + (60-secondsCounter3) +"s"));
							secondsCounter3++;
						} else {
							secondsCounter3 = 0;
							minutesCounter3++;
						}
						if(minutesCounter3 == breakMinutesDesired){
							panel3.setBackground(Color.white);
							screen3.setText("Finished");
							if(secondsCounter4 != 60){
								panel4.setBackground(Color.red);
								minutesTracker4++;
								minutes = (breakSecondsDesired-minutesTracker4)/60;
								screen2.setText(String.valueOf(minutes + "m " + (60-secondsCounter4) +"s"));
								secondsCounter4++;
							} else {
								secondsCounter4 = 0;
								minutesCounter4++;
							}
							if(minutesCounter4 == workMinutesDesired){
								panel4.setBackground(Color.white);
								screen4.setText("Finished");
								if(secondsCounter5 != 60){
									panel5.setBackground(Color.green);
									minutesTracker5++;
									minutes = (workSecondsDesired-minutesTracker5)/60;
									screen5.setText(String.valueOf(minutes + "m " + (60-secondsCounter5) +"s"));
									secondsCounter5++;
								} else {
									secondsCounter5 = 0;
									minutesCounter5++;
								}
								if(minutesCounter5 == breakMinutesDesired){
									panel5.setBackground(Color.white);
									screen5.setText("Finished");
									if(secondsCounter6 != 60){
										panel6.setBackground(Color.red);
										minutesTracker6++;
										minutes = (breakSecondsDesired-minutesTracker6)/60;
										screen6.setText(String.valueOf(minutes + "m " + (60-secondsCounter6) +"s"));
										secondsCounter6++;
									} else {
										secondsCounter6 = 0;
										minutesCounter6++;
									}
									if(minutesCounter6 == workMinutesDesired){
										panel5.setBackground(Color.white);
										screen5.setText("Finished");
										if(secondsCounter7 != 60){
											panel7.setBackground(Color.green);
											minutesTracker7++;
											minutes = (workSecondsDesired-minutesTracker7)/60;
											screen5.setText(String.valueOf(minutes + "m " + (60-secondsCounter7) +"s"));
											secondsCounter7++;
										} else {
											secondsCounter7 = 0;
											minutesCounter7++;
										}
										if(minutesCounter7 == breakMinutesDesired){
											panel7.setBackground(Color.white);
											screen7.setText("Finished");
											if(secondsCounter8 != 60){
												panel8.setBackground(Color.red);
												minutesTracker8++;
												minutes = (breakSecondsDesired-minutesTracker8)/60;
												screen8.setText(String.valueOf(minutes + "m " + (60-secondsCounter8) +"s"));
												secondsCounter8++;
											} else {
												secondsCounter8 = 0;
												minutesCounter8++;
											}
										}
									}
								}
							}
						}
					}
				}	
			}
		}
	}	
}
public class PomoTimer {
	public static void main(String[] args) {
		//Sets up the window for the GUI
		JFrame frame = new JFrame("Pomodoro Timer by Elam"); //Title of window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Adds content to GUI
		Content panel = new Content();
		frame.getContentPane().add(panel);
		//Allows it to open when executed
		frame.pack();
		frame.setVisible(true);
		//frame.setResizable(false); //Disables window resize

	}

}
