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

import database.DbConnection;
import database.DbRepository;
import model.Employee;

public class UpdateEmployee extends JFrame {
	private Font font = new Font("", Font.PLAIN, 10);
	private JButton buttonUpdate, buttonCancel, buttonFetch;
	private JTextField textFieldName, textFieldAge, textFieldSalary, textFieldEnterId;
	private JLabel labelName, labelAge, labelSalary, labelEnterId, labelFetchError, labelNameShowError,
			labelAgeShowError, labelSalaryShowError, labelAllFieldRequired;

	public UpdateEmployee() {
		StartUpdateGUI();
	}

	private void StartUpdateGUI() {

		// Save data into container.
		Container c = getContentPane();
		c.setLayout(null);

		labelFetchError = new JLabel();
		labelFetchError.setBounds(120, 10, 300, 30);
		c.add(labelFetchError);

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
					formValidationUpdate();
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
		if (id.isEmpty()) {
			labelFetchError.setFont(font);
			labelFetchError.setForeground(Color.RED);
			labelFetchError.setText("Please enter Employee Id!!!");
		} else {
			Employee getData = DbRepository.getEmpById(Long.parseLong(id));
			String name = getData.getName();
			String age = getData.getAge();
			String salary = getData.getSalary();

			textFieldName.setText(name);
			textFieldAge.setText(age);
			textFieldSalary.setText(salary);
		}

		// Textfield events Employee Salary.
		textFieldEnterId.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				labelFetchError.setText(null);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				labelFetchError.setText(null);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				labelFetchError.setText(null);
			}
		});
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

	private void formValidationUpdate() {
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
		} else {
			try {
				updateEmployeeData();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
