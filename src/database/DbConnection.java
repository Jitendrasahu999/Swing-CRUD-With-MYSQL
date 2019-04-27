package database;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DbConnection {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Load the Drivers.

			connection = DriverManager.getConnection("jdbc:mysql://localhost/empdb?user=root&password=root"); // Use database and passwords.

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Driver not loaded successfully...");
			e.printStackTrace();
		}

		return connection;
	}

}