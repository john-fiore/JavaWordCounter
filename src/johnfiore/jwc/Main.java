package johnfiore.jwc;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Dimension;

public class Main
{
	static App appPanel;
	
	public static void main(String[] args)
	{
		// Set Look-and-Feel theme so it doesn't look bad
		try
		{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex)
		{
			ex.printStackTrace();
		}
		
		// Create main window and add appPanel to it.
		JFrame appWindow = new JFrame("Java Word Counter");
		appPanel = new App();
		
		appWindow.setSize(new Dimension(510, 320));
		appWindow.setResizable(false);
		appWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appWindow.add(appPanel);
		appWindow.pack();
		appWindow.setVisible(true);
	}
}