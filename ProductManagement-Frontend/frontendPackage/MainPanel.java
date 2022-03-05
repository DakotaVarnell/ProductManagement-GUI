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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

public class MainPanel extends JPanel{
	private int count;
	private JButton push;
	private JLabel lblPushes;
	private ProductCollection myStore;
	

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
		JTextField inventory = new JTextField();
		inventory.setBounds(207, 261, 385, 70);
		inventory.setEditable(false);
		add(inventory);
		
		//4 adds the add, remove, and update inventory buttons to our GUI
		JButton addInventory = new JButton("Add");
		addInventory.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		addInventory.setBounds(207, 371, 89, 23);
		add(addInventory);
		
		JButton removeInventory = new JButton("Remove");
		removeInventory.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		removeInventory.setBounds(316, 371, 89, 23);
		add(removeInventory);
		
		JButton updateInventory = new JButton("Update Inventory");
		updateInventory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateInventory.setBounds(427, 371, 165, 23);
		add(updateInventory);
		
		
		//Adds the current inventory text area to our GUI to show what the text below is
		JTextArea current = new JTextArea();
		current.setFont(new Font("Times New Roman", Font.BOLD, 20));
		current.setText("Current Inventory");
		current.setEditable(false);
		current.setBounds(207, 226, 385, 35);
		add(current);
		
		//Action Listener to show the selected items under a certain category
		JButton btnShowItem = new JButton("Show Items");
		btnShowItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductCollection prods = myStore.retrieveCollection((String)categories.getSelectedItem());
				inventory.setText(prods.toString()+ "\n");
			}
		});
		btnShowItem.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnShowItem.setBounds(207, 132, 115, 23);
		add(btnShowItem);
		

		

	}
	
	
	public void doClose() {
		myStore.toRead();
		myStore.toWrite();
	}
}



