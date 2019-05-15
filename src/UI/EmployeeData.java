package UI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import database.DbRepository;
import model.Employee;

public class EmployeeData extends JFrame {

	private JButton buttonAdd, buttonList;
	private JTextField textFieldName, textFieldAge, textFieldSalary;
	private JLabel labelName, labelAge, labelSalary, labelNameShowError, labelAgeShowError, labelSalaryShowError,
			labelAllFieldRequired;
	private Font font = new Font("", Font.PLAIN, 10);

	public EmployeeData() {
		StartGUI();
	}

	private void StartGUI() {

		Container c = getContentPane();
		c.setLayout(null);

		labelNameShowError = new JLabel();
		labelNameShowError.setBounds(120, 99, 300, 30);
		c.add(labelNameShowError);

		labelAgeShowError = new JLabel();
		labelAgeShowError.setBounds(120, 139, 300, 30);
		c.add(labelAgeShowError);

		labelSalaryShowError = new JLabel();
		labelSalaryShowError.setBounds(120, 179, 300, 30);
		c.add(labelSalaryShowError);

		labelAllFieldRequired = new JLabel();
		labelAllFieldRequired.setBounds(120, 60, 300, 30);
		c.add(labelAllFieldRequired);

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

		// Button Add Employee.
		buttonAdd = new JButton("ADD");
		buttonAdd.setBounds(120, 240, 80, 30);
		c.add(buttonAdd);
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				formValidation();
			}
		});

		// Button Show List Employee.
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

	private void formValidation() {
		String textFieldEmployeeName = textFieldName.getText();
		String textFieldEmployeeAge = textFieldAge.getText();
		String textFieldEmployeeSalary = textFieldSalary.getText();

		if (textFieldEmployeeName.trim().isEmpty() && textFieldEmployeeAge.trim().isEmpty()
				&& textFieldEmployeeSalary.trim().isEmpty()) {
			labelAllFieldRequired.setFont(font);
			labelAllFieldRequired.setForeground(Color.RED);
			labelAllFieldRequired.setText("All fields are required !!");
		} else if (textFieldEmployeeName.trim().isEmpty()) {
			labelNameShowError.setFont(font);
			labelNameShowError.setForeground(Color.RED);
			labelNameShowError.setText("Please enter the Employee Name!!");
		} else if (textFieldEmployeeAge.trim().isEmpty()) {
			labelAgeShowError.setFont(font);
			labelAgeShowError.setForeground(Color.RED);
			labelAgeShowError.setText("Please enter the Employee Age!!");
		} else if (textFieldEmployeeSalary.trim().isEmpty()) {
			labelSalaryShowError.setFont(font);
			labelSalaryShowError.setForeground(Color.RED);
			labelSalaryShowError.setText("Please enter the Employee Salary!!");
		}else {
			insertEmployeeData();
		}

		// Textfield events Employee Name.
		textFieldName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				labelNameShowError.setText(null);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				labelNameShowError.setText(null);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				labelNameShowError.setText(null);
			}
		});
		
		// Textfield events Employee Age.
		textFieldAge.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				labelAgeShowError.setText(null);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				labelAgeShowError.setText(null);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				labelAgeShowError.setText(null);
			}
		});
		
		// Textfield events Employee Salary.
		textFieldSalary.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				labelSalaryShowError.setText(null);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				labelSalaryShowError.setText(null);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				labelSalaryShowError.setText(null);
			}
		});
	}

}
