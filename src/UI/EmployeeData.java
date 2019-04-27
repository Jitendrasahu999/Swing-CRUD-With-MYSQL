package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.DbRepository;
import model.Employee;

public class EmployeeData extends JFrame {

	private JButton buttonAdd, buttonList;
	private JTextField textFieldName, textFieldAge, textFieldSalary;
	private JLabel labelName, labelAge, labelSalary;

	public EmployeeData() {
		StartGUI();
	}

	private void StartGUI() {

		Container c = getContentPane();
		c.setLayout(null);

		labelName = new JLabel("Name");
		labelName.setBounds(70, 80, 300, 30);
		c.add(labelName);

		textFieldName = new JTextField(15);
		textFieldName.setBounds(120, 84, 200, 20);
		c.add(textFieldName);

		labelAge = new JLabel("Age");
		labelAge.setBounds(70, 120, 300, 30);
		c.add(labelAge);

		textFieldAge = new JTextField(15);
		textFieldAge.setBounds(120, 124, 200, 20);
		c.add(textFieldAge);

		labelSalary = new JLabel("Salary");
		labelSalary.setBounds(70, 160, 300, 30);
		c.add(labelSalary);

		textFieldSalary = new JTextField(15);
		textFieldSalary.setBounds(120, 164, 200, 20);
		c.add(textFieldSalary);

		//Button Add Employee.
		buttonAdd = new JButton("ADD");
		buttonAdd.setBounds(120, 240, 80, 30);
		c.add(buttonAdd);
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				insertEmployeeData();
			}
		});
		
		//Button Show List Employee.
		buttonList = new JButton("Show List");
		buttonList.setBounds(240, 240, 80, 30);
		c.add(buttonList);
		buttonList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new ViewAllEmployeeData();
			}
		});

		setSize(400, 350);
		setTitle("Add New Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);  

	}

	// Insert Data Method calling from ButtonAdd.
	private void insertEmployeeData() {
		String textFieldEmployeeName = textFieldName.getText();
		String textFieldEmployeeAge = textFieldAge.getText();
		String textFieldEmployeeSalary = textFieldSalary.getText();

		Employee emp = new Employee();
		emp.setName(textFieldEmployeeName);
		emp.setAge(textFieldEmployeeAge);
		emp.setSalary(textFieldEmployeeSalary);

		Boolean checkData = DbRepository.insert(emp);
		if (checkData == true) {
			JOptionPane.showMessageDialog(null, "Data saved");
			textFieldName.setText("");
			textFieldAge.setText("");
			textFieldSalary.setText("");
			this.dispose();
			new ViewAllEmployeeData();
		} else {
			JOptionPane.showMessageDialog(null, "Not saved");
			textFieldName.setText("");
			textFieldAge.setText("");
			textFieldSalary.setText("");
		}
	}

}
