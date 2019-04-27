package UI;

import java.awt.Container;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.DbRepository;
import model.Employee;

public class EmployeeDataList {

	private DefaultTableModel tmodel;
	private JTable table;
	private JFrame frame;
	

	public EmployeeDataList() {
		 showEmployeeData();
		

	}

	private void showEmployeeData() {
		frame = new JFrame("Test");
		tmodel = new DefaultTableModel();
		table = new JTable(tmodel);
		setColHeader();
		table.setSize(500, 500);
		table.setLocation(250, 300);
		frame.add(new JScrollPane(table));
		
		List<Employee> getAllEmployee = DbRepository.getAllEmps();
		System.out.println("GetAllEmployee "+ getAllEmployee);
		
		

		
		
		frame.setSize(800, 600);
		frame.setTitle("Employee List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setResizable(false);
		frame.setVisible(true);
	}

	private void setColHeader() {
		tmodel.addColumn("Name");
		tmodel.addColumn("Age");
		tmodel.addColumn("Salary");
	}

}
