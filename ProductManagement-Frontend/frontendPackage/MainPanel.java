package frontendPackage;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import project1package.Product;
import project1package.ProductCollection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MainPanel extends JPanel{
	private int count;
	private JButton push;
	private JLabel lblPushes;
	private ProductCollection myStore;
	private ImageIcon icon;
	

	public MainPanel() {
		setBackground(new Color(0, 0, 139));
		setLayout(null);
		
		//Initialize our product collection with the text file and read all the data in
		myStore = new ProductCollection("./inventoryTest.txt");
		myStore.toRead();
		System.out.println(myStore);
		
		//1  Adds the main title label to our music store GUI
		JLabel lblMusicStore = new JLabel("5th Avenue Music");
		lblMusicStore.setForeground(new Color(250, 250, 210));
		lblMusicStore.setFont(new Font("Times New Roman", Font.BOLD, 50));
		lblMusicStore.setBackground(new Color(250, 250, 210));
		lblMusicStore.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicStore.setBounds(10, 11, 790, 44);
		add(lblMusicStore);
		
		//2  Adds the categories combo box to our GUI
		JComboBox categories = new JComboBox();
		categories.setFont(new Font("Times New Roman", Font.BOLD, 20));
		categories.setModel(new DefaultComboBoxModel(myStore.getCategories().toArray()));
		categories.setBounds(207, 86, 385, 22);
		add(categories);
		
		//Adds our entire inventory in a list
		JTextArea inventory = new JTextArea();
		inventory.setBounds(207, 261, 385, 99);
		inventory.setEditable(false);
		add(inventory);
		
		//4 adds the add, remove, and update inventory buttons to our GUI
		JButton addInventory = new JButton("Add");
		addInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("How many would you like to add? ");
				int quantityIncrease = Integer.parseInt(s);
				System.out.println(inventory.getSelectedText());
				Product id = myStore.findInstrument(inventory.getSelectedText());
				myStore.increaseStatus(id.getId(), quantityIncrease);
				System.out.println(id);
				myStore.toWrite();
				
			}
		});
		addInventory.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		addInventory.setBounds(207, 371, 89, 23);
		add(addInventory);
		
		
		//Allows us to decrease the amount of an items inventory
		JButton removeInventory = new JButton("Remove");
		removeInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("How many would you like to remove? ");
				int quantityDecrease = Integer.parseInt(s);
				System.out.println(inventory.getSelectedText());
				Product id = myStore.findInstrument(inventory.getSelectedText());
				myStore.decreaseStatus(id.getId(), quantityDecrease);
				System.out.println(id);
				myStore.toWrite();
			}
		});
		removeInventory.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		removeInventory.setBounds(316, 371, 89, 23);
		add(removeInventory);
		
		
		
		//This will update our inventory to the most current state
		JButton updateInventory = new JButton("Update Inventory");
		updateInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ArrayList <Product> prods = myStore.retrieveCollection((String)categories.getSelectedItem());
				
				String str = "";

				for (Product p : prods) {
					str += p + "\n";
				}
				
				myStore.toWrite();
				inventory.setText(str);
				
				
			}
		});
		updateInventory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateInventory.setBounds(427, 371, 165, 23);
		add(updateInventory);
		
		
		//Adds the current inventory text area to our GUI to show what the text below is
		JTextArea current = new JTextArea();
		current.setFont(new Font("Times New Roman", Font.BOLD, 20));
		current.setText("Current Inventory(Highlight ID's to edit)");
		current.setEditable(false);
		current.setBounds(207, 226, 385, 35);
		add(current);
		
		//Action Listener to show the selected items under a certain category
		JButton btnShowItem = new JButton("Show Items");
		btnShowItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList <Product> prods = myStore.retrieveCollection((String)categories.getSelectedItem());
				
				String str = "";

				for (Product p : prods) {
					str += p + "\n";
				}
				
				
				inventory.setText(str);
			}
		});
		btnShowItem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnShowItem.setBounds(207, 132, 115, 23);
		add(btnShowItem);
		
		
		//Displays our picture/logo on the screen
		JLabel picture;
		icon = new ImageIcon("guitarlogo.png");
		picture = new JLabel(icon);
		picture.setBounds(627, 11, 150, 157);
		add(picture);
		

		

	}
	
	
	public void doClose() {
		myStore.toRead();
		myStore.toWrite();
	}
}



