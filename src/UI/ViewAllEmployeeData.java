package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DbConnection;

public class ViewAllEmployeeData extends JFrame {
	DefaultTableModel tmodel;
	JPanel btnpanel;
	JPanel panel;
	JTable table;
	JButton buttonUpdate, buttonDelete, buttonReturn, buttonRefresh;
	Connection c1;
	Statement stm;
	ResultSet rs;
	Container con = getContentPane();

	public ViewAllEmployeeData() {
		c1 = DbConnection.getConnection();
		try {

			stm = c1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		} catch (Exception e) {
			e.printStackTrace();
		}

		tmodel = new DefaultTableModel();
		table = new JTable(tmodel);
		SetColHeader();
		setSize(600, 500);
		setLocation(250, 300);
		setTitle("All Employees");
		setResizable(false);
		con.add(new JScrollPane(table));

		try {
			rs = stm.executeQuery("select * from employee");

			while (rs.next()) {
				tmodel.insertRow(0,
						new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4) });
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "tush");
		}
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);  

	}

	private void SetColHeader() {
		tmodel.addColumn("Id");
		tmodel.addColumn("Name");
		tmodel.addColumn("Age");
		tmodel.addColumn("Salary");

		addButtons();
	}

	private void addButtons() {
		// Update Button
		buttonUpdate = new JButton("Update");
		buttonUpdate.setBounds(110, 400, 70, 30);
		con.add(buttonUpdate);
		buttonUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateEmployee();
			}
		});

		// Delete Button
		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(210, 400, 70, 30);
		con.add(buttonDelete);
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DeletePopup();
			}
		});

		// Return Button
		buttonReturn = new JButton("Return");
		buttonReturn.setBounds(310, 400, 70, 30);
		con.add(buttonReturn);
		buttonReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EmployeeData();
				dispose();

			}
		});

		// Refresh Button
		buttonRefresh = new JButton("Refresh Table");
		buttonRefresh.setBounds(410, 400, 100, 30);
		con.add(buttonRefresh);
	}
}