package UI;

import java.awt.Color;
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

public class DeletePopup {
	private Font font = new Font("", Font.PLAIN, 10);
	private JButton buttonDelete, buttonCancel;
	private JTextField textFieldId;
	private JLabel labelId, labelIdShowError;
	private JFrame frame;

	public DeletePopup() {
		deletePopupStart();
	}

	private void deletePopupStart() {
		frame = new JFrame("Delete Employee");

		labelIdShowError = new JLabel();
		labelIdShowError.setBounds(120, 10, 300, 30);
		frame.add(labelIdShowError);

		labelId = new JLabel("Enter Employee ID");
		labelId.setBounds(20, 38, 200, 30);
		frame.add(labelId);

		textFieldId = new JTextField();
		textFieldId.setBounds(120, 40, 150, 25);
		frame.add(textFieldId);

		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(30, 100, 80, 30);
		frame.add(buttonDelete);
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteEmployeeById();
			}
		});

		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(130, 100, 80, 30);
		frame.add(buttonCancel);
		buttonCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		frame.setSize(300, 200);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Database Operation call by this methods.
	private void deleteEmployeeById() {
		String employeeId = textFieldId.getText();
		if (employeeId.isEmpty()) {
			labelIdShowError.setFont(font);
			labelIdShowError.setForeground(Color.RED);
			labelIdShowError.setText("Please Enter the Id!!");
		} else {
			boolean isDelete = DbRepository.delete(Long.parseLong(employeeId));

			if (isDelete == true) {
				JOptionPane.showMessageDialog(null, "Data Deleted");
				textFieldId.setText("");
				frame.dispose();
			}
		}

		// Textfield events Employee Name.
		textFieldId.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				labelIdShowError.setText(null);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				labelIdShowError.setText(null);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				labelIdShowError.setText(null);
			}
		});

	}

}
