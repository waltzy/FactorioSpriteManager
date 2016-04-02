package com.graymatter.spritemanager.ui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class OpenNewWorkspaceDialouge extends JDialog {

	
	
	private JPanel contentPane;
	private JTextField modFolderField;
	private JTextField AssetFolderField;
	private JButton btnSave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OpenNewWorkspaceDialouge frame = new OpenNewWorkspaceDialouge();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OpenNewWorkspaceDialouge(JFrame frame, boolean bool){
		super(frame, bool);
		initUI();
	}
	
	/**
	 * Create the frame.
	 */
	public OpenNewWorkspaceDialouge() {
		initUI();
	}

	public void initUI(){
		setTitle("Create New Workspace");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		modFolderField = new JTextField();
		modFolderField.setEditable(false);
		GridBagConstraints gbc_modFolderField = new GridBagConstraints();
		gbc_modFolderField.insets = new Insets(0, 0, 5, 5);
		gbc_modFolderField.fill = GridBagConstraints.HORIZONTAL;
		gbc_modFolderField.gridx = 0;
		gbc_modFolderField.gridy = 0;
		contentPane.add(modFolderField, gbc_modFolderField);
		modFolderField.setColumns(60);
		
		JButton btnSetModFolder = new JButton("Set Mod Folder");
		btnSetModFolder.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gbc_btnSetModFolder = new GridBagConstraints();
		gbc_btnSetModFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetModFolder.insets = new Insets(0, 0, 5, 0);
		gbc_btnSetModFolder.gridx = 1;
		gbc_btnSetModFolder.gridy = 0;
		contentPane.add(btnSetModFolder, gbc_btnSetModFolder);
		
		AssetFolderField = new JTextField();
		AssetFolderField.setEditable(false);
		GridBagConstraints gbc_AssetFolderField = new GridBagConstraints();
		gbc_AssetFolderField.insets = new Insets(0, 0, 5, 5);
		gbc_AssetFolderField.fill = GridBagConstraints.HORIZONTAL;
		gbc_AssetFolderField.gridx = 0;
		gbc_AssetFolderField.gridy = 1;
		contentPane.add(AssetFolderField, gbc_AssetFolderField);
		AssetFolderField.setColumns(60);
		
		JButton btnSetAssetsFolder = new JButton("Set Assets Folder");
		btnSetAssetsFolder.setHorizontalAlignment(SwingConstants.LEADING);
		GridBagConstraints gbc_btnSetAssetsFolder = new GridBagConstraints();
		gbc_btnSetAssetsFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSetAssetsFolder.insets = new Insets(0, 0, 5, 0);
		gbc_btnSetAssetsFolder.gridx = 1;
		gbc_btnSetAssetsFolder.gridy = 1;
		contentPane.add(btnSetAssetsFolder, gbc_btnSetAssetsFolder);
		
		btnSave = new JButton("Save");
		GridBagConstraints gbc_btnSave = new GridBagConstraints();
		gbc_btnSave.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSave.gridx = 1;
		gbc_btnSave.gridy = 2;
		contentPane.add(btnSave, gbc_btnSave);
		pack();
	}
	
}
