//Dakota Varnell
//OOP with Java
//Project 2

package frontendPackage;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class MainFrame {

	public static void main(String[] args) {
		//Titles our new frame as Music Store
		JFrame frame = new JFrame("Music Store");
		
		//Closes the frame when the window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Sets the size of the frame to 800, 500
		frame.setSize(800, 500);

		//creates our new panel within our frame where all of our GUI is displayed
		MainPanel myPanel = new MainPanel();

		//Adds the panel to the frame
		frame.getContentPane().add(myPanel);
		
		//Make the frame not resizeable
		frame.setResizable(false);
		
		//frame.pack();
		
		//Makes our frame visible
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				myPanel.doClose();
			}
		});
	}
}

