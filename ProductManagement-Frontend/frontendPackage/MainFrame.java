package frontendPackage;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class MainFrame {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Music Store");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);

		MainPanel myPanel = new MainPanel();

		frame.getContentPane().add(myPanel);
		frame.setResizable(false);
		//frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				myPanel.doClose();
			}
		});
	}
}

