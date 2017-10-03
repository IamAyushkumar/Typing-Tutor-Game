package GameTypingTutor;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {

	private JLabel lbltimer;
	private JLabel lblscore;
	private JLabel lblword;
	private JTextField txtword;
	private JButton startbutton;
	private JButton stopbutton;

	private int timeleft;
	private int score;
	private boolean running;
	private String[] data;
	private Timer timer;

	public TypingTutor(String feeder) {

		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);

		Font font = new Font("Times New Roman", 1, 75);

		lbltimer = new JLabel();
		lbltimer.setText("Timer  :");
		lbltimer.setBackground(Color.black);;
		lbltimer.setFont(font);
		super.add(lbltimer);

		lblscore = new JLabel();
		lblscore.setText("SCORE  :");
		lblscore.setBackground(Color.BLACK);
		lblscore.setFont(font);
		super.add(lblscore);

		lblword = new JLabel();
		lblword.setText(" ");
		lblword.setOpaque(true);
		lblword.setBackground(Color.GREEN);
		lblword.setFont(font);
		super.add(lblword);

		txtword = new JTextField();
		txtword.setText(" ");
		txtword.setFont(font);
		super.add(txtword);

		startbutton = new JButton();
		startbutton.setText("*START*");
		startbutton.setFont(font);
		startbutton.addActionListener(this);
		super.add(startbutton);

		stopbutton = new JButton();
		stopbutton.setText("*STOP*");
		stopbutton.setFont(font);
		stopbutton.addActionListener(this);
		super.add(stopbutton);
		// stopbutton.setIcon(defaultIcon);

		super.setSize(1000, 1000);
		super.setTitle("=> TYPING MASTER <=");
		

		data = feeder.split(" ");
		initgame();
		
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public void initgame() {
		timeleft = 50;
		score = 0;
		running = false;
		timer = new Timer(2500, this);
		lbltimer.setText("TIMER   : " + timeleft);
		lblscore.setText("SCORE  :" + score);
		lblword.setText(" ");
		txtword.setText("");
		startbutton.setText("*START*");
		stopbutton.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startbutton) {
			handlestart();
		} else if (e.getSource() == stopbutton) {
			handlestop();
		} else if (e.getSource() == timer) {
			handletimer();
		}

	}

	public void handlestart() {
		if (running) {
			
			timer.stop();
			
			running = false;
			
			startbutton.setText("*RESUME*");
			
			txtword.setEditable(false);
			
			
		}
		
		else{
			
			timer.start();
			
			running = true;
			
			startbutton.setText("*PAUSE*");
			
			stopbutton.setEnabled(true);
			
			txtword.setEditable(true);
			
			
			
		}

	}

	public void handlestop() {

		timer.stop();
		
		 JOptionPane.showMessageDialog(this, "GAME OVER || YOUR SCORE IS => " + score + " \n THANKS FOR PLAYING");
		 int choice = JOptionPane.showConfirmDialog(this, "PLAY AGAIN?");
		 
		 if(choice == JOptionPane.YES_OPTION)
		 {
			 initgame();
		 }
		 else{
			 super.dispose();
		 }

	}

	public void handletimer() {
		if (timeleft > 0) {

			timeleft--;
			
			
			String expectedword = txtword.getText();
			String actualword = lblword.getText();

			if (expectedword.equals(actualword) && expectedword.length() > 0) {
				score++;
			}

			lblscore.setText("SCORE :" + score);

			lblword.setText(data[(int) (Math.random() * data.length)]);
			lbltimer.setText("TIMER  :" + timeleft);


			txtword.setText("");
			txtword.setFocusable(true);
		}

		else {
			handlestop();
		}
	}

}
