package UI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Welcome {
	private JFrame frame;
	private JButton buttonStartProject, buttonExit;
	private JLabel labelWelcome, labelMadeBy;
	
	public Welcome() {
		startGUI();
	}

	private void startGUI() {
		frame = new JFrame("CRUD Operation Java Swing with MySQL");
		
		labelWelcome = new JLabel("CRUD Operation Java Swing with MySQL", JLabel.CENTER);
		labelWelcome.setBounds(0, 30, 800, 100);
		labelWelcome.setFont(new Font("Serif", Font.PLAIN, 40));
		frame.add(labelWelcome);
		
		labelMadeBy = new JLabel("Jitendra Kumar Sahu");
		labelMadeBy.setBounds(680, 410, 800, 100);
		labelMadeBy.setFont(new Font("Serif", Font.PLAIN, 13));
		frame.add(labelMadeBy);
		
		buttonStartProject = new JButton("Start Project");
		buttonStartProject.setBounds(280, 400, 110, 30);
		frame.add(buttonStartProject);
		buttonStartProject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new EmployeeData();				
			}
		});
		
		buttonExit = new JButton("Close");
		buttonExit.setBounds(430, 400, 110, 30);
		frame.add(buttonExit);
		buttonExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		
		frame.setSize(800, 500);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
