package com.graymatter.spritemanager.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JList;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.EtchedBorder;
import javax.swing.JComboBox;

public class ProjectEditorWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProjectEditorWindow frame = new ProjectEditorWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProjectEditorWindow() {
		UIUtils.setIcon(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024 , 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel spriteListPanel = new JPanel();
		spriteListPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_spriteListPanel = new GridBagConstraints();
		gbc_spriteListPanel.insets = new Insets(0, 0, 5, 5);
		gbc_spriteListPanel.fill = GridBagConstraints.BOTH;
		gbc_spriteListPanel.gridx = 0;
		gbc_spriteListPanel.gridy = 0;
		contentPane.add(spriteListPanel, gbc_spriteListPanel);
		GridBagLayout gbl_spriteListPanel = new GridBagLayout();
		gbl_spriteListPanel.columnWidths = new int[]{0, 0};
		gbl_spriteListPanel.rowHeights = new int[]{0, 0, 0};
		gbl_spriteListPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_spriteListPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		spriteListPanel.setLayout(gbl_spriteListPanel);
		
		JPanel managedSpritesMenuPanel = new JPanel();
		GridBagConstraints gbc_managedSpritesMenuPanel = new GridBagConstraints();
		gbc_managedSpritesMenuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_managedSpritesMenuPanel.anchor = GridBagConstraints.NORTH;
		gbc_managedSpritesMenuPanel.insets = new Insets(5, 5, 5, 5);
		gbc_managedSpritesMenuPanel.gridx = 0;
		gbc_managedSpritesMenuPanel.gridy = 0;
		spriteListPanel.add(managedSpritesMenuPanel, gbc_managedSpritesMenuPanel);
		managedSpritesMenuPanel.setLayout(new BoxLayout(managedSpritesMenuPanel, BoxLayout.X_AXIS));
		
		JLabel lblNewLabel = new JLabel("Managed Sprites");
		managedSpritesMenuPanel.add(lblNewLabel);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		managedSpritesMenuPanel.add(horizontalGlue);
		
		JButton btnNew = new JButton("New");
		managedSpritesMenuPanel.add(btnNew);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		managedSpritesMenuPanel.add(btnDelete);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		spriteListPanel.add(list, gbc_list);
		
		JPanel managedSpriteDetails = new JPanel();
		managedSpriteDetails.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_managedSpriteDetails = new GridBagConstraints();
		gbc_managedSpriteDetails.insets = new Insets(0, 0, 5, 5);
		gbc_managedSpriteDetails.fill = GridBagConstraints.BOTH;
		gbc_managedSpriteDetails.gridx = 1;
		gbc_managedSpriteDetails.gridy = 0;
		contentPane.add(managedSpriteDetails, gbc_managedSpriteDetails);
		GridBagLayout gbl_managedSpriteDetails = new GridBagLayout();
		gbl_managedSpriteDetails.columnWidths = new int[]{0, 0};
		gbl_managedSpriteDetails.rowHeights = new int[]{0, 0, 0};
		gbl_managedSpriteDetails.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_managedSpriteDetails.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		managedSpriteDetails.setLayout(gbl_managedSpriteDetails);
		
		JPanel detailsTitlePanel = new JPanel();
		GridBagConstraints gbc_detailsTitlePanel = new GridBagConstraints();
		gbc_detailsTitlePanel.insets = new Insets(0, 0, 5, 0);
		gbc_detailsTitlePanel.fill = GridBagConstraints.BOTH;
		gbc_detailsTitlePanel.gridx = 0;
		gbc_detailsTitlePanel.gridy = 0;
		managedSpriteDetails.add(detailsTitlePanel, gbc_detailsTitlePanel);
		
		JLabel lblManagedSprieDetails = new JLabel("Managed Sprie Details");
		detailsTitlePanel.add(lblManagedSprieDetails);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(5, 5, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 1;
		managedSpriteDetails.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblType = new JLabel("Sprite Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(5, 5, 5, 5);
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 0;
		panel_1.add(lblType, gbc_lblType);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(5, 5, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_1.add(comboBox, gbc_comboBox);
		
		JLabel lblModassetpath = new JLabel("Mod Asset Path");
		GridBagConstraints gbc_lblModassetpath = new GridBagConstraints();
		gbc_lblModassetpath.anchor = GridBagConstraints.EAST;
		gbc_lblModassetpath.insets = new Insets(5, 5, 5, 5);
		gbc_lblModassetpath.gridx = 0;
		gbc_lblModassetpath.gridy = 1;
		panel_1.add(lblModassetpath, gbc_lblModassetpath);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(5, 5, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblWorkingAssetPath = new JLabel("Working Asset Path");
		GridBagConstraints gbc_lblWorkingAssetPath = new GridBagConstraints();
		gbc_lblWorkingAssetPath.anchor = GridBagConstraints.EAST;
		gbc_lblWorkingAssetPath.insets = new Insets(5, 5, 5, 5);
		gbc_lblWorkingAssetPath.gridx = 0;
		gbc_lblWorkingAssetPath.gridy = 2;
		panel_1.add(lblWorkingAssetPath, gbc_lblWorkingAssetPath);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(5, 5, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_1.add(textField_1, gbc_textField_1);
		
		JButton btnChangeUpdate = new JButton("Change");
		GridBagConstraints gbc_btnChangeUpdate = new GridBagConstraints();
		gbc_btnChangeUpdate.insets = new Insets(5, 5, 5, 0);
		gbc_btnChangeUpdate.gridx = 2;
		gbc_btnChangeUpdate.gridy = 2;
		panel_1.add(btnChangeUpdate, gbc_btnChangeUpdate);
		
		JLabel lblLuaLibPath = new JLabel("LUA lib Path");
		GridBagConstraints gbc_lblLuaLibPath = new GridBagConstraints();
		gbc_lblLuaLibPath.anchor = GridBagConstraints.EAST;
		gbc_lblLuaLibPath.insets = new Insets(5, 5, 5, 5);
		gbc_lblLuaLibPath.gridx = 0;
		gbc_lblLuaLibPath.gridy = 3;
		panel_1.add(lblLuaLibPath, gbc_lblLuaLibPath);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(5, 5, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panel_1.add(textField_2, gbc_textField_2);
		
		JLabel lblItemName = new JLabel("Item Name");
		GridBagConstraints gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.anchor = GridBagConstraints.EAST;
		gbc_lblItemName.insets = new Insets(5, 5, 5, 5);
		gbc_lblItemName.gridx = 0;
		gbc_lblItemName.gridy = 4;
		panel_1.add(lblItemName, gbc_lblItemName);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(5, 5, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 4;
		panel_1.add(textField_3, gbc_textField_3);
		
		JButton btnEdit = new JButton("Update");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(5, 5, 5, 0);
		gbc_btnEdit.fill = GridBagConstraints.BOTH;
		gbc_btnEdit.gridx = 2;
		gbc_btnEdit.gridy = 4;
		panel_1.add(btnEdit, gbc_btnEdit);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(5, 5, 5, 0);
		gbc_separator.gridwidth = 3;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 5;
		panel_1.add(separator, gbc_separator);
		
		JLabel lblFilePattern = new JLabel("File Pattern");
		lblFilePattern.setToolTipText("<html>For files with filename myRenderoutputfile001.png the corresponding pattern would be <br/><br/>myRenderoutputfile%03d.png<br/><br/><br/>%d = any integer and %03d = any ineger padded to length 3 with zeros.<br/><br/><br/>The number of leading zeros must be specified for fiel detection to work. only integers can currently be used.</html>");
		GridBagConstraints gbc_lblFilePattern = new GridBagConstraints();
		gbc_lblFilePattern.anchor = GridBagConstraints.EAST;
		gbc_lblFilePattern.insets = new Insets(0, 5, 5, 5);
		gbc_lblFilePattern.gridx = 0;
		gbc_lblFilePattern.gridy = 6;
		panel_1.add(lblFilePattern, gbc_lblFilePattern);
		
		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.gridwidth = 2;
		gbc_textField_4.insets = new Insets(0, 5, 5, 0);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 6;
		panel_1.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 7;
		panel_1.add(verticalStrut, gbc_verticalStrut);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.gridwidth = 3;
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 8;
		panel_1.add(panel_9, gbc_panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		Component horizontalGlue_1 = Box.createHorizontalGlue();
		panel_9.add(horizontalGlue_1);
		
		JButton btnRescanSprites = new JButton("Rescan Sprites");
		panel_9.add(btnRescanSprites);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut);
		
		JButton btnGenerateStripes = new JButton("Generate Stripes");
		panel_9.add(btnGenerateStripes);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_9.add(horizontalStrut_1);
		
		JButton btnSaveToMod = new JButton("Save to mod");
		panel_9.add(btnSaveToMod);
		
		Component horizontalGlue_2 = Box.createHorizontalGlue();
		panel_9.add(horizontalGlue_2);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(5, 5, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		
		JLabel lblTiles = new JLabel("Tiles");
		panel_2.add(lblTiles);
		
		JList list_1 = new JList();
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 0;
		gbc_list_1.gridy = 1;
		panel.add(list_1, gbc_list_1);
		
		JPanel stripesPanel = new JPanel();
		stripesPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_stripesPanel = new GridBagConstraints();
		gbc_stripesPanel.gridheight = 2;
		gbc_stripesPanel.insets = new Insets(0, 0, 0, 5);
		gbc_stripesPanel.fill = GridBagConstraints.BOTH;
		gbc_stripesPanel.gridx = 0;
		gbc_stripesPanel.gridy = 1;
		contentPane.add(stripesPanel, gbc_stripesPanel);
		GridBagLayout gbl_stripesPanel = new GridBagLayout();
		gbl_stripesPanel.columnWidths = new int[]{0, 0};
		gbl_stripesPanel.rowHeights = new int[]{0, 0, 0};
		gbl_stripesPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_stripesPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		stripesPanel.setLayout(gbl_stripesPanel);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		stripesPanel.add(panel_3, gbc_panel_3);
		
		JLabel lblStripes = new JLabel("Stripes");
		panel_3.add(lblStripes);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		stripesPanel.add(panel_4, gbc_panel_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.gridheight = 2;
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 1;
		gbc_panel_5.gridy = 1;
		contentPane.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JPanel panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 0);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_5.add(panel_6, gbc_panel_6);
		
		JLabel lblConsole = new JLabel("Console");
		panel_6.add(lblConsole);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 1;
		panel_5.add(textArea, gbc_textArea);
		
		JPanel tileViewPanel = new JPanel();
		tileViewPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_tileViewPanel = new GridBagConstraints();
		gbc_tileViewPanel.fill = GridBagConstraints.BOTH;
		gbc_tileViewPanel.gridx = 2;
		gbc_tileViewPanel.gridy = 2;
		contentPane.add(tileViewPanel, gbc_tileViewPanel);
		GridBagLayout gbl_tileViewPanel = new GridBagLayout();
		gbl_tileViewPanel.columnWidths = new int[]{0, 0};
		gbl_tileViewPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_tileViewPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_tileViewPanel.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		tileViewPanel.setLayout(gbl_tileViewPanel);
		
		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.insets = new Insets(0, 0, 5, 0);
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 0;
		tileViewPanel.add(panel_7, gbc_panel_7);
		
		JLabel lblSprite = new JLabel("Sprite");
		panel_7.add(lblSprite);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(200);
		GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
		gbc_horizontalStrut_2.gridx = 0;
		gbc_horizontalStrut_2.gridy = 1;
		tileViewPanel.add(horizontalStrut_2, gbc_horizontalStrut_2);
		
		JPanel panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 2;
		tileViewPanel.add(panel_8, gbc_panel_8);
	}

}
