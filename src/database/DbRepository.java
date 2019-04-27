package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class DbRepository {

	//Insert method save data in database.
	public static boolean insert(Employee emp) {
		Connection con = DbConnection.getConnection();

		try {

			String insert = "INSERT INTO employee(name, age, salary) VALUES(?,?,?)";

			PreparedStatement ps = con.prepareStatement(insert);

			ps.setString(1, emp.getName());
			ps.setString(2, emp.getAge());
			ps.setString(3, emp.getSalary());

			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return true;

	}

	// Get All Data for Employee.
	public static List<Employee> getAllEmps() {

		Connection con = DbConnection.getConnection();

		List<Employee> allEmp = new ArrayList<Employee>();

		try {

			String select = "SELECT * FROM employee";

			PreparedStatement ps = con.prepareStatement(select);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();

				emp.setId(rs.getLong("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getString("age"));
				emp.setSalary(rs.getString("salary"));

				allEmp.add(emp);

			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allEmp;
	}

	// Delete by id for Employee.
	public static boolean delete(Long id) {
		Connection con = DbConnection.getConnection();

		try {
			String delete = "DELETE FROM employee where id = ?";

			PreparedStatement ps = con.prepareStatement(delete);

			ps.setLong(1, id);
			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return true;

	}

	// Get by ID for Employee Data
	public static Employee getEmpById(Long id) {
		Employee emp = null;
		Connection con = DbConnection.getConnection();

		try {
			String select = "SELECT * FROM employee where id = ?";

			PreparedStatement ps = con.prepareStatement(select);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Long id1 = rs.getLong("id");
				String name = rs.getString("name");
				String age = rs.getString("age");
				String salary = rs.getString("salary");

				emp = new Employee(id,name, age, salary);
			}
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return emp;
	}

	//Update data in database.
	public static boolean updateEmployee(Employee emp) throws Exception {

		Connection con = DbConnection.getConnection();
		String select = "UPDATE employee SET name = ?, age = ?, salary = ? WHERE id = ?";

		PreparedStatement ps = con.prepareStatement(select);

		ps.setString(1, emp.getName());
		ps.setString(2, emp.getAge());
		ps.setString(3, emp.getSalary());
		
		ps.setLong(4, emp.getId());

		ps.executeUpdate();

		ps.close();
		con.close();
		return true;

	}

}
