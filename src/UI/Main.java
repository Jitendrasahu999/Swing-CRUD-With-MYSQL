package UI;

import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new Welcome();
		} 
		catch (Exception e) {
			System.out.println("Error!!!!" + e);
		}
	}
}
