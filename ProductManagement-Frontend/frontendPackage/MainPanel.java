//Dakota Varnell
//OOP with Java
//Project 2


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
import javax.swing.JEditorPane;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JCheckBox;

public class MainPanel extends JPanel{
	private int count;
	private JButton push;
	private JLabel lblPushes;
	private ProductCollection myStore;
	private ImageIcon icon;
	private JTextField txtSuggestProduct;
	private JTextField txtMaxInventory;
	
	//Our mainpanel constructor
	public MainPanel() {
		setBackground(new Color(0, 0, 139));
		setLayout(null);
		
		//Initialize our product collection with the text file and read all the data in
		myStore = new ProductCollection("./inventoryTest.txt");
		myStore.toRead();
		//System.out.println(myStore);
		
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
		
		//Scroll pane for our inventory textArea
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(207, 261, 385, 99);
		add(scrollPane);
		
		//3 This progress bar will update as the inventory increases or decreases, if the inventory is full(total quantity 400) it will turn red
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMaximum(400);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBackground(Color.WHITE);
		progressBar.setValue(myStore.getInventorySize());
		if(progressBar.getValue() == progressBar.getMaximum()) {
			progressBar.setForeground(Color.RED);
			}
		progressBar.setBounds(207, 201, 385, 30);
		add(progressBar);
		
		//4 Adds our entire inventory in a text area
		JTextArea inventory = new JTextArea();
		scrollPane.setViewportView(inventory);
		inventory.setEditable(false);
		
		//5 adds the add inventory button to our GUI
		JButton addInventory = new JButton("Add");
		addInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = JOptionPane.showInputDialog("How many would you like to add? ");
				int quantityIncrease = Integer.parseInt(s);
				//System.out.println(inventory.getSelectedText());
				Product id = myStore.findInstrument(inventory.getSelectedText());
				myStore.increaseStatus(id.getId(), quantityIncrease);
				//System.out.println(id);
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
				//System.out.println(inventory.getSelectedText());
				Product id = myStore.findInstrument(inventory.getSelectedText());
				myStore.decreaseStatus(id.getId(), quantityDecrease);
				//System.out.println(id);
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
				
				progressBar.setValue(myStore.getInventorySize());
				inventory.setText(str);
				
				
			}
		});
		updateInventory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		updateInventory.setBounds(427, 371, 165, 23);
		add(updateInventory);
		
		
		
		//Adds the "current inventory" text area to our GUI to show what the text below is showing us
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
		
		
		//6 Displays our picture/logo on the screen
		JLabel picture;
		icon = new ImageIcon("guitarlogo.png");
		picture = new JLabel(icon);
		picture.setBounds(20, 66, 150, 157);
		add(picture);
		
		
		//7 This button will suggest products related to guitars in the inventory text area
		JRadioButton guitarBtn = new JRadioButton("Guitar");
		guitarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(guitarBtn.isSelected()) {
					Product p = new Product("12","Stratocaster","Fender",600.0,"Guitar",14,"file location");
					ArrayList <Product> prods = myStore.suggestCollection(p);
					
					String str = "";

					for (Product prod : prods) {
						str += prod + "\n";
					}
					
					
					inventory.setText(str);
				}
				
			}
		});
		guitarBtn.setBackground(Color.WHITE);
		guitarBtn.setBounds(627, 255, 111, 28);
		add(guitarBtn);
		
		
		
		//This button will suggest products related to amps in the inventory text area
		JRadioButton ampButton = new JRadioButton("Amp");
		ampButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ampButton.isSelected()) {
					Product p = new Product("12","Stratocaster","Fender",600.0,"Amp",14,"file location");
					ArrayList <Product> prods = myStore.suggestCollection(p);
					
					String str = "";

					for (Product prod : prods) {
						str += prod + "\n";
					}
					
					
					inventory.setText(str);
				}
				
			
			}
		});
		
		ampButton.setBackground(Color.WHITE);
		ampButton.setBounds(627, 274, 111, 28);
		add(ampButton);
		
		
		//This button will suggest products related to pedals in the inventory text area
		JRadioButton pedalButton = new JRadioButton("Pedal");
		pedalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pedalButton.isSelected()) {
					Product p = new Product("12","Stratocaster","Fender",600.0,"Pedal",14,"file location");
					ArrayList <Product> prods = myStore.suggestCollection(p);
					
					String str = "";

					for (Product prod : prods) {
						str += prod + "\n";
					}
					
					
					inventory.setText(str);
				}
				
			}
			
		});
		pedalButton.setBackground(Color.WHITE);
		pedalButton.setBounds(627, 296, 111, 28);
		add(pedalButton);
		
		txtSuggestProduct = new JTextField();
		txtSuggestProduct.setBackground(Color.WHITE);
		txtSuggestProduct.setText("Suggest Product");
		txtSuggestProduct.setBounds(627, 232, 111, 25);
		add(txtSuggestProduct);
		txtSuggestProduct.setColumns(10);
		
		
		//8 This checkbox will set the photo for our logo to not visible and hide the photo
		JCheckBox HidePhoto = new JCheckBox("Hide Photo");
		HidePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(HidePhoto.isSelected()) {
					picture.setVisible(false);
				}
				else {
					picture.setVisible(true);
				}
			}
		});
		HidePhoto.setBounds(20, 50, 98, 20);
		add(HidePhoto);
		

		
		
		
		//Just a label that titles our max inventory limit
		txtMaxInventory = new JTextField();
		txtMaxInventory.setText("Max Inventory");
		txtMaxInventory.setColumns(10);
		txtMaxInventory.setBackground(new Color(255, 255, 255));
		txtMaxInventory.setBounds(513, 180, 79, 25);
		add(txtMaxInventory);
		

		

	}
	
	
	public void doClose() {
		myStore.toRead();
		myStore.toWrite();
	}
}



