package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import database.DbConnection;
import database.DbRepository;
import model.Employee;

public class UpdateEmployee extends JFrame {
	private JButton buttonUpdate, buttonCancel, buttonFetch;
	private JTextField textFieldName, textFieldAge, textFieldSalary, textFieldEnterId;
	private JLabel labelName, labelAge, labelSalary, labelEnterId;

	public UpdateEmployee() {
		StartUpdateGUI();
	}

	private void StartUpdateGUI() {
		
		//Save data into container.
		Container c = getContentPane();
		c.setLayout(null);
		
		labelEnterId = new JLabel("Enter Employee ID");
		labelEnterId.setBounds(10, 30, 300, 30);
		c.add(labelEnterId);

		textFieldEnterId = new JTextField();
		textFieldEnterId.setBounds(120, 34, 180, 20);
		c.add(textFieldEnterId);

		buttonFetch = new JButton("Fetch");
		buttonFetch.setBounds(320, 30, 60, 25);
		c.add(buttonFetch);

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

		buttonFetch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				fetchEmployeeData();
			}
		});

		// Button Add Employee.
		buttonUpdate = new JButton("Update");
		buttonUpdate.setBounds(120, 240, 80, 30);
		c.add(buttonUpdate);
		buttonUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					updateEmployeeData();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// Button Show List Employee.
		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(240, 240, 80, 30);
		c.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setSize(400, 350);
		setTitle("Update Employee");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);  

	}

	private void fetchEmployeeData() {

		String id = textFieldEnterId.getText();
		if (id != null && id != "") {
			Employee getData = DbRepository.getEmpById(Long.parseLong(id));
			
			String name = getData.getName();
			String age = getData.getAge();
			String salary = getData.getSalary();
			
			textFieldName.setText(name);
			textFieldAge.setText(age);
			textFieldSalary.setText(salary);
		}else {
			JOptionPane.showMessageDialog(null, "Please Enter Employee ID !!!!");
		}
	}

	// Update Data Method calling from ButtonUpdate.
	private void updateEmployeeData() throws Exception {
		String id = textFieldEnterId.getText();
		String textFieldEmployeeName = textFieldName.getText();
		String textFieldEmployeeAge = textFieldAge.getText();
		String textFieldEmployeeSalary = textFieldSalary.getText();

		Employee emp = new Employee();
		emp.setId(Long.parseLong(id));
		emp.setName(textFieldEmployeeName);
		emp.setAge(textFieldEmployeeAge);
		emp.setSalary(textFieldEmployeeSalary);

		boolean checkUpdate = DbRepository.updateEmployee(emp);

		if (checkUpdate == true) {
			JOptionPane.showMessageDialog(null, "Data Updated");
			textFieldName.setText("");
			textFieldAge.setText("");
			textFieldSalary.setText("");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Not saved");
			textFieldName.setText("");
			textFieldAge.setText("");
			textFieldSalary.setText("");
		}
	}

}
